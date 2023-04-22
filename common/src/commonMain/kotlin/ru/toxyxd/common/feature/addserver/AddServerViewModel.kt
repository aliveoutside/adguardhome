package ru.toxyxd.common.feature.addserver

import moe.tlaster.precompose.viewmodel.ViewModel
import ru.toxyxd.common.database.ServersListDatabase
import ru.toxyxd.common.domain.model.Server

class AddServerViewModel(
    private val database: ServersListDatabase
) : ViewModel() {
    fun addServer(name: String, host: String, port: Int, username: String, password: String) {
        database.insertServer(Server(name, host, port, username, password))
    }
}