package ru.toxyxd.common.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.toxyxd.common.domain.model.Server

interface StorageRepository {
    fun getServers(): List<Server>
    fun getFakeServers(): List<Server>
    fun getSelectedServer(): Flow<Server>
    fun setSelectedServer(server: Server)
}