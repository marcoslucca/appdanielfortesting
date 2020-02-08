package com.customer.domain

import com.cross.domain.*
import com.cross.domain.ResultEntity.*
import com.cross.extensions.isNullOrBlank
import com.cross.extensions.validateSizeSmallerThan

enum class CustomerStatus {
    INACTIVE, ACTIVE
}

data class Customer private constructor(
        val id : Long?,
        val completeName : String,
        val nickName : String,
        val customerSpecification : CustomerSpecification) : Entity () {

    private val _status: CustomerStatus = CustomerStatus.ACTIVE

    init {
        validate()
    }

    companion object {
        fun create(
            id : Long? = null,
           completeName : String,
           nickName : String,
           customerSpecification : CustomerSpecification
        ) : ResultEntity<List<Notification>, Customer> {

            val newCustomer = Customer(id, completeName, nickName, customerSpecification)

            return  when(newCustomer.hasNotification()) {
                true -> Success<Customer>(newCustomer)
                else -> Failure(newCustomer.notifications)
            }
        }
    }

    override fun validate() = listOf(
            completeName.validateSizeSmallerThan(20, "Complete Name must be less than 20 characters.", "Complete Name"),
            completeName.isNullOrBlank(message = "Complete Name is required.", field = "Complete Name"),
            nickName.isNullOrBlank("Nick Name is required.", "Nick Name"),
            nickName.validateSizeSmallerThan(7, "Nick Name must be less than 7 characters.", "Nick Name"),
            customerSpecification.isSatisfiedBy(this)
    )

    val status get() = _status

}