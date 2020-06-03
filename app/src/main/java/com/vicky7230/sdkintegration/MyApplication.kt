package com.vicky7230.sdkintegration

import android.app.Application
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.Logger
import com.moengage.core.MoEngage
import com.moengage.core.model.AppStatus
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val moEngage = MoEngage.Builder(this, "V785NC055OGG9CER7Y3Q80MA")
            .setNotificationSmallIcon(R.mipmap.ic_launcher)
            .setNotificationLargeIcon(R.mipmap.ic_launcher)
            .setLogLevel(Logger.VERBOSE)
            .optOutTokenRegistration()
            .build()

        MoEngage.initialise(moEngage)

        ///For Fresh Install of App
        MoEHelper.getInstance(applicationContext).setAppStatus(AppStatus.INSTALL)
    }
}