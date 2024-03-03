package com.furkanmulayim.tarifce.data.service

import com.furkanmulayim.tarifce.data.model.CategoryData
import io.reactivex.Single
import retrofit2.http.GET

interface MaterialsAPI {
    @GET("furkanmulayim/Tarifce/master/app/src/main/assets/malzemeler.json")
    fun getMaterials(): Single<List<CategoryData>>
}