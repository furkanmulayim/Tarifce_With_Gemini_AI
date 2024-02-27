package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.data.service.FoodAPIService
import com.furkanmulayim.tarifce.domain.model.Food
import com.furkanmulayim.tarifce.domain.model.FoodCategory
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
                        saveDatabase()
                        deneme()
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }

    fun saveDatabase() {
        val foodList = food.value
        foodList?.let {
            for (foodItem in it) {
                frepo.saveFoods(
                    foodItem.id,
                    foodItem.image,
                    foodItem.name,
                    foodItem.category,
                    foodItem.stars,
                    foodItem.trend,
                    foodItem.duration,
                    foodItem.calorie,
                    foodItem.person,
                    foodItem.level,
                    foodItem.hastags,
                    foodItem.specific
                )
            }
        } ?: println("Yemek listesi boş.")
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