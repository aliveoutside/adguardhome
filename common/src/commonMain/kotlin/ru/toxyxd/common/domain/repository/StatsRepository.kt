package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.stats.AdGuardStats

interface StatsRepository {
    suspend fun getStats(): AdGuardStats
}