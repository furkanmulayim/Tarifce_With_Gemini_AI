package com.furkanmulayim.tarifce.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("materials") val materials: List<Material>
)