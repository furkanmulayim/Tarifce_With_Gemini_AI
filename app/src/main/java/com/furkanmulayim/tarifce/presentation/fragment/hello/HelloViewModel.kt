package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.service.FoodAPIService
import com.furkanmulayim.tarifce.domain.model.Food
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.furkanmulayim.tarifce.presentation.domain.model.FoodCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HelloViewModel(application: Application) : BaseViewModel(application) {

    val selectedCategory = MutableLiveData<String>()
    val food = MutableLiveData<List<Food>>()

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
                        println("furkan burasi geldi: " + t[0].calorie)
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )

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