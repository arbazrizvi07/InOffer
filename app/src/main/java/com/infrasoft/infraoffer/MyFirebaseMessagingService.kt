package com.infrasoft.infraoffer

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


class MyFirebaseMessagingService : FirebaseMessagingService() {

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
            .setContentText(data.get("discription"))

        val notificationIntent = Intent(this, HomeActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)
        // Add as notification
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

    private fun storeNotificationData(data: MutableMap<String, String>) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .add(data as MutableMap<String, Any>)
            .addOnSuccessListener { documentReference ->
                Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.id)
            }
            .addOnFailureListener { e -> Log.w("Failed", "Error adding document", e) }
    }
}