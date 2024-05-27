package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.FragmentSeeAllBinding
import com.furkanmulayim.tarifce.util.onSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : BaseFragment<FragmentSeeAllBinding, SeeAllViewModel>() {

    private lateinit var allAdapter: AllFoodAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSeeAllBinding {
        return FragmentSeeAllBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCategory()
        observeFood()
        onClickListeners()
    }

    private fun onClickListeners() {
        with(binding) {
            backButton.onSingleClickListener {
                onBackPressed()
            }
        }
    }

    private fun observeFood() {
        viewModel.foodList.observe(viewLifecycleOwner) { data ->
            data.let { foods ->
                if (foods != null) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.filterFoodsByCategory()?.let { setAdapter(it) }
                    }
                }
            }
        }
    }

    private fun observeCategory() {
        viewModel.category.observe(viewLifecycleOwner) { data ->
            data?.let { binding.categoryName.text = data }
        }
    }

    private fun setAdapter(foods: List<Food>) {
        allAdapter = AllFoodAdapter(mcontext, foods, ::onClickAdapterFoodItem)
        with(binding.foodsRcyc) {
            adapter = allAdapter
            val lm = LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false)
            layoutManager = lm
        }
    }

    private fun onClickAdapterFoodItem(food: Food) {
        val bundle = Bundle().apply { putParcelable("FoodDetail", food) }
        val action = SeeAllFragmentDirections.actionSeeAllFragmentToDetailFragment()
        navigateTo(action.actionId, bundle)
    }
}