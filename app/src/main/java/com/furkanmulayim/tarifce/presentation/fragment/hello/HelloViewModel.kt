package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.furkanmulayim.tarifce.domain.model.Food
import com.furkanmulayim.tarifce.presentation.domain.model.FoodCategory

class HelloViewModel(application: Application) : BaseViewModel(application) {


    val selectedCategory = MutableLiveData<String>()

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

    private val food = arrayListOf(
        Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        ), Food(
            image = R.drawable.svg_dene,
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = 4.6,
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        )
    )

    fun listReturn(): ArrayList<FoodCategory> {
        return categories
    }

    fun foodReturn(): ArrayList<Food> {
        return food
    }

    fun selectedCategories(category:String){
        selectedCategory.value = category
    }
}