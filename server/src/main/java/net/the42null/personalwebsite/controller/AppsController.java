package net.the42null.personalwebsite.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.the42null.personalwebsite.Entity.ItemContainer;
import net.the42null.personalwebsite.Entity.MenuPanel;
import net.the42null.personalwebsite.Entity.PageUpdateBar;
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
	@GetMapping("/{appName:^[a-z0-9]{1,20}$}")//TODO: Make redirect instead?
	public String exampleController(@PathVariable("appName") String appName, Model model) {
		String appNameSerialized = InputSanitizer.sanitizeInput(appName, InputSanitizer.DEFAULT_NUM_OF_CHARACTERS);
		model.addAttribute("pageTitle", Character.toUpperCase(appNameSerialized.charAt(0))+(appNameSerialized.length()>1? appNameSerialized.substring(1): ""));

		MenuPanel app = appContainers.stream().filter(container ->
											  container.getHeader().toLowerCase().equals(appNameSerialized))
											  .findFirst()
											  .orElse(null);
		model.addAttribute("menuContainer", app);


		return "apps/generatedAppPage";
	}
}