package com.cross.events

import com.cross.domain.Notification
import com.cross.domain.Notifications

data class DomainInvalidEvent(val notifications : Notifications) {

    constructor(notification: Notification) : this(Notifications(notification))

}