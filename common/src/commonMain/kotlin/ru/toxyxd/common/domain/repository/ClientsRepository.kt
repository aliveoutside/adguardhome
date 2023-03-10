package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.clients.AdGuardClients

interface ClientsRepository {
    suspend fun getClients(): AdGuardClients
}