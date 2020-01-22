package org.planet_express.services

import org.planet_express.domain.EmployeeId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeService(
        val employees: EmployeeRepository
) {

    @GetMapping("/employee/{id}")
    fun findEmployee(@PathVariable id: EmployeeId) = employees.findById(id)

}