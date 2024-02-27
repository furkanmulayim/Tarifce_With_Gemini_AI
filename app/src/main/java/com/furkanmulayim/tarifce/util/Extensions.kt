package com.furkanmulayim.tarifce.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.furkanmulayim.tarifce.R

//Görselleri indiriyoruz
fun ImageView.loadImage(url: String?) {
    val opt = RequestOptions().error(R.drawable.button_squircle_white)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this).waitForLayout()
}