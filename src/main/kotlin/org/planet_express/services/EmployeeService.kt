package org.planet_express.services

import org.planet_express.domain.Employee
import org.planet_express.domain.EmployeeId
import org.springframework.web.bind.annotation.*

@RestController
internal class EmployeeService(
        val employees: EmployeeRepository
) {

    @GetMapping("/employees/{id}")
    fun findEmployee(@PathVariable id: EmployeeId) = employees.findById(id)

    @GetMapping("/employees")
    fun findEmployees() = employees.findAll()

    data class EmployeeHiringForm(val name: String, val lastName: String, val title: String? = null)

    @PostMapping("/employees")
    fun hireEmployee(@RequestBody hiringForm: EmployeeHiringForm) = employees.save(Employee( hiringForm.name, hiringForm.lastName, hiringForm.title ))

}