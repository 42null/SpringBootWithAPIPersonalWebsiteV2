package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.Customer;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import edu.wctc.wholesale.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    public Customer getCustomerByID(int id) throws ResourceNotFoundException {
        Optional<Customer> optional = customerRepo.findById(id);
        return optional.orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", Integer.toString(id)));
    }
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerRepo.findAll().forEach(list::add);
        System.out.println("list ="+list);
        return list;
    }

    @Override
    public boolean addCustomer(Customer customer) {//TODO: Make error if it already exists
        if(!customerRepo.existsById(customer.getCustomerID())){
            customerRepo.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) {//TODO: Make error if it already exists
        if(customerRepo.findById(customerId).isPresent()){
            customerRepo.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Customer getCustomerById(int customerId) throws ResourceNotFoundException{
        Optional<Customer> optional = customerRepo.findById(customerId);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", Integer.toString(customerId)));
    }

}
