package com.cross.domain

import arrow.core.Option

sealed class ResultEntity<out L, out R> {
    class Failure(val notifications: List<Notification>) : ResultEntity<List<Notification>, Nothing>()
    class Success<T : Entity>(val success: T) : ResultEntity<Nothing, T>()
}

open abstract class Entity {

    abstract fun validate(): Option<List<Notification>>

    val notifications: Option<List<Notification>>
        get() = validate()

    fun hasNotification() = validate().isDefined()

}