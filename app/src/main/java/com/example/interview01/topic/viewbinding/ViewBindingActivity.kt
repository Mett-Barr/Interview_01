package com.example.interview01.topic.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interview01.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBindingBinding

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    private fun initView() {
        binding.textView.text = num.toString()
    }

    private fun initListener() {
        binding.button.setOnClickListener {
            num++
            binding.textView.text = num.toString()
        }
    }
}