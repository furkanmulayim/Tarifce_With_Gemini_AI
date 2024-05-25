package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.data.service.ai.GoogleAI
import kotlinx.coroutines.launch


class AskAiViewModel(application: Application, private var savedStateHandle: SavedStateHandle) :
    BaseViewModel(application) {

    val dataGeldiMi = MutableLiveData<Boolean>()
    val bundleList = MutableLiveData<String>()
    val mesajlar = MutableLiveData<ArrayList<Message>>()

    init {
        getBundle()
    }

    private fun getBundle() {
        savedStateHandle.get<String>("selectedList").let {
            if (it != null) {
                bundleList.value =
                    it.plus(" malzemeleri ile evde yapabileceğim bir yemeğin yapılışını anlatır mısın?")
            }
        }
    }

    fun askGoogleAI(context: Context, text: String) {
        viewModelScope.launch {
            try {
                val response = GoogleAI().generateResponse(text).replace("* ", "").replace(" *", "")
                    .replace(" **", "").replace("** ", "").replace(":", "")
                dataGeldiMi.value = true
                val result = response.replace(Regex("\\*\\*(.*?)\\*\\*")) {
                    "👉 ${it.groupValues[1].uppercase()}"
                }.replace("*", " ")

                mesajEkle(result, false)
            } catch (e: Exception) {
                val errorMessage = getString(context, R.string.google_ai_error)

                dataGeldiMi.value = true
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