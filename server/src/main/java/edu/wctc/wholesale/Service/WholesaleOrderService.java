package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.WholesaleOrder;
import edu.wctc.wholesale.exception.ResourceNotFoundException;

import java.util.List;

public interface WholesaleOrderService {
    List<WholesaleOrder> getAllOrders();

    boolean addOrder(WholesaleOrder wholesaleOrder);

    boolean deleteOrder(int orderNum);

    WholesaleOrder getOrderById(int orderId) throws ResourceNotFoundException;
}
