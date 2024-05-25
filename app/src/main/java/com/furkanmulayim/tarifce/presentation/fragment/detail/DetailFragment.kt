package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.base.BaseFragment
import com.furkanmulayim.tarifce.databinding.FragmentDetailBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.stringToList
import com.furkanmulayim.tarifce.util.viewMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    private lateinit var hastagAdapter: HastagAdapter
    private var isProductFav: Boolean = false
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        observeArgumentFood()
        observeFavItem()
        onClickListeners()
    }

    private fun onClickListeners() {
        with(binding) {
            backButton.onSingleClickListener {
                onBackPressed()
            }
            savedButton.onSingleClickListener {
                viewModel?.food?.value.let { prod ->
                    if (prod?.id != null) {
                        val f: Drawable?
                        if (isProductFav) {
                            isProductFav = false
                            f = AppCompatResources.getDrawable(
                                mcontext, R.drawable.svg_save
                            )
                            viewModel?.deleteSingleProducct(prod.id!!)
                        } else {
                            isProductFav = true
                            f = AppCompatResources.getDrawable(
                                mcontext, R.drawable.svg_save_dark
                            )
                            viewModel?.saveFavoriProduct(prod)
                        }
                        if (f != null) binding.savedButton.foreground = f
                    } else {
                        viewMessage(mcontext, getString(R.string.saved_hata))
                    }
                }
            }
        }
    }

    private fun observeFavItem() {
        viewModel.favItem.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                if (data.id != null) {
                    val foreground =
                        AppCompatResources.getDrawable(mcontext, R.drawable.svg_save_dark)
                    binding.savedButton.foreground = foreground
                    isProductFav = true
                }
            }
        }
    }

    private fun observeArgumentFood() {
        viewModel.food.observe(viewLifecycleOwner) { data ->
            data?.let { food ->
                binding.shapeableImageView.loadImage(food.image)
                food.hastags?.let { setHastags(it) }
                food.specific?.let { setBottomSheet(it) }
            }
        }
    }

    private fun setHastags(hastag: String) {
        val adapter = HastagAdapter(stringToList(hastag))
        binding.ingrRcyc.adapter = adapter
        val layoutManager = GridLayoutManager(mcontext, 2)
        binding.ingrRcyc.layoutManager = layoutManager
    }


    private fun setBottomSheet(prepareText: String) {
        binding.seeThePrepare.onSingleClickListener {
            val bundle = Bundle().apply {
                putString("prepare", prepareText)
            }
            val act =
                DetailFragmentDirections.actionDetailFragmentToPrepareBottomSheetDialogFragment()
            navigateTo(act.actionId, bundle)
        }
    }
}