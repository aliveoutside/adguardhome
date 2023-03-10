package ru.toxyxd.common.domain.model.global

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdGuardStatus(
    @SerialName("dns_addresses")
    val dnsAddresses: List<String>,
    @SerialName("dns_port")
    val dnsPort: Int,
    @SerialName("http_port")
    val httpPort: Int,
    @SerialName("protection_enabled")
    val protectionEnabled: Boolean,
    @SerialName("dhcp_available")
    val dhcpAvailable: Boolean,
    @SerialName("running")
    val running: Boolean,
    @SerialName("version")
    val version: String,
    @SerialName("language")
    val language: String,
)
