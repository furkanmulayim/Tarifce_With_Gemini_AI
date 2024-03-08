package com.furkanmulayim.tarifce.presentation.fragment.ai

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
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.databinding.FragmentAskAiBinding

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
        extractSelectedMaterials()
        setupAdapter()
        initializeUi()
        setClickListeners()
        observMessageLoading()
    }

    private fun extractSelectedMaterials() {
        val selectedMaterials = arguments?.getStringArray("material_list")
        selectedMaterials?.let {
            selectedMaterialsMessage =
                it.joinToString(" ") + " malzemeleri ile evde yapabileceğim bir tane yemeğin yapılışını anlatır mısın?"
        }
    }

    private fun setupAdapter() {
        storyAdapter = StoryAdapter(requireContext())
        binding.foodAiRcyc.layoutManager = LinearLayoutManager(requireContext())
        binding.foodAiRcyc.adapter = storyAdapter
    }

    private fun initializeUi() {
        addInitialMessage()
    }

    private fun setClickListeners() {
        binding.sendButton.setOnClickListener {
            enterStandbyMode()
            val message = "Bu sefer bir öncekinden farklı olarak $selectedMaterialsMessage"
            addMessageToConversation(message, Message(message, true))
        }
        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_askAiFragment_to_chooseFragment)
        }
    }

    private fun addInitialMessage() {
        addMessageToConversation(selectedMaterialsMessage, Message(selectedMaterialsMessage, true))
    }

    private fun addMessageToConversation(messageText: String, message: Message) {
        enterStandbyMode()
        viewModel.messageList.postValue(listOf(message))
        viewModel.askGoogleAI(messageText)
        observeGoogleAiMessage()
    }


    private fun observeGoogleAiMessage() {
        viewModel.messageList.observe(viewLifecycleOwner) {
            it.let {
                storyAdapter.updateList(it)
            }
        }
    }

    private fun observMessageLoading() {
        viewModel.dataGeldiMi.observe(viewLifecycleOwner) {
            exitStandbyMode()
        }
    }

    private fun exitStandbyMode() {
        binding.sendButton.visibility = View.VISIBLE
        binding.shimmerFrameLayout.visibility = View.GONE
    }

    private fun enterStandbyMode() {
        binding.sendButton.visibility = View.GONE
        binding.shimmerFrameLayout.visibility = View.VISIBLE
    }
}