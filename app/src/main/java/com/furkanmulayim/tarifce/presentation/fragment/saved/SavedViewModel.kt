package com.furkanmulayim.tarifce.presentation.fragment.saved

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Saved
import com.furkanmulayim.tarifce.presentation.BaseViewModel
import com.furkanmulayim.tarifce.repository.SavedDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    application: Application, private var srepo: SavedDaoRepository
) : BaseViewModel(application) {

    var food = MutableLiveData<List<Saved>>()
    fun getFoodList() {
        srepo.getAllFoods()
        food = srepo.foodsPostViewModel()
    }
}