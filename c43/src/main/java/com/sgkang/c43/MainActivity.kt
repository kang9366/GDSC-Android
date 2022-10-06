package com.sgkang.c43

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.action = "ACTION_DETAIL"
            intent.data = Uri.parse("http://google.com")
            startActivity(intent)
        }
    }
}