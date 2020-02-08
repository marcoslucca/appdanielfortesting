package com.cross.events

import com.cross.logs.LoggerConfiguration
import com.google.gson.Gson
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse
import java.nio.charset.StandardCharsets


@Component
class DomainInvalidEventHandler (val response : HttpServletResponse, val logger: LoggerConfiguration) {

    private val badRequest = 400

    private val contentType = "application/json"

    @EventListener
    fun eventHandler(domainInvalidEvent: DomainInvalidEvent) {
        val notifications = Gson().toJson(domainInvalidEvent.notifications)
        logger.withInfoMessage("There were the following errors creating the entity $notifications")
        val out = response.writer
        response.reset()
        response.status = badRequest
        response.contentType = contentType
        response.characterEncoding = StandardCharsets.UTF_8.name()
        out.print(notifications)
        out.flush()
        out.close()
    }

}