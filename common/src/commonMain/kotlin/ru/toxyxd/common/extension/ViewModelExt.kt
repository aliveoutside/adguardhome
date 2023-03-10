package ru.toxyxd.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.ui.LocalViewModelStoreOwner
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.ViewModelStoreOwner
import moe.tlaster.precompose.viewmodel.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools
import kotlin.reflect.KClass

@Composable
inline fun <reified T : ViewModel> getViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val owner = LocalViewModelStoreOwner.current
    return remember(qualifier, parameters) {
        owner.getViewModel(qualifier, parameters)
    }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null,
): Lazy<T> {
    return lazy(mode) {
        getViewModel<T>(qualifier, parameters)
    }
}

fun <T : ViewModel> ViewModelStoreOwner.viewModel(
    qualifier: Qualifier? = null,
    clazz: KClass<T>,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    parameters: ParametersDefinition? = null,
): Lazy<T> {
    return lazy(mode) { getViewModel(qualifier, clazz, parameters) }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    return getViewModel(qualifier, T::class, parameters)
}

fun <T : ViewModel> ViewModelStoreOwner.getViewModel(
    qualifier: Qualifier? = null,
    clazz: KClass<T>,
    parameters: ParametersDefinition? = null,
): T {
    return this.viewModelStore.getViewModel(
        key = qualifier?.value ?: (clazz.toString() + parameters?.invoke()),
        clazz = clazz
    ) {
        KoinPlatformTools.defaultContext().get().get(clazz, qualifier, parameters)
    }
}