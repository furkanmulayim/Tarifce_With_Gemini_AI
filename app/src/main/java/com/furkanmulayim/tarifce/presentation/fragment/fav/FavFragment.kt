package com.furkanmulayim.tarifce.presentation.fragment.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.FragmentFavBinding
import com.furkanmulayim.tarifce.presentation.fragment.see_all.AllFoodAdapter
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : BaseFragment<FragmentFavBinding, FavViewModel>() {

    private lateinit var allAdapter: AllFoodAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavBinding {
        return FragmentFavBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavs()
        observeFavItems()
        onClickListener()
    }

    private fun onClickListener() {
        binding.backButton.onSingleClickListener {
            onBackPressed()
        }
    }

    private fun observeFavItems() {
        viewModel.foodList.observe(viewLifecycleOwner) { data ->
            data?.let {
                setAdapter(data)
                setEmptyListUI(data)
            }
        }
    }

    private fun setAdapter(foods: List<Food>) {
        allAdapter = AllFoodAdapter(mcontext, foods.reversed(), ::onClickAdapterFoodItem)
        with(binding.foodsRcyc) {
            adapter = allAdapter
            val lm = LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false)
            layoutManager = lm
        }
    }

    private fun setEmptyListUI(list: List<Food>) {
        if (list.isEmpty()) {
            viewGone(binding.foodsRcyc)
            viewVisible(binding.listEmpty)
        } else {
            viewVisible(binding.foodsRcyc)
            viewGone(binding.listEmpty)
        }
    }

    private fun onClickAdapterFoodItem(food: Food) {
        val bundle = Bundle().apply { putParcelable("FoodDetail", food) }
        val action = FavFragmentDirections.actionFavFragmentToDetailFragment()
        navigateTo(action.actionId, bundle)
    }
}