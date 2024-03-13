package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.data.service.ai.GoogleAI
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import kotlinx.coroutines.launch


class AskAiViewModel(application: Application) : BaseViewModel(application) {

    val dataGeldiMi = MutableLiveData<Boolean>()
    val messageList: ArrayList<Message>? = null
    fun askGoogleAI(text: String) {
        viewModelScope.launch {
            try {
                val response = GoogleAI().generateResponse(text)
                dataGeldiMi.value = true
                messageList?.add(Message(mesaj = response, isuser = false))
            } catch (e: Exception) {
                val errorMessage =
                    "Google AI'dan yanıt alınamadı. Galiba Yemeğinizi ocakta unuttu. Lütfen Daha Sonra Tekrar Deneyiniz."
                dataGeldiMi.value = true
                messageList?.add(Message(mesaj = errorMessage, isuser = false))
            }
        }
    }
}