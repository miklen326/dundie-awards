package com.ninjaone.dundie_awards.service;

/**
 * Service interface for adding awards to employees.
 * Provides functionality to update the number of awards for individual employee or organization.
 */
public interface AwardsService {
    /**
     * Add the number of awards (Dundie Awards) to a specific employee or organization.
     *
     * @param rewardedEntityId the unique identifier of the employee or organization
     * @param dundieAwards the number of awards to be assigned to the employee
     * @return the updated total count of employees
     */
    int addAwards(long rewardedEntityId, int dundieAwards);
}
