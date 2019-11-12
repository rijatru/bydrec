package com.test.bydrec.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.bydrec.R
import com.test.bydrec.model.Item
import com.test.bydrec.view.HeaderItemListView
import com.test.bydrec.view.ItemListView
import com.test.bydrec.view.ItemView
import com.test.bydrec.viewmodel.ListItemViewHolder

class ItemAdapter(private val context: Context, private var items: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { // TODO This code can be handled by a ViewFactory
        val view: View = when (viewType) {
            R.integer.list_item -> ItemListView(this.context)
            R.integer.list_header_item -> HeaderItemListView(this.context)
            else -> ItemListView(this.context)
        }
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView: ItemView = holder.itemView as ItemView
        itemView.bind(items[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clearItems() {
        val numberOfItemsToRemove = items.size
        val itemsToRemove: List<Item> = ArrayList()
        if (items.size >= numberOfItemsToRemove) {
            for (i in items.size - 1 downTo items.size - numberOfItemsToRemove) {
                (itemsToRemove as ArrayList).add(items[i])
            }
            itemsToRemove.forEach { removeItem(it) }
        }
    }

    private fun removeItem(item: Item) {
        val index = items.indexOf(item)
        if (index != -1) {
            (items as ArrayList).removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
