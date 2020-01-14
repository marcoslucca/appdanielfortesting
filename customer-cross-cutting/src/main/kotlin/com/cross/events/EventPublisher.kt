package com.cross.events

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class EventPublisher (val eventPublisher: ApplicationEventPublisher) {

    fun publisher(any: Any) {
        eventPublisher.publishEvent(any)
    }

}