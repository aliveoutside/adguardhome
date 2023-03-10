package ru.toxyxd.common.di

import org.koin.core.KoinApplication
import ru.toxyxd.common.data.di.dataModule
import ru.toxyxd.common.feature.clients.di.clientsModule
import ru.toxyxd.common.feature.home.di.homeModule
import ru.toxyxd.common.feature.logs.di.logsModule
import ru.toxyxd.common.feature.server.di.serverModule

fun KoinApplication.setupModules() {
    modules(dataModule)
    modules(clientsModule)
    modules(homeModule)
    modules(logsModule)
    modules(serverModule)
}