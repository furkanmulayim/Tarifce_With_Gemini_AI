package com.furkanmulayim.tarifce.presentation.fragment.all_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.databinding.ItemAllFoodBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.google.android.material.imageview.ShapeableImageView

class AllFoodAdapter(
    private val dataList: ArrayList<Food>
) : RecyclerView.Adapter<AllFoodAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemAllFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.itemFoodCategoryBack
        val duration: TextView = binding.foodDuration
        val stars: TextView = binding.foodStars
        val name: TextView = binding.foodName

        init {
            binding.root.setOnClickListener {
                val item = dataList[adapterPosition]
                val action =
                    AllFoodFragmentDirections.actionAllFoodFragmentToDetailFragment(item.name)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.image.loadImage(item.image)
        holder.duration.text = item.duration
        holder.stars.text = item.stars
        holder.name.text = item.name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Food>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}
