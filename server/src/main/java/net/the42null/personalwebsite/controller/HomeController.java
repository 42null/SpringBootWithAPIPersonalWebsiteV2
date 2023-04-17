package net.the42null.personalwebsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.the42null.personalwebsite.Entity.GithubRepository;
import net.the42null.personalwebsite.Entity.WholesaleOrder;
import net.the42null.personalwebsite.Service.WholesaleOrderService;
import net.the42null.personalwebsite.dto.DtoOrder;
import net.the42null.personalwebsite.repo.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@CrossOrigin(origins="http://localhost:63342")
public class HomeController {

    private String[] rules;
    private GithubRepository[] pageRepositories = new GithubRepository[1];

//    TODO: Move
    public String getDaysSinceUpdatedAt(GithubRepository repository) {
        LocalDateTime updatedAt = repository.getUpdatedAt();
        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(updatedAt, now);
        if(days >= 182){
            return "date_old";
        }else if(days > 7){
            return "date_older";
        }else{
            return "date_recent";
        }
    }

    @PostConstruct
    private void initData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            rules = mapper.readValue(Paths.get("server/src/main/resources/static/content/croquetRules.json").toFile(), String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            rules = new String[0];
        }


        RestTemplate restTemplate = new RestTemplate();
        pageRepositories = restTemplate.getForObject("https://api.github.com/users/42null/repos", GithubRepository[].class);
        Arrays.sort(pageRepositories, (r1, r2) -> r2.getUpdatedAt().compareTo(r1.getUpdatedAt()));


    }


//NAV (START)
    @GetMapping("/")
    public String showHome(Model model){
//        model.addAttribute("orderList", wholesaleOrderService.getAllOrders());

        return "index";
    }
//--- About Me
    @GetMapping("/about/aboutMe")
    public String showAboutMe(Model model) {
        model.addAttribute("pageTitle", "Rules of Golf Croquet");

        /**
         * Add the array of Strings to the model
         */

        return "about/aboutme";
    }
    //--- Platforms
    @GetMapping("/platforms")
    public String showPlatformsHubPage(Model model) {
        model.addAttribute("pageTitle", "platforms");
        return "platforms/platformsHub";
    }
    @GetMapping("/platforms/github")
    public String showGithubPage(Model model) {
        model.addAttribute("pageTitle", "GitHub");
        model.addAttribute("pageRepositories", pageRepositories);
        return "platforms/github";
    }

//NAV (END)



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
