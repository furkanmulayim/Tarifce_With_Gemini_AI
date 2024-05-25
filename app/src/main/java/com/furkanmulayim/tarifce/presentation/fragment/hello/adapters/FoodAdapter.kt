package com.furkanmulayim.tarifce.presentation.fragment.hello.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.ItemFoodBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.google.android.material.imageview.ShapeableImageView

class FoodAdapter(
    private val context: Context,
    private val dataList: ArrayList<Food>,
    private val onClick: (Food) -> (Unit)
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        private val stars: TextView = binding.foodStars
        private val category: TextView = binding.foodCategory
        private val image: ShapeableImageView = binding.shapeableImageView
        val duration: TextView = binding.foodDuration
        val name: TextView = binding.foodName

        fun bind(item: Food) {
            name.text = item.name
            stars.text = item.stars
            image.loadImage(item.image)
            duration.text = item.duration
            category.text = item.category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_items)
        holder.itemView.startAnimation(animation)

        holder.itemView.onSingleClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
