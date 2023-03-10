package ru.toxyxd.common.data.utils

import io.ktor.client.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import ru.toxyxd.common.domain.repository.StorageRepository


class BaseRequestFlow(
    storageRepository: StorageRepository,
    private val httpClient: HttpClient
) {
    private val baseRequestFlow: Flow<Pair<HttpClient, HttpRequestBuilder.() -> Unit>> =
        storageRepository.getSelectedServer().map { server ->
            val requestBuilder: HttpRequestBuilder.() -> Unit = {
                url {
                    protocol = URLProtocol.HTTP
                    host = server.host
                    port = server.port
                    encodedPath = "/control/"
                }
                if (server.password.isNotEmpty()) {
                    val authProvider = BasicAuthProvider({
                        BasicAuthCredentials(
                            server.username, server.password
                        )
                    })
                    let {
                        runBlocking {
                            authProvider.addRequestHeaders(it)
                        }
                    }
                }
            }
            Pair(httpClient, requestBuilder)
        }

    operator fun invoke(): Flow<Pair<HttpClient, HttpRequestBuilder.() -> Unit>> = baseRequestFlow
}