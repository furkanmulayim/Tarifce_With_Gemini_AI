package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.databinding.ItemMessageBinding
import com.furkanmulayim.tarifce.util.onSingleClickListener
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible

class StoryAdapter(
    var onClickShare: (String) -> (Unit),
    var dataList: ArrayList<Message>,
    val context: Context
) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    private var lastAddedItemPosition: Int = -1

    inner class ViewHolder(binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        private val userSpace: ImageView = binding.userSpace
        private val aiSpace: ImageView = binding.aiSpace
        private val sl: ImageView = binding.spaceLeft
        private val sr: ImageView = binding.spaceRight
        var message: TextView = binding.message
        private val mBack: ConstraintLayout = binding.messageBack
        val lay: LinearLayout = binding.lay
        private val shareButton: ImageView = binding.shareButton

        fun bind(item: Message) {
            shareButton.onSingleClickListener {
                if (dataList[adapterPosition].mesaj != getString(
                        context,
                        R.string.google_ai_error
                    )
                ) {
                    onClickShare.invoke(item.mesaj)
                }
            }
            if (item.isuser) {
                viewGone(aiSpace); viewVisible(userSpace); viewVisible(sl); viewGone(sr)
                mBack.setBackgroundResource(R.drawable.message_back_me)
                message.setTextColor(ContextCompat.getColor(context, R.color.white))
                viewGone(shareButton)
                message.setPadding(
                    message.paddingLeft,
                    message.paddingTop,
                    message.paddingRight,
                    30
                )
            } else {
                viewVisible(aiSpace); viewGone(userSpace); viewGone(sl); viewVisible(sr)
                mBack.setBackgroundResource(R.drawable.message_back_ai)
                message.setTextColor(ContextCompat.getColor(context, R.color.main_text_color0))
                viewVisible(shareButton)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.message.text = item.mesaj

        if (position == lastAddedItemPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_items)
            holder.lay.startAnimation(animation)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(messages: ArrayList<Message>) {
        dataList.clear()
        dataList.addAll(messages)
        notifyItemRangeChanged(0, dataList.size)
    }
}
