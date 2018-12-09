package com.infrasoft.infraoffer

import android.app.Notification
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.graphics.Color


class MyFirebaseMessagingService : FirebaseMessagingService() {

    val NOTIFICATION_CHANNEL_ID = "10001"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d("Notification", "Message data payload: " + remoteMessage.data)
            handleNow(remoteMessage.data)
        }

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d("Notification", "Message Notification Body: ${it.body}")
        }
    }

    private fun handleNow(data: MutableMap<String, String>) {
        storeNotificationData(data)

        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(data.get("dealname"))
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
            .setContentText(data.get("discription"))

        val notificationIntent = Intent(this, HomeActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)
        // Add as notification
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            assert(manager != null)
            builder.setChannelId(NOTIFICATION_CHANNEL_ID)
            manager.createNotificationChannel(notificationChannel)
        }
        assert(manager != null)
        manager.notify(0 /* Request Code */, builder.build())
    }

    private fun storeNotificationData(data: MutableMap<String, String>) {
        val db = FirebaseFirestore.getInstance()
        db.collection("notification")
            .add(data as MutableMap<String, Any>)
            .addOnSuccessListener { documentReference ->
                Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.id)
            }
            .addOnFailureListener { e -> Log.w("Failed", "Error adding document", e) }
    }
}