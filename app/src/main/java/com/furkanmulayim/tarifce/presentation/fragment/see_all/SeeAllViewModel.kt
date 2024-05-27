package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.enums.Categorie
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    application: Application,
    private val foodsRepository: FoodRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    var foodList: MutableLiveData<ArrayList<Food>> = MutableLiveData()
    var category: MutableLiveData<String?> = MutableLiveData()

    init {
        getBundle()
    }

    private fun getBundle() {
        savedStateHandle.get<String>("category").let { categ ->
            categ?.let { getFoodData(); category.value = categ }
        }
    }

    private fun getFoodData() {
        viewModelScope.launch {
            foodList = foodsRepository.getData()
        }
    }

    suspend fun filterFoodsByCategory(): List<Food>? {
        return withContext(Dispatchers.Default) {
            if (category.value.equals(Categorie.BUTUN.names)) {
                foodList.value
            } else {
                foodList.value?.filter { it.category == category.value }
            }
        }
    }
}