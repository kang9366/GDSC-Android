package com.sgkang.ch10

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val KEY_TEXT_REPLY = "key_text_reply"
        val remoteInput = RemoteInput.getResultsFromIntent(p1)
        val replyMessage = remoteInput.getCharSequence(KEY_TEXT_REPLY).toString()
        Toast.makeText(p0, replyMessage, Toast.LENGTH_LONG).show()
    }
}