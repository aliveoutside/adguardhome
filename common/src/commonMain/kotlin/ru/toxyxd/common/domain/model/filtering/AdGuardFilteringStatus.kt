package ru.toxyxd.common.domain.model.filtering

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AdGuardFilteringStatus(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("filters")
    val filters: List<Filter>,
    @SerialName("interval")
    val interval: Int,
    @SerialName("user_rules")
    val userRules: List<String>? = null,
    @SerialName("whitelist_filters")
    val whitelistFilters: List<WhitelistFilters>? = null,
)

@Serializable
data class Filter(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("rules_count")
    val rulesCount: Int,
    @SerialName("url")
    val url: String
)

@Serializable
data class WhitelistFilters(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("name")
    val name: String,
    @SerialName("rules_count")
    val rulesCount: Int,
    @SerialName("url")
    val url: String
)