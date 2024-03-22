package com.furkanmulayim.tarifce.presentation.fragment.shopping

import ShoppingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.databinding.FragmentShoppingListBinding
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShoppingListFragment : BaseFragment<FragmentShoppingListBinding>(),
    ShoppingItemClickListener {

    private val viewModel: ShoppingListViewModel by viewModels()
    private lateinit var adapter: ShoppingAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentShoppingListBinding {
        return FragmentShoppingListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observList()
        observClicked()
        clickListener()
    }

    private fun observList() {
        viewModel.cardList.observe(viewLifecycleOwner) {
            it?.let {
                setAdapter(it)
            }
            if (it.isEmpty()) {
                viewGone(binding.shoppingListRcyc)
                viewVisible(binding.shoppingListEmpty)
            } else {
                viewVisible(binding.shoppingListRcyc)
                viewGone(binding.shoppingListEmpty)
            }
        }
    }

    private fun observClicked() {
        viewModel.isSelectedAdapter.observe(viewLifecycleOwner) { isFilterOn ->

            val isListNotNull = !viewModel.cardList.value.isNullOrEmpty()
            if (isFilterOn) {
                binding.allListedButton.background = null
                binding.filterListedButton.background = AppCompatResources.getDrawable(
                    requireContext(), R.drawable.shopping_list_swiper_selected
                )
                if (isListNotNull) {
                    val newList = viewModel.cardList.value!!.filter { it.issold == 0 }
                    setAdapter(newList.reversed())
                } else {
                    setAdapter(listOf())
                }
            } else {
                binding.filterListedButton.background = null
                binding.allListedButton.background = AppCompatResources.getDrawable(
                    requireContext(), R.drawable.shopping_list_swiper_selected
                )
                if (isListNotNull) {
                    val newList = viewModel.cardList.value!!
                    setAdapter(newList.reversed())
                } else {
                    setAdapter(listOf())
                }
            }
        }
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = ShoppingListFragmentDirections.actionShoppingListFragmentToHelloFragment()
            navigateTo(act.actionId)
        }
        binding.createListButton.setOnClickListener {
            val act = ShoppingListFragmentDirections.actionShoppingListFragmentToMaterialFragment()
            navigateTo(act.actionId)
        }

        binding.allListedButton.setOnClickListener {
            if (viewModel.isSelectedAdapter.value == true) viewModel.isSelectedAdapter.value = false
        }

        binding.filterListedButton.setOnClickListener {
            if (viewModel.isSelectedAdapter.value == false) viewModel.isSelectedAdapter.value = true
        }
    }

    private fun setAdapter(list: List<Shopliste>) {
        adapter = if (list.isNotEmpty()) {
            ShoppingAdapter(mContext, ArrayList(list.reversed()), this)
        } else {
            ShoppingAdapter(mContext, arrayListOf(), this)
        }
        binding.shoppingListRcyc.layoutManager = GridLayoutManager(mContext, 3)
        binding.shoppingListRcyc.adapter = adapter
    }


    override fun onItemIsSold(id: Int, isSold: Int) {
        viewModel.updateItem(id, isSold)
            viewModel.cardList.value?.filter {
                it.id == id
            }?.indices
    }

    override fun onItemDelete(id: Int) {
        viewModel.deleteItem(id)
    }
}