package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.service.TotalAwardsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TotalAwardsServiceImpl implements TotalAwardsService {
    private final EmployeeRepository employeeRepository;

    public TotalAwardsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Cacheable("totalAwards")
    public Integer getTotalAwards() {
        return employeeRepository.calcTotalAwards();
    }
}
