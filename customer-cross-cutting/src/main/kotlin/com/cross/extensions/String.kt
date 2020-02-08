package com.cross.extensions

import com.cross.domain.Notification
import com.cross.domain.ValidationResult

fun String.validateSizeSmallerThan(length: Int, message: String, field : String? = null) : ValidationResult<Notification, String> {
    return when {
        this.length > length -> ValidationResult.Failure(Notification(notification = message, field = field))
        else -> ValidationResult.Success(this)
    }

}

fun String.isNullOrBlank(message: String, field : String? = null) : ValidationResult<Notification, String> {
   return when {
        this.isNullOrBlank() -> ValidationResult.Failure(Notification(notification = message, field = field))
       else -> ValidationResult.Success(this)
    }
}