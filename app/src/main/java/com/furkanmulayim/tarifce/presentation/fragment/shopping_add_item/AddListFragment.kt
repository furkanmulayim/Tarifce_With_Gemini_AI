package com.furkanmulayim.tarifce.presentation.fragment.shopping_add_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentAddListBinding

class AddListFragment : Fragment() {

    private lateinit var viewModel: AddListViewModel
    private lateinit var binding: FragmentAddListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_list, container, false)
        viewModel = ViewModelProvider(this).get(AddListViewModel::class.java)
        return binding.root
    }


}