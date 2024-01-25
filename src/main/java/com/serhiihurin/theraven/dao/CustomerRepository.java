package com.serhiihurin.theraven.dao;

import com.serhiihurin.theraven.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
