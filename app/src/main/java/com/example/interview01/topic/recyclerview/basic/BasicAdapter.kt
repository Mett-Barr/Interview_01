package com.example.interview01.topic.recyclerview.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview01.databinding.ItemRvBinding

class BasicAdapter : RecyclerView.Adapter<BasicAdapter.ViewHolder>() {

    // 這裡是模擬數據，你可以根據需要進行調整
    private val dataList: MutableList<Int> = mutableListOf()

    // 設定click事件
    private var click: (Int) -> Unit = {}
    fun setClick(callback: (Int) -> Unit) {
        click = callback
    }

    // 使用ViewBinding來與每一行的UI元素互動
    inner class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root)

    // 當需要一個新的ViewHolder時，這個方法將會被調用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 使用ViewBinding來獲取布局
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)

        // 設定點擊事件
        binding.txt.setOnClickListener {
            click(viewHolder.adapterPosition)
        }

        return viewHolder
    }

    // 當需要顯示資料到某一行時，這個方法將會被調用
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 取出數據並賦值給UI元素
        holder.binding.txt.text = dataList[position].toString()
    }

    // 返回列表中的總數
    override fun getItemCount(): Int {
        return dataList.size
    }

    // 提供一個方法來更新數據
    fun setData(newData: List<Int>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()  // 通知數據已經更改，這樣RecyclerView將刷新其內容
    }
}