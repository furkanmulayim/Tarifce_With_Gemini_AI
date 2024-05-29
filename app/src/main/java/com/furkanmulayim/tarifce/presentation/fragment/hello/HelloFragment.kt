package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.enums.Categorie
import com.furkanmulayim.tarifce.data.enums.FragmentNames
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import com.furkanmulayim.tarifce.presentation.fragment.hello.adapters.FoodAdapter
import com.furkanmulayim.tarifce.presentation.fragment.hello.adapters.FoodCategoryAdapter
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HelloFragment : BaseFragment<FragmentHelloBinding, HelloViewModel>() {

    private lateinit var categoryAdapter: FoodCategoryAdapter
    private lateinit var foodAdapter: FoodAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHelloBinding {
        return FragmentHelloBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedCategory.value = Categorie.BUTUN.names
        observeConnection()
        initCategoryAdapter()
        observeFoods()
        observeCategories()
        initClickListener()
    }

    private fun observeConnection() {
        viewModel.isInternetAvailable.observe(viewLifecycleOwner) { conn ->
            if (conn) viewGone(binding.ncl.connectionLayout)
            else viewVisible(binding.ncl.connectionLayout)
        }
    }

    private fun initClickListener() {
        with(binding) {
            seeAllButton.onSingleClickListener {
                val categ = viewModel.selectedCategory.value
                val bundle = Bundle().apply {
                    putString("category", categ)
                }
                val act = HelloFragmentDirections.actionHelloFragmentToSeeAllFragment()
                navigateTo(act.actionId, bundle)
            }

            shoppingListButton.onSingleClickListener {
                val action = HelloFragmentDirections.actionHelloFragmentToShoppingListFragment()
                navigateTo(action.actionId)
            }

            aiButton.onSingleClickListener {
                val bundle = Bundle().apply {
                    putString("fragmentState", FragmentNames.HELLO.names)
                }
                val action = HelloFragmentDirections.actionHelloFragmentToCategoryFragment()
                navigateTo(action.actionId, bundle)
            }

            savedButton.onSingleClickListener {
                val action = HelloFragmentDirections.actionHelloFragmentToFavFragment()
                navigateTo(action.actionId)
            }


        }
    }

    private fun initCategoryAdapter() {
        categoryAdapter =
            FoodCategoryAdapter(viewModel.getCategories(), ::onClickAdapterCategItem)
        with(binding.categoryRcyc) {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setFoodsAdapter(foods: List<Food>) {
        foodAdapter = FoodAdapter(mcontext, foods as ArrayList<Food>, ::onClickAdapterFoodItem)
        with(binding.foodsRcyc) {
            adapter = foodAdapter
            layoutManager = GridLayoutManager(mcontext, 2)
        }
    }

    private fun observeFoods() {
        viewModel.foodList.observe(viewLifecycleOwner) { data ->
            data.let { foods ->
                if (foods != null) {
                    setFoodsAdapter(foods)
                }
            }
        }
    }

    private fun observeCategories() {
        viewModel.selectedCategory.observe(viewLifecycleOwner) { categ ->
            binding.itemName.text = categ
            if (categ == Categorie.BUTUN.names) {
                viewModel.foodList.value?.let { setFoodsAdapter(it) }
            } else {
                val all = viewModel.foodList.value
                viewLifecycleOwner.lifecycleScope.launch {
                    all?.let { viewModel.filterFoods(it) }?.let { setFoodsAdapter(it) }
                }
            }
        }
    }

    private fun onClickAdapterCategItem(category: String) {
        viewModel.selectedCategories(category)
    }

    private fun onClickAdapterFoodItem(food: Food) {
        val bundle = Bundle().apply { putParcelable("FoodDetail", food) }
        val action = HelloFragmentDirections.actionHelloFragmentToDetailFragment().actionId
        navigateTo(action, bundle)
    }
}