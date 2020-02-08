package com.cross.extensions

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.cross.domain.Notification

fun String.validateSizeSmallerThan(length: Int, message: String, field : String? = null) : Option<List<Notification>> {
    return when {
        this.length > length -> Some(mutableListOf(Notification(notification = message, field = field)))
        else -> None
    }

}

fun String.isNullOrBlank(message: String, field : String? = null) : Option<List<Notification>> {
   return when {
        this.isNullOrBlank() -> Some(mutableListOf(Notification(notification = message, field = field)))
       else -> None
    }
}