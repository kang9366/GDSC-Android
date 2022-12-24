package com.sgkang.c90

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sgkang.c90.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.visibleBtn.setOnClickListener{
            binding.textView.visibility = View.VISIBLE
        }
        binding.invisibleBtn.setOnClickListener {
            binding.textView.visibility = View.INVISIBLE
        }
    }
}