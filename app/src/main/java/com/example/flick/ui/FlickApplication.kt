package com.example.flick.ui

import android.app.Application
import com.example.cache.di.cacheModule
import com.example.domain.di.domainModule
import com.example.flick.ui.di.presentationModule
import com.example.remote.di.networkModule
import com.example.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlickApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FlickApplication)
            modules(
                listOf(
                    domainModule, networkModule, cacheModule, presentationModule,
                    repositoryModule
                )
            )
        }
    }
}
