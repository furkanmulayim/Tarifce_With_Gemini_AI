package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.enums.Categorie
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.FoodCategory
import com.furkanmulayim.tarifce.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : BaseViewModel(application) {

    var foodList = MutableLiveData<ArrayList<Food>>()
    val selectedCategory = MutableLiveData<String>()

    init {
        getFoodData()
    }

    private val categories = arrayListOf( //Kategoriler
        FoodCategory(Categorie.BUTUN.image, Categorie.BUTUN.back, Categorie.BUTUN.names),
        FoodCategory(Categorie.VEGAN.image, Categorie.VEGAN.back, Categorie.VEGAN.names),
        FoodCategory(Categorie.KLASIK.image, Categorie.KLASIK.back, Categorie.KLASIK.names),
        FoodCategory(Categorie.KULTUR.image, Categorie.KULTUR.back, Categorie.KULTUR.names),
        FoodCategory(Categorie.DENIZ.image, Categorie.DENIZ.back, Categorie.DENIZ.names)
    )

    fun getCategories(): ArrayList<FoodCategory> = categories

    //Kategori seçtirmek için
    fun selectedCategories(category: String) {
        selectedCategory.value = category
    }

    private fun getFoodData() {
        viewModelScope.launch {
            foodList = foodRepository.getData()
        }
    }


    suspend fun filterFoods(all: ArrayList<Food>): List<Food> {
        return withContext(Dispatchers.Default) {
            all.filter { it.category == selectedCategory.value }
        }
    }


}