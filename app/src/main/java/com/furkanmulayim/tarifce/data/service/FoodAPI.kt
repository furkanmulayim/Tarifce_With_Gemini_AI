package com.furkanmulayim.tarifce.data.service

import com.furkanmulayim.tarifce.domain.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {
    @GET("furkanmulayim/Tarifce/master/app/src/main/assets/yemek_tarifleri.json")
    fun getFood(): Single<List<Food>>
}