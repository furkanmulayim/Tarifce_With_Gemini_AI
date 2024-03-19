package com.furkanmulayim.tarifce.presentation.fragment.shopping_material

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.databinding.FragmentMaterialBinding
import com.furkanmulayim.tarifce.presentation.fragment.shopping.ShoppingListFragmentDirections
import com.furkanmulayim.tarifce.util.viewGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterialFragment : BaseFragment<FragmentMaterialBinding>() {

    private val viewModel: MaterialViewModel by viewModels()
    private lateinit var adapter: ShoppingMaterialAdapter
    private val selectedMaterialList: MutableList<Shopliste> = mutableListOf()

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMaterialBinding {
        return FragmentMaterialBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.shimmerFrameLayout.startShimmer()
        viewModel.getData()
        clickListener()
        setAdapter()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = MaterialFragmentDirections.actionMaterialFragmentToShoppingListFragment()
            navigateTo(act.actionId)
        }

        binding.sendButton.setOnClickListener {
            controlListAndNavigate()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        binding.materialRcyc.layoutManager = GridLayoutManager(requireContext(), 1)
        adapter = ShoppingMaterialAdapter(emptyList()) {
            for (element in it) {
                selectedMaterialList.add(element)
            }
        }
        binding.materialRcyc.adapter = adapter

        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categories ->
            adapter.categories = categories
            shimmerKapat()
            adapter.notifyDataSetChanged()
        }
    }

    private fun controlListAndNavigate() {
        if (selectedMaterialList.isEmpty()) {
            handleEmptyCategory()
        } else {
            if (viewModel.saveDatabase(selectedMaterialList.distinct())) {
                val act = MaterialFragmentDirections.actionMaterialFragmentToShoppingListFragment()
                navigateTo(act.actionId)

            }
        }
    }

    private fun handleEmptyCategory() {
        // TODO burda malzeme seçilmemiş demektir. Kullanıcıyla iletisim !!!
    }

    private fun shimmerKapat() {
        viewGone(binding.shimmerFrameLayout)
        binding.materialRcyc.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }
}