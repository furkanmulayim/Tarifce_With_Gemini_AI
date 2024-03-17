package com.furkanmulayim.tarifce.data.repo

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.food.FoodDao
import com.furkanmulayim.tarifce.data.model.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodDaoRepository(private var fdao: FoodDao) {


    private var foodList: MutableLiveData<List<Food>> = MutableLiveData()
    private var food: MutableLiveData<Food> = MutableLiveData()

    fun foodsPostViewModel(): MutableLiveData<List<Food>> {
        return foodList
    }

    fun foodPostViewModel(): MutableLiveData<Food> {
        return food
    }

    fun getAllFoods() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            foodList.value = fdao.allFoods()
        }
    }

    fun getFood(name: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            food.value = fdao.foodGet(name)
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
}