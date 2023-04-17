package net.the42null.personalwebsite.Service;

import net.the42null.personalwebsite.Entity.Customer;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    Customer getCustomerById(int customerId) throws ResourceNotFoundException;

    void save(Customer customer);

}
