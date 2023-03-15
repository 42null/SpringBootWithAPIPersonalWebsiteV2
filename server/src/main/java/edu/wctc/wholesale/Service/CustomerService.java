package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.Customer;
import edu.wctc.wholesale.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    Customer getCustomerById(int customerId) throws ResourceNotFoundException;
}
