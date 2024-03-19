package com.furkanmulayim.tarifce.presentation.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentSplashBinding
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.checkInternetConnection()
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.isInternetAvailable.observe(viewLifecycleOwner) { connect ->
            if (connect == true) {
                //eğer bağlantı varsa
                viewModel.baglantiVar(requireView())
            } else {
                //eğer bağlantı yoksa
                viewVisible(binding.baglaniYokButton)
                baglantiClickListener()
            }
        }
    }


    private fun baglantiClickListener() {
        binding.button.setOnClickListener {
            viewGone(binding.baglaniYokButton)
            viewModel.baglantiYok()
        }
    }

}