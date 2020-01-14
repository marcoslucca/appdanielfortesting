package com.cross.commands

import com.cross.extensions.toPatternDDMMYYYY
import org.joda.time.DateTime
import java.util.*

open abstract class BaseCommand (val id : UUID = UUID.randomUUID(), private val _createdAt : DateTime = DateTime.now()) {

    val createdAt get()  = _createdAt.toPatternDDMMYYYY()

}