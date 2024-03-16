package com.furkanmulayim.tarifce.presentation.fragment.shopping_material

import android.annotation.SuppressLint
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
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.databinding.FragmentMaterialBinding
import com.furkanmulayim.tarifce.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterialFragment : Fragment() {

    private lateinit var viewModel: MaterialViewModel
    private lateinit var binding: FragmentMaterialBinding
    private lateinit var adapter: ShoppingMaterialAdapter
    private val selectedMaterialList: MutableList<Shopliste> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_material, container, false)
        viewModel = ViewModelProvider(this)[MaterialViewModel::class.java]
        return binding.root
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
            requireParentFragment().navigate(R.id.action_materialFragment_to_shoppingListFragment)
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
                requireParentFragment().navigate(R.id.action_materialFragment_to_shoppingListFragment)
            }
        }
    }

    private fun handleEmptyCategory() {
        // burda malzeme seçilmemiş demektir. Kullanıcıyla iletisim !!!
    }

    private fun shimmerKapat() {
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.materialRcyc.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }
}