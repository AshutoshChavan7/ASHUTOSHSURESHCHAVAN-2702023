package com.avisys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avisys.dto.CustomerDTO;
import com.avisys.entities.Customer;
import com.avisys.services.CustomerServices;

@CrossOrigin()				 // this is for accepting request from any origin
@RestController                 //this annotation is to return results in json format and client content negotiation
@RequestMapping("/customers")   // this is for mapping request to specific controller
                         
public class CustomerController {

	@Autowired
	CustomerServices service; // service bean will be injected in customercontroller by Spring boot (IOC)

	
	// this method will return all customers to client
	@GetMapping("getall") // to map URI to method
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> list = service.fetchAllCustomers();
		return ResponseEntity.ok(list);
	}
	

	// this method will return customer based on firstname or lastname pattern to
	// client
	@GetMapping("/findCustomerByName")
	public List<Customer> findcustomer(@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName) {

		return service.searchUsers(firstName, lastName);
	}
	

	// this method will return customer based on mobile number given ,if not found
	// it will return not found status in response header
	@GetMapping("/findCustomerByPhone")
	public ResponseEntity<Customer> getCustomerByMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
		Customer customer = service.getCustomerByMobileNumber(mobileNumber);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}
	
	
	//this will create new customer and store in DB ,updated method in task 4 to add multiple numbers under single customer
	@PostMapping
	    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customer) {
		Customer customer1 = new Customer();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        for (String number : customer.getMobileNumbers()) {
            customer1.addMobileNumber(number);
        }
        
		return service.createCustomer(customer1);
	        
	    }
	
	
	
	
	

}
