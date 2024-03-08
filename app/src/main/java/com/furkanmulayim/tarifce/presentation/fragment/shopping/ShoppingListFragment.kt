package com.furkanmulayim.tarifce.presentation.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentShoppingListBinding

class ShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel

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
        clickListener()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            nav(R.id.action_shoppingListFragment_to_helloFragment)
        }
        binding.createListButton.setOnClickListener {
            nav(R.id.action_shoppingListFragment_to_addListFragment)
        }
    }

    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }
}