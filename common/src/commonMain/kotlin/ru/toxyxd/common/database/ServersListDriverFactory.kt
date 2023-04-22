package ru.toxyxd.common.database

import app.cash.sqldelight.db.SqlDriver

expect class ServersListDriverFactory {
    fun createDriver(): SqlDriver
}