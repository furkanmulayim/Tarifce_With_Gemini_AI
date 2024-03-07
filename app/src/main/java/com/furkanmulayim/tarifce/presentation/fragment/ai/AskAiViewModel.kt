package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.data.service.ai.GoogleAI
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import kotlinx.coroutines.launch


class AskAiViewModel(application: Application) : BaseViewModel(application) {

    val messageList = mutableListOf<Message>()
    val dataGeldiMi = MutableLiveData<Boolean>()
    fun askGoogleAI(text: String) {
        viewModelScope.launch {
            try {
                val response = GoogleAI().generateResponse(text)
                messageList.add(Message(response, false))
                dataGeldiMi.value = true
            } catch (e: Exception) {
                val errorMessage = "Google AI'dan yanıt alınamadı: ${e.message}"
                messageList.add(Message(errorMessage, false))
                dataGeldiMi.value = true
            }
        }
    }

}