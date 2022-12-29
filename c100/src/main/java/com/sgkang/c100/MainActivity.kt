package com.sgkang.c100

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sgkang.c100.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: MyViewModel by viewModels()

        binding.button.setOnClickListener {
            model.calSum().observe(this){
                binding.resultView.text = it
            }
        }

    }
}