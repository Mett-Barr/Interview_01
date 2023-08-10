package com.example.interview01.topic.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.interview01.databinding.ItemRvBinding

abstract class BaseRvAdapter<T, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseRvAdapter.BaseViewHolder<VB>>(diffCallback) {

    abstract fun onCreate(binding: VB, position: Int)
    abstract fun bind(binding: VB, item: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        onCreate(holder.binding, position)
        bind(holder.binding, item, position)
    }

    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}

class RvAdapter : BaseRvAdapter<Int, ItemRvBinding>(
    ItemRvBinding::inflate,
    object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }) {

    private var click: (Int) -> Unit = {}

    fun setClick(callback: (Int) -> Unit) {
        click = callback
    }

    override fun onCreate(binding: ItemRvBinding, position: Int) {
        // You can do some initial setup or configuration here
        binding.root.setOnClickListener {
            click(position)
        }
    }

    override fun bind(binding: ItemRvBinding, item: Int, position: Int) {
        // Bind your data to your views here
        binding.apply {
            txt.text = item.toString()
        }
    }
}
