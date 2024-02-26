package com.furkanmulayim.tarifce.domain.model

data class Food(
    val image: Int, //degisecek
    val name: String,
    val category: String,
    val stars: Double,
    var duration: String,
    var calorie: String,
    var person: String,
    var level: String,
    var hastags: String,
    var specific: String
)