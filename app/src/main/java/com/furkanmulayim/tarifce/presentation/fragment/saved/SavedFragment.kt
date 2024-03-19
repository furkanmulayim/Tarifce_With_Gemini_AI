package com.furkanmulayim.tarifce.presentation.fragment.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Saved
import com.furkanmulayim.tarifce.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>() {

    private val viewModel: SavedViewModel by viewModels()
    private var savedAdapter = SavedAdapter(arrayListOf())

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSavedBinding {
        return FragmentSavedBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        observSaved()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = SavedFragmentDirections.actionSavedFragmentToHelloFragment()
            navigateTo(act.actionId)
        }
    }

    private fun observSaved() {
        viewModel.food.observe(viewLifecycleOwner) {
            it.let {
                savedAdapter.updateList(it as ArrayList<Saved>)
            }
        }
    }
}