package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.data.service.FoodAPIService
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.FoodCategory
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(application: Application, var frepo: FoodDaoRepository) :
    BaseViewModel(application) {

    val selectedCategory = MutableLiveData<String>()
    val food = MutableLiveData<List<Food>>()
    var deneme = MutableLiveData<List<Food>>()

    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable()

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
                        deneme()
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }

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
        } else {
            println("Liste Boş amkl ")
        }
    }


    init {
    getCardList()
    deneme = frepo.foodsPostViewModel()
    }

    private fun getCardList() {
    frepo.getAllFoods()
    }

    fun deneme() {
    println("GELDİ:" + (deneme.value?.size ?: println("Bomboş")))
    }


    fun getFoods() {
        getFoodFromApi()
    }

    fun listReturn(): ArrayList<FoodCategory> {
        return categories
    }

    fun selectedCategories(category: String) {
        selectedCategory.value = category
    }
}