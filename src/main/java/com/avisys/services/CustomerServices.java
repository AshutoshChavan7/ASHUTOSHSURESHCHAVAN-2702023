package com.avisys.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.avisys.entities.Customer;

//service interface is made to achive loose coupling and bridge design pattern 
public interface CustomerServices {

	List<Customer> fetchAllCustomers();
	
	public List<Customer> searchUsers(String firstName, String lastName);
	
	 public Customer getCustomerByMobileNumber(String mobileNumber);
	 
	 public ResponseEntity<String> createCustomer(Customer customer);
	 
	 public ResponseEntity<Customer> getCustomer(Long id);
}
