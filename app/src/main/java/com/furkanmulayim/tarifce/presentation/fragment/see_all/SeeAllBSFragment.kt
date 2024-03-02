package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentSeeAllBSBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllBSFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel: SeeAllBViewModel
    private lateinit var binding: FragmentSeeAllBSBinding
    private var foodAdapter = AllFoodAdapter(arrayListOf())
    private lateinit var bundleData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundleData = arguments?.getString("category").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_see_all_b_s, container, false)
        viewModel = ViewModelProvider(this)[SeeAllBViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setFoods()
    }

    private fun setAdapter() {
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    private fun setFoods() {
        binding.categoryName.text = bundleData
        viewModel.commonData(bundleData)
        viewModel.foods.observe(viewLifecycleOwner) { f ->
            f?.let {
                println("Fragment" + it.size)
                foodAdapter.updateList(it)
            }

        }
    }

}