package com.ninjaone.dundie_awards.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.dundie_awards.dto.EmployeeDto;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void resetDb() {
        employeeRepository.deleteAll();
        organizationRepository.deleteAll();
    }

    @Test
    public void getAllEmployees() throws Exception {
        final Organization organization = createOrganization("Test");
        createEmployee("Michael", "Ivanov", organization);

        this.mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].firstName", is("Michael")));
    }

    @Test
    public void createEmployee() throws Exception {
        final Organization organization = createOrganization("Test");

        final EmployeeDto dto = new EmployeeDto();
        dto.setFirstName("Michael");
        dto.setLastName("Ivanov");
        dto.setOrganizationId(organization.getId());

        this.mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Michael")));

        final List<Employee> found = employeeRepository.findAll();
        assertThat(found).extracting(Employee::getFirstName).containsOnly("Michael");
    }

    @Test
    public void updateEmployee() throws Exception {
        final Organization organization = createOrganization("Test");
        final Employee employee = createEmployee("Michael", "Ivanov", organization);

        final EmployeeDto dto = new EmployeeDto();
        dto.setFirstName("Test");
        dto.setLastName("Test");
        dto.setOrganizationId(organization.getId());

        this.mockMvc.perform(put("/api/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Test")));

        List<Employee> found = employeeRepository.findAll();
        assertThat(found).extracting(Employee::getFirstName).containsOnly("Test");
    }

    @Test
    public void getEmployeeById() throws Exception {
        final Organization organization = createOrganization("Test");
        final Employee employee = createEmployee("Michael", "Ivanov", organization);

        this.mockMvc.perform(get("/api/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Michael")));
    }

    @Test
    public void deleteEmployee() throws Exception {
        final Organization organization = createOrganization("Test");
        final Employee employee = createEmployee("Michael", "Ivanov", organization);

        this.mockMvc.perform(delete("/api/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        final List<Employee> found = employeeRepository.findAll();
        assertThat(found).hasSize(0);
    }

    private Employee createEmployee(String firstName, String lastName, Organization organization) {
        final Employee employee = new Employee(firstName, lastName, organization);
        return employeeRepository.saveAndFlush(employee);
    }

    private Organization createOrganization(String organizationName) {
        final Organization organization = new Organization(organizationName);
        return organizationRepository.saveAndFlush(organization);
    }
}
