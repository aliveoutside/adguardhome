package ru.toxyxd.common.feature.clients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import ru.toxyxd.common.domain.usecase.clients.GetClients
import ru.toxyxd.common.uikit.PageViewModel

class ClientsViewModel(
    private val getClients: GetClients
) : PageViewModel<GetClients.ClientsPage>() {
    var isRefreshing by mutableStateOf(false)
        private set

    init {
        reload()
    }

    override suspend fun load(): GetClients.ClientsPage {
        return getClients()
    }

    fun swipeRefreshReload() {
        isRefreshing = true
        viewModelScope.launch {
            setState(load())
            isRefreshing = false
        }
    }
}