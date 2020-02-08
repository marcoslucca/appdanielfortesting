package com.cross.domain

import arrow.core.Either

interface Specification<T> {

    fun isSatisfiedBy(entity : T) : Either<Notification, T>

}