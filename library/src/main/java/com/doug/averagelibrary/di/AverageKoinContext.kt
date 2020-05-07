package com.doug.averagelibrary.di

import android.content.Context
import com.doug.averagelibrary.Average
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication

internal object AverageKoinContext {
    private lateinit var appContext: Context

    val koin: Koin by lazy {
        koinApplication {
            androidContext(appContext)
            modules(listOf(apiModule, retrofitModule, repositoryModule))
        }.koin
    }

    @Synchronized
    fun init(context: Context) {
        if (!::appContext.isInitialized) {
            appContext = context.applicationContext
        }
    }
}
