package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.safeBrowsing.AdGuardSafeBrowsingStatus

interface SafeBrowsingRepository {
    suspend fun getSafeBrowsingStatus(): AdGuardSafeBrowsingStatus
}