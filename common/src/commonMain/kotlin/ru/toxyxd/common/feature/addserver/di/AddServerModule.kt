package ru.toxyxd.common.feature.addserver.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.extension.viewModel
import ru.toxyxd.common.feature.addserver.AddServerViewModel

val addServerModule = module {
    vm()
}
private fun Module.vm() {
    viewModel { AddServerViewModel(get()) }
}