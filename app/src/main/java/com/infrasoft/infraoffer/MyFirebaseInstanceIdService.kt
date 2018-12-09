package com.infrasoft.infraoffer

import android.content.Intent
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import android.support.v4.content.LocalBroadcastManager




class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onCreate() {
        super.onCreate()

    }
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        var intent = Intent("Custom_action")
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("DeviceToken", "Refreshed token: " + refreshedToken!!)
        intent.putExtra("token", refreshedToken)
        localBroadcastManager.sendBroadcast(intent)
    }
}