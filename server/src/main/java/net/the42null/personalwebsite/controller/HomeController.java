package net.the42null.personalwebsite.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.the42null.personalwebsite.Entity.*;
import net.the42null.personalwebsite.Service.WholesaleOrderService;
import net.the42null.personalwebsite.dto.DtoOrder;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;
import net.the42null.personalwebsite.helpers.AgeFormatter;
import net.the42null.personalwebsite.helpers.ContentPather;
import net.the42null.personalwebsite.repo.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@CrossOrigin(origins= {
//        "http://localhost:63342",
//        "http://nullified.mooo.com:63342",
//        "http://nullified.mooo.com:80",
//        "http://nullified.mooo.com:8080",
//        "http://192.168.1.24:8080",
//        "http://192.168.1.24:80",
//        "http://192.168.1.24:63342"
//})
public class HomeController {

    private String[] rules;
    private GithubRepository[] pageRepositories;
    private PageUpdateBar[] pageUpdateBars;
    private Contact[] contacts;

/*Containers*/
    private List<ItemContainer> aboutMeContainers;
    private List<ItemContainer> personalWebsiteContainers;
    private List<ItemContainer> portfolioWebsiteContainers;

    @PostConstruct
    private void initData() {
        ObjectMapper mapper = new ObjectMapper();
//        Mapper settings
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        Settings for content path generation
        ContentPather cPather = new ContentPather();


        try {
            rules = mapper.readValue(cPather.generateResourcePath("croquetRules.json"), String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            rules = new String[0];
        }

        RestTemplate restTemplate = new RestTemplate();
        pageRepositories = restTemplate.getForObject("https://api.github.com/users/42null/repos", GithubRepository[].class);
        assert pageRepositories != null;
        Arrays.sort(pageRepositories, (r1, r2) -> r2.getPushedAt().compareTo(r1.getPushedAt()));

        try {
            aboutMeContainers = List.of(mapper.readValue(cPather.generateResourcePath("aboutMes.json"), ItemContainer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            aboutMeContainers = List.of(new ItemContainer[0]);
        }

        try {
            pageUpdateBars = mapper.readValue(cPather.generateResourcePath("pageUpdateBars.json"), PageUpdateBar[].class);
        } catch (IOException e) {
            e.printStackTrace();
            pageUpdateBars = new PageUpdateBar[0];
        }
        /*Contacts*/
        try {
            contacts = mapper.readValue(cPather.generateResourcePath("contacts.json"), Contact[].class);
        } catch (IOException e) {
            e.printStackTrace();
            contacts = new Contact[0];
        }
        /*Personal Websites*/
        try {
            personalWebsiteContainers = List.of(mapper.readValue(cPather.generateResourcePath("websites.json"), ItemContainer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            personalWebsiteContainers = List.of(new ItemContainer[0]);
        }
        /*Portfolio Websites*/
        try {
            portfolioWebsiteContainers = List.of(mapper.readValue(cPather.generateResourcePath("portfolioSites.json"), ItemContainer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            portfolioWebsiteContainers = List.of(new ItemContainer[0]);
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

    @GetMapping("/test")
    public String showTempTest(Model model){
        return "quickTempTest";
    }

    @GetMapping("/about")
    public String showAboutMe(Model model) {
        model.addAttribute("imageUrl", "/img/pageImages/OfMe/KMGraduation2.JPG");
        model.addAttribute("pageTitle", "About Me");
//        model.addAttribute("heroImgSrc", "https://avatars.githubusercontent.com/u/67847710");

//        TODO: Optimise filtering
        List<ItemContainer> education = aboutMeContainers.stream()
                                                            .filter(a -> (a.getType() == ItemContainer.Type.EDUCATION))
                                                            .sorted((a, b) -> b.getId().compareTo(a.getId()))
                                                            .collect(Collectors.toList());
        List<ItemContainer> achievements = aboutMeContainers.stream()
                                                            .filter(a -> (a.getType() == ItemContainer.Type.ACCOMPLISHMENTS))
                                                            .sorted((a, b) -> b.getId().compareTo(a.getId()))
                                                            .collect(Collectors.toList());
        List<ItemContainer> workExperience = aboutMeContainers.stream()
                                                            .filter(a -> (a.getType() == ItemContainer.Type.WORK_EXPERIENCE))
                                                            .sorted((a, b) -> b.getId().compareTo(a.getId()))
                                                            .collect(Collectors.toList());
        List<ItemContainer> certifications = aboutMeContainers.stream()
                                                            .filter(a -> (a.getType() == ItemContainer.Type.CERTIFICATE))
                                                            .sorted((a, b) -> b.getId().compareTo(a.getId()))
                                                            .collect(Collectors.toList());
        model.addAttribute("contentBoxesEducation", education);
        model.addAttribute("contentBoxesAchievements", achievements);
        model.addAttribute("contentBoxesWorkExperience", workExperience);
        model.addAttribute("contentBoxesCertifications", certifications);
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

    //--- Apps
    @GetMapping("/apps")
    public String showAppsHubPage(Model model) {
        model.addAttribute("pageTitle", "Apps");
//
////        TODO: Optimise filtering & filter out labeled as examples or tests
//        List<MenuPanel> apps = appContainers.stream()
////                                                .filter(a -> (a.getType() == ItemContainer.Type.EDUCATION))
////                                                .sorted((a, b) -> b.getId().compareTo(a.getId()))
//                                                .collect(Collectors.toList());
//        model.addAttribute("menuContainers", apps);

        return "apps/appsHub";
    }

    @GetMapping("/personalSite")
    public String showPersonalSite(Model model) {
        model.addAttribute("pageTitle", "Personal Website Copies");
        model.addAttribute("contentBoxes", personalWebsiteContainers);
        model.addAttribute("noticeHeader", "Recent Website Portfolio");
        model.addAttribute("noticeMessage", "I host a copy of this website in multiple locations for practice, version deployment, and accessibility. Because I push updates regularly, the functionality of some of these installations may be hampered, nullified.mooo.com will host the most stable version.");
        return "web/web";
    }
    @GetMapping("/portfolioSites")
    public String showWebPage(Model model){
        model.addAttribute("pageTitle", "Portfolio Sites");
        model.addAttribute("contentBoxes", portfolioWebsiteContainers);
        model.addAttribute("noticeHeader", "Portfolio Sites");
        model.addAttribute("noticeMessage", "These are standalone websites that I have worked on for demonstrations in either my free time or for a class project. They are seperated from this site");
        return "web/webCardsWithEmbed";
    }

    //PORTFOLIO SITES (START)
    @GetMapping("/portfolioSiteCompanyDatabase")
    public String showPortfolioCompanyDatabase(Model model){
        model.addAttribute("pageTitle", "Portfolio Sites");
        model.addAttribute("contentBoxes", portfolioWebsiteContainers);
        model.addAttribute("noticeHeader", "Portfolio Sites");
        model.addAttribute("noticeMessage", "These are standalone websites that I have worked on for demonstrations in either my free time or for a class project. They are seperated from this site");
        return "standalonePortfolioSites/MemmelModule4Assignment/index";
    }
    //PORTFOLIO SITES (END)
//NAV (END)

//CONTACT (START)
    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("pageTitle", "Contact Me");
        model.addAttribute("contacts", contacts);

        return "contact/contact";
    }
    @GetMapping("/contactForm")
    public String showContactFormPage(Model model) {
        return "contact/contact";
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
