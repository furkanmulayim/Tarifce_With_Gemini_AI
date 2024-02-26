package com.furkanmulayim.tarifce.presentation.fragment.detail

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
import com.furkanmulayim.tarifce.databinding.FragmentDetailBinding
import com.furkanmulayim.tarifce.presentation.fragment.prepare.PrepareBSFragment

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setIngredients()
        clickListener()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            nav(R.id.action_detailFragment_to_helloFragment)
        }
        binding.seeThePrepare.setOnClickListener {
            PrepareBSFragment().show(childFragmentManager, "")
        }
    }

    private fun setIngredients() {
        ingredientsAdapter = IngredientsAdapter(viewModel.ingrList())
        binding.ingrRcyc.adapter = ingredientsAdapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.ingrRcyc.layoutManager = layoutManager
    }


    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }
}