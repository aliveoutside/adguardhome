package ru.toxyxd.common.di

import org.koin.core.KoinApplication
import ru.toxyxd.common.data.di.dataModule
import ru.toxyxd.common.database.di.databaseModule
import ru.toxyxd.common.feature.addserver.di.addServerModule
import ru.toxyxd.common.feature.clients.di.clientsModule
import ru.toxyxd.common.feature.home.di.homeModule
import ru.toxyxd.common.feature.logs.di.logsModule
import ru.toxyxd.common.feature.server.di.serverModule

fun KoinApplication.setupCommonModules() {
    modules(addServerModule)
    modules(dataModule)
    modules(databaseModule())
    modules(clientsModule)
    modules(homeModule)
    modules(logsModule)
    modules(serverModule)
}