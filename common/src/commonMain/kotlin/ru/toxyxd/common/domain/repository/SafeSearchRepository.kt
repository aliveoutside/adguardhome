package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.safeSearch.AdGuardSafeSearchStatus

interface SafeSearchRepository {
    suspend fun getSafeSearchStatus(): AdGuardSafeSearchStatus
}