package com.furkanmulayim.tarifce.presentation.fragment.choose

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
import com.furkanmulayim.tarifce.databinding.FragmentChooseBinding
import com.furkanmulayim.tarifce.data.model.Category

class ChooseFragment : Fragment() {

    private lateinit var viewModel: ChooseViewModel
    private lateinit var binding: FragmentChooseBinding
    private lateinit var adapter: MaterialAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose, container, false)
        viewModel = ViewModelProvider(this).get(ChooseViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getData()
        clickListener()
        setAdapter()
    }

    private fun clickListener() {
        binding.backButton.setOnClickListener {
            nav(R.id.action_chooseFragment_to_helloFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        binding.materialRcyc.layoutManager = GridLayoutManager(requireContext(), 1)
        adapter = MaterialAdapter(emptyList()){
            println("****************************************")
            for (element in it){
                println("Basıldı" +element)
            }
        }
        binding.materialRcyc.adapter = adapter

        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categories ->
            adapter.categories = categories
            // Değişiklikleri RecyclerView'a bildir
            adapter.notifyDataSetChanged()
        }
    }


    private fun nav(id: Int) {
        Navigation.findNavController(requireView()).navigate(id)
    }
}
