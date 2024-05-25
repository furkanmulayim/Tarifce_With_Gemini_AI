package com.furkanmulayim.tarifce.presentation.fragment.shopping

import ShoppingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.enums.FragmentNames
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.databinding.FragmentShoppingListBinding
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty


@AndroidEntryPoint
class ShoppingListFragment : BaseFragment<FragmentShoppingListBinding, ShoppingListViewModel>(),
    ShoppingItemClickListener {
    private lateinit var adapter: ShoppingAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentShoppingListBinding {
        return FragmentShoppingListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
        observList()
        setupAdapter()
        clickListener()
    }

    private fun setupAdapter() {
        adapter = ShoppingAdapter(mcontext, arrayListOf(), this)
        binding.shoppingListRcyc.layoutManager = GridLayoutManager(mcontext, 3)
        binding.shoppingListRcyc.adapter = adapter
    }

    private fun observList() {
        viewModel.shopList.observe(viewLifecycleOwner) { list ->
            list?.let {
                val uniqueNames = mutableSetOf<String>()
                val uniqueSavedList = mutableListOf<Material>()

                for (saved in it) {
                    if (saved.name?.let { it1 -> uniqueNames.add(it1) } == true) {
                        uniqueSavedList.add(saved)
                    }
                }
                adapter.updateList(ArrayList(uniqueSavedList))

            }
            setEmptyListUI(list)
        }
    }

    private fun setEmptyListUI(list: List<Material>) {
        if (list.isEmpty()) {
            viewGone(binding.shoppingListRcyc)
            viewVisible(binding.shoppingListEmpty)
        } else {
            viewVisible(binding.shoppingListRcyc)
            viewGone(binding.shoppingListEmpty)
        }
    }

    private fun clickListener() {
        binding.backButton.onSingleClickListener {
            onBackPressed()
        }
        binding.createListButton.onSingleClickListener {
            val bundle = Bundle().apply {
                putString("fragmentState", FragmentNames.SHOPPING.names)
            }
            val act = ShoppingListFragmentDirections.actionShoppingListFragmentToCategoryFragment()
            navigateTo(act.actionId, bundle)
        }
        binding.deleteButton.onSingleClickListener {
            if (!viewModel.shopList.value.isNullOrEmpty()) {
                viewModel.shopList.value = mutableListOf()
                viewModel.deleteAllSql()
            } else {
                emptyListMessage()
            }
        }
    }

    private fun emptyListMessage() {
        Toasty.custom(
            mcontext,
            getString(R.string.empty_list_already),
            null,
            Toast.LENGTH_SHORT,
            false
        ).show()
    }

    override fun onItemDelete(name: String) {
        viewModel.deleteItem(name)
    }
}