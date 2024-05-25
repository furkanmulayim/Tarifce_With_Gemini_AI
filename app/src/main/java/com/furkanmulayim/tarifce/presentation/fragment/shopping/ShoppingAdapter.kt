import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.databinding.ItemShoppingMaterialsBinding
import com.furkanmulayim.tarifce.presentation.fragment.shopping.ShoppingItemClickListener
import com.furkanmulayim.tarifce.util.loadImage
import com.furkanmulayim.tarifce.util.onSingleClickListener

class ShoppingAdapter(
    private val context: Context,
    var dataList: ArrayList<Material>,
    private val clickListener: ShoppingItemClickListener
) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemShoppingMaterialsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val image: ImageView = binding.image
        val back: CardView = binding.cardView
        private val deleteButton: Button = binding.buttonDelete


        fun bind(item: Material) {
            name.text = item.name
            image.loadImage(item.url)

        }

        init {
            itemView.onSingleClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }

            deleteButton.onSingleClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val itemId = dataList[pos].name
                    dataList.removeAt(pos)
                    notifyItemRemoved(pos)
                    notifyItemRangeChanged(pos, itemCount - pos)
                    if (itemId != null) {
                        clickListener.onItemDelete(itemId)
                    }
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

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newList: ArrayList<Material>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyItemRangeChanged(0, newList.size)
    }
}