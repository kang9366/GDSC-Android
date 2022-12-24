package com.sgkang.c95

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sgkang.c95.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            when(binding.fab.isExtended){
                true -> binding.fab.shrink()
                false -> binding.fab.extend()
            }
        }
    }
}