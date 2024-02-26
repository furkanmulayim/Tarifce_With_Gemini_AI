package com.furkanmulayim.tarifce.presentation.fragment.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    private lateinit var viewModel: SavedViewModel
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            nav(R.id.action_savedFragment_to_helloFragment)
        }
    }

    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }

}