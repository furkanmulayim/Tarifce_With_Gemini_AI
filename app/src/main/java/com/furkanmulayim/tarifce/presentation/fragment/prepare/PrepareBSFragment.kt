package com.furkanmulayim.tarifce.presentation.fragment.prepare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentPrepareBSBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PrepareBSFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPrepareBSBinding
    private lateinit var viewModel: BottomSheetDialogViewModel
    private lateinit var prepareAdapter: PreparesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prepare_b_s, container, false)
        viewModel = ViewModelProvider(this)[BottomSheetDialogViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPrepare()

    }

    private fun setPrepare() {
        prepareAdapter = PreparesAdapter(viewModel.preparesList())
        binding.prepareRcyc.adapter = prepareAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.prepareRcyc.layoutManager = layoutManager
    }

}