package com.cross.commands

import com.cross.events.EventPublisher
import com.cross.logs.LoggerConfiguration
import org.springframework.beans.factory.annotation.Autowired

open abstract class BaseCommandHandler {

    @Autowired
    protected lateinit var eventPublisher: EventPublisher

    @Autowired
    protected lateinit var logger: LoggerConfiguration

}