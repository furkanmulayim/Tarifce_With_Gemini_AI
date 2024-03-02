package com.furkanmulayim.tarifce.presentation.fragment.choose

import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.Material

class ChooseViewModel : ViewModel() {

    private val categories = listOf(
        Material("Süt ürünleri", listOf("süt", "yoğurt", "peynir","fasulye", "nohut","balık", "kalamar","balık", "kalamar")),
        Material("Deniz Ürünleri", listOf("balık", "kalamar","balık", "kalamar","balık", "kalamar","balık", "kalamar")),
        Material("Deneme", listOf("dene", "yap","balık", "kalamar","balık", "kalamar","balık", "kalamar","balık", "kalamar","balık", "kalamar"))
    )

    fun listMaterial(): List<Material> {
        return categories
    }
}