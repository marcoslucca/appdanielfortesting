package com.customer.domain

interface CustomerRepository {

    fun insert(customer: Customer)

    fun findAll() : List<CustomerProjection>

    fun countByCompleteNameAndNickNameAndDifferentId(completeName : String, nickName : String, id : Long?) : Int

}