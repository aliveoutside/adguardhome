package ru.toxyxd.common.domain.model.logs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QueryLogInfo(
    @SerialName("anonymize_client_ip")
    val anonymizeClientIp: Boolean,
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("interval")
    val interval: Double
)
