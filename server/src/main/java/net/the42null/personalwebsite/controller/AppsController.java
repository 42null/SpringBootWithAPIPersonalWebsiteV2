package net.the42null.personalwebsite.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.the42null.personalwebsite.Entity.ItemContainer;
import net.the42null.personalwebsite.Entity.MenuPanel;
import net.the42null.personalwebsite.Entity.PageUpdateBar;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;
import net.the42null.personalwebsite.helpers.ContentPather;
import net.the42null.personalwebsite.helpers.InputSanitizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static net.the42null.personalwebsite.helpers.InputSanitizer.DEFAULT_NUM_OF_CHARACTERS;


@Controller
@RequestMapping("/apps")
public class AppsController {

	private List<MenuPanel> appContainers;


	@PostConstruct
	private void initData(){
//		TODO: Reduce duplicate code
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

//        Settings for content path generation //DUPLICATE CODE but used as movement of .json files could change
		ContentPather cPather = new ContentPather();
		/*Apps*/
		try {
			appContainers = List.of(mapper.readValue(cPather.generateResourcePath("apps.json"), MenuPanel[].class));
		} catch (IOException e) {
			e.printStackTrace();
			appContainers = List.of(new MenuPanel[0]);
		}
	}
	@GetMapping("/{appName:^[a-zA-Z0-9]{1,20}$}")//TODO: Make redirect instead?
	public String exampleController(@PathVariable("appName") String appName, Model model) {
		String appNameSterialized = InputSanitizer.sanitizeInput(appName, InputSanitizer.DEFAULT_NUM_OF_CHARACTERS);
		model.addAttribute("pageTitle", Character.toUpperCase(appNameSterialized.charAt(0))+(appNameSterialized.length()>1? appNameSterialized.substring(1): ""));

		MenuPanel app = appContainers.stream().filter(container ->
											  container.getUrlEnding().toLowerCase().equals(appNameSterialized))
											  .findFirst()
											  .orElse(null);
		if(app == null){
			app = appContainers.stream().filter(container ->
									    container.getType().equals(MenuPanel.Type.ALL))
										.findFirst()
										.orElse(null);
			model.addAttribute("pageTitle", "App Not Found");
			if(app == null){
				throw new RuntimeException("'ALL' apps menuPanel not found");
			}
		}else{
			model.addAttribute("pageTitle", Character.toUpperCase(appNameSterialized.charAt(0))+(appNameSterialized.length()>1? appNameSterialized.substring(1): ""));
		}
		model.addAttribute("menuContainer", app);
		return "apps/generatedAppPage";
	}
}