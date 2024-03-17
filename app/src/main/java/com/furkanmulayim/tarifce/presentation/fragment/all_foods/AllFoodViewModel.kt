package com.furkanmulayim.tarifce.presentation.fragment.all_foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllFoodViewModel @Inject constructor(private var frepo: FoodDaoRepository) : ViewModel() {

    private var temp = MutableLiveData<List<Food>>()
    var foods = MutableLiveData<List<Food>>()

    fun commonData(name: String) {
        getData(name)
    }

    private fun getData(categoryName: String) {
        val job = viewModelScope.launch {
            frepo.getAllFoods()
            temp.value = frepo.foodsPostViewModel().value
            temp.value?.let { foodsList ->
                foods.postValue(foodsList.filter {
                    it.category.equals(
                        categoryName, ignoreCase = true
                    )
                })
            }
        }

    }

}