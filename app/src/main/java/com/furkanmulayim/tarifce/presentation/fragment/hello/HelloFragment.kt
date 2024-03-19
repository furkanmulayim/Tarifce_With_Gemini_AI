package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentHelloBinding
import com.furkanmulayim.tarifce.util.viewGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelloFragment : BaseFragment<FragmentHelloBinding>() {

    private var foodAdapter = FoodAdapter(arrayListOf())
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
        binding.foodsRcyc.adapter = foodAdapter
        binding.foodsRcyc.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getData()
        setItems()
        observeLiveData()
        setAdapter()
        clickListener()
    }

    /*    private fun clearCache() {
            try {
                val dir: File = requireContext().cacheDir
                deleteDir(dir)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        private fun deleteDir(dir: File?): Boolean {
            if (dir != null && dir.isDirectory) {
                val children: Array<String> = dir.list()
                for (i in children.indices) {
                    val success: Boolean = deleteDir(File(dir, children[i]))
                    if (!success) {
                        return false
                    }
                }
                return dir.delete()
            } else if (dir != null && dir.isFile) {
                return dir.delete()
            } else {
                return false
          }
            binding.logoTV.setOnClickListener {
                clearCache()
            }
        }*/


    //  onclick listeners*/
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
            val action =
                HelloFragmentDirections.actionHelloFragmentToAllFoodFragment(itemName = category)
            navigateTo(action.actionId, bundle = action.arguments)
        }

    }

    //  Show category names in recycler view*/
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

    //  adapter ayarlama*/
    private fun setAdapter() {
        binding.itemFoodCategoryRcyc.adapter = itemAdapter
        binding.let {}
        binding.itemFoodCategoryRcyc.layoutManager = GridLayoutManager(requireContext(), 5)
    }


    ///  food list observe */
    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner) { foods ->
            foods?.let {
                viewModel.comeFirstDataFoodsByCategory()
                viewModel.seciliUrunler.observe(viewLifecycleOwner) {
                    it?.let {
                        stopShimmer()
                        foodAdapter.updateList(it)
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