package com.customer.commands

import com.cross.commands.BaseCommandHandler
import com.cross.domain.ResultEntity.*
import com.customer.domain.Customer
import com.customer.domain.CustomerRepository
import com.cross.events.DomainInvalidEvent
import com.customer.domain.CustomerSpecification
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRED)
class CreateCustomerHandler(val customerSpecification: CustomerSpecification, val customerRepository: CustomerRepository) : BaseCommandHandler() {

    fun handle(customerCommand: CreateCustomerCommand) {

        logger.toField("complete name", customerCommand.completeName)
                .toField("nick name", customerCommand.nickName)
                .withInfoMessage("Received command with parameters")

        Customer
                .create(completeName = customerCommand.completeName,
                        nickName = customerCommand.nickName,
                        customerSpecification = customerSpecification)
                .bimap(
                        {
                            eventPublisher.publisher(DomainInvalidEvent(it))
                        },
                        {
                            customerRepository.insert(it)
                        }
                )
    }

}