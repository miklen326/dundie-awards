package com.ninjaone.dundie_awards.dto;

import jakarta.validation.constraints.Min;

public class AwardsRequestDto {
    @Min(value = 1, message = "dundieAwards must be greater than zero")
    private int dundieAwards;

    public int getDundieAwards() {
        return dundieAwards;
    }

    public void setDundieAwards(int dundieAwards) {
        this.dundieAwards = dundieAwards;
    }
}
