package com.furkanmulayim.tarifce.presentation.fragment.shopping_add_item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.data.model.ShoppingList
import com.furkanmulayim.tarifce.databinding.ItemShoppingMaterialsBinding
import com.furkanmulayim.tarifce.util.loadImage

class ShoppingListAdapter(var dataList: ArrayList<ShoppingList>) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemShoppingMaterialsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val image: ImageView = binding.image
        val weight: TextView = binding.weight
        val incr: Button = binding.buttonIncrease
        val dec: Button = binding.buttonDecrease


        init {
            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemShoppingMaterialsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.let { vh ->
            vh.name.text = item.name
            vh.image.loadImage(item.image)
            vh.weight.text = item.weight

            vh.dec.setOnClickListener {
                val weight = vh.weight.text.toString().toDouble()
                if (weight > 0.5) {
                    vh.weight.text = (weight - 0.5).toString()
                }
            }

            vh.incr.setOnClickListener {
                val weight = vh.weight.text.toString().toDouble()
                vh.weight.text = (weight + 0.5).toString()
            }
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun updateList(newList: ArrayList<ShoppingList>) {
        dataList.clear()
        dataList = newList
        notifyItemRangeChanged(0, dataList.size)
    }

}
