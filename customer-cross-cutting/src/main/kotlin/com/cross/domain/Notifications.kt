package com.cross.domain

data class Notification (val field : String? = null, val notification: String = "") {
    fun hasValue() : Boolean = !notification.isNullOrEmpty()
}

class Notifications () {

    constructor(notification: Notification) : this() {
        addNotification(notification)
    }

    private val _notifications = mutableListOf<Notification>()

    fun addNotification(notification: Notification) {
        if (notification.hasValue()) {
            _notifications.add(notification)
        }
    }

    val notifications: List<Notification>
        get() = _notifications

    fun isNotEmpty() = _notifications.isNotEmpty()

}