package ru.toxyxd.common.data.di

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.data.repository.*
import ru.toxyxd.common.data.utils.BaseRequestFlow
import ru.toxyxd.common.domain.repository.*
import ru.toxyxd.common.domain.usecase.clients.GetClients
import ru.toxyxd.common.domain.usecase.server.GetServerHome

val dataModule = module {
    network()
    baseFlow()

    factory()
    repository()
}


private fun Module.baseFlow() {
    single {
        BaseRequestFlow(get(), get())
    }
}

private fun Module.factory() {
    factory { GetServerHome(get(), get(), get(), get(), get(), get()) }
    factory { GetClients(get()) }
}

private fun Module.network() {
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}

private fun Module.repository() {
    single<ClientsRepository> { ClientsRepositoryImpl(get()) }
    single<FilteringRepository> { FilteringRepositoryImpl(get()) }
    single<GlobalRepository> { GlobalRepositoryImpl(get()) }
    single<LogsRepository> { LogsRepositoryImpl(get()) }
    single<ParentalRepository> { ParentalRepositoryImpl(get()) }
    single<SafeBrowsingRepository> { SafeBrowsingRepositoryImpl(get()) }
    single<SafeSearchRepository> {SafeSearchRepositoryImpl(get())}
    single<StatsRepository> { StatsRepositoryImpl(get()) }
    single<StorageRepository> { StorageRepositoryImpl(get()) }
}