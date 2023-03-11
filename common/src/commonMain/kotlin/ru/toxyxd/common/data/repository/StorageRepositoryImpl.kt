package ru.toxyxd.common.data.repository

import kotlinx.coroutines.flow.flow
import ru.toxyxd.common.domain.model.Server
import ru.toxyxd.common.domain.repository.StorageRepository


class StorageRepositoryImpl: StorageRepository {
    //private var serversDelegate: ArrayList<Server>? by context.sharedPrefs("servers")
    private var selectedServerDelegate: Server? = null

    override fun getServers(): ArrayList<Server> {
        throw UnsupportedOperationException()
    }

    override fun getFakeServers() = arrayListOf(
        Server(
            "test",
            "cock.toxyxd.keenetic.link",
            443,
            "toxyxd",
            "admin"
        )
    )

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