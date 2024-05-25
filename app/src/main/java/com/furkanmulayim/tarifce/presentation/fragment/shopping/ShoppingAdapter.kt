import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.databinding.ItemShoppingMaterialsBinding
import com.furkanmulayim.tarifce.presentation.fragment.shopping.ShoppingItemClickListener
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener
import kotlinx.coroutines.*

class ShoppingAdapter(
    private val context: Context,
    var dataList: ArrayList<Shopliste>,
    private val clickListener: ShoppingItemClickListener
) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemShoppingMaterialsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val image: ImageView = binding.image
        val back: CardView = binding.cardView
        private val deleteButton: Button = binding.buttonDelete


        fun bind(item: Shopliste) {
            name.text = item.name
            image.loadImage(item.image)

            if (item.issold == 0) {
                back.setCardBackgroundColor(getColor(context, R.color.white))
                image.foreground = null
                name.paintFlags = name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            } else {
                back.setCardBackgroundColor(getColor(context, R.color.yellow))
                image.foreground = ContextCompat.getDrawable(context, R.drawable.check)
                name.paintFlags = name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        init {
            itemView.onSingleClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = dataList[position]
                    if (item.issold == 0) {
                        item.issold = 1
                        clickListener.onItemIsSold(item.id, 1)
                        back.setCardBackgroundColor(getColor(context, R.color.yellow))
                        image.foreground = ContextCompat.getDrawable(context, R.drawable.check)
                        name.paintFlags = name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        item.issold = 0
                        clickListener.onItemIsSold(item.id, 0)
                        back.setCardBackgroundColor(getColor(context, R.color.white))
                        image.foreground = null
                        name.paintFlags = name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }

            deleteButton.onSingleClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val itemId = dataList[pos].id
                    dataList.removeAt(pos)
                    notifyItemRemoved(pos)
                    notifyItemRangeChanged(pos, itemCount - pos)
                    clickListener.onItemDelete(itemId)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingMaterialsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_items_2)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newList: ArrayList<Shopliste>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyItemRangeChanged(0, newList.size)
    }
}