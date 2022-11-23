package com.sgkang.ch15

import android.app.RemoteInput
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        receiveInput()
    }

    private fun receiveInput(){
        val KEY_REPLY = "key_text_reply"
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        remoteInput?.let {
            val text = it.getCharSequence(KEY_REPLY).toString()
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }
    }
}