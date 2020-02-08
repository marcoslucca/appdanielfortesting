package com.cross.domain

data class Notification(val field: String? = null, val notification: String = "")

sealed class ValidationResult<out L, out R> {

    companion object {
        fun <T> validate(validations: List<ValidationResult<Notification, T>>) =
                validations
                        .filter { it is ValidationResult.Failure }
                        .map { it as ValidationResult.Failure }
                        .map { it.notification }

        fun <T> hasNotification(validations: List<ValidationResult<Notification, T>>) =
                validations
                        .filter { it is ValidationResult.Failure }
                        .isEmpty()
    }

    class Failure(val notification: Notification) : ValidationResult<Notification, Nothing>()
    class Success<T>(val success: T) : ValidationResult<Nothing, T>()
}