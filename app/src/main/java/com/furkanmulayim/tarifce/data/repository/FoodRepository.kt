package com.furkanmulayim.tarifce.data.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.datasource.FoodDataSource
import com.furkanmulayim.tarifce.data.model.Food

class FoodRepository(private var foodDataSource: FoodDataSource) {
    fun getData(): MutableLiveData<ArrayList<Food>> = foodDataSource.getData()
}