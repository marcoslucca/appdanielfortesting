package com.customer.domain

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.getOrElse
import com.cross.domain.*
import com.cross.domain.ResultEntity.*
import com.cross.extensions.isNullOrBlank
import com.cross.extensions.validateSizeSmallerThan

enum class CustomerStatus {
    INACTIVE, ACTIVE
}

data class Customer private constructor(
        val id: Long?,
        val completeName: String,
        val nickName: String,
        val customerSpecification: CustomerSpecification) : Entity() {

    private val _status: CustomerStatus = CustomerStatus.ACTIVE

    init {
        validate()
    }

    companion object {
        fun create(
                id: Long? = null,
                completeName: String,
                nickName: String,
                customerSpecification: CustomerSpecification
        ): Either<List<Notification>, Customer> {

            val newCustomer = Customer(id, completeName, nickName, customerSpecification)

            return when (newCustomer.hasNotification()) {
                true -> Right(newCustomer)
                else -> Left(newCustomer.notifications.getOrElse { emptyList() })
            }
        }
    }

    override fun validate() =
            completeName.validateSizeSmallerThan(20, "Complete Name must be less than 20 characters.", "Complete Name")
                    .flatMap {
                        completeName.isNullOrBlank(message = "Complete Name is required.", field = "Complete Name")
                    }
                    .flatMap {
                        nickName.isNullOrBlank("Nick Name is required.", "Nick Name")
                    }
                    .flatMap {
                        nickName.validateSizeSmallerThan(7, "Nick Name must be less than 7 characters.", "Nick Name")
                    }

}