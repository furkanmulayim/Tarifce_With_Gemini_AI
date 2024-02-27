package com.furkanmulayim.tarifce.presentation.fragment.see_all

import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.Food

class SeeAllBViewModel : ViewModel() {

    private val food = arrayListOf(
        Food(
            id = 1,
            image = "R.drawable.svg_dene",
            name = "Köri Soslu Tavuk",
            category = "Trend",
            stars = "4.6",
            trend = "0",
            duration = "35-45",
            calorie = "365",
            person = "2-3",
            level = "orta",
            hastags = "domates/biber/sogan",
            specific = "bla bla bla"
        )
    )


    fun foodReturn(): ArrayList<Food> {
        return food
    }
}