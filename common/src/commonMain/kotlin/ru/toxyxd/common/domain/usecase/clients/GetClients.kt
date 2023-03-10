package ru.toxyxd.common.domain.usecase.clients

import ru.toxyxd.common.domain.model.clients.AdGuardClients
import ru.toxyxd.common.domain.repository.ClientsRepository

class GetClients(
    private val clientsRepository: ClientsRepository
) {
    suspend operator fun invoke(): ClientsPage {
        return ClientsPage(
            clients = clientsRepository.getClients()
        )
    }

    data class ClientsPage(
        val clients: AdGuardClients
    )
}