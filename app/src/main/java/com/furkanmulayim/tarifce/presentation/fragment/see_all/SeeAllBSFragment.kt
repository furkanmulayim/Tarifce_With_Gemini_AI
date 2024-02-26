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
import com.furkanmulayim.tarifce.presentation.fragment.hello.FoodAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SeeAllBSFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel: SeeAllBViewModel
    private lateinit var binding: FragmentSeeAllBSBinding
    private lateinit var foodAdapter: AllFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_see_all_b_s, container, false)
        viewModel = ViewModelProvider(this)[SeeAllBViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFoods()
    }

    private fun setFoods() {
        foodAdapter = AllFoodAdapter(viewModel.foodReturn())
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 1)
    }

}