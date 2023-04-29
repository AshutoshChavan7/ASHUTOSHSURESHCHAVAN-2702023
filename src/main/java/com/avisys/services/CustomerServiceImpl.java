package com.avisys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.avisys.entities.Customer;
import com.avisys.repositories.CustomerRepository;

import jakarta.transaction.Transactional;

@Service    // this annotation to tell spring that this is service bean
@Transactional  // this annotation is used to achive default isolation level given by spring ,so that this application will not fail in case of concurrent access
public class CustomerServiceImpl implements CustomerServices {
	
	@Autowired
	CustomerRepository repo;   //field level autowiring of repository bean

	
	//this method will take all customers from reoository and gives it to controller
	@Override
	public List<Customer> fetchAllCustomers() 
	{
		// TODO Auto-generated method stub
		return repo.findAll();
				
	}

	//this method will return customer based on firstname or lastname pattern to controller
	@Override
	public List<Customer> searchUsers(String firstName, String lastName) {
		List<Customer> users = new ArrayList<>();
        if (firstName != null && !firstName.isEmpty()) {
            users.addAll(repo.findByFirstNameContainingIgnoreCase(firstName));
        }
        if (lastName != null && !lastName.isEmpty()) {
            users.addAll(repo.findByLastNameContainingIgnoreCase(lastName));
        }
        return users;
	}

	//this method will return customer based on mobile number given ,if not found it will return not found status in response header
	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		Optional<Customer> customer = repo.findByMobileNumber(mobileNumber);
        return customer.orElse(null);
	}

	//this method will insert new customer and return status whether customer added or not 
	@Override
	public ResponseEntity<String> createCustomer(Customer customer) {
		Optional<Customer> existingCustomer = repo.findByMobileNumber(customer.getMobileNumber());
        if (existingCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create Customer. Mobile number already present.");
        }
        repo.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
