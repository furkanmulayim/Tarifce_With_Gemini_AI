package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.databinding.FragmentAskAiBinding
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible

class AskAiFragment : BaseFragment<FragmentAskAiBinding, AskAiViewModel>() {

    private lateinit var storyAdapter: StoryAdapter
    private var selectedMaterialsMessage: String = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAskAiBinding {
        return FragmentAskAiBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storyAdapter = StoryAdapter(::onClickShare, arrayListOf(), mcontext)
        binding.foodAiRcyc.layoutManager = LinearLayoutManager(mcontext)
        binding.foodAiRcyc.adapter = storyAdapter

        observeBundleList()
        setClickListeners()
        observeDataAndSetupAdapter()
    }

    private fun onClickShare(message: String) {
        if (message.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = getString(R.string.intent_type)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            mcontext.startActivity(Intent.createChooser(intent, getString(R.string.intent_title)))
        }
    }

    private fun observeBundleList() {
        viewModel.bundleList.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                println("veri size $data")
                selectedMaterialsMessage = data
                viewModel.mesajEkle(data, true)
                enterStandbyMode()
                viewModel.askGoogleAI(mcontext, data)
            } else selectedMaterialsMessage = "Herhangi bir"

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
        with(binding) {
            sendButton.onSingleClickListener {
                enterStandbyMode()
                viewModel.dataGeldiMi.value = false

                val message =
                    "${getString(R.string.google_ai_second_message)} $selectedMaterialsMessage"
                viewModel.mesajEkle(message, true)
                viewModel.askGoogleAI(mcontext, message)
            }

            backButton.onSingleClickListener {
                onBackPressed()
            }
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