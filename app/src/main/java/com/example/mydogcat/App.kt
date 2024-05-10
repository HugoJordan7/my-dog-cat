package com.example.mydogcat

import android.app.Application
import com.example.mydogcat.di.networkModule
import com.example.mydogcat.di.reducerModule
import com.example.mydogcat.di.repositoryModule
import com.example.mydogcat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    reducerModule
                )
            )
        }
    }
}