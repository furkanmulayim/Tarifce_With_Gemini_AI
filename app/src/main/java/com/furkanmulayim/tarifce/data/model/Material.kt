package com.furkanmulayim.tarifce.data.model

import com.google.gson.annotations.SerializedName

data class Material(
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String = ""
)