package ru.toxyxd.common.domain.repository

import ru.toxyxd.common.domain.model.parental.AdGuardParentalStatus

interface ParentalRepository {
    suspend fun getParentalStatus(): AdGuardParentalStatus
}