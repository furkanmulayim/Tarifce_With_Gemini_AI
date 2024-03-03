package com.furkanmulayim.tarifce.data.model

import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("categories") val categories: List<Category>
)