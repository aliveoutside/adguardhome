package ru.toxyxd.common.feature.server.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.extension.viewModel
import ru.toxyxd.common.feature.server.status.StatusViewModel

var serverModule = module {
    vm()
}

private fun Module.vm() {
    viewModel { StatusViewModel(get()) }
}