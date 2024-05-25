package com.furkanmulayim.tarifce.presentation.fragment.categorie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.enums.FragmentNames
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.databinding.FragmentCategoryBinding
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    private val selectedMaterialList: ArrayList<Material> = ArrayList()
    private lateinit var adapter: CategoryAdapter
    private var beforeFragment = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCategorie()
        onClickListener()
        observeFragmentState()
    }

    private fun onClickListener() {
        with(binding) {
            backButton.onSingleClickListener {
                onBackPressed()
            }

            okButton.onSingleClickListener {
                controlListAndNavigate()
            }
        }
    }

    private fun observeFragmentState() {
        viewModel.fragmentState.observe(viewLifecycleOwner) { data ->
            if (data != null) beforeFragment = data
            println("BOSs $data")
            setByFragmentState()
        }
    }

    private fun setByFragmentState() {
        viewGone(binding.deleteButton)
    }

    private fun observeCategorie() {
        viewModel.category.observe(viewLifecycleOwner) { data ->
            setAdapter(data)
        }
    }

    private fun setAdapter(array: ArrayList<CategoryData>) {
        binding.materialRcyc.layoutManager = GridLayoutManager(mcontext, 1)
        adapter = CategoryAdapter(array, ::onAdapterItemClickListener)
        binding.materialRcyc.adapter = adapter
    }

    private fun onAdapterItemClickListener(item: MutableSet<Material>) {
        selectedMaterialList.clear()
        for (element in item) {
            selectedMaterialList.add(element)
        }
    }

    private fun controlListAndNavigate() {
        if (selectedMaterialList.isEmpty()) {
            handleEmptyCategory()
        } else {
            navigateToResultFragment()
        }
    }

    private fun handleEmptyCategory() {
        Toasty.custom(
            mcontext,
            getString(R.string.please_material_choose),
            AppCompatResources.getDrawable(requireContext(), R.drawable.delete),
            Toast.LENGTH_SHORT,
            true
        ).show()
    }

    private fun navigateToResultFragment() {
        when (beforeFragment) {
            FragmentNames.HELLO.names -> {
                val action = CategoryFragmentDirections.actionCategoryFragmentToAskAiFragment()
                navigateTo(action.actionId, viewModel.setBundleList(selectedMaterialList))
            }

            FragmentNames.SHOPPING.names -> {
                val bundle = Bundle().apply {
                    putParcelableArrayList("material_list", selectedMaterialList)
                }
                val action =
                    CategoryFragmentDirections.actionCategoryFragmentToShoppingListFragment()
                navigateTo(action.actionId, bundle)
            }

            else -> println("BOSs")
        }
    }
}