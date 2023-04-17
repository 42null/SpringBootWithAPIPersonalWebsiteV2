package net.the42null.personalwebsite.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import net.the42null.personalwebsite.Entity.WholesaleOrder;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;

import java.util.List;

public interface WholesaleOrderService {
    List<WholesaleOrder> getAllOrders();

    boolean addOrder(WholesaleOrder wholesaleOrder);

    boolean deleteOrder(int orderNum) throws ResourceNotFoundException;

    WholesaleOrder getOrderById(int orderId) throws ResourceNotFoundException;

    WholesaleOrder patch(int id, JsonPatch patch) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException;
}
