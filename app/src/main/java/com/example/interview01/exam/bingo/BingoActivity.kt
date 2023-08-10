package com.example.interview01.exam.bingo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.window.Dialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.interview01.databinding.ActivityBingoBinding
import com.example.interview01.databinding.ItemTextBinding
import javax.xml.transform.OutputKeys
import kotlin.random.Random


class BingoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBingoBinding
    private val mAdapter = MyAdapter()

    val list = MutableList(9) {
        num(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBingoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRv()
        initLis()
    }

    private fun initLis() {
//        mAdapter.callback = {
//            list[it]
//        }
        binding.button2.setOnClickListener {
            list.clear()
            list.addAll((1..25).shuffled().take(9).map { num(it) })
            mAdapter.list.clear()
            mAdapter.list.addAll(list)
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun initRv() {
        binding.rv.apply {
            layoutManager = GridLayoutManager(this@BingoActivity, 3)
            adapter = mAdapter
            mAdapter.list.clear()
            mAdapter.list.addAll(list)
            mAdapter.notifyDataSetChanged()

            mAdapter.bingo = {
                list.clear()
                list.addAll((1..25).shuffled().take(9).map { num(it) })
                mAdapter.list.clear()
                mAdapter.list.addAll(list)
            }
        }
    }

    fun ran() {

    }
}

class MyAdapter : Adapter<MyAdapter.ViewHolder>() {
    // 建立ViewHolder
    inner class ViewHolder(val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root)


    val list = MutableList(9) {
        num(it)
    }

    val hor = MutableList(3) { false }
    val ver = MutableList(3) { false }
    var left = false
    var right = false

    var bingo: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        binding.root.setOnClickListener {
            if (!list[holder.adapterPosition].boolean) {
                binding.txt.setTextColor(Color.BLACK)
                binding.txt.setBackgroundColor(Color.WHITE)
                list[holder.adapterPosition].boolean = true
            } else {
                binding.txt.setTextColor(Color.WHITE)
                binding.txt.setBackgroundColor(Color.BLACK)
                list[holder.adapterPosition].boolean = false
            }

            // hor
            repeat(3) {
                hor[it] = list[it].boolean && list[it + 1].boolean && list[it + 2].boolean
            }

            // ver
            repeat(3) {
                ver[it] = (list[it].boolean && list[it + 3].boolean && list[it + 6].boolean)
            }

            left = (list[0].boolean && list[4].boolean && list[8].boolean)

            right = (list[2].boolean && list[4].boolean && list[6].boolean)

            Log.d("!!!", "$hor  $ver  $left  $right")

            val size = hor.count { it } + ver.count { it } + if (left) 1 else 0 + if (right) 1 else 0
            if (size >= 2) {
                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setTitle("Bingo")
                builder.setMessage("Bingo")
                builder.setPositiveButton("Positive") { _, _ ->
                    // Handle positive button click here
                    bingo()
                }
                builder.setNegativeButton("Negative") { _, _ ->
                    // Handle negative button click here
                    bingo()
                }
                builder.show()
            }

            this@MyAdapter.notifyDataSetChanged()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txt.text = list[position].num.toString()
        if (list[position].boolean) {
            holder.binding.txt.setTextColor(Color.BLACK)
            holder.binding.txt.setBackgroundColor(Color.WHITE)
        } else {
            holder.binding.txt.setTextColor(Color.WHITE)
            holder.binding.txt.setBackgroundColor(Color.BLACK)
        }

        line(holder.binding, position)
    }

    fun line(binding: ItemTextBinding, position: Int) {
        // hor
        if (hor[position / 3]) {
            binding.hor.visibility = View.VISIBLE
        } else {
            binding.hor.visibility = View.GONE
        }

        // ver
        if (ver[position % 3]) {
            binding.ver.visibility = View.VISIBLE
        } else {
            binding.ver.visibility = View.GONE
        }

        if (position == 0 || position == 4 || position == 8) {
            if (left) {
                binding.left.visibility = View.VISIBLE
            } else {
                binding.left.visibility = View.GONE
            }
        }

        if (position == 2 || position == 4 || position == 6) {
            if (right) {
                binding.right.visibility = View.VISIBLE
            } else {
                binding.right.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

data class num(
    var num: Int,
    var boolean: Boolean = false
)