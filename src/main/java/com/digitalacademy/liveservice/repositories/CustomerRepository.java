package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public static final String getCustomer = "SELECT * From customer where customer_id= :customerId ";
    @Query(value = getCustomer, nativeQuery = true)
    List<Customer> getCustomer(String customerId);

    Customer findById(int customerId);
}
