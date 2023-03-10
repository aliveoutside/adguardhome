package ru.toxyxd.common.data.repository

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.toxyxd.common.data.utils.BaseRequestFlow
import ru.toxyxd.common.domain.model.safeSearch.AdGuardSafeSearchStatus
import ru.toxyxd.common.domain.repository.SafeSearchRepository

class SafeSearchRepositoryImpl(
    private val baseRequestFlow: BaseRequestFlow
) : SafeSearchRepository {
    override suspend fun getSafeSearchStatus(): AdGuardSafeSearchStatus =
        withContext(Dispatchers.IO) {
            baseRequestFlow().first().let { (client, requestBuilder) ->
                client.get {
                    requestBuilder(this)
                    url {
                        it.appendPathSegments("safesearch/status")
                    }
                }.body()
            }
        }
}