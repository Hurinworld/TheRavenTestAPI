package com.serhiihurin.theraven.dto;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long customerId;
    private String fullName;
    private String email;
    private String phone;
}
