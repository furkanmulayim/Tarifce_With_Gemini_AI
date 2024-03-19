package com.furkanmulayim.tarifce.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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

fun View.hideKeyboard() {
//Klavye Kapatmak için
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.showMessage(message: String) {
    //kullanıcıya mesaj göstermek için
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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