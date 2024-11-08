package com.ninjaone.dundie_awards.dto;

import jakarta.validation.constraints.Min;

public class AwardsRequestDto {
    @Min(value = 0, message = "dundieAwards must be greater than or equal to zero")
    private int dundieAwards;

    public int getDundieAwards() {
        return dundieAwards;
    }

    public void setDundieAwards(int dundieAwards) {
        this.dundieAwards = dundieAwards;
    }
}
