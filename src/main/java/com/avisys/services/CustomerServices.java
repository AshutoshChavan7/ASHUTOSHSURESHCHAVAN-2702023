package com.avisys.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.avisys.entities.Customer;

//service interface is made to achive loose coupling and bridge design pattern 
public interface CustomerServices {

	List<Customer> fetchAllCustomers();
	
	 List<Customer> searchUsers(String firstName, String lastName);
	
	  Customer getCustomerByMobileNumber(String mobileNumber);
	 
	  ResponseEntity<String> createCustomer(Customer customer);
	 
	  ResponseEntity<Customer> getCustomer(Long id);
	 
	 ResponseEntity<String> deleteCustomerByMobileNumber(String mobileNumber);
}
