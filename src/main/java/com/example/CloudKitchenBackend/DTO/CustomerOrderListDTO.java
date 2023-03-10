package com.example.CloudKitchenBackend.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerOrderListDTO {
    private List<CustomerOrderDTO> orders;
    private int currentPage;

    private long totalElements;

    private int totalPages;
}
