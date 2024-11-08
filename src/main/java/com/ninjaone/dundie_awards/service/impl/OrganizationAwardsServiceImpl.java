package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.service.AwardsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("organization-awards-srv")
public class OrganizationAwardsServiceImpl implements AwardsService {
    private final EmployeeRepository employeeRepository;

    public OrganizationAwardsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    @CacheEvict(value = "totalAwards", allEntries = true)
    public int addAwards(long organizationId, int dundieAwards) {
        return employeeRepository.updateAwardsByOrganization(organizationId, dundieAwards);
    }
}
