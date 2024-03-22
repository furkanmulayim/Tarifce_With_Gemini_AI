package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import com.furkanmulayim.tarifce.util.viewGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelloFragment : BaseFragment<FragmentHelloBinding>() {

    private lateinit var foodAdapter: FoodAdapter
    private val viewModel: HelloViewModel by viewModels()
    private lateinit var itemAdapter: FoodCategoryAdapter
    private var category: String = "Trend"

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHelloBinding {
        return FragmentHelloBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerFrameLayout.startShimmer()
        viewModel.getData()
        setItems()
        observeLiveData()
        clickListener()
    }


    private fun clickListener() {
        binding.savedButton.setOnClickListener {
            val action = HelloFragmentDirections.actionHelloFragmentToSavedFragment()
            navigateTo(action.actionId)
        }

        binding.aiButton.setOnClickListener {
            val action = HelloFragmentDirections.actionHelloFragmentToChooseFragment()
            navigateTo(action.actionId)
        }

        binding.shoppingListButton.setOnClickListener {
            val action = HelloFragmentDirections.actionHelloFragmentToShoppingListFragment()
            navigateTo(action.actionId)
        }

        binding.seeAllButton.setOnClickListener {
            val action = HelloFragmentDirections.actionHelloFragmentToAllFoodFragment(category)
            navigateTo(action.actionId, bundle = action.arguments)
        }
    }

    private fun setItems() {
        viewModel.selectedCategories(category)
        itemAdapter = FoodCategoryAdapter(viewModel.listReturn()) { categoryName ->
            viewModel.selectedCategories(categoryName)
            viewModel.selectedCategory.observe(viewLifecycleOwner) { categ ->
                categ?.let {
                    category = categ
                    binding.itemName.text = category
                }
            }
        }
        observeLiveData()
        binding.itemFoodCategoryRcyc.adapter = itemAdapter
        binding.itemFoodCategoryRcyc.layoutManager = GridLayoutManager(mContext, 5)
    }


    private fun navigateToDetail(name: String) {
        val action = HelloFragmentDirections.actionHelloFragmentToDetailFragment(name)
        navigateTo(action.actionId, bundle = action.arguments)
    }

    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner) { foods ->
            foods?.let {
                viewModel.comeFirstDataFoodsByCategory()
                viewModel.seciliUrunler.observe(viewLifecycleOwner) {
                    it?.let {
                        stopShimmer()
                        foodAdapter = FoodAdapter(it as ArrayList<Food>, ::navigateToDetail)
                        binding.foodsRcyc.adapter = foodAdapter
                        binding.foodsRcyc.layoutManager = GridLayoutManager(mContext, 2)
                    }
                }
            }
        }
    }

    private fun stopShimmer() {
        viewGone(binding.shimmerFrameLayout)
        binding.foodsRcyc.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }
}