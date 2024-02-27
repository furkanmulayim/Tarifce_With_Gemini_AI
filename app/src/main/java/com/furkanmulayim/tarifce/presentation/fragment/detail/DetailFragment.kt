package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentDetailBinding
import com.furkanmulayim.tarifce.presentation.fragment.prepare.PrepareBSFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var id: Int = -1
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
            id = DetailFragmentArgs.fromBundle(it).id
            viewModel.commonData(id)
        }
        observeLiveData()
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

    fun observeLiveData(){
        viewModel.food.observe(viewLifecycleOwner) { f ->
            f.let {
                binding.itemName.text = f.name
                binding.itemCategory.text = f.category
                binding.stars.text = f.stars
                binding.itemMinute.text = f.duration
                binding.itemCalorie.text = f.calorie
                binding.itemPerson.text = f.person
                binding.itemLevel.text = f.level
                setIngredients(f.hastags)
            }
        }
    }



    private fun setIngredients(ingr:String) {
        ingredientsAdapter = IngredientsAdapter(viewModel.ingrList(ingr))
        binding.ingrRcyc.adapter = ingredientsAdapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.ingrRcyc.layoutManager = layoutManager
    }


    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }
}