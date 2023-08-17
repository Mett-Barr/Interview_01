package com.example.interview01.topic.recyclerview.basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview01.databinding.ActivityRecyclerViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class BasicRvActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private var mAdapter = BasicAdapter()
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
            layoutManager = LinearLayoutManager(this@BasicRvActivity)
            adapter = mAdapter
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            repeat(3) {
                delay(500)
                list.add(it)
                mAdapter.setData(list.toList()) // 這裡改為使用 setData
            }
            Log.d("!!!", "list = ${list.size}")
        }
    }

    private fun initListener() {
        mAdapter.setClick {
            list.removeAt(it)
            mAdapter.setData(list.toList()) // 這裡改為使用 setData
        }

        binding.addBtn.setOnClickListener {
            list.add(Random.nextInt())
            mAdapter.setData(list.toList()) // 這裡改為使用 setData
        }
    }
}