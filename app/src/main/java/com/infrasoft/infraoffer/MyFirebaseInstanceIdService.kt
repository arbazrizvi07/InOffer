package com.infrasoft.infraoffer

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("DeviceToken", "Refreshed token: " + refreshedToken!!)
    }
}