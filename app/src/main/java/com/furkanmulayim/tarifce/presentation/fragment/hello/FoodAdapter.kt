package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.ItemFoodBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.google.android.material.imageview.ShapeableImageView

class FoodAdapter(
    private val dataList: ArrayList<Food>,
    private val onClick:(String) -> (Unit)
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.shapeableImageView
        val duration: TextView = binding.foodDuration
        val stars: TextView = binding.foodStars
        val name: TextView = binding.foodName
        val category: TextView = binding.foodCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.image.loadImage(item.image)
        holder.duration.text = item.duration
        holder.stars.text = item.stars
        holder.name.text = item.name
        holder.category.text = item.category

        holder.itemView.setOnClickListener {
            onClick(item.name)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Food>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
