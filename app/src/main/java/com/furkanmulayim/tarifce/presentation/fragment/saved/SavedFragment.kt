package com.furkanmulayim.tarifce.presentation.fragment.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Saved
import com.furkanmulayim.tarifce.databinding.FragmentSavedBinding
import com.furkanmulayim.tarifce.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private lateinit var viewModel: SavedViewModel
    private lateinit var binding: FragmentSavedBinding
    private var savedAdapter = SavedAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        viewModel = ViewModelProvider(this)[SavedViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.savedRcyc.adapter = savedAdapter
        binding.savedRcyc.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getFoodList()
        clickListener()
        observSaved()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            requireParentFragment().navigate(R.id.action_savedFragment_to_helloFragment)
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