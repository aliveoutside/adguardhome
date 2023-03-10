package ru.toxyxd.common.domain.model.logs

enum class LogReason {
    NotFilteredNotFound,
    NotFilteredWhiteList,
    NotFilteredError,

    FilteredBlackList,
    FilteredSafeBrowsing,
    FilteredParental,
    FilteredInvalid,
    FilteredSafeSearch,
    FilteredBlockedService,

    Rewrite,
    RewriteEtcHosts,
    RewriteRule
}