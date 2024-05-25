package com.furkanmulayim.tarifce.presentation.fragment.detail.prepare_bs

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.furkanmulayim.tarifce.base.BaseViewModel

class BottomViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    var bundle: MutableLiveData<String> = MutableLiveData()

    init {
        getBundle()
    }

    private fun getBundle() {
        savedStateHandle.get<String>("prepare").let { data ->
            data?.let { bundle.value = it }
        }
    }
}