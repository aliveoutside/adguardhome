package ru.toxyxd.common.extension

import moe.tlaster.precompose.viewmodel.ViewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.instance.newInstance
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

inline fun <reified T : ViewModel> Module.viewModel(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T> {
    return factory(qualifier, definition)
}

@KoinReflectAPI
inline fun <reified T : ViewModel> Module.viewModel(
    qualifier: Qualifier? = null
): KoinDefinition<T> {
    return factory(qualifier) { newInstance(it) }
}