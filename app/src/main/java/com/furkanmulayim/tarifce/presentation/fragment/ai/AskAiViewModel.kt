package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.config.AiAPIConfig
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch


class AskAiViewModel(application: Application) : BaseViewModel(application) {

    val messageList = mutableListOf<Message>()
    val dataGeldiMi = MutableLiveData<Boolean>()
    fun askGoogleAI(text: String) {
        val generativeModel = GenerativeModel(
            modelName = "models/gemini-pro", apiKey = AiAPIConfig().googleAiApiKey
        )

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(text)
                if (!response.text.isNullOrEmpty()) {
                    messageList.add(Message(response.text!!, false))
                }
                dataGeldiMi.value = true

            } catch (e: Exception) {
                dataGeldiMi.value = true
                val met = "Google AI'dan yanıt alınamadı: ${e.message}"
                messageList.add(Message(met,false))

            }
        }
    }

}