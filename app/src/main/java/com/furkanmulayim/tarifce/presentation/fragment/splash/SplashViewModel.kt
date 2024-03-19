package com.furkanmulayim.tarifce.presentation.fragment.splash

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.presentation.fragment.material_choose.ChooseFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private val _isInternetAvailable = MutableLiveData<Boolean>()
    val isInternetAvailable: LiveData<Boolean> get() = _isInternetAvailable

    fun checkInternetConnection() {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities =
            activeNetwork?.let { connectivityManager.getNetworkCapabilities(it) }

        _isInternetAvailable.value =
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun baglantiVar(view: View) {
        launch {
            delay(500)
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_helloFragment)
        }
    }

    fun baglantiYok() {
        checkInternetConnection()
    }
}
