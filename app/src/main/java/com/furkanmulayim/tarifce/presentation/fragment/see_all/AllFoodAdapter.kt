package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.ItemAllFoodBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.google.android.material.imageview.ShapeableImageView

class AllFoodAdapter(
    val context: Context,
    private val dataList: List<Food>,
    val onClickItem: (Food) -> (Unit)
) : RecyclerView.Adapter<AllFoodAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemAllFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        private val rootItem: LinearLayout = binding.rootItem
        private val image: ShapeableImageView = binding.itemFoodCategoryBack
        val duration: TextView = binding.foodDuration
        private val stars: TextView = binding.foodStars
        val name: TextView = binding.foodName

        fun bind(item: Food) {
            image.loadImage(item.image)
            duration.text = item.duration
            stars.text = item.stars
            name.text = item.name

            rootItem.onSingleClickListener {
                onClickItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_items)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
