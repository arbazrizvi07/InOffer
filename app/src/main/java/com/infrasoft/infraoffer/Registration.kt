package com.infrasoft.infraoffer

import com.google.gson.annotations.SerializedName


class Registration {
    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("notificationId")
    var notificationId: String? = null
    @SerializedName("latitude")
    var latitude: Double? = null
    @SerializedName("longitute")
    var longitute: Double? = null
}