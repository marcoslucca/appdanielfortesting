package com.cross.domain

sealed class ResultEntity<out L, out R> {
    class Failure(val notifications: List<Notification>) : ResultEntity<List<Notification>, Nothing>()
    class Success<T : Entity>(val success: T) : ResultEntity<Nothing, T>()
}

open abstract class Entity {

    abstract fun validate() : List<ValidationResult<Notification, Any>>

    val notifications : List<Notification>
        get() = ValidationResult.validate(validate())

    fun hasNotification() : Boolean {
        return ValidationResult.hasNotification(validate())
    }

}