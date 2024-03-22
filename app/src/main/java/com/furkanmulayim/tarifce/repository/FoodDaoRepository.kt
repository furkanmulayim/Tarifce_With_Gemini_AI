package com.furkanmulayim.tarifce.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.food.FoodDao
import com.furkanmulayim.tarifce.data.model.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodDaoRepository(private var fdao: FoodDao) {

    private var foodList: MutableLiveData<List<Food>?> = MutableLiveData()
    fun foodsPostViewModel(): MutableLiveData<List<Food>?> {
        return foodList
    }

    fun getAllFoods() {
        CoroutineScope(Dispatchers.IO).launch {
            foodList.postValue(fdao.allFoods())
        }
    }


    fun saveFoods(
        id: Int,
        image: String,
        name: String,
        category: String,
        stars: String,
        trend: String,
        duration: String,
        calorie: String,
        person: String,
        level: String,
        hastags: String,
        specific: String
    ) {

        CoroutineScope(Dispatchers.Main).launch {
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
            withContext(Dispatchers.IO) {
                fdao.addFood(newFood)
            }
        }

    }
}