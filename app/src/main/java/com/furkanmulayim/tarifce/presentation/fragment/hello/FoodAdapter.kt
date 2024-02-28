package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.util.loadImage
import com.google.android.material.imageview.ShapeableImageView


class FoodAdapter(
    private val dataList: ArrayList<Food>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ShapeableImageView = itemView.findViewById(R.id.shapeableImageView)
        val duration: TextView = itemView.findViewById(R.id.food_duration)
        val stars: TextView = itemView.findViewById(R.id.food_stars)
        val name: TextView = itemView.findViewById(R.id.food_name)
        val category: TextView = itemView.findViewById(R.id.food_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.image.loadImage(item.image)
        holder.duration.text = item.duration
        holder.stars.text = item.stars
        holder.name.text = item.name
        holder.category.text = item.category

        item.id




        holder.itemView.setOnClickListener {
            val act = HelloFragmentDirections.actionHelloFragmentToDetailFragment(item.name)
            Navigation.findNavController(it).navigate(act)
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

