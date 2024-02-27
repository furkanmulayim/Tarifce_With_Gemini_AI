package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(application: Application, var frepo: FoodDaoRepository) :
    BaseViewModel(application) {

    var food = MutableLiveData<Food>()

    fun commonData(id: Int) {
        getData(id)
    }

    private fun getData(id: Int) {
        frepo.getFood(id)
        food.value = frepo.foodPostViewModel().value?.get(0)
    }


    fun ingrList(ingredients:String): List<String> {
        return ingredients.split("@")
    }


}