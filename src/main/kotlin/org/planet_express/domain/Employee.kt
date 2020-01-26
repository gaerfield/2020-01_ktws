package org.planet_express.domain

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Embeddable
class EmployeeId(id: String? = null) : BaseEntityId(id)

@Entity
internal class Employee(
        val name: String,
        val lastName: String,
        val title: String?
) : BaseEntity<EmployeeId> (EmployeeId())
