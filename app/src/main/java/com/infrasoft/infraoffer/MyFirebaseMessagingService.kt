package com.infrasoft.infraoffer

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

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
        Log.d("Notification", "Message data payload: ")
    }
}