package com.furkanmulayim.tarifce.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryData(
    val id: String? = "",
    val materials: List<Material>? = null
) : Parcelable

@Parcelize
data class Material(
    val name: String? = "",
    val url: String? = ""
) : Parcelable