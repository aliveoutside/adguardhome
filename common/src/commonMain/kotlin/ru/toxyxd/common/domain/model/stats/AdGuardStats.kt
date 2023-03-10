package ru.toxyxd.common.domain.model.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdGuardStats(
    @SerialName("avg_processing_time")
    val avgProcessingTime: Double,
    @SerialName("blocked_filtering")
    val blockedFiltering: List<Int>,
    @SerialName("dns_queries")
    val dnsQueries: List<Int>,
    @SerialName("num_blocked_filtering")
    val numBlockedFiltering: Int,
    @SerialName("num_dns_queries")
    val numDnsQueries: Int,
    @SerialName("num_replaced_parental")
    val numReplacedParental: Int,
    @SerialName("num_replaced_safebrowsing")
    val numReplacedSafebrowsing: Int,
    @SerialName("num_replaced_safesearch")
    val numReplacedSafesearch: Int,
    @SerialName("replaced_parental")
    val replacedParental: List<Int>,
    @SerialName("replaced_safebrowsing")
    val replacedSafebrowsing: List<Int>,
    @SerialName("time_units")
    val timeUnits: String,
    @SerialName("top_blocked_domains")
    val topBlockedDomains: List<Map<String, Int>>,
    @SerialName("top_clients")
    val topClients: List<Map<String, Int>>,
    @SerialName("top_queried_domains")
    val topQueriedDomains: List<Map<String, Int>>
)

