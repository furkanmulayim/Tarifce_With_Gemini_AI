package com.furkanmulayim.tarifce.presentation.fragment.all_foods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentAllFoodBinding
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import com.furkanmulayim.tarifce.presentation.fragment.material_choose.ChooseFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFoodFragment : BaseFragment<FragmentAllFoodBinding>() {

    private val viewModel: AllFoodViewModel by viewModels()
    private var foodAdapter = AllFoodAdapter(arrayListOf())
    private lateinit var bundleData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundleData = arguments?.getString("itemName").toString()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllFoodBinding {
        return FragmentAllFoodBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setFoods()
        clickListener()
    }

    private fun setAdapter() {
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = AllFoodFragmentDirections.actionAllFoodFragmentToHelloFragment()
            navigateTo(act.actionId)
        }

    }

    private fun setFoods() {
        binding.categoryName.text = bundleData
        viewModel.commonData(bundleData)
        viewModel.foods.observe(viewLifecycleOwner) { f ->
            f?.let {
                foodAdapter.updateList(it)
            }

        }
    }

}