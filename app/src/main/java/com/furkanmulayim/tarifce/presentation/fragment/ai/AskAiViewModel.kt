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
    val mesajlar = MutableLiveData<ArrayList<Message>>()

    fun askGoogleAI(text: String) {
        viewModelScope.launch {
            try {
                var response =
                    GoogleAI().generateResponse(text)
                        .replace("* ", "")
                        .replace(" *", "")
                        .replace(" **", "")
                        .replace("** ", "")
                        .replace(":", "")
                dataGeldiMi.value = true
                val result = response.replace(Regex("\\*\\*(.*?)\\*\\*")) {
                    "👉 ${it.groupValues[1].uppercase()}"
                }

                mesajEkle(result, false)
            } catch (e: Exception) {
                val errorMessage =
                    "Google AI'dan yanıt alınamadı. Galiba Yemeğinizi ocakta unuttu. Lütfen Daha Sonra Tekrar Deneyiniz."
                dataGeldiMi.value = true
                println("LOGF: response: " + e.localizedMessage)
                mesajEkle(errorMessage, false)
            }
        }
    }

    fun mesajEkle(mesaj: String, isUser: Boolean) {
        val tempList = mesajlar.value ?: arrayListOf()
        tempList.add(Message(mesaj = mesaj, isuser = isUser))
        mesajlar.postValue(tempList)
    }

}