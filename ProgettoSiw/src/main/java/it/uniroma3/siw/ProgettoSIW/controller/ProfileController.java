package it.uniroma3.siw.ProgettoSIW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.ProgettoSIW.model.Credentials;
import it.uniroma3.siw.ProgettoSIW.model.User;
import it.uniroma3.siw.ProgettoSIW.repository.UserRepository;
import it.uniroma3.siw.ProgettoSIW.service.CredentialsService;
import it.uniroma3.siw.ProgettoSIW.service.NFTService;
import it.uniroma3.siw.ProgettoSIW.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	UserService us;
	
	@Autowired
    private NFTService service;
	
	@Autowired
	CredentialsService cs;
	
	@RequestMapping(value="/profilo",method = RequestMethod.GET)
	public String showProfilePage(Model model) {//#authentication.getPrincipal().getUser().getFirstName()
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		Credentials c = cs.getCredentialsByUsername(currentUserName);
		User user =us.getUserById(c.getId());
		model.addAttribute("user", user) ;
		return "profilo";
	}
	
	@GetMapping("/profile/MyNFTS")
    public String showAllUserNFTS(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		Credentials c = cs.getCredentialsByUsername(currentUserName);
    	model.addAttribute("nfts",service.getAllNFTsFromPerson(c.getId()));
    	return "myNFTs";
    }

}
