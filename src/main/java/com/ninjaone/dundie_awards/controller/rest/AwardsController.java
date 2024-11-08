package com.ninjaone.dundie_awards.controller.rest;

import com.ninjaone.dundie_awards.dto.AwardsRequestDto;
import com.ninjaone.dundie_awards.dto.AwardsResponseDto;
import com.ninjaone.dundie_awards.service.AwardsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing awards.
 * Provides endpoints to add awards for individual employees or organizations.
 */
@RestController
@RequestMapping("/api/awards")
public class AwardsController {
    private final AwardsService employeeAwardsService;
    private final AwardsService organizationAwardsService;

    public AwardsController(@Qualifier("employee-awards-srv") AwardsService employeeAwardsService,
                            @Qualifier("organization-awards-srv") AwardsService organizationAwardsService) {
        this.employeeAwardsService = employeeAwardsService;
        this.organizationAwardsService = organizationAwardsService;
    }

    /**
     * Adds the number of awards to a specific employee.
     *
     * @param employeeId the unique identifier of the employee
     * @param awardsRequestDto the request object containing the number of awards to be add
     * @return a response object containing the updated award count for the employee
     */
    @PostMapping("/add-awards-by-employee/{employeeId}")
    public AwardsResponseDto addAwardsByEmployee(@PathVariable long employeeId, @Valid @RequestBody AwardsRequestDto awardsRequestDto) {
        int updated = employeeAwardsService.addAwards(employeeId, awardsRequestDto.getDundieAwards());
        return new AwardsResponseDto(updated);
    }

    /**
     * Adds the number of awards for all employees in a specific organization.
     *
     * @param organizationId the unique identifier of the organization
     * @param awardsRequestDto the request object containing the number of awards to be add
     * @return a response object containing the updated award count for the organization
     */
    @PostMapping("/add-awards-by-organization/{organizationId}")
    public AwardsResponseDto addAwardsByOrganization(@PathVariable long organizationId, @Valid @RequestBody AwardsRequestDto awardsRequestDto) {
        int updated = organizationAwardsService.addAwards(organizationId, awardsRequestDto.getDundieAwards());
        return new AwardsResponseDto(updated);
    }
}
