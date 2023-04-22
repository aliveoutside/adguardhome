package ru.toxyxd.common.data.repository

import kotlinx.coroutines.flow.flow
import ru.toxyxd.common.database.ServersListDatabase
import ru.toxyxd.common.domain.model.Server
import ru.toxyxd.common.domain.repository.StorageRepository


class StorageRepositoryImpl(
    private val database: ServersListDatabase
): StorageRepository {
    //private var serversDelegate: ArrayList<Server>? by context.sharedPrefs("servers")
    private var selectedServerDelegate: Server? = null

    override fun getServers(): List<Server> {
        return database.getServers()
    }

    override fun addServer(server: Server) {
        database.insertServer(server)
    }

    override fun removeServer(server: Server) {
        database.deleteServer(server)
    }

    override fun removeAllServers() {
        database.deleteAll()
    }

    override fun getSelectedServer() =
        flow {
            emit(
                selectedServerDelegate ?: throw IllegalArgumentException("Selected server is null")
            )
        }

    override fun setSelectedServer(server: Server) {
        selectedServerDelegate = server
    }
}