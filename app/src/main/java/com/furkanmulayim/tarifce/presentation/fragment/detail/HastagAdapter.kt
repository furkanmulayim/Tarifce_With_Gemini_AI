package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.databinding.ItemFoodHastagBinding

class HastagAdapter(
    private val dataList: List<String>
) : RecyclerView.Adapter<HastagAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemFoodHastagBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.foodIngr
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFoodHastagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.name.text = item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
