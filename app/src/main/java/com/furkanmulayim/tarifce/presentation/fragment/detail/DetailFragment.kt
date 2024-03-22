package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentDetailBinding
import com.furkanmulayim.tarifce.presentation.fragment.material_choose.ChooseFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModels()
    private var name: String = ""
    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        arguments?.let {
            name = DetailFragmentArgs.fromBundle(it).id
            viewModel.commonData(name)
        }
        observeLiveData()
        clickListener()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            val act = DetailFragmentDirections.actionDetailFragmentToHelloFragment()
            navigateTo(act.actionId)
        }

        binding.savedButton.setOnClickListener {
            viewModel.saveFood()
        }
    }

    //send bundle prepare to bottom sheet dialog with nav graph
    private fun setBottomSheet(bundle: String) {
        binding.seeThePrepare.setOnClickListener {
            val act = DetailFragmentDirections.actionDetailFragmentToPrepareBSFragment(bundle)
            navigateTo(act.actionId)
        }
    }

    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner) { f ->
            f?.let {
                setIngredients(it.hastags)
                viewModel.setImage(it.image, binding.shapeableImageView)
                setBottomSheet(it.specific)
            }
        }
    }

    private fun setIngredients(ingr: String) {
        ingredientsAdapter = IngredientsAdapter(viewModel.ingrList(ingr))
        binding.ingrRcyc.adapter = ingredientsAdapter
        val layoutManager = GridLayoutManager(mContext, 2)
        binding.ingrRcyc.layoutManager = layoutManager
    }
}