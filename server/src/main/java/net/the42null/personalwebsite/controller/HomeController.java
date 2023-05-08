package net.the42null.personalwebsite.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.the42null.personalwebsite.Entity.*;
import net.the42null.personalwebsite.Service.WholesaleOrderService;
import net.the42null.personalwebsite.dto.DtoOrder;
import net.the42null.personalwebsite.helpers.AgeFormatter;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@CrossOrigin(origins="http://localhost:63342")
public class HomeController {

    private String[] rules;
    private GithubRepository[] pageRepositories;
    private PageUpdateBar[] pageUpdateBars;
    private Contact[] contacts;

/*Containers*/
    private List<ItemContainer> aboutMeContainers;
    private List<ItemContainer> websiteContainers;


    @PostConstruct
    private void initData() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);



        try {
            rules = mapper.readValue(Paths.get("server/src/main/resources/static/content/croquetRules.json").toFile(), String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            rules = new String[0];
        }


        RestTemplate restTemplate = new RestTemplate();
        pageRepositories = restTemplate.getForObject("https://api.github.com/users/42null/repos", GithubRepository[].class);
        assert pageRepositories != null;
        Arrays.sort(pageRepositories, (r1, r2) -> r2.getPushedAt().compareTo(r1.getPushedAt()));


        try {
            aboutMeContainers = List.of(mapper.readValue(Paths.get("server/src/main/resources/static/content/aboutMes.json").toFile(), ItemContainer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            aboutMeContainers = List.of(new ItemContainer[0]);
        }

        try {
            pageUpdateBars = mapper.readValue(Paths.get("server/src/main/resources/static/content/pageUpdateBars.json").toFile(), PageUpdateBar[].class);
        } catch (IOException e) {
            e.printStackTrace();
            pageUpdateBars = new PageUpdateBar[0];
        }
        /*Contacts*/
        try {
            contacts = mapper.readValue(Paths.get("server/src/main/resources/static/content/contacts.json").toFile(), Contact[].class);
        } catch (IOException e) {
            e.printStackTrace();
            contacts = new Contact[0];
        }
        /*Websites*/
        try {
            websiteContainers = List.of(mapper.readValue(Paths.get("server/src/main/resources/static/content/websites.json").toFile(), ItemContainer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            websiteContainers = List.of(new ItemContainer[0]);
        }
    }


//NAV (START)
    @GetMapping("/")
    public String showHome(Model model){
//        model.addAttribute("orderList", wholesaleOrderService.getAllOrders());
        model.addAttribute("pageTitle", "AJ Memmel");
        model.addAttribute("heroImgSrc", "/img/pageImages/CoverImage.jpg");
//        model.addAttribute("heroImgSrc", "https://avatars.githubusercontent.com/u/67847710");
        model.addAttribute("updateBars", pageUpdateBars);
        return "index";
    }

    @GetMapping("/about")
    public String showAboutMe(Model model) {
        model.addAttribute("imageUrl", "/img/pageImages/OfMe/KMGraduation2.JPG");
        model.addAttribute("pageTitle", "About Me");
//        model.addAttribute("heroImgSrc", "https://avatars.githubusercontent.com/u/67847710");

//        TODO: Optimise filtering
        List<ItemContainer> achievements = aboutMeContainers.stream()
                                                            .filter(a -> (a.getId() >= 1000 && a.getId() < 2000))
                                                            .collect(Collectors.toList());
        List<ItemContainer> workExperience = aboutMeContainers.stream()
                                                            .filter(a -> (a.getId() >= 2000 && a.getId() < 3000))
                                                            .collect(Collectors.toList());
        List<ItemContainer> relevantCoursework = aboutMeContainers.stream()
                                                            .filter(a -> (a.getId() >= 2000 && a.getId() < 3000))
                                                            .collect(Collectors.toList());
        model.addAttribute("contentBoxesAchievements", achievements);
        model.addAttribute("contentBoxesWorkExperience", workExperience);
        model.addAttribute("contentBoxesRelevantCoursework", relevantCoursework);
        return "about/aboutMe";
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

        AgeFormatter ageFormatter = new AgeFormatter();
        model.addAttribute("ageFormatter", ageFormatter);

//        String message = "Hello, world!";
//        model.addAttribute("message", message);
        return "platforms/github";
    }
    @GetMapping("/websites")
    public String showWebPage(Model model) {
        model.addAttribute("pageTitle", "Websites");
        model.addAttribute("contentBoxes", websiteContainers);
        return "/web/web";
    }
//NAV (END)

//CONTACT (START)
    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("pageTitle", "Contact Me");
        model.addAttribute("contacts", contacts);

        return "/contact/contact";
    }
    @GetMapping("/contactForm")
    public String showContactFormPage(Model model) {
        return "/contact/contact";
    }
    //generics

//CONTACT (END)

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
        orderRepository.findAll().forEach(order -> list.add(convertToDto(order)));

        List<WholesaleOrder> wholesaleOrderList = wholesaleOrderService.getAllOrders();
        for(WholesaleOrder wholesaleOrder: wholesaleOrderList){
            list.add(convertToDto(wholesaleOrder));
        }
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
