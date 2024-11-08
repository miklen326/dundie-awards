package com.ninjaone.dundie_awards.service;

import com.ninjaone.dundie_awards.exception.EmployeeNotFoundException;
import com.ninjaone.dundie_awards.exception.OrgNotFoundException;
import com.ninjaone.dundie_awards.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing employee-related operations.
 * Provides methods for retrieving, creating, updating, and deleting employees.
 */
public interface EmployeeService {
    /**
     * Retrieves a paginated list of all employees.
     *
     * @param pageable the pagination parameters (e.g., page, size, sort options)
     * @return a page containing a subset of employees based on pagination settings
     */

    Page<Employee> getAllEmployees(Pageable pageable);
    /**
     * Retrieves an employee by their unique identifier.
     *
     * @param employeeId the unique identifier of the employee
     * @return the employee with the specified ID
     * @throws EmployeeNotFoundException if no employee with the specified ID is found
     */
    Employee getEmployeeById(long employeeId);

    /**
     * Creates a new employee.
     *
     * @param firstName the first name of the new employee
     * @param lastName the last name of the new employee
     * @param organizationId the unique identifier of the organization the employee belongs to
     * @return the newly created employee
     * @throws OrgNotFoundException if no organization with the specified ID is found
     */
    Employee createEmployee(String firstName, String lastName, long organizationId);

    /**
     * Updates an existing employee's information.
     *
     * @param employeeId the unique identifier of the employee to update
     * @param firstName the new first name of the employee
     * @param lastName the new last name of the employee
     * @param organizationId the unique identifier of the organization the employee belongs to
     * @return the updated employee
     * @throws EmployeeNotFoundException if no employee with the specified ID is found
     * @throws OrgNotFoundException if no organization with the specified ID is found
     */
    Employee updateEmployee(long employeeId, String firstName, String lastName, long organizationId);

    /**
     * Deletes an employee by their unique identifier.
     *
     * @param employeeId the unique identifier of the employee to delete
     * @throws EmployeeNotFoundException if no employee with the specified ID is found
     */
    void deleteEmployee(long employeeId);
}
