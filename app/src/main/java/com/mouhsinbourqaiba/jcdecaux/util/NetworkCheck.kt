package com.mouhsinbourqaiba.jcdecaux.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.mouhsinbourqaiba.jcdecaux.BuildConfig
import timber.log.Timber

abstract class NetworkCheck : Application() {
    private var instance: NetworkCheck? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.i("Creating our Application")
    }

    open fun getInstance(): NetworkCheck? {
        return instance
    }

    open fun hasNetwork(): Boolean {
        return instance!!.checkIfHasNetwork()
    }

    open fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

