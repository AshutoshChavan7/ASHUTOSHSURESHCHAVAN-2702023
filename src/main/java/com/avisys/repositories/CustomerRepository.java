package com.avisys.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	//all customers from db  
	List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

	//customer based on firstname or lastname pattern from db
	List<Customer> findByLastNameContainingIgnoreCase(String lastName);
	    
	//customer based on mobile number
	Optional<Customer> findByMobileNumber(String mobileNumber);

}
