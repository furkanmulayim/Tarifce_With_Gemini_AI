package com.furkanmulayim.tarifce.presentation.fragment.shopping

import ShoppingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.databinding.FragmentShoppingListBinding
import com.furkanmulayim.tarifce.util.navigate
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListFragment : Fragment(), ShoppingItemClickListener {

    private lateinit var binding: FragmentShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var adapter: ShoppingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_list, container, false)
        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observList()
        observClicked()
        clickListener()
    }

    private fun observList() {
        viewModel.cardList.observe(viewLifecycleOwner) {
            setAdapter(it.reversed())
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
            if (isFilterOn) {
                binding.allListedButton.background = null
                binding.filterListedButton.background = AppCompatResources.getDrawable(
                    requireContext(), R.drawable.shopping_list_swiper_selected
                )
                if (isListIsNotNull()) {
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
                if (isListIsNotNull()) {
                    val newList = viewModel.cardList.value!!
                    setAdapter(newList.reversed())
                } else {
                    setAdapter(listOf())
                }
            }
        }
    }

    private fun isListIsNotNull(): Boolean {
        return !viewModel.cardList.value.isNullOrEmpty()
    }

    private fun setAdapter(list: List<Shopliste>) {
        adapter = if (list.isNotEmpty()) {
            ShoppingAdapter(requireContext(), ArrayList(list), this)
        } else {
            ShoppingAdapter(requireContext(), arrayListOf(), this)
        }
        binding.shoppingListRcyc.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.shoppingListRcyc.adapter = adapter
    }


    private fun clickListener() {
        binding.backButton.setOnClickListener {
            requireParentFragment().navigate(R.id.action_shoppingListFragment_to_helloFragment)
        }
        binding.createListButton.setOnClickListener {
            requireParentFragment().navigate(R.id.action_shoppingListFragment_to_materialFragment)
        }

        binding.allListedButton.setOnClickListener {
            viewModel.isSelectedAdapter.value = false
        }

        binding.filterListedButton.setOnClickListener {
            viewModel.isSelectedAdapter.value = true
        }
    }

    override fun onItemIsSold(id: Int, isSold: Int) {
        viewModel.updateItem(id, isSold)
    }

    override fun onItemDelete(id: Int) {
        viewModel.deleteItem(id)
    }
}