package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.furkanmulayim.tarifce.util.loadImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(application: Application, var frepo: FoodDaoRepository) :
    BaseViewModel(application) {

    private var foods = MutableLiveData<List<Food>>()
    var food = MutableLiveData<Food>()

    fun commonData(name: String) {
        getData(name)
    }

    private fun getData(name: String) {
        frepo.getAllFoods()
        foods.value = frepo.foodsPostViewModel().value
        foods.value?.let {
            for (i in 0..it.size) {
                val item = it[i]
                if (item.name == name) {
                    food.value = item
                    break
                }
            }
        }
    }

    fun ingrList(ingredients: String): List<String> {
        return ingredients.split("@")
    }

    fun setImage(link: String, imageView: ImageView) {
        imageView.loadImage(link)
    }


}