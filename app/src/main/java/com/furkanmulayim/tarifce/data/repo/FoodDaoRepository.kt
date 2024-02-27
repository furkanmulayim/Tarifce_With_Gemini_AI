package com.furkanmulayim.tarifce.data.repo

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.FoodDao
import com.furkanmulayim.tarifce.domain.model.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodDaoRepository(var fdao: FoodDao) {


    private var foodList: MutableLiveData<List<Food>> = MutableLiveData()

    fun foodsPostViewModel(): MutableLiveData<List<Food>> {
        return foodList
    }

    fun getAllFoods() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            foodList.value = fdao.allFoods()
        }
    }

    fun saveFoods(
        id: Int,
        image: String,
        name: String,
        category: String,
        stars: Double,
        trend: Boolean,
        duration: String,
        calorie: String,
        person: String,
        level: String,
        hastags: String,
        specific: String
    ) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val newFood = Food(
                id = id,
                image = image,
                name = name,
                category = category,
                stars = stars,
                trend = trend,
                duration = duration,
                calorie = calorie,
                person = person,
                level = level,
                hastags = hastags,
                specific = specific
            )
            fdao.addFood(newFood)
        }

    }

    fun deleteFood(
        id: Int,
        image: String,
        name: String,
        category: String,
        stars: Double,
        trend: Boolean,
        duration: String,
        calorie: String,
        person: String,
        level: String,
        hastags: String,
        specific: String
    ) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val deleteFood = Food(
                id = id,
                image = image,
                name = name,
                category = category,
                stars = stars,
                trend = trend,
                duration = duration,
                calorie = calorie,
                person = person,
                level = level,
                hastags = hastags,
                specific = specific
            )
            fdao.deleteFood(deleteFood)
        }

    }
}