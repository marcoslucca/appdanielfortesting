package com.cross.domain

sealed class ResultEntity<out L, out R> {
    class Failure(val notifications: Notifications) : ResultEntity<Notifications, Nothing>()
    class Success<T : Entity>(val success: T) : ResultEntity<Nothing, T>()
}

open abstract class Entity {

    private val _notifications = Notifications()

    val notifications: Notifications
        get() = _notifications

    abstract fun validate()

    protected  fun addNotification(notification: Notification) {
        if (notification.hasValue()) {
            _notifications.addNotification(notification)
        }
    }

    fun hasNotifications() : Boolean = _notifications.isNotEmpty()

}