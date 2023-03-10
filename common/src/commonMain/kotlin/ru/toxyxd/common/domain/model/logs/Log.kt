package ru.toxyxd.common.domain.model.logs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Log(
    @SerialName("answer")
    val answer: List<Answer>? = null,
    @SerialName("answer_dnssec")
    val answerDnssec: Boolean,
    @SerialName("cached")
    val cached: Boolean,
    @SerialName("client")
    val client: String,
    @SerialName("client_id")
    val clientId: String? = null,
    @SerialName("client_info")
    val clientInfo: ClientInfo,
    @SerialName("client_proto")
    val clientProto: String,
    @SerialName("ecs")
    val ecs: String? = null,
    @SerialName("elapsedMs")
    val elapsedMs: String,
    @Deprecated("Use rules[*].filter_list_id instead.")
    @SerialName("filterId")
    val filterId: Int? = null,
    @SerialName("original_answer")
    val originalAnswer: List<OriginalAnswer>? = null,
    @SerialName("question")
    val question: Question,
    @SerialName("reason")
    val reason: LogReason,
    @SerialName("rule")
    @Deprecated("Use rules[*].text instead.")
    val rule: String? = null,
    @SerialName("rules")
    val rules: List<Rule>,
    @SerialName("service_name")
    val serviceName: String? = null,
    @SerialName("status")
    val status: String,
    @SerialName("time")
    val time: String,
    @SerialName("upstream")
    val upstream: String
)

@Serializable
data class Answer(
    @SerialName("ttl")
    val ttl: Int,
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: String
)

@Serializable
data class ClientInfo(
    @SerialName("disallowed")
    val disallowed: Boolean,
    @SerialName("disallowed_rule")
    val disallowedRule: String,
    @SerialName("name")
    val name: String,
    @SerialName("whois")
    val whois: Whois
)

@Serializable
data class OriginalAnswer(
    @SerialName("ttl")
    val ttl: Int,
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: String
)

@Serializable
data class Question(
    @SerialName("class")
    val classX: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("unicode_name")
    val unicodeName: String? = null
)

@Serializable
data class Rule(
    @SerialName("filter_list_id")
    val filterListId: Int,
    @SerialName("text")
    val text: String
)

@Serializable
data class Whois(
    @SerialName("city")
    val city: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("orgname")
    val orgname: String? = null
)