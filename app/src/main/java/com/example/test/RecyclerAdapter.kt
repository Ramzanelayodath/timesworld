package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private val ctx : Context ,private val parentItems: List<Data.Data>) :
    RecyclerView.Adapter< RecyclerViewAdapter.ParentViewHolder>() {

    var onItemClick: ((Data.Data.Taxonomy,Boolean) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ParentViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.parent_item_layout, parent, false)
        return ParentViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ParentViewHolder, position: Int) {
        val item = parentItems[position]
        holder.title.text = item.name+"("+item.taxonomies.size.toString()+")"
        holder.recyclerView.layoutManager = LinearLayoutManager(ctx,RecyclerView.VERTICAL,false)
        holder.itemView.setOnClickListener {
            item.isExpanded = !item.isExpanded
            if (item.isExpanded) {
                holder.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                holder.recyclerView.visibility = View.VISIBLE
                val adapter = RecyclerItemAdapter(item.taxonomies)
                holder.recyclerView.adapter = adapter
                adapter.onItemClick = {it,isChecked ->
                     onItemClick?.invoke(it,isChecked)
                }
            }
            else {
                holder.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                holder.recyclerView.visibility = View.GONE
            }

        }
    }


    override fun getItemCount(): Int {
        return parentItems.size
    }


    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var title: TextView
         var image : ImageView
         var recyclerView : RecyclerView

        init {
            title = itemView.findViewById(R.id.txtCategory)
            image = itemView.findViewById(R.id.img)
            recyclerView = itemView.findViewById(R.id.recyclerChild)
        }
    }
}

