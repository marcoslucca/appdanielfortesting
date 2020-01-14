package com.customer.repository

import com.customer.domain.CustomerProjection
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object CustomerTable : Table("customer") {
    val id = long("id").primaryKey()
    val completeName = text("complete_name")
    val nickName = text("nick_name")
    val status = integer("status")
}

fun CustomerTable.rowToCustomerProjection(row: ResultRow) : CustomerProjection =
        CustomerProjection(id = row[id], completeName = row[completeName], nickName = row[nickName], status = row[status].toString())
