package com.furkanmulayim.tarifce.presentation.fragment.choose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Category
import com.furkanmulayim.tarifce.data.model.Material


class MaterialAdapter(var categories: List<Category>
) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.food_name)
        val subRecyc: RecyclerView = itemView.findViewById(R.id.subAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food2, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.text.text = item.name

        val subLayoutManager = GridLayoutManager(holder.itemView.context, 3)
        holder.subRecyc.layoutManager = subLayoutManager
        val subAdapter = SubAdapter(categories[position].materials)
        holder.subRecyc.adapter = subAdapter
    }


    override fun getItemCount(): Int {
        return categories.size
    }

    // SubAdapter'ı buraya yerleştirin
    inner class SubAdapter(private val items: List<Material>) :
        RecyclerView.Adapter<SubAdapter.SubViewHolder>() {

        inner class SubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val categ: TextView = itemView.findViewById(R.id.food_ingr)
            val image: ImageView = itemView.findViewById(R.id.itemImage)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_material_ingredients, parent, false)
            return SubViewHolder(view)
        }

        override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
            val item = items[position]
            holder.categ.text = item.name
            item.imageUrl
           // holder.image.setBackgroundResource(item.)
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }

}

