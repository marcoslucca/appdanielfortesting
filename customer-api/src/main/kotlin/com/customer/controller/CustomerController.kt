package com.customer.controller

import com.customer.commands.CreateCustomerCommand
import com.customer.commands.CreateCustomerHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/customer")
@RestController
class CustomerController (val customerHandler: CreateCustomerHandler) {

    @PostMapping
    fun createCustomer(@RequestBody customerCommand: CreateCustomerCommand) : ResponseEntity<CreateCustomerCommand> {
        customerHandler.handle(customerCommand)
        return ResponseEntity.ok(customerCommand)
    }

}