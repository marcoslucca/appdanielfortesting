package com.cross.events

import com.cross.domain.Notifications
import com.cross.logs.LoggerConfiguration
import org.junit.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletResponse

class DomainInvalidEventHandlerTest {

    @Test
    fun shouldValidateBehaviorToDomainInvalidHandler() {
        val outputStream = mock(OutputStreamWriter::class.java)
        val response = mock(HttpServletResponse::class.java)
        val logger = mock(LoggerConfiguration::class.java)
        `when`(response.writer).thenReturn(PrintWriter(outputStream))
        val domainInvalidEventHandler = DomainInvalidEventHandler(response = response, logger = logger)
        domainInvalidEventHandler.eventHandler(DomainInvalidEvent(Notifications()))
        verify(response, times(1)).reset()
        verify(response, times(1)).writer
        verify(response).status = 400
        verify(response).contentType = "application/json"
        verify(response).characterEncoding = StandardCharsets.UTF_8.name()
        verify(outputStream, times(1)).flush()
        verify(outputStream, times(1)).close()
    }

}