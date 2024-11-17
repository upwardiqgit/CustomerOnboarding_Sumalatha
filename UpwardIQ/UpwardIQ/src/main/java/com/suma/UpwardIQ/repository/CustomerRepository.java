package com.suma.UpwardIQ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suma.UpwardIQ.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	boolean existsByCustomerId(String customerId); 
	
	// Custom query to filter by customer name and gender
    @Query("SELECT c FROM Customer c WHERE " +
           "(:name IS NULL OR c.name LIKE %:name%) AND " +
           "(:gender IS NULL OR c.gender = :gender)")
    List<Customer> findByCustomerNameAndGender(
            @Param("name") String customerName,
            @Param("gender") String gender);
}
