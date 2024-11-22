package com.issog.capstonemadeone.core.utils.base

import android.app.Application
import com.issog.capstonemadeone.core.di.databaseModule
import com.issog.capstonemadeone.core.di.networkModule
import com.issog.capstonemadeone.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

abstract class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule
            )
            addModule(this)
        }
    }

    open fun addModule(koin: KoinApplication) {}
}