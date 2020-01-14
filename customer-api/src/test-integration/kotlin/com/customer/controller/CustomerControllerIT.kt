package com.customer.controller

import com.customer.commands.CreateCustomerCommand
import org.hamcrest.core.StringContains
import org.junit.Assert.*
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class CustomerControllerIT {

    private val resource = "http://api:8080/api/customer"

    @Test
    fun shouldCreateCustomer() {
        val createCustomerCommand = CreateCustomerCommand(completeName = "Renato Portallupi", nickName = "renato")

        val response = RestTemplate().postForEntity(resource, createCustomerCommand, CreateCustomerCommand::class.java)

        val createCustomerCommandReceived = response.body
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Renato Portallupi", createCustomerCommandReceived?.completeName)
        assertEquals("renato", createCustomerCommandReceived?.nickName)
        assertNotNull(createCustomerCommandReceived?.createdAt)
        assertNotNull(createCustomerCommandReceived?.id)
    }

    @Test
    fun shouldValidateCreationCustomerWithEmptyCompleteNameAndEmptyNickName() {
        val createCustomerCommand = CreateCustomerCommand(completeName = "", nickName = "")

        try {
            RestTemplate().postForEntity(resource, createCustomerCommand, String::class.java)
        } catch (e: HttpClientErrorException) {
            assertEquals(HttpStatus.BAD_REQUEST, e.statusCode)
            val body = e.responseBodyAsString
            assertThat(body, StringContains.containsString("Complete Name is required."))
            assertThat(body, StringContains.containsString("Nick Name is required."))
        }
    }

    @Test
    fun shouldValidateCreationCustomerWithCompleteNameSizeIsGreaterThanMaxLengthAndNickNameSizeIsGreaterThanMaxLength() {
        val createCustomerCommand = CreateCustomerCommand(completeName = "Silvio Santos Ipsum ma oi.", nickName = "silviosantosipsummaoi.")

        try {
            RestTemplate().postForEntity(resource, createCustomerCommand, String::class.java)
        } catch (e: HttpClientErrorException) {
            assertEquals(HttpStatus.BAD_REQUEST, e.statusCode)
            val body = e.responseBodyAsString
            assertThat(body, StringContains.containsString("Complete Name must be less than 20 characters."))
            assertThat(body, StringContains.containsString("Nick Name must be less than 7 characters."))
        }
    }

}