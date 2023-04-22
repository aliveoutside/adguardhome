package ru.toxyxd.common.database.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.database.ServersListDatabase
import ru.toxyxd.common.database.ServersListDriverFactory

actual fun databaseModule(): Module = module {
    single {
        ServersListDatabase(ServersListDriverFactory(androidContext()))
    }
}