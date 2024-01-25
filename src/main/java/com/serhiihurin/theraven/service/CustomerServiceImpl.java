package com.serhiihurin.theraven.service;

import com.serhiihurin.theraven.dao.CustomerRepository;
import com.serhiihurin.theraven.dto.CustomerRequestDTO;
import com.serhiihurin.theraven.dto.CustomerUpdateRequestDTO;
import com.serhiihurin.theraven.entity.Customer;
import com.serhiihurin.theraven.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Could not find customer with id: " + customerId));
    }

    @Override
    public Customer addCustomer(CustomerRequestDTO customerRequestDTO) {
        return customerRepository.save(
                Customer.builder()
                        .created(1L)
                        .updated(0L)
                        .fullName(customerRequestDTO.getFullName())
                        .email(customerRequestDTO.getEmail())
                        .phone(customerRequestDTO.getPhone())
                        .isActive(true)
                        .build()
        );
    }

    @Override
    public Customer updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long customerId) {
        Customer oldCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Could not find product with id: " + customerId));
        if (customerUpdateRequestDTO.getFullName() != null) {
            oldCustomer.setFullName(customerUpdateRequestDTO.getFullName());
        }
        if (customerUpdateRequestDTO.getPhone() != null) {
            oldCustomer.setPhone(customerUpdateRequestDTO.getPhone());
        }
        oldCustomer.setUpdated(oldCustomer.getUpdated() + 1L);
        return customerRepository.save(oldCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Could not find customer with id: " + customerId));
        customer.setIsActive(false);
        customerRepository.save(customer);
    }
}
