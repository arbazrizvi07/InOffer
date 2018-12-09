package com.infrasoft.infraoffer

import android.app.Application
import android.support.multidex.MultiDexApplication

class InfraOfferApplication : MultiDexApplication() {

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