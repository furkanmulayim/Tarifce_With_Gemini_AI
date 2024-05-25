package com.furkanmulayim.tarifce.presentation.fragment.fav

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repository.FavDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    application: Application,
    private var favDaoRepository: FavDaoRepository
) : BaseViewModel(application) {

    private var _foodList = MutableLiveData<List<Food>>()
    val foodList: LiveData<List<Food>>
        get() = _foodList


    fun getFavs() {
        _foodList = favDaoRepository.getLikeDatas()
    }
}