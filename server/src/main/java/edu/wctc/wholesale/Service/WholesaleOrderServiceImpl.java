package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.WholesaleOrder;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import edu.wctc.wholesale.repo.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WholesaleOrderServiceImpl implements WholesaleOrderService {

    @Autowired
    private WholesaleOrderRepository wholesaleOrderRepo;

    @Override
    public List<WholesaleOrder> getAllOrders() {
        List<WholesaleOrder> list = new ArrayList<>();
        wholesaleOrderRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean addOrder(WholesaleOrder wholesaleOrder) {
        if(!wholesaleOrderRepo.existsById(wholesaleOrder.getId())){
            wholesaleOrderRepo.save(wholesaleOrder);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int customerId) {//TODO: Make error if it already exists
        if(wholesaleOrderRepo.findById(customerId).isPresent()){
            wholesaleOrderRepo.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public WholesaleOrder getOrderById(int orderId) throws ResourceNotFoundException{
        Optional<WholesaleOrder> optional = wholesaleOrderRepo.findById(orderId);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Order", "id", Integer.toString(orderId)));
    }
}
