package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.filtering.AdGuardFilteringStatus

interface FilteringRepository {
    suspend fun getFilteringStatus(): AdGuardFilteringStatus
}