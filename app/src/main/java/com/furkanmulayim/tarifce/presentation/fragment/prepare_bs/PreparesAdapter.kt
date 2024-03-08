package com.furkanmulayim.tarifce.presentation.fragment.prepare_bs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.databinding.ItemFoodPreparesBinding

class PreparesAdapter(
    private val dataList: List<String>
) : RecyclerView.Adapter<PreparesAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemFoodPreparesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.prepare
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodPreparesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
