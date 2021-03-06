package it.uniroma3.siw.ProgettoSIW.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.ProgettoSIW.exception.NftNotFoundException;
import it.uniroma3.siw.ProgettoSIW.model.NFT;
import it.uniroma3.siw.ProgettoSIW.service.NFTService;

@RestController
@RequestMapping("/restNFT")
public class NFTRestController {
	
	//reference : https://spring.io/guides/tutorials/rest/

	@Autowired
    private NFTService service;
	
	//curl GET http://127.0.0.1:8080/restNFT/1   example
	
	@RequestMapping(value="/{nft_id}",method=RequestMethod.GET)
	public NFT getNFTbyID(@PathVariable int nft_id) {
		return service.getSingleNFTbyId((long) nft_id);
	}
	
	//curl GET http://127.0.0.1:8080/restNFT/user/1
	@RequestMapping(value="/user/{persona_id}",method=RequestMethod.GET)
	public List<NFT> getAllNFTsFromPerson(@PathVariable int persona_id) {
		return service.getAllNFTsFromPerson((long) persona_id);
	}
	
	//curl -X POST http://127.0.0.1:8080/restNFT/nft -d '{}'
	@RequestMapping(value="/nft",method=RequestMethod.POST)
	public NFT saveNFT(@RequestBody  NFT nft) {
		return service.saveNewNFT(nft) ;
	}
	
	//curl --data "param1=value1&param2=value2" http://127.0.0.1:8080/restNFT/manyNFTs   example
	@RequestMapping(value="/manyNFTs",method=RequestMethod.POST)
	public List<NFT> saveAllNFTs(@RequestBody List<NFT> nfts) {
		return service.saveAllNFT(nfts);
	}
	
	@PutMapping("/{nft_id}")
	public NFT updateNFT(@RequestBody NFT nft, @PathVariable int nft_id) {
		return service.replaceNFT(nft, (long) nft_id);
	}
	
	@DeleteMapping("/{nft_id}")
	public void deleteNFT(@PathVariable int nft_id) {
		service.deleteById(nft_id);
	}
}






















