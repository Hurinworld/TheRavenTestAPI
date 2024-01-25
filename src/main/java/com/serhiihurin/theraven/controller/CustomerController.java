package com.serhiihurin.theraven.controller;

import com.serhiihurin.theraven.dto.CustomerRequestDTO;
import com.serhiihurin.theraven.dto.CustomerResponseDTO;
import com.serhiihurin.theraven.dto.CustomerUpdateRequestDTO;
import com.serhiihurin.theraven.facade.interfaces.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerFacade customerFacade;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(customerFacade.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerFacade.getCustomer(customerId));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerFacade.addCustomer(customerRequestDTO);
        if (customerResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(customerResponseDTO);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO
    ) {
        return ResponseEntity.ok(customerFacade.updateCustomer(customerUpdateRequestDTO, customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerFacade.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
