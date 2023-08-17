package com.example.interview01.topic.recyclerview.advanced

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.interview01.databinding.ItemRvBinding

class RvAdapter : BaseRvAdapter<Int, ItemRvBinding>(
    object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
) {

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemRvBinding =
        ItemRvBinding::inflate

    private var click: (Int) -> Unit = {}

    fun setClick(callback: (Int) -> Unit) {
        click = callback
    }

    override fun setupClickListener(binding: ItemRvBinding, position: () -> Int) {
        binding.root.setOnClickListener {
            click(position())
        }
    }

    override fun bind(binding: ItemRvBinding, item: Int, position: Int) {
        binding.apply {
            txt.text = item.toString()
        }
    }
}
