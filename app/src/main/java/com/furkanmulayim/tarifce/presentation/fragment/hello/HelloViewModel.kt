package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.FoodCategory
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.data.service.FoodAPIService
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.furkanmulayim.tarifce.util.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(application: Application, var frepo: FoodDaoRepository) :
    BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())

    val selectedCategory = MutableLiveData<String>()
    var food = MutableLiveData<List<Food>>()

    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable()

    /**
    init {
    getFoodList()
    food = frepo.foodsPostViewModel()
    }
     */
    private fun getFoodList() {
        frepo.getAllFoods()
    }


    private val categories = arrayListOf(
        FoodCategory(
            image = R.drawable.icon_trend, back = R.drawable.item_foot_category_back, name = "Trend"
        ), FoodCategory(
            image = R.drawable.icon_vegan, back = R.drawable.circle_white, name = "Vegan"
        ), FoodCategory(
            image = R.drawable.icon_classic, back = R.drawable.circle_white, name = "Klasik"
        ), FoodCategory(
            image = R.drawable.icon_kultur, back = R.drawable.circle_white, name = "Kültür"
        ), FoodCategory(
            image = R.drawable.icon_deniz, back = R.drawable.circle_white, name = "Deniz"
        )
    )

    fun getData() {
        //get download time
        val updateTime = sharedPrefs.getTime()

        if (updateTime != null && updateTime != 0L) {
            println("GELDİ SQLİTE")
            getFoodList()
            food = frepo.foodsPostViewModel()
        } else {
            println("GELDİ API")
            getFoodFromApi()
        }
    }

    // download data from API
    private fun getFoodFromApi() {
        disposable.add(
            foodAPIService.getData()
                //async new thread
                .subscribeOn(Schedulers.newThread())
                //show main Thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {
                        food.postValue(t)
                        println("Sayı " + t.size)
                        saveDatabase(t)
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }

    // save the data downloaded from API in SQLite
    fun saveDatabase(list: List<Food>) {

        if (list.isNotEmpty()) {
            for (foodItem in list) {
                frepo.saveFoods(
                    id = 0,
                    image = foodItem.image,
                    name = foodItem.name,
                    category = foodItem.category,
                    stars = foodItem.stars,
                    trend = foodItem.trend,
                    duration = foodItem.duration,
                    calorie = foodItem.calorie,
                    person = foodItem.person,
                    level = foodItem.level,
                    hastags = foodItem.hastags,
                    specific = foodItem.specific
                )
            }

            //take the saved time
            sharedPrefs.saveTime(System.nanoTime())
        } else {
            println("Liste Boş")
        }
    }


    fun listReturn(): ArrayList<FoodCategory> {
        return categories
    }

    fun selectedCategories(category: String) {
        selectedCategory.value = category
    }
}