package ru.toxyxd.common.data.repository

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.toxyxd.common.data.utils.BaseRequestFlow
import ru.toxyxd.common.domain.model.global.AdGuardStatus
import ru.toxyxd.common.domain.repository.GlobalRepository


class GlobalRepositoryImpl(
    private val baseRequestFlow: BaseRequestFlow
) : GlobalRepository {
    override suspend fun getStatus(): AdGuardStatus = withContext(Dispatchers.IO) {
        baseRequestFlow().first().let { (client, requestBuilder) ->
            client.get {
                requestBuilder(this)
                url {
                    it.appendPathSegments("status")
                }
            }.body()
        }
    }
}