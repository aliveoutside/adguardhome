package ru.toxyxd.common.domain.usecase.server

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ru.toxyxd.common.domain.model.filtering.AdGuardFilteringStatus
import ru.toxyxd.common.domain.model.global.AdGuardStatus
import ru.toxyxd.common.domain.model.parental.AdGuardParentalStatus
import ru.toxyxd.common.domain.model.safeBrowsing.AdGuardSafeBrowsingStatus
import ru.toxyxd.common.domain.model.safeSearch.AdGuardSafeSearchStatus
import ru.toxyxd.common.domain.model.stats.AdGuardStats
import ru.toxyxd.common.domain.repository.*

class GetServerHome(
    private val globalRepository: GlobalRepository,
    private val statsRepository: StatsRepository,
    private val filteringRepository: FilteringRepository,
    private val safeBrowsingRepository: SafeBrowsingRepository,
    private val safeSearchRepository: SafeSearchRepository,
    private val parentalRepository: ParentalRepository
) {
    suspend operator fun invoke(): ServerHomePage = withContext(Dispatchers.IO) {
        val status = async { globalRepository.getStatus() }
        val stats = async { statsRepository.getStats() }
        val filteringStatus = async { filteringRepository.getFilteringStatus() }
        val safeSearchStatus = async { safeSearchRepository.getSafeSearchStatus() }
        val parentalStatus = async { parentalRepository.getParentalStatus() }
        val safeBrowsingStatus = async { safeBrowsingRepository.getSafeBrowsingStatus() }

        ServerHomePage(
            status = status.await(),
            stats = stats.await(),
            filteringStatus = filteringStatus.await(),
            safeBrowsingStatus = safeBrowsingStatus.await(),
            safeSearchStatus = safeSearchStatus.await(),
            parentalStatus = parentalStatus.await()
        )
    }

    data class ServerHomePage(
        val status: AdGuardStatus,
        val stats: AdGuardStats,
        val filteringStatus: AdGuardFilteringStatus,
        val safeBrowsingStatus: AdGuardSafeBrowsingStatus,
        val safeSearchStatus: AdGuardSafeSearchStatus,
        val parentalStatus: AdGuardParentalStatus
    )
}