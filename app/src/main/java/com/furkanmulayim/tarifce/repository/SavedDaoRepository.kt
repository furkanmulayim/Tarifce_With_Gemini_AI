package com.furkanmulayim.tarifce.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.food.FoodDao
import com.furkanmulayim.tarifce.data.db.saved_food.SavedDao
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.Saved
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedDaoRepository(private var sdao: SavedDao) {


    private var foodList: MutableLiveData<List<Saved>> = MutableLiveData()
    private var food: MutableLiveData<Saved> = MutableLiveData()

    fun foodsPostViewModel(): MutableLiveData<List<Saved>> {
        return foodList
    }

    fun foodPostViewModel(): MutableLiveData<Saved> {
        return food
    }

    fun getAllFoods() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            foodList.value = sdao.allList()
        }
    }

    fun saveFoods(
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
            val newFood = Saved(
                id = 0,
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
            sdao.addSaved(newFood)
        }

    }
}