package edu.wctc.wholesale.controller;

import edu.wctc.wholesale.Entity.WholesaleOrder;
import edu.wctc.wholesale.Service.WholesaleOrderService;
import edu.wctc.wholesale.dto.DtoOrder;
import edu.wctc.wholesale.repo.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin(origins="http://localhost:63343")
public class HomeController {


    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("orderList", wholesaleOrderService.getAllOrders());
//        model.addAttribute("orderList", getAllOrders());
        return "index";
    }

    @Autowired
    private WholesaleOrderService wholesaleOrderService;

    @Autowired
    private WholesaleOrderRepository orderRepository;

    private DtoOrder convertToDto(WholesaleOrder wholesaleOrder) {
        DtoOrder dtoOrder = new DtoOrder();
        dtoOrder.setCustomerName(wholesaleOrder.getCustomer().getName());
        dtoOrder.setPurchaseDate(wholesaleOrder.getPurchaseDate());
        dtoOrder.setPurchaseOrderNumber(wholesaleOrder.getPurchaseOrderNumber());
        dtoOrder.setProductName(wholesaleOrder.getProduct().getName());
        dtoOrder.setTerms(wholesaleOrder.getTerms());
        dtoOrder.setShippedDate(wholesaleOrder.getShippedDate());
        dtoOrder.setProductCost(wholesaleOrder.getProduct().getCost());

//        System.out.println(">>\n"+dtoOrder.getCustomerName());
//        System.out.println(dtoOrder.getPurchaseDate());
//        System.out.println(dtoOrder.getPurchaseOrderNum());
//        System.out.println(dtoOrder.getProductName());
//        System.out.println(dtoOrder.getTerms());
//        System.out.println(dtoOrder.getShippedDate());
//        System.out.println(dtoOrder.getProductCost());

        return dtoOrder;
    }

//    private WholesaleOrder convertToEntity


    @GetMapping
    public List<DtoOrder> getAllOrders() {
        List<DtoOrder> list = new ArrayList<>();
//        orderRepository.findAll().forEach(list::add);
//        orderRepository.findAll().forEach(order -> list.add(convertToDto(order)));

//        List<WholesaleOrder> wholesaleOrderList = wholesaleOrderService.getAllOrders();
//        for(WholesaleOrder wholesaleOrder: wholesaleOrderList){
//            list.add(convertToDto(wholesaleOrder));
//        }
        return list;
    }

//    @GetMapping("/{productId}")
//    public Product getOneOrder(@PathVariable String orderIndex){
//        try {
//            int id = Integer.parseInt(orderIndex);
//            Product product = service.getOrderB(id);
//            return product;
//        } catch (NumberFormatException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
//        } catch (ResourceNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//    }
}
