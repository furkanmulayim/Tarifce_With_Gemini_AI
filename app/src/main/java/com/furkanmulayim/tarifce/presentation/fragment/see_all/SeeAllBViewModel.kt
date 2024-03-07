package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllBViewModel @Inject constructor(application: Application, private var frepo: FoodDaoRepository) :
    BaseViewModel(application) {

    private var temp = MutableLiveData<List<Food>>()
    var foods = MutableLiveData<List<Food>>()

    fun commonData(name: String) {
        getData(name)
    }

    private fun getData(categoryName: String) {
        frepo.getAllFoods()
        temp.value = frepo.foodsPostViewModel().value
        temp.value?.let { foodsList ->
            println("--" + foodsList.size)
            println("gelen category: " + foodsList[1].category)
            foods.postValue(foodsList.filter {
                it.category.equals(
                    categoryName, ignoreCase = true
                )
            })
        }
    }

}