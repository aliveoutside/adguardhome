package ru.toxyxd.common.database

import ru.toxyxd.Database
import ru.toxyxd.common.domain.model.Server

class ServersListDatabase(
    factory: ServersListDriverFactory
) {
    private val database = Database(factory.createDriver())
    private val serversQueries = database.serverQueries

    fun getServers() = serversQueries.selectAll(::mapServer).executeAsList()

    fun insertServer(server: Server) = serversQueries.insert(
        name = server.name,
        host = server.host,
        port = server.port.toLong(),
        username = server.username,
        password = server.password,
    )
    fun deleteServer(server: Server) = serversQueries.delete(
        name = server.name,
    )
    fun deleteAll() = serversQueries.deleteAll()

    private fun mapServer(
        name: String,
        host: String,
        port: Long,
        username: String,
        password: String,
    ) = Server(
        name = name,
        host = host,
        port = port.toInt(),
        username = username,
        password = password,
    )
}