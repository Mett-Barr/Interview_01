package com.example.interview01.topic.recyclerview.advanced

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRvAdapter<T, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseRvAdapter.BaseViewHolder<VB>>(diffCallback) {

    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
    abstract fun bind(binding: VB, item: T, position: Int)

    open fun onCreate(viewHolder: BaseViewHolder<VB>) {
        // Optional: Subclasses can override if needed
    }

    open fun setupClickListener(binding: VB, position: () -> Int) {
        // Optional: Subclasses can override if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = BaseViewHolder(binding)
        onCreate(viewHolder)
        setupClickListener(binding) { viewHolder.adapterPosition }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        bind(holder.binding, item, position)
    }

    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}