package com.cross.logs

import net.logstash.logback.marker.EmptyLogstashMarker
import net.logstash.logback.marker.LogstashMarker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope

import net.logstash.logback.marker.Markers.*
import net.logstash.logback.marker.ObjectFieldsAppendingMarker
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class LoggerConfiguration {

    private val logger : Logger = LoggerFactory.getLogger(LoggerConfiguration::class.java)

    private val fields = EmptyLogstashMarker()

    fun toField(key : String, value : String) : LoggerConfiguration {
        fields.and<EmptyLogstashMarker>(append(key, value))
        return this
    }

    fun withInfoMessage(message : String) {
        logger.info(fields, message)
    }

    fun withErrorMessage(message : String) {
        logger.error(fields, message)
    }

    fun withDebugMessage(message : String) {
        logger.debug(fields, message)
    }

    fun withWarnMessage(message : String) {
        logger.warn(fields, message)
    }

}