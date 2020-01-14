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
        fun create(id : Long? = null,
                   completeName : String,
                   nickName : String,
                   customerSpecification : CustomerSpecification) : ResultEntity<Notifications, Customer> {
            val newCustomer = Customer(id, completeName, nickName, customerSpecification)

            if (newCustomer.hasNotifications()) {
                return Failure(newCustomer.notifications)
            }

            return Success<Customer>(newCustomer)
        }
    }

    override fun validate() {
        with(completeName) {
            addNotification(Notification(field = "Complete Name", notification = this.isNullOrBlank("Complete Name is required.")))
            addNotification(Notification(field = "Complete Name", notification = this.validateSizeSmallerThan(20, "Complete Name must be less than 20 characters.")))
        }

        with(nickName) {
            addNotification(Notification(field = "Nick Name", notification = this.isNullOrBlank("Nick Name is required.")))
            addNotification(Notification(field = "Nick Name", notification = this.validateSizeSmallerThan(7, "Nick Name must be less than 7 characters.")))
        }

        addNotification(customerSpecification.isSatisfiedBy(this))
    }

    val status get() = _status

}