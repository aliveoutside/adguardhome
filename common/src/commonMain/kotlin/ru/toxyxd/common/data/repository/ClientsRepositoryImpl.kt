package ru.toxyxd.common.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.toxyxd.common.data.utils.BaseRequestFlow
import ru.toxyxd.common.domain.model.clients.AdGuardClients
import ru.toxyxd.common.domain.repository.ClientsRepository

class ClientsRepositoryImpl(
    private val baseRequestFlow: BaseRequestFlow
) : ClientsRepository {
    override suspend fun getClients(): AdGuardClients = withContext(Dispatchers.IO) {
        baseRequestFlow().first().let { (client, requestBuilder) ->
            client.get {
                requestBuilder(this)
                url {
                    it.appendPathSegments("clients")
                }
            }.body()
        }
    }
}