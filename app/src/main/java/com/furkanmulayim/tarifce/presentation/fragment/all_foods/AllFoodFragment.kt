package com.furkanmulayim.tarifce.presentation.fragment.all_foods

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
import com.furkanmulayim.tarifce.databinding.FragmentAllFoodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFoodFragment : Fragment() {

    private lateinit var viewModel: AllFoodViewModel
    private lateinit var binding: FragmentAllFoodBinding
    private var foodAdapter = AllFoodAdapter(arrayListOf())
    private lateinit var bundleData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundleData = arguments?.getString("itemName").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_food, container, false)
        viewModel = ViewModelProvider(this)[AllFoodViewModel::class.java]
        return binding.root
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
        Navigation.findNavController(requireView())
            .navigate(R.id.action_allFoodFragment_to_helloFragment)
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