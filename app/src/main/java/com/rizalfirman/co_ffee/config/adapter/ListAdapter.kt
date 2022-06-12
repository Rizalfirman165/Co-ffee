package com.rizalfirman.co_ffee.config.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalfirman.co_ffee.config.model.PlantDisease
import com.rizalfirman.co_ffee.databinding.ListItemBinding

class ListAdapter(private val listAdapter: ArrayList<PlantDisease>, private val onClick: (PlantDisease) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    inner class ListViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo)= listAdapter[position]
        holder.binding.apply {
            imageItem.setImageResource(photo)
            tvItemName.text = name
            tvItemDescription.text = description

        }
        holder.itemView.setOnClickListener {
            onClick(listAdapter[position])
        }
    }

    override fun getItemCount(): Int = listAdapter.size
}