package it.uniroma3.siw.ProgettoSIW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.ProgettoSIW.model.NFT;
import it.uniroma3.siw.ProgettoSIW.service.CredentialsService;
import it.uniroma3.siw.ProgettoSIW.service.NFTService;
import it.uniroma3.siw.ProgettoSIW.service.UserService;




@Controller
public class NFTSController {

    @Autowired
    private NFTService service;
    
	@Autowired
	CredentialsService cs;
	
	@Autowired
	UserService us;
	
//	@Autowired
//	FirebaseStorageStrategy fss;

    @GetMapping(value = "/NFTS")
    public String getNFTS(Model model) {
    	model.addAttribute("nfts",service.getAllNFTs());
        return "market";
    }
    
    @RequestMapping(value="/addNFT",method=RequestMethod.GET)
    public String addNft(Model model) {
    	model.addAttribute("nft",new NFT());
    	return "new_nft_form";
    }
    
    @RequestMapping(value="/nft_complete_creation",method=RequestMethod.POST)
    public String processNFTCreation(@ModelAttribute("nft")NFT nft,@RequestParam("file")MultipartFile file,BindingResult result) {
    	if(file.isEmpty()) return "new_nft_fom";
    	try {
    	//upload image to firebase storage
//    	String url = fss.uploadFile(file);    	
    	//set new FirebaseStorageURL to nft.image
//    	nft.image=url;
    	//save nft
		service.saveNewNFT(nft);
    	return "home";
    	}catch(Exception e) {
    		//TODO:ADD upload error
    		return "new_nft_form";
    	}
    }
    
    

//    @GetMapping(value = "/artwork/{id}")
//    public String getArtwork(@PathVariable("id") Long id, Model model) {
//        NFT artwork = this.service.findById(id);
//        model.addAttribute("artwork", artwork);
//        return "artwork";
//    }

//    @PostMapping(value = "/admin/artwork")
//    public String addNewArtwork(@ModelAttribute("artwork") NFT artwork, Model model, BindingResult result) {
//        this.validator.validate(artwork, result);
//        if (!result.hasErrors()) {
//            this.service.add(artwork);
//            return "admin/home";
//        }
//        return "admin/artworkForm";
//    }

//    @GetMapping(value = "/admin/artwork")
//    public String showArtworkForm(Model model) {
//        model.addAttribute("artwork", new NFT());
//        model.addAttribute("artists", this.artistService.findAll());
//        model.addAttribute("artCollections", this.collectionService.findAll());
//        return "admin/artworkForm";
//    }
}
