package ru.toxyxd.common.data.controller

import java.util.Base64

class ConnectionController {
    private var session = ""

    fun getSession() = session


    /**
     * Set session
     *
     * @param session Base64 encoded session (username:password)
     */
    fun setSession(session: String) {
        this.session = session
    }


    /**
     * Set session
     *
     * @param username
     * @param password
     */
    fun setSession(username: String, password: String) {
        val data = "$username:$password"
        this.session =
            Base64.getEncoder().encodeToString(data.toByteArray())
    }

    fun clearSession() {
        this.session = ""
    }

    fun hasSession() = session.isNotEmpty()
}