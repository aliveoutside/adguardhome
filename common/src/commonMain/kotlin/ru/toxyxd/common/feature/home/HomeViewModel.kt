package ru.toxyxd.common.feature.home

import ru.toxyxd.common.domain.model.Server
import ru.toxyxd.common.domain.repository.StorageRepository
import ru.toxyxd.common.uikit.PageViewModel

class HomeViewModel(
    private val repository: StorageRepository
) : PageViewModel<List<Server>>() {
    init { reload() }
    override suspend fun load(): List<Server> {
        return repository.getFakeServers()
    }

    fun setServer() {
        repository.setSelectedServer(
            repository.getFakeServers().first()
        )
    }
}