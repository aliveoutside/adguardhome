package ru.toxyxd.common.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import ru.toxyxd.Database
import java.io.File

actual class ServersListDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:test.db")
        if (!File("test.db").exists()) {
            Database.Schema.create(driver)
        }
        return driver
    }
}