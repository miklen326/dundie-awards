package com.ninjaone.dundie_awards.controller.rest;

import java.util.Map;

import com.ninjaone.dundie_awards.dto.EmployeeDto;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Employees.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a paginated list of employees.
     *
     * @param pageable the pagination parameters (e.g., page=0, size=3, sort=id,DESC)
     * @return a page of employees in DTO format
     */
    @GetMapping
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable).map(this::convert);
    }

    /**
     * Creates a new employee.
     *
     * @param employee the employee data transfer object containing details of the new employee
     * @return the created employee in DTO format
     */
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employee) {
        return convert(employeeService.createEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getOrganizationId()
        ));
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the unique identifier of the employee
     * @return the employee with the specified ID in DTO format
     */
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable long id) {
        return convert(employeeService.getEmployeeById(id));
    }

    /**
     * Updates the details of an existing employee by ID.
     *
     * @param id the unique identifier of the employee to update
     * @param employee the employee data transfer object containing updated details
     * @return the updated employee in DTO format
     */
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable long id, @Valid  @RequestBody EmployeeDto employee) {
        return convert(employeeService.updateEmployee(
                id,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getOrganizationId()
        ));
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id the unique identifier of the employee to delete
     * @return a map indicating successful deletion (e.g., {"deleted": true})
     */
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return Map.of("deleted", true);
    }

    /**
     * Converts an Employee entity to an EmployeeDto.
     *
     * @param employee the employee entity to convert
     * @return the converted employee data transfer object (DTO)
     */
    private EmployeeDto convert(Employee employee) {
        final EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setDundieAwards(employee.getDundieAwards());
        dto.setOrganizationId(employee.getOrganization().getId());
        return dto;
    }
}