package ru.toxyxd.common.domain.model.clients

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AdGuardClients(
    @SerialName("auto_clients")
    val autoClients: List<AutoClient>,
    @SerialName("clients")
    val clients: List<Client>? = null,
    @SerialName("supported_tags")
    val supportedTags: List<String>
)

@Serializable
data class AutoClient(
    @SerialName("ip")
    val ip: String,
    @SerialName("name")
    val name: String,
    @SerialName("source")
    val source: String,
    @SerialName("whois_info")
    val whoisInfo: WhoisInfo
)

@Serializable
data class Client(
    @SerialName("blocked_services")
    val blockedServices: List<String>?,
    @SerialName("filtering_enabled")
    val filteringEnabled: Boolean,
    @SerialName("ids")
    val ids: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("parental_enabled")
    val parentalEnabled: Boolean,
    @SerialName("safebrowsing_enabled")
    val safebrowsingEnabled: Boolean,
    @SerialName("safesearch_enabled")
    val safesearchEnabled: Boolean,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("upstreams")
    val upstreams: List<String>,
    @SerialName("use_global_blocked_services")
    val useGlobalBlockedServices: Boolean,
    @SerialName("use_global_settings")
    val useGlobalSettings: Boolean
)

@Serializable
data class WhoisInfo(
    val info: String? = null
)