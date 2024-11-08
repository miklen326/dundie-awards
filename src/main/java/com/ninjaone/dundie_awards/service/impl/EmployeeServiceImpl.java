package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.exception.EmployeeNotFoundException;
import com.ninjaone.dundie_awards.exception.OrgNotFoundException;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAllWithOrganization(pageable);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> employeeNotFoundException(employeeId));
    }

    @Override
    @Transactional
    public Employee createEmployee(String firstName, String lastName, long organizationId) {
        final Organization organization = getOrganizationReferenceById(organizationId);
        final Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setOrganization(organization);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(long employeeId, String firstName, String lastName, long organizationId) {
        final Organization organization = getOrganizationReferenceById(organizationId);
        if (employeeRepository.updateEmployee(employeeId, firstName, lastName, organization) == 0) {
            throw employeeNotFoundException(employeeId);
        }
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> employeeNotFoundException(employeeId));
    }

    @Override
    @Transactional
    public void deleteEmployee(long employeeId) {
        final Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> employeeNotFoundException(employeeId));
        employeeRepository.delete(employee);
    }

    private Organization getOrganizationReferenceById(long organizationId) {
        try {
            return organizationRepository.getReferenceById(organizationId);
        } catch (EntityNotFoundException e) {
            throw new OrgNotFoundException("No Organization found with ID=" + organizationId);
        }
    }

    private EmployeeNotFoundException employeeNotFoundException(long employeeId) {
        return new EmployeeNotFoundException("No Employee found with ID=" + employeeId);
    }
}
