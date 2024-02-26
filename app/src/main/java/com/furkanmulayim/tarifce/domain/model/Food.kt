package com.furkanmulayim.tarifce.domain.model

data class Food(
    val image: String, //degisecek
    val name: String,
    val category: String,
    val stars: Double,
    val trend:Boolean,
    var duration: String,
    var calorie: String,
    var person: String,
    var level: String,
    var hastags: String,
    var specific: String
)