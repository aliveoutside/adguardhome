package ru.toxyxd.common.domain.model.logs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryLog(
    @SerialName("oldest")
    val oldest: String,
    @SerialName("data")
    val data: List<Log>,
)