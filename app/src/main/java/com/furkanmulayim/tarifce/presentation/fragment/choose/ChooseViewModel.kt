package com.furkanmulayim.tarifce.presentation.fragment.choose

import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.model.MaterialItem

class ChooseViewModel : ViewModel() {

    private val categories = listOf(
        Material(
            "Süt ürünleri", listOf(
                MaterialItem("Süt", R.drawable.finger),
                MaterialItem("Yoğurt", R.drawable.finger),
                MaterialItem("Peynir", R.drawable.finger),
                MaterialItem("Fasulye", R.drawable.finger),
                MaterialItem("Nohut", R.drawable.finger)
            )
        ), Material(
            "Deniz Ürünleri", listOf(
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Kalamar", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger)
            )
        ), Material(
            "Deneme", listOf(
                MaterialItem("Dene", R.drawable.finger),
                MaterialItem("Yap", R.drawable.finger),
                MaterialItem("Balık", R.drawable.finger),
                MaterialItem("Kalamar", R.drawable.finger)
            )
        )
    )

    fun listMaterial(): List<Material> {
        return categories
    }
}