package com.furkanmulayim.tarifce.presentation.fragment.hello

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.FoodCategory


class FoodCategoryAdapter(
    private val dataList: ArrayList<FoodCategory>, private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder>() {


    private var selectedPosition = RecyclerView.SCROLLBAR_POSITION_DEFAULT

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val back: ConstraintLayout = itemView.findViewById(R.id.item_food_category_back)
        val front: ImageView = itemView.findViewById(R.id.imageView2)
        val name: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food_category, parent, false)
        return ViewHolder(view)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = dataList[position]
        //Burası Değişecek
        holder.back.setBackgroundResource(item.back)
        holder.front.setBackgroundResource(item.image)
        holder.name.text = item.name

        // Tıklanan ögenin pozisyonunu kontrol et
        if (position == selectedPosition) {
            // Tıklanan ögenin arka planını beyaz yap
            holder.back.setBackgroundResource(R.drawable.item_foot_category_back)
        } else {
            // Diğer ögelerin arka planını siyah yap
            holder.back.setBackgroundResource(R.drawable.circle_white)
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position
            onClick(item.name)
            notifyDataSetChanged() // Veri setini güncelle
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }


}

