package com.customer.domain

import com.cross.domain.Notification
import com.cross.domain.Specification
import org.springframework.stereotype.Component

@Component
class CustomerSpecification (private val repository: CustomerRepository) : Specification<Customer> {
    override fun isSatisfiedBy(entity: Customer): Notification {
        return when(repository.countByCompleteNameAndNickNameAndDifferentId(entity.completeName, entity.nickName, entity.id)) {
            0 -> Notification()
            else -> Notification(notification = "Customer already exists.")
        }
    }
}