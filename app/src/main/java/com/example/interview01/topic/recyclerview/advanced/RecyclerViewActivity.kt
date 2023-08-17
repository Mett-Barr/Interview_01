package com.example.interview01.topic.recyclerview.advanced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview01.databinding.ActivityRecyclerViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private var mAdapter = RvAdapter()
    private val list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
        initListener()
    }

    private fun initView() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            adapter = mAdapter
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            repeat (3) {
                delay(500)
                list.add(it)
                mAdapter.submitList(list.toList())
            }

            Log.d("!!!", "list = ${list.size}")
        }
    }

    private fun initListener() {
        mAdapter.setClick {
            list.removeAt(it)
            mAdapter.submitList(list.toList())
        }

        binding.addBtn.setOnClickListener {
            list.add(Random.nextInt())
            mAdapter.submitList(list.toList())
        }
    }
}