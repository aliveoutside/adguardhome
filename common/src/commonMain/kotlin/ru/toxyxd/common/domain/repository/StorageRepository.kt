package ru.toxyxd.common.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.toxyxd.common.domain.model.Server

interface StorageRepository {
    fun getServers(): List<Server>?
    fun addServer(server: Server)
    fun removeServer(server: Server)
    fun removeAllServers()
    fun getSelectedServer(): Flow<Server>
    fun setSelectedServer(server: Server)
}