package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import com.furkanmulayim.tarifce.presentation.fragment.see_all.SeeAllBSFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelloFragment : Fragment() {

    private var foodAdapter = FoodAdapter(arrayListOf())
    private lateinit var viewModel: HelloViewModel
    private lateinit var binding: FragmentHelloBinding
    private lateinit var itemAdapter: FoodCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hello, container, false)
        viewModel = ViewModelProvider(this)[HelloViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        //adapter init
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getData()
        observeLiveData()
        setItems()
        clickListener()
    }

    private fun clickListener() {
        binding.savedButton.setOnClickListener {
            nav(R.id.action_helloFragment_to_savedFragment)
        }

        binding.aiButton.setOnClickListener {
            nav(R.id.action_helloFragment_to_chooseFragment)
        }

        binding.seeAllButton.setOnClickListener {
            SeeAllBSFragment().show(childFragmentManager, "")
        }
    }

    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }

    private fun setItems() {
        itemAdapter = FoodCategoryAdapter(viewModel.listReturn()) { it ->
            viewModel.selectedCategories(it)
            viewModel.selectedCategory.observe(viewLifecycleOwner, Observer { categ ->
                binding.itemName.text = categ
            })
        }
        binding.itemFoodCategoryRcyc.adapter = itemAdapter
        binding.itemFoodCategoryRcyc.layoutManager = GridLayoutManager(requireContext(), 5)
    }

    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner, Observer {
            it.let {
                foodAdapter.updateList(it)
            }
        })
    }
}