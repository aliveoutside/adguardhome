package ru.toxyxd.common.domain.model.safeSearch

import kotlinx.serialization.Serializable

@Serializable
data class AdGuardSafeSearchStatus(
    val enabled: Boolean
)
