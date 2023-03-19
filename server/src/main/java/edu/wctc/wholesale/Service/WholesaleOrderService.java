package edu.wctc.wholesale.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.wctc.wholesale.Entity.WholesaleOrder;
import edu.wctc.wholesale.exception.ResourceNotFoundException;

import java.util.List;

public interface WholesaleOrderService {
    List<WholesaleOrder> getAllOrders();

    boolean addOrder(WholesaleOrder wholesaleOrder);

    boolean deleteOrder(int orderNum) throws ResourceNotFoundException;

    WholesaleOrder getOrderById(int orderId) throws ResourceNotFoundException;

    WholesaleOrder patch(int id, JsonPatch patch) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException;
}
