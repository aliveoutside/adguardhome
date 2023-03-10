package ru.toxyxd.common.feature.logs

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import moe.tlaster.precompose.viewmodel.viewModelScope
import ru.toxyxd.common.data.pagingsource.LogsPagingSource
import ru.toxyxd.common.domain.model.logs.Log
import ru.toxyxd.common.domain.repository.LogsRepository
import ru.toxyxd.common.uikit.PageViewModel

class LogsViewModel(
    private val logsRepository: LogsRepository
) : PageViewModel<Flow<PagingData<Log>>>() {
    init {
        reload()
    }

    override suspend fun load(): Flow<PagingData<Log>> =
        Pager(
            pagingSourceFactory = { LogsPagingSource(logsRepository) },
            config = PagingConfig(pageSize = 20)
        ).flow.cachedIn(viewModelScope)
}