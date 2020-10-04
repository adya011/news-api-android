package com.nanda.archmvi2

import android.app.Application
import com.nanda.archmvi2.data.di.Modules
import org.koin.android.ext.android.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, Modules.getAll())
    }
}