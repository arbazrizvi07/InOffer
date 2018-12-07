package com.infrasoft.infraoffer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RegistrationResponse {

    @SerializedName("data")
    @Expose
    var data: List<CustomerID>? = null
    @SerializedName("action")
    @Expose
    var action: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

}