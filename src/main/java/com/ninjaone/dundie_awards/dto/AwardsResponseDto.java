package com.ninjaone.dundie_awards.dto;

public class AwardsResponseDto {
    private final int updatedItems;

    public AwardsResponseDto(int updatedItems) {
        this.updatedItems = updatedItems;
    }

    public int getUpdatedItems() {
        return updatedItems;
    }
}
