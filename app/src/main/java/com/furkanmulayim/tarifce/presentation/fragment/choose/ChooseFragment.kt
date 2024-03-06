package com.furkanmulayim.tarifce.presentation.fragment.choose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentChooseBinding

class ChooseFragment : Fragment() {

    private lateinit var viewModel: ChooseViewModel
    private lateinit var binding: FragmentChooseBinding
    private lateinit var adapter: MaterialAdapter
    private val selectedMaterialList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose, container, false)
        viewModel = ViewModelProvider(this)[ChooseViewModel::class.java]
        return binding.root
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
            nav(R.id.action_chooseFragment_to_helloFragment)
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
            for ((i, element) in it.withIndex()) {
                println(element)
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
            .navigate(R.id.action_chooseFragment_to_askAiFragment, bundle)
    }

    private fun shimmerKapat() {
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.materialRcyc.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }

    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }
}
