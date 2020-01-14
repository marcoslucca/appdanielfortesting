package com.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.cross, com.customer")
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication>(*args)
}
