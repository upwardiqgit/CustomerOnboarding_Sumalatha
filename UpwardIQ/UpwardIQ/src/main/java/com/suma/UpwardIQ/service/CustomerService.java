package com.suma.UpwardIQ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suma.UpwardIQ.model.Customer;
import com.suma.UpwardIQ.repository.CustomerRepository;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean isCustomerIdUnique(String customerId) {
        return customerRepository.existsByCustomerId(customerId);
    }

    public void saveCustomer(Customer customer) {
      
    	
            customerRepository.save(customer);
        
    }

    public Customer findCustomerById(Long id) {
    	return customerRepository.findById(id).get();
    }
	public List<Customer>  findByCustomerNameAndGender(String name, String gender) {
		return customerRepository.findByCustomerNameAndGender(name, gender);
	}

	
}
