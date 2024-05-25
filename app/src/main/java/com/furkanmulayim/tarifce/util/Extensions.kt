package com.furkanmulayim.tarifce.util

import OnSingleClickListener
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.furkanmulayim.tarifce.R
import es.dmoral.toasty.Toasty

//Görselleri indiriyoruz
fun ImageView.loadImage(url: String?) {
    val opt = RequestOptions().error(R.drawable.button_squircle_white)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this).waitForLayout()
}

fun viewGone(view: View) {
    view.visibility = View.GONE
}

fun viewVisible(view: View) {
    view.visibility = View.VISIBLE
}

fun viewMessage(context: Context, message: String) {
    Toasty.custom(
        context,
        message,
        null,
        Toast.LENGTH_SHORT,
        false
    ).show()
}


fun stringToList(str: String): List<String> {
    return str.split(",")
}

fun stringToListSlash(str: String): List<String> {
    return str.split("/")
}

fun handleMessage(context: Context, message: Int, icon: Drawable, tintColor: Int) {
    Toasty.custom(
        context, message, icon, tintColor, Toast.LENGTH_SHORT, true, true
    ).show();
}

fun View.hideKeyboard() {
//Klavye Kapatmak için
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.onSingleClickListener(listener: View.OnClickListener) {
    this.setOnClickListener(
        object : OnSingleClickListener() {
            override fun onSingleClick(view: View?) {
                listener.onClick(view)
            }
        },
    )
}

fun Activity.showExitDialog() {
    val builder = androidx.appcompat.app.AlertDialog.Builder(this)
    builder.setTitle("Çıkış")
    builder.setMessage("Uygulamadan çıkmak istediğinizden emin misiniz?")
    builder.setPositiveButton("Evet") { _, _ ->
        finish() // Uygulamayı kapat
    }
    builder.setNegativeButton("Hayır", null)
    val dialog = builder.create()
    dialog.show()
}