package com.nanda.axiataassignment

import android.app.Application
import com.nanda.axiataassignment.data.di.Modules
import org.koin.android.ext.android.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, Modules.getAll())
    }
}