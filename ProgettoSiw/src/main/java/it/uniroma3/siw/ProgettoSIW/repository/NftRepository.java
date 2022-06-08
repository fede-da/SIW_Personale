package it.uniroma3.siw.ProgettoSIW.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.ProgettoSIW.model.NFT;


public interface NftRepository extends CrudRepository<NFT, Long> {
	public List<NFT> findByTitle(String title);
	
}
