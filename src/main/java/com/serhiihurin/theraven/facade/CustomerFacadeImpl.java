package com.serhiihurin.theraven.facade;

import com.serhiihurin.theraven.dto.CustomerRequestDTO;
import com.serhiihurin.theraven.dto.CustomerResponseDTO;
import com.serhiihurin.theraven.dto.CustomerUpdateRequestDTO;
import com.serhiihurin.theraven.facade.interfaces.CustomerFacade;
import com.serhiihurin.theraven.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerFacadeImpl implements CustomerFacade {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        log.info("Getting all customers");
        return modelMapper.map(
                customerService.getAllCustomers(),
                new TypeToken<List<CustomerResponseDTO>>(){}.getType()
        );
    }

    @Override
    public CustomerResponseDTO getCustomer(Long customerId) {
        log.info("Getting customer with ID: " + customerId);
        return modelMapper.map(
                customerService.getCustomer(customerId),
                CustomerResponseDTO.class
        );
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
        boolean isParametersCorrect = checkRequestParameters(customerRequestDTO);
        if (!isParametersCorrect) {
            return null;
        }
        log.info("Adding new customer: " + customerRequestDTO.getFullName());
        return modelMapper.map(
                customerService.addCustomer(customerRequestDTO),
                CustomerResponseDTO.class
        );
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long customerId) {
        log.info("Updating customer with ID: " + customerId);
        return modelMapper.map(
                customerService.updateCustomer(customerUpdateRequestDTO, customerId),
                CustomerResponseDTO.class
        );
    }

    @Override
    public void deleteCustomer(Long customerId) {
        log.info("deleting customer with ID: " + customerId);
        customerService.deleteCustomer(customerId);
    }

    private boolean checkRequestParameters(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO.getFullName().length() < 2 || customerRequestDTO.getFullName().length() > 50) {
            return false;
        }
        else if (!customerRequestDTO.getEmail().contains("@")
                || customerRequestDTO.getEmail().length() < 2
                || customerRequestDTO.getEmail().length() > 100) {
            return false;
        }
        else return customerRequestDTO.getPhone().startsWith("+")
                    || customerRequestDTO.getPhone().length() < 6
                    || customerRequestDTO.getPhone().length() > 14;
    }
}
