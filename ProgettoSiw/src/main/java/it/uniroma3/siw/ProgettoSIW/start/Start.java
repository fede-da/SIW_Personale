package it.uniroma3.siw.ProgettoSIW.start;

import it.uniroma3.siw.ProgettoSIW.model.*;
import it.uniroma3.siw.ProgettoSIW.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	NFTService nftService;
	
    @Autowired
    private CredentialsService credentialsService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	NFT first = new NFT();
    	NFT second = new NFT();
    	NFT third = new NFT();
    	NFT fourth = new NFT();
    	credentialsService.saveCredentials(new Credentials("test1@test.com","p",2));
    	credentialsService.saveCredentials(new Credentials("test2@test.com","p",2));
    	nftService.saveNewNFT(first);
    	nftService.saveNewNFT(second);
    	nftService.saveAllNFT( List.of(third,fourth) );
    }
}
