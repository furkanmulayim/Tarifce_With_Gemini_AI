package com.furkanmulayim.tarifce.presentation.fragment.material_choose

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Category
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.databinding.ItemMaterialGeneralBinding
import com.furkanmulayim.tarifce.databinding.ItemMaterialIngredientsBinding
import com.furkanmulayim.tarifce.util.loadImage

class MaterialAdapter(
    var categories: List<Category>, private val onClick: (MutableSet<String>) -> Unit
) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    private val selectedItems = mutableSetOf<String>()

    inner class ViewHolder(binding: ItemMaterialGeneralBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val text: TextView = binding.foodName
        val subRecyc: RecyclerView = binding.subAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMaterialGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
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
            holder.image.loadImage(item.imageUrl)

            holder.itemView.setOnClickListener {
                if (selectedItems.contains(item.name)) {
                    holder.background.setBackgroundResource(R.drawable.button_materials_back3)
                    holder.image.loadImage(item.imageUrl)
                    selectedItems.remove(item.name)
                } else {
                    holder.background.setBackgroundResource(R.drawable.button_materials_back4)
                    val checkedUrl: String = it.context.getString(R.string.checked_link)
                    holder.image.loadImage(checkedUrl)
                    selectedItems.add(item.name)
                }
                onClick(selectedItems)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}
