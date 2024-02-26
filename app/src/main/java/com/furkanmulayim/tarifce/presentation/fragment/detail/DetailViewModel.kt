package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.app.Application
import com.furkanmulayim.tarifce.presentation.BaseViewModel

class DetailViewModel(application: Application) : BaseViewModel(application) {
    private val ingredients = " 500 Gr. Nohut haşlama@200 ml tahin@75 ml Zeytin Yağı@5 Diş Sarımsak@Çay Kaşığı Kimyon@1 Adet Limon@Yarım Su Bardağı Su@1 Çay Bardağı Deneme"


    fun ingrList(): List<String> {
        return ingredients.split("@")
    }


}