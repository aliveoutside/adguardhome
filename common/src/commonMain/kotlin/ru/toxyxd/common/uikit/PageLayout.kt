package ru.toxyxd.common.uikit

import androidx.compose.runtime.Composable

@Composable
fun <T> PageLayout(
    state: PageViewModel.State<T>,
    onReload: () -> Unit,
    successContent: @Composable (data: T) -> Unit
) {
    when (state) {
        is PageViewModel.State.Loading -> FullscreenLoading()
        is PageViewModel.State.Error -> FullscreenError(
            onReload = onReload,
            exception = state.exception
        )

        is PageViewModel.State.Loaded -> successContent(state.data)
    }
}