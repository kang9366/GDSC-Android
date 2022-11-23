package com.sgkang.ch15

import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            Toast.makeText(this, "button is clicked", Toast.LENGTH_LONG).show()

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelID = "id"
                val channelName = "name"
                val channel = NotificationChannel(
                    channelID,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.description = "description"
                channel.setShowBadge(true)
                manager.createNotificationChannel(channel)
                builder = NotificationCompat.Builder(this, channelID)
            }else{
                builder = NotificationCompat.Builder(this)
            }

            val text = "안녕하세요"
            val titleText = "홍길동"
            val title: Spannable = SpannableString(titleText).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, titleText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            val KEY_TEXT_REPLY = "key_text_reply"
            val replyLabel = "답장"

            var remoteInput: androidx.core.app.RemoteInput = androidx.core.app.RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            val replyIntent = Intent(this, Receiver::class.java)
            val replyPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            builder.apply {
                setSmallIcon(R.drawable.small_icon)
                setWhen(System.currentTimeMillis())
                setContentText(text)
                setContentTitle(title)
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))

                addAction(
                    NotificationCompat.Action.Builder(
                        android.R.drawable.stat_notify_more,
                        replyLabel,
                        replyPendingIntent,
                    ).addRemoteInput(remoteInput).build()
                )
            }
            manager.notify(1, builder.build())
        }
    }
}