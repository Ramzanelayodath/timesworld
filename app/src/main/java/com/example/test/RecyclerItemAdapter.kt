package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemAdapter(private val childItems: List<Data.Data.Taxonomy>) :
    RecyclerView.Adapter< RecyclerItemAdapter.ParentViewHolder>() {

    var onItemClick: ((Data.Data.Taxonomy,Boolean) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemAdapter.ParentViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.child_item_layout, parent, false)
        return ParentViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerItemAdapter.ParentViewHolder, position: Int) {
        val item = childItems[position]
        holder.rdItem.text = item.name
        holder.rdItem.isChecked = item.isChecked
        holder.rdItem.setOnClickListener {
            holder.rdItem.isChecked = !item.isChecked
            item.isChecked = !item.isChecked
                onItemClick?.invoke(item,item.isChecked)
        }

    }


    override fun getItemCount(): Int {
        return childItems.size
    }


    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rdItem : RadioButton

        init {
            rdItem = itemView.findViewById(R.id.rdItem)

        }
    }
}
