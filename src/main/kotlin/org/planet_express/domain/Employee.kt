package org.planet_express.domain

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Embeddable
data class EmployeeId(
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        val id: String = UUID.randomUUID().toString()
) : Serializable


@Entity
internal class Employee(
        val name: String,
        val lastName: String,
        val title: String
) : BaseEntity<EmployeeId> (EmployeeId())
