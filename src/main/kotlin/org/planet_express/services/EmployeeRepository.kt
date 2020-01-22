package org.planet_express.services

import org.planet_express.domain.Employee
import org.planet_express.domain.EmployeeId
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, EmployeeId> {
    fun findByName(name: String)
}