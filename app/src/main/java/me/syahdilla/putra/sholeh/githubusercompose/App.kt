package me.syahdilla.putra.sholeh.githubusercompose

import android.app.Application
import me.syahdilla.putra.sholeh.githubusercompose.di.mainFeatures
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@App)
            loadKoinModules(mainFeatures)
        }
    }

}