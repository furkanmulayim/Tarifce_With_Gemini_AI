package com.furkanmulayim.tarifce.presentation.fragment.material_choose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentChooseBinding
import com.furkanmulayim.tarifce.presentation.fragment.detail.DetailFragmentDirections
import com.furkanmulayim.tarifce.util.viewGone

class ChooseFragment : BaseFragment<FragmentChooseBinding>() {

    private val viewModel: ChooseViewModel by viewModels()
    private lateinit var adapter: MaterialAdapter
    private val selectedMaterialList: MutableList<String> = mutableListOf()

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentChooseBinding {
        return FragmentChooseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.shimmerFrameLayout.startShimmer()
        viewModel.getData()
        clickListener()
        setAdapter()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = ChooseFragmentDirections.actionChooseFragmentToHelloFragment()
            navigateTo(act.actionId)
        }

        binding.sendButton.setOnClickListener {
            controlListAndNavigate()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        binding.materialRcyc.layoutManager = GridLayoutManager(requireContext(), 1)

        adapter = MaterialAdapter(emptyList()) {
            selectedMaterialList.clear()
            for (element in it) {
                selectedMaterialList.add(element)
            }
        }
        binding.materialRcyc.adapter = adapter

        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categories ->
            adapter.categories = categories
            shimmerKapat()
            adapter.notifyDataSetChanged()
        }
    }

    private fun controlListAndNavigate() {
        if (selectedMaterialList.isEmpty()) {
            handleEmptyCategory()
        } else {
            val bundle = Bundle().apply {
                putStringArray("material_list", selectedMaterialList.toTypedArray())
            }
            navigateToResultFragment(bundle)
        }
    }

    private fun handleEmptyCategory() {
        // burda malzeme seçilmemiş demektir. Kullanıcıyla iletisim !!!
    }

    private fun navigateToResultFragment(bundle: Bundle) {
        Navigation.findNavController(requireView())
        val act = ChooseFragmentDirections.actionChooseFragmentToAskAiFragment()
        navigateTo(act.actionId)
    }

    private fun shimmerKapat() {
        viewGone(binding.shimmerFrameLayout)
        binding.materialRcyc.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }
}
