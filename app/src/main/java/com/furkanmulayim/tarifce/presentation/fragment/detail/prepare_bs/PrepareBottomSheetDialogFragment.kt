package com.furkanmulayim.tarifce.presentation.fragment.detail.prepare_bs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.databinding.FragmentPrepareBottomSheetDialogBinding
import com.furkanmulayim.tarifce.util.stringToListSlash
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PrepareBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPrepareBottomSheetDialogBinding
    private lateinit var viewModel: BottomViewModel
    private lateinit var prepareAdapter: PreparesAdapter
    private lateinit var bundleData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundleData = arguments?.getString("prepare").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_prepare_bottom_sheet_dialog,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[BottomViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBundle()
    }

    private fun observeBundle() {
        viewModel.bundle.observe(viewLifecycleOwner) { data ->
            data?.let { setPrepareAdapter(it) }
        }
    }

    private fun setPrepareAdapter(bundle: String) {
        prepareAdapter = PreparesAdapter(stringToListSlash(bundle))
        binding.prepareRcyc.adapter = prepareAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.prepareRcyc.layoutManager = layoutManager
    }

}