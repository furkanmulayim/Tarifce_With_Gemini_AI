package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.FoodCategory
import com.furkanmulayim.tarifce.data.service.food.FoodAPIService
import com.furkanmulayim.tarifce.repository.FoodDaoRepository
import com.furkanmulayim.tarifce.util.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(
    application: Application, private var frepo: FoodDaoRepository
) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())
    var food = MutableLiveData<List<Food>?>()
    val selectedCategory = MutableLiveData<String>()
    var seciliUrunler = MutableLiveData<List<Food>>()

    private fun getFoodList() {
        frepo.getAllFoods()
        food = frepo.foodsPostViewModel()
        comeFirstDataFoodsByCategory()
    }


    private val categories = arrayListOf(
        FoodCategory(
            image = R.drawable.icon_trend, back = R.drawable.item_foot_category_back, name = "Trend"
        ), FoodCategory(
            image = R.drawable.icon_vegan, back = R.drawable.circle_white, name = "Vegan"
        ), FoodCategory(
            image = R.drawable.icon_classic, back = R.drawable.circle_white, name = "Klasik"
        ), FoodCategory(
            image = R.drawable.icon_kultur, back = R.drawable.circle_white, name = "Kültür"
        ), FoodCategory(
            image = R.drawable.icon_deniz, back = R.drawable.circle_white, name = "Deniz"
        )
    )

    fun getData() {
        val updateTime = sharedPrefs.getTime()
        if (updateTime != null && updateTime != 0L) {
            getFoodList()
        } else {
            getFoodFromApi()
        }
    }

    //TODO İlerde repository katmanına alınacak!
    private fun getFoodFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = FoodAPIService().getData()
                if (response.isSuccessful) {
                    val foodListResponse = response.body()
                    if (foodListResponse != null) {
                        saveDatabase(foodListResponse)
                        food.postValue(foodListResponse)
                    } else {
                        // TODO hata durumu!
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    fun comeFirstDataFoodsByCategory() {
        CoroutineScope(Dispatchers.Default).launch {
            seciliUrunler.postValue(food.value?.filter {
                it.category.equals(
                    selectedCategory.value, ignoreCase = true
                )
            })
        }

    }


    private fun saveDatabase(list: List<Food>) {
        if (list.isNotEmpty()) {
            for (foodItem in list) {
                frepo.saveFoods(
                    0,
                    foodItem.image,
                    foodItem.name,
                    foodItem.category,
                    foodItem.stars,
                    foodItem.trend,
                    foodItem.duration,
                    foodItem.calorie,
                    foodItem.person,
                    foodItem.level,
                    foodItem.hastags,
                    foodItem.specific
                )
                //take the saved time
                sharedPrefs.saveTime(System.nanoTime())

            }
        }
    }


    fun listReturn(): ArrayList<FoodCategory> {
        return categories
    }

    fun selectedCategories(category: String) {
        selectedCategory.value = category
        comeFirstDataFoodsByCategory()
    }

}