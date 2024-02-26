package com.furkanmulayim.tarifce.presentation.fragment.see_all

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.domain.model.Food
import com.google.android.material.imageview.ShapeableImageView


class AllFoodAdapter(
    private val dataList: ArrayList<Food>
) : RecyclerView.Adapter<AllFoodAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image:ShapeableImageView = itemView.findViewById(R.id.item_food_category_back)
        val duration: TextView = itemView.findViewById(R.id.food_duration)
        val stars: TextView = itemView.findViewById(R.id.food_stars)
        val name: TextView = itemView.findViewById(R.id.food_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_food, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.image.setBackgroundResource(item.image)
        holder.duration.text = item.duration
        holder.stars.text = item.stars.toString()
        holder.name.text = item.name

        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_helloFragment_to_detailFragment)
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Food>) {
        dataList.clear()
        dataList.addAll((newList))
        notifyDataSetChanged()
    }


}

