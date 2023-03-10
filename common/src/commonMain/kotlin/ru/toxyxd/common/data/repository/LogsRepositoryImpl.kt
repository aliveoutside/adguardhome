package ru.toxyxd.common.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.toxyxd.common.data.utils.BaseRequestFlow
import ru.toxyxd.common.domain.model.logs.QueryLog
import ru.toxyxd.common.domain.repository.LogsRepository


class LogsRepositoryImpl(
    private val requestFlow: BaseRequestFlow
) : LogsRepository {
    override suspend fun getLogs(
        olderThan: String?,
        offset: Int?,
        limit: Int?,
        search: String?,
        responseStatus: LogsRepository.ResponseStatus
    ): QueryLog = withContext(Dispatchers.IO) {
        requestFlow().first().let { (client, requestBuilder) ->
            client.get {
                requestBuilder(this)
                url {
                    it.appendPathSegments("querylog")
                    if (olderThan != null) {
                        it.encodedParameters.append("older_than", olderThan)
                    }
                    if (offset != null) {
                        it.encodedParameters.append("offset", offset.toString())
                    }
                    if (limit != null) {
                        it.encodedParameters.append("limit", limit.toString())
                    }
                    if (search != null) {
                        it.encodedParameters.append("search", search)
                    }
                    it.encodedParameters.append("response_status", responseStatus.name.lowercase())
                }
            }.body()
        }
    }
}