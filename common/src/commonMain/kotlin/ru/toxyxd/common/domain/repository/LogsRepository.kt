package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.logs.QueryLog

interface LogsRepository {
    suspend fun getLogs(
        olderThan: String? = null,
        offset: Int? = 0,
        limit: Int? = 50,
        search: String? = null,
        responseStatus: ResponseStatus = ResponseStatus.ALL,
    ): QueryLog

    enum class ResponseStatus {
        ALL,
        FILTERED,
        BLOCKED,
        BLOCKED_SAFEBROWSING,
        BLOCKED_PARENTAL,
        WHITELISTED,
        REWRITTEN,
        SAFE_SEARCH,
        PROCESSED
    }
}