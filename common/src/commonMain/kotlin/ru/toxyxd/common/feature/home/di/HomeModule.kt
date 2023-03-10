package ru.toxyxd.common.feature.home.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.toxyxd.common.extension.viewModel
import ru.toxyxd.common.feature.home.HomeViewModel

val homeModule = module {
    vm()
}

private fun Module.vm() {
    viewModel { HomeViewModel(get()) }
}