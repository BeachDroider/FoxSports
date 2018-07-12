package com.example.foad.foxsports

import android.app.Application
import com.example.foad.foxsports.di.AppComponent
import com.example.foad.foxsports.di.DaggerAppComponent

class FoxSportsApplication : Application() {

    var appComponent : AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()

    }

}