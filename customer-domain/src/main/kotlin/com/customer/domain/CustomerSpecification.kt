package com.customer.domain

import com.cross.domain.Notification
import com.cross.domain.Specification
import com.cross.domain.ValidationResult
import org.springframework.stereotype.Component

@Component
class CustomerSpecification (private val repository: CustomerRepository) : Specification<Customer> {
    override fun isSatisfiedBy(entity: Customer): ValidationResult<Notification, Customer> {
        return when(repository.countByCompleteNameAndNickNameAndDifferentId(entity.completeName, entity.nickName, entity.id)) {
            0 -> ValidationResult.Success(entity)
            else -> ValidationResult.Failure(Notification(notification = "Customer already exists."))
        }
    }
}