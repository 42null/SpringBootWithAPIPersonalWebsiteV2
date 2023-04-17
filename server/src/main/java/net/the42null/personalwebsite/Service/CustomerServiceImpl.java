package net.the42null.personalwebsite.Service;

import net.the42null.personalwebsite.Entity.Customer;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;
import net.the42null.personalwebsite.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

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

    public Customer getCustomerByName(String name) throws ResourceNotFoundException{
        Optional<Customer> optional = customerRepo.findByName(name);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", (name)));
    }

//    public Customer getCustomerByName(String name) throws ResourceNotFoundException{
////        List<WholesaleOrder> list = new ArrayList<>();
//        cus.findAll().forEach(order -> {
//            if(order.getCustomer()){
//
//            }
//        });

//        List<WholesaleOrder> wholesaleOrderList = wholesaleOrderService.getAllOrders();
//        for(WholesaleOrder wholesaleOrder: wholesaleOrderList){
//            list.add(convertToDto(wholesaleOrder));
//        }

//        throw new  ResourceNotFoundException("Order", "id", name);
//    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

}
