package com.infrasoft.infraoffer

import android.app.Application

class InfraOfferApplication : Application() {

    private var appInstance: InfraOfferApplication? = null

    fun getInstance(): InfraOfferApplication? {
        return appInstance
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        RetrofitClient.create()
    }


}