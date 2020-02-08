package com.customer.domain

import arrow.core.Either
import com.cross.domain.Notification
import com.cross.domain.Specification
import org.springframework.stereotype.Component

@Component
class CustomerSpecification (private val repository: CustomerRepository) : Specification<Customer> {
    override fun isSatisfiedBy(entity: Customer): Either<Notification, Customer> {
        return when(repository.countByCompleteNameAndNickNameAndDifferentId(entity.completeName, entity.nickName, entity.id)) {
            0 -> Either.right(entity)
            else -> Either.left(Notification(notification = "Customer already exists."))
        }
    }
}