package com.cross.extensions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.toPatternDDMMYYYY() : String = this.toString(DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"))