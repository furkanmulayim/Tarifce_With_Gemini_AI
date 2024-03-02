package com.furkanmulayim.tarifce.presentation.fragment.hello

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
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelloFragment : Fragment() {

    private var foodAdapter = FoodAdapter(arrayListOf())
    private lateinit var viewModel: HelloViewModel
    private lateinit var binding: FragmentHelloBinding
    private lateinit var itemAdapter: FoodCategoryAdapter
    private var category: String = "Trend"

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

        /**adapter init*/
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 2)

        /** get data from apı or sqlite (situation!)*/
        viewModel.getData()
        setItems()
        observeLiveData()

        setAdapter()
        clickListener()
    }

    /** onclick listeners*/
    private fun clickListener() {
        binding.savedButton.setOnClickListener {
            nav(R.id.action_helloFragment_to_savedFragment)
        }

        binding.aiButton.setOnClickListener {
            nav(R.id.action_helloFragment_to_chooseFragment)
        }

        /**send bundle all foods to bottom sheet dialog with nav graph*/
        binding.seeAllButton.setOnClickListener {
            val act = HelloFragmentDirections.actionHelloFragmentToSeeAllBSFragment(category)
            Navigation.findNavController(it).navigate(act)
        }
    }

    /** Show category names in recycler view*/
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
    }

    /** adapter ayarlama*/
    private fun setAdapter() {
        binding.itemFoodCategoryRcyc.adapter = itemAdapter
        binding.itemFoodCategoryRcyc.layoutManager = GridLayoutManager(requireContext(), 5)
    }


    /** food list observe*/
    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner) { foods ->
            foods?.let {
                viewModel.comeFirstDataFoodsByCategory()
                viewModel.seciliUrunler.observe(viewLifecycleOwner){
                    it?.let {
                        foodAdapter.updateList(it)
                    }
                }
            }
        }
    }

    /** navigaation transactions*/
    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }

}