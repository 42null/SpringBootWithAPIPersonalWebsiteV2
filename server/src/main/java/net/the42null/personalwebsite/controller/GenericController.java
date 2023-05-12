package net.the42null.personalwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller controls generic pages that do not have their own html pages but instead are automatically generated
 */
@Controller
//@CrossOrigin(origins="http://localhost:63342")
public class GenericController {

	@GetMapping("/generic")
	public String showPlatformsHubPage(Model model) {
		model.addAttribute("pageTitle", "");
		return "generics/GenericMessagePage";
	}

	@PostMapping("/thankYou")
	public String save(@RequestParam("text") String text, Model model) {
		// Save the text somewhere
//		model.addAttribute("savedText", text);

		model.addAttribute("pageTitle", "Form submitted");
		model.addAttribute("header", "Thank You!");
		model.addAttribute("subheader", "Your form has been submitted");
		model.addAttribute("furtherText", "We have NOT recorded your message \""+text+"\". With the transfer to V2, we have not created a database to link and will not until the project is further along");

//		model.addAttribute("heroImgSrc", "https://assets.website-files.com/5ee732bebd9839b494ff27cd/5ef09471731cbb40b3d85aac_firefox_2019_logo.jpg");
		return "generics/GenericMessagePage";
	}

}
