package com.furkanmulayim.tarifce.presentation.fragment.shopping_add_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.ShoppingList
import com.furkanmulayim.tarifce.databinding.FragmentAddListBinding

class AddListFragment : Fragment() {

    private lateinit var viewModel: AddListViewModel
    private lateinit var binding: FragmentAddListBinding
    private var adapter = ShoppingListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_list, container, false)
        viewModel = ViewModelProvider(this)[AddListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle()
        setupAdapter()
        clickListener()
    }

    private fun bundle() {
        val bundle = arguments
        if (bundle != null) {
            val selectedList =
                arguments?.getSerializable("selectedMaterialArrayList") as ArrayList<ShoppingList>
            viewModel.list.value = selectedList
            observeData()
        }
    }

    private fun setupAdapter() {
        adapter = ShoppingListAdapter(arrayListOf())
        binding.listRcyc.layoutManager = LinearLayoutManager(requireContext())
        binding.listRcyc.adapter = adapter
    }

    private fun clickListener() {
        binding.addMaterialsButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_addListFragment_to_materialFragment)
        }

        binding.addDevamButton.setOnClickListener{
            activity?.onBackPressed()
        }
    }

    private fun observeData() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            binding.addMaterialsButton.visibility = View.GONE
            binding.addDevamButton.visibility = View.VISIBLE
            adapter.updateList(list as ArrayList<ShoppingList>)
        }
    }

}