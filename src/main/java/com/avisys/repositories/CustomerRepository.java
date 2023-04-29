package com.avisys.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	
	//here fetching policy of any to many is lazy so to avoid lazy initialization exception ,used join fetch 
	 @Query("SELECT c FROM Customer c JOIN FETCH c.mobileNumbers WHERE c.id = :id")
	    Optional<Customer> findByIdWithMobileNumbers(@Param("id") Long id);

	
}
