package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.service.AwardsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("employee-awards-srv")
public class EmployeeAwardsServiceImpl implements AwardsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeAwardsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    @CacheEvict(value = "totalAwards", allEntries = true)
    public int addAwards(long employeeId, int dundieAwards) {
        return employeeRepository.updateAwardsByEmployee(employeeId, dundieAwards);
    }
}
