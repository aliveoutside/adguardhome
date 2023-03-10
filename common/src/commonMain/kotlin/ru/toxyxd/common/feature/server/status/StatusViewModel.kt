package ru.toxyxd.common.feature.server.status

import ru.toxyxd.common.domain.usecase.server.GetServerHome
import ru.toxyxd.common.uikit.PageViewModel

class StatusViewModel(
    private val getServerHome: GetServerHome
) : PageViewModel<GetServerHome.ServerHomePage>() {

    init {
        reload()
    }

    override suspend fun load(): GetServerHome.ServerHomePage {
        return getServerHome()
    }
}