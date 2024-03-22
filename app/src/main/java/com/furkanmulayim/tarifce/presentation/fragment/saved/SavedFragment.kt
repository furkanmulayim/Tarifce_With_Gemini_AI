package com.furkanmulayim.tarifce.presentation.fragment.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Saved
import com.furkanmulayim.tarifce.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

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
        viewModel.getData()
        setupAdapter()
        clickListener()
        observSaved()
    }

    private fun setupAdapter() {
        savedAdapter = SavedAdapter(arrayListOf())
        binding.savedRcyc.layoutManager = LinearLayoutManager(mContext)
        binding.savedRcyc.adapter = savedAdapter
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = SavedFragmentDirections.actionSavedFragmentToHelloFragment()
            navigateTo(act.actionId)
        }
    }

    private fun observSaved() {
        viewModel.food.observe(viewLifecycleOwner) {
            it?.let {
                Toasty.error(mContext, "Lütfen Malzeme Seçiniz!", Toast.LENGTH_SHORT, false).show()
                savedAdapter.updateList(it as ArrayList<Saved>)
            }
        }
    }
}