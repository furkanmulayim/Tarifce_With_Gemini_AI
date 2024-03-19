package com.furkanmulayim.tarifce.presentation.fragment.prepare_bs

import android.app.Application
import com.furkanmulayim.tarifce.base.BaseViewModel

class BottomSheetDialogViewModel(application: Application) : BaseViewModel(application) {

    fun preparesList(bundle:String): List<String> {
        return bundle.split("/")
    }
}