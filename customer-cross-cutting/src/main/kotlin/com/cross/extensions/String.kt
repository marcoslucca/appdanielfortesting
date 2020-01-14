package com.cross.extensions

fun String.validateSizeSmallerThan(length: Int, message: String) : String {
    if (this.length > length) {
        return message
    }
    return ""
}

fun String.isNullOrBlank(message: String) : String {
    if (this.isNullOrBlank()) {
        return message
    }
    return ""
}