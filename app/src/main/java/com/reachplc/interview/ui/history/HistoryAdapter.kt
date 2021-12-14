package com.reachplc.interview.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reachplc.interview.data.local.ProductHistory
import com.reachplc.interview.databinding.HistoryItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(private val onItemClicked: (ProductHistory) -> Unit)
    : ListAdapter<ProductHistory, HistoryAdapter.ItemViewHolder>  (DiffCallback)
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener { onItemClicked(current) }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductHistory) {
            val accessTime: List<String> = item.lastVisit.visit.dateToString("dd/MM/yyyy HH:mm").split(" ")
            binding.apply {
                titleTxt.text = item.product.name
                dateTxt.text = accessTime[0]
                timeTxt.text = accessTime[1]
            }
        }
        private fun Date.dateToString(format: String): String {
            val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
            return dateFormatter.format(this)
        }
    }



    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ProductHistory>() {
            override fun areItemsTheSame(oldItem: ProductHistory, newItem: ProductHistory): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ProductHistory, newItem: ProductHistory): Boolean {
                return oldItem.lastVisit.productId == newItem.lastVisit.productId
            }
        }
    }
}