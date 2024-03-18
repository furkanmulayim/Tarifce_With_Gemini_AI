package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
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
import com.furkanmulayim.tarifce.util.viewGone
import com.furkanmulayim.tarifce.util.viewVisible

class StoryAdapter(
    var dataList: ArrayList<Message>, val context: Context
) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    private var lastAddedItemPosition: Int = -1

    inner class ViewHolder(binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val userSpace: ImageView = binding.userSpace
        val aiSpace: ImageView = binding.aiSpace
        val sl: ImageView = binding.spaceLeft
        val sr: ImageView = binding.spaceRight
        var message: TextView = binding.message
        val mBack: ConstraintLayout = binding.messageBack
        val lay: LinearLayout = binding.lay
        val shareButton: ImageView = binding.shareButton

        init {
            shareButton.setOnClickListener {
                val b = dataList[adapterPosition].mesaj
                if (b != getString(context, R.string.google_ai_error)) {
                    shareMessage(b)
                }
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
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim)
            holder.lay.startAnimation(animation)
        }

        if (item.isuser) {
            setUserMessageTemplate(holder)
            holder.message.setTextColor(ContextCompat.getColor(context, R.color.white))
            viewGone(holder.shareButton)
            holder.message.setPadding(
                holder.message.paddingLeft,
                holder.message.paddingTop,
                holder.message.paddingRight,
                30
            )
        } else {
            setAiMessageTemplate(holder)
            holder.message.setTextColor(ContextCompat.getColor(context, R.color.main_text_color0))
            viewVisible(holder.shareButton)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun setUserMessageTemplate(holder: ViewHolder) {

        holder.let {
            viewGone(it.aiSpace)
            viewVisible(it.userSpace)
            viewVisible( it.sl)
            viewGone(it.sr)
            it.mBack.setBackgroundResource(R.drawable.message_back_me)
        }
    }

    private fun setAiMessageTemplate(holder: ViewHolder) {
        holder.let {
            viewVisible(it.aiSpace)
            viewGone(it.userSpace)
            viewGone( it.sl)
            viewVisible(it.sr)
            it.mBack.setBackgroundResource(R.drawable.message_back_ai)
        }
    }

    private fun shareMessage(message: String) {
        // Metni paylaşma işlemleri
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        context.startActivity(Intent.createChooser(intent, "Metni Paylaş"))
    }

    fun updateList(messages: ArrayList<Message>) {
        dataList.clear()
        dataList.addAll(messages)
        notifyItemRangeChanged(0, dataList.size)
    }
}
