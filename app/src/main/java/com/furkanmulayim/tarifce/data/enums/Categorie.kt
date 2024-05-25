package com.furkanmulayim.tarifce.data.enums

import com.furkanmulayim.tarifce.R

enum class Categorie(val image: Int, val back: Int, val names: String) {
    BUTUN(
        image = R.drawable.icon_trend,
        back = R.drawable.item_foot_category_back,
        names = "Bütün"
    ),
    VEGAN(image = R.drawable.icon_vegan, back = R.drawable.circle_white, names = "Vegan"),
    KLASIK(image = R.drawable.icon_classic, back = R.drawable.circle_white, names = "Klasik"),
    KULTUR(image = R.drawable.icon_kultur, back = R.drawable.circle_white, names = "Kültür"),
    DENIZ(image = R.drawable.icon_deniz, back = R.drawable.circle_white, names = "Deniz")
}