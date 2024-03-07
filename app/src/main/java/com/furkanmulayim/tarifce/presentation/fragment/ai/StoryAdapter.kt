package com.furkanmulayim.tarifce.presentation.fragment.ai

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.tarifce.R
import com.furkanmulayim.tarifce.data.model.Message
import com.furkanmulayim.tarifce.databinding.ItemMessageBinding

class StoryAdapter(
    private val dataList: ArrayList<Message>, val context: Context
) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    private var lastAddedItemPosition: Int = -1

    inner class ViewHolder(binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userSpace: ImageView = binding.userSpace
        val aiSpace: ImageView = binding.aiSpace
        val sl: ImageView = binding.spaceLeft
        val sr: ImageView = binding.spaceRight
        val message: TextView = binding.message
        val mBack: ConstraintLayout = binding.messageBack
        val lay: LinearLayout = binding.lay

        init {
            itemView.setOnClickListener {
                //
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        } else {
            setAiMessageTemplate(holder)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun setUserMessageTemplate(holder: ViewHolder) {
        holder.let {
            it.userSpace.visibility = View.VISIBLE
            it.aiSpace.visibility = View.GONE
            it.sl.visibility = View.VISIBLE
            it.sr.visibility = View.GONE
            it.mBack.setBackgroundResource(R.drawable.message_back_me)
        }
    }

    private fun setAiMessageTemplate(holder: ViewHolder) {
        holder.let {
            it.aiSpace.visibility = View.VISIBLE
            it.userSpace.visibility = View.GONE
            it.sl.visibility = View.GONE
            it.sr.visibility = View.VISIBLE
            it.mBack.setBackgroundResource(R.drawable.message_back_ai)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Message>) {
        dataList.clear()
        dataList.addAll(newList)
        lastAddedItemPosition = newList.size - 1
        notifyItemInserted(newList.size - 1)
        notifyDataSetChanged()
    }
}
