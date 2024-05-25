package com.furkanmulayim.tarifce.data.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.datasource.CategoryDataSource
import com.furkanmulayim.tarifce.data.model.CategoryData

class CategoryRepository(private var categoryDataSource: CategoryDataSource) {
    fun getData(): MutableLiveData<ArrayList<CategoryData>> = categoryDataSource.getData()
}