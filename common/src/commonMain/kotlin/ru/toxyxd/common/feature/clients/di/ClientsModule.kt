package ru.toxyxd.common.feature.clients.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.extension.viewModel
import ru.toxyxd.common.feature.clients.ClientsViewModel

val clientsModule = module {
    vm()
}

private fun Module.vm() {
    viewModel { ClientsViewModel(get()) }
}