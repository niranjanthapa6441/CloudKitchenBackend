package com.example.CloudKitchenBackend.Request;

import com.example.CloudKitchenBackend.Model.Restaurant;
import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class MenuRequest {

    @NotBlank(message = "Restaurant id should not be empty")
    private String restaurantId;

    @NotBlank(message = "Menu description should not be empty")
    private String Description;

    @NotBlank(message = "Opening time Should not be empty")
    private String openingTime;

    @NotBlank(message = "Closing time should not be empty")
    private String closingTime;
}