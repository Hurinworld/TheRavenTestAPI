package com.serhiihurin.theraven.service.interfaces;

import com.serhiihurin.theraven.dto.CustomerRequestDTO;
import com.serhiihurin.theraven.dto.CustomerUpdateRequestDTO;
import com.serhiihurin.theraven.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomer(Long customerId);

    Customer addCustomer(CustomerRequestDTO customerRequestDTO);

    Customer updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long customerId);

    void deleteCustomer(Long customerId);
}
