package ru.toxyxd.common

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.toxyxd.common.di.setupModules

abstract class AdGuardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AdGuardApplication)
            setupModules()
        }
    }
}