package it.uniroma3.siw.ProgettoSIW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.ProgettoSIW.model.Credentials;
import it.uniroma3.siw.ProgettoSIW.model.User;
import it.uniroma3.siw.ProgettoSIW.service.CredentialsService;
import it.uniroma3.siw.ProgettoSIW.validator.CredentialsValidator;


@Controller
public class RegController {
	
	@Autowired
	private CredentialsService credentialService;
	
	@Autowired
	private CredentialsValidator cv;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegistrationForm(Model model) {
	    model.addAttribute("credentials", new Credentials());
	    return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST) 
	public String processRegister(@ModelAttribute("credentials")Credentials credentials,BindingResult result) {
		this.cv.validate(credentials, result);
		
		if(result.hasErrors()) {
			return "register";
		}
		credentials.setUser(new User());
		credentialService.saveCredentials(credentials);
	    return "login";
	}

}
