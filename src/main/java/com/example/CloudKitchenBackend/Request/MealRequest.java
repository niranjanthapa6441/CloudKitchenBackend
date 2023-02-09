package com.example.CloudKitchenBackend.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MealRequest {
    @NotBlank(message = "Meal Should not be empty")
    private String meal;
}
