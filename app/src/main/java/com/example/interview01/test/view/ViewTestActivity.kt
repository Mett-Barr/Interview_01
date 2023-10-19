package com.example.interview01.test.view

import android.graphics.Canvas
import android.graphics.SurfaceTexture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.TextureView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.interview01.R
import com.example.interview01.databinding.ActivityViewTestBinding
import java.util.LinkedList

class ViewTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewTestBinding
    private val eventLog = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textureViewTest()
    }


    private fun textureViewTest() {
        binding.myTextureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                ContextCompat.getDrawable(this@ViewTestActivity,  android.R.drawable.sym_def_app_icon).let {
                    val canvas: Canvas? = binding.myTextureView.lockCanvas(null)
                    it?.setBounds(0, 0, width, height)
                    canvas?.let { it1 -> it?.draw(it1) }
                    canvas?.let { it1 -> binding.myTextureView.unlockCanvasAndPost(it1) }
                }
                updateEventLog("SurfaceTexture available!")
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
                updateEventLog("SurfaceTexture size changed!")
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                updateEventLog("SurfaceTexture updated!")
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                updateEventLog("SurfaceTexture destroyed!")
                return true
            }
        }

        binding.myTextureView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    updateEventLog("TextureView touched down!")
                    return@setOnTouchListener false
                }
                MotionEvent.ACTION_UP -> {
                    updateEventLog("TextureView touched up!")
                    binding.myTextureView.performClick()
                    return@setOnTouchListener false
                }
            }
            return@setOnTouchListener false
        }

        binding.myTextureView.setOnClickListener {
            updateEventLog("TextureView OnClick!")
        }
    }

    private fun updateEventLog(message: String) {
        if (eventLog.size >= 7) { // 限制事件紀錄的數量為七個
            eventLog.poll()
        }
        eventLog.offer(message)
        binding.eventLogTextview.text = eventLog.joinToString("\n")
    }
}