package com.cross.domain

interface Specification<T> {

    fun isSatisfiedBy(entity : T) : Notification

}