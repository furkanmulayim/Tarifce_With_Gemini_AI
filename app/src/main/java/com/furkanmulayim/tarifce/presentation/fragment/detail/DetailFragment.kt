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
import com.furkanmulayim.tarifce.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var name: String = ""
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
        arguments?.let {
            name = DetailFragmentArgs.fromBundle(it).id
            viewModel.commonData(name)
        }
        observeLiveData()
        clickListener()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            navigate(R.id.action_detailFragment_to_helloFragment)
        }

        binding.savedButton.setOnClickListener{
            viewModel.saveFood()
        }
    }

    //send bundle prepare to bottom sheet dialog with nav graph
    private fun setBottomSheet(bundle: String) {
        binding.seeThePrepare.setOnClickListener {
            val act = DetailFragmentDirections.actionDetailFragmentToPrepareBSFragment(bundle)
            Navigation.findNavController(it).navigate(act)
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
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.ingrRcyc.layoutManager = layoutManager
    }
}