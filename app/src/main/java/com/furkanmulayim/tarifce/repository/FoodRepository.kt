package com.furkanmulayim.tarifce.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.service.food.FoodAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepository(private var frepo: FoodAPIService) {

    private var foodList: MutableLiveData<List<Food>?> = MutableLiveData()

    fun foodsPostViewModel(): MutableLiveData<List<Food>?> {
        return foodList
    }


}