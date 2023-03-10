package ru.toxyxd.common.feature.logs.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.extension.viewModel
import ru.toxyxd.common.feature.logs.LogsViewModel

val logsModule = module {
    vm()
}

private fun Module.vm() {
    viewModel { LogsViewModel(get()) }
}