package com.serhiihurin.theraven.facade.interfaces;

import com.serhiihurin.theraven.dto.CustomerRequestDTO;
import com.serhiihurin.theraven.dto.CustomerResponseDTO;
import com.serhiihurin.theraven.dto.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerFacade {
    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomer(Long customerId);

    CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long customerId);

    void deleteCustomer(Long customerId);
}
