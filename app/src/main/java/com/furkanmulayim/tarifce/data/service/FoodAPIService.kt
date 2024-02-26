package com.furkanmulayim.tarifce.data.service

import com.furkanmulayim.tarifce.domain.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIService {

    //https://raw.githubusercontent.com/furkanmulayim/Tarifce/master/app/src/main/assets/yemek_tarifleri.json
    private val baseUr = "https://raw.githubusercontent.com/"
    private val api =
        Retrofit.Builder().baseUrl(baseUr).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(FoodAPI::class.java)

    fun getData(): Single<List<Food>> {
        return api.getFood()
    }

}