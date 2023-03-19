package edu.wctc.wholesale.controller;

import edu.wctc.wholesale.Entity.Customer;
import edu.wctc.wholesale.Service.CustomerService;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}

	@GetMapping("/{customerId}")
	public Customer getOneCustomer(@PathVariable String customerId){
		try {
			int id = Integer.parseInt(customerId);
			Customer customer = customerService.getCustomerById(id);
			return customer;
		} catch (NumberFormatException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}



}
