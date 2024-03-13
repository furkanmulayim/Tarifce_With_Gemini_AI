package com.furkanmulayim.tarifce.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.furkanmulayim.tarifce.R

//Görselleri indiriyoruz
fun ImageView.loadImage(url: String?) {
    val opt = RequestOptions().error(R.drawable.button_squircle_white)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this).waitForLayout()
}

fun viewGone(view:View){
    view.visibility = View.GONE
}
fun viewVisible(view:View){
    view.visibility = View.VISIBLE
}