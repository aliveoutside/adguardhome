package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.global.AdGuardStatus

interface GlobalRepository {
    suspend fun getStatus(): AdGuardStatus
}