package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedItemAdapter(private val selectedItem: MutableList<Data.Data.Taxonomy>) : RecyclerView.Adapter< SelectedItemAdapter.ViewHolder>() {
    var onItemClick: ((Data.Data.Taxonomy) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedItemAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyler_row_selected_items, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: SelectedItemAdapter.ViewHolder, position: Int) {
         holder.txtName.text = selectedItem[position].name
         holder.imgClose.setOnClickListener {
             onItemClick!!.invoke(selectedItem[position])
             selectedItem.removeAt(position)
             notifyItemRemoved(position)

         }
    }


    override fun getItemCount(): Int {
        return selectedItem.size
    }


    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView
        var imgClose : ImageView


        init {
            txtName = itemView.findViewById(R.id.txtName)
            imgClose = itemView.findViewById(R.id.imgClose)

        }
    }
}