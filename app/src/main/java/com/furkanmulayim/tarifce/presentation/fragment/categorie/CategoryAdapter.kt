package com.furkanmulayim.tarifce.presentation.fragment.categorie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.databinding.ItemMaterialGeneralBinding
import com.furkanmulayim.tarifce.databinding.ItemMaterialIngredientsBinding
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewMessage

class CategoryAdapter(
    private var categories: List<CategoryData>,
    private val onClick: (MutableSet<Material>) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val selectedItems = mutableSetOf<Material>()

    inner class ViewHolder(binding: ItemMaterialGeneralBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val text: TextView = binding.foodName
        val subRecyc: RecyclerView = binding.subAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMaterialGeneralBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.text.text = item.id

        val subLayoutManager = GridLayoutManager(holder.itemView.context, 3)
        holder.subRecyc.layoutManager = subLayoutManager
        val subAdapter = categories[position].materials?.let { SubAdapter(it) }
        holder.subRecyc.adapter = subAdapter
    }

    override fun getItemCount(): Int = categories.size

    inner class SubAdapter(private val items: List<Material>) :
        RecyclerView.Adapter<SubAdapter.SubViewHolder>() {

        inner class SubViewHolder(binding: ItemMaterialIngredientsBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val categ: TextView = binding.foodIngr
            val image: ImageView = binding.itemImage
            val background: ConstraintLayout = binding.constraintLayout2
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
            val binding = ItemMaterialIngredientsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return SubViewHolder(binding)
        }

        override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
            val item = items[position]
            holder.categ.text = item.name
            holder.image.loadImage(item.url)

            holder.itemView.onSingleClickListener {
                if (selectedItems.contains(item)) {
                    holder.background.setBackgroundResource(R.drawable.button_materials_back3)

                    selectedItems.remove(item)
                } else {
                    holder.background.setBackgroundResource(R.drawable.button_materials_back4)
                    selectedItems.add(item)
                }
                onClick(selectedItems)
            }
        }

        override fun getItemCount(): Int = items.size
    }
}
