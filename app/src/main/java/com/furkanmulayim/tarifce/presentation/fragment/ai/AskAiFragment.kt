package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.databinding.FragmentAskAiBinding
import com.furkanmulayim.tarifce.util.navigate
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible

class AskAiFragment : Fragment() {

    private lateinit var viewModel: AskAiViewModel
    private lateinit var binding: FragmentAskAiBinding
    private lateinit var storyAdapter: StoryAdapter
    private var selectedMaterialsMessage: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ask_ai, container, false)
        viewModel = ViewModelProvider(this)[AskAiViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        storyAdapter = StoryAdapter(arrayListOf(), requireContext())
        binding.foodAiRcyc.layoutManager = LinearLayoutManager(requireContext())
        binding.foodAiRcyc.adapter = storyAdapter

        extractSelectedMaterials()
        setClickListeners()
        observeDataAndSetupAdapter()
    }

    private fun extractSelectedMaterials() {
        val selectedMaterials = arguments?.getStringArray("material_list")
        selectedMaterials?.let {
            selectedMaterialsMessage =
                it.joinToString(" ") + getString(R.string.google_ai_first_message)
            viewModel.mesajEkle(selectedMaterialsMessage, true)
            enterStandbyMode()
            viewModel.askGoogleAI(requireContext(),selectedMaterialsMessage)
        }
    }

    private fun observeDataAndSetupAdapter() {
        viewModel.mesajlar.observe(viewLifecycleOwner) {
            it?.let {
                val alist: ArrayList<Message>? = viewModel.mesajlar.value
                alist?.let { arrlist ->
                    storyAdapter.updateList(arrlist)
                    binding.foodAiRcyc.scrollToPosition(arrlist.size - 1)
                }
                if (viewModel.dataGeldiMi.value == true) exitStandbyMode() else enterStandbyMode()
            }
        }
    }

    private fun setClickListeners() {
        binding.sendButton.setOnClickListener {
            enterStandbyMode()
            viewModel.dataGeldiMi.value = false

            val message = "${getString(R.string.google_ai_second_message) } $selectedMaterialsMessage"
            viewModel.mesajEkle(message, true)
            viewModel.askGoogleAI(requireContext(),message)
        }
        binding.backButton.setOnClickListener {
            requireParentFragment().navigate(R.id.action_askAiFragment_to_chooseFragment)
        }
    }

    private fun exitStandbyMode() {
        viewVisible(binding.sendButton)
        viewGone(binding.shimmerFrameLayout)
    }

    private fun enterStandbyMode() {
        viewGone(binding.sendButton)
        viewVisible(binding.shimmerFrameLayout)
    }
}