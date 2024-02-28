package com.furkanmulayim.tarifce.presentation.fragment.prepare

import android.app.Application
import com.furkanmulayim.tarifce.presentation.BaseViewModel

class BottomSheetDialogViewModel(application: Application) : BaseViewModel(application) {

    fun preparesList(bundle:String): List<String> {
        return bundle.split("/")
    }
}