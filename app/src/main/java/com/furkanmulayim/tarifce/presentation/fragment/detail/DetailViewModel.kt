package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.repository.FoodDaoRepository
import com.furkanmulayim.tarifce.repository.SavedDaoRepository
import com.furkanmulayim.tarifce.util.loadImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application, var frepo: FoodDaoRepository, var srepo: SavedDaoRepository
) : BaseViewModel(application) {

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


    fun saveFood() {
        saveDatabase()
    }

    private fun saveDatabase() {
        food.value?.let {
            viewModelScope.launch {
                srepo.saveFoods(
                    image = it.image,
                    name = it.name,
                    category = it.category,
                    stars = it.stars,
                    trend = it.trend,
                    duration = it.duration,
                    calorie = it.calorie,
                    person = it.person,
                    level = it.level,
                    hastags = it.hastags,
                    specific = it.specific,
                )
            }
        }
    }
}