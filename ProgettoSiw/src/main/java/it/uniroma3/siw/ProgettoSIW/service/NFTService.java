package it.uniroma3.siw.ProgettoSIW.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import it.uniroma3.siw.ProgettoSIW.exception.NftNotFoundException;
import it.uniroma3.siw.ProgettoSIW.exception.PostException;
import it.uniroma3.siw.ProgettoSIW.model.NFT;
import it.uniroma3.siw.ProgettoSIW.repository.NftRepository;


@Service
public class NFTService {
	
	@Autowired
	private NftRepository nr;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public NFT getSingleNFTbyId(Long id) {
		return nr.findById(id).orElseThrow(()-> new NftNotFoundException((long)id));
	}
	
//	@Transactional
//	public List<NFT> getAllNFTsWithSameId(Long id) {
//		TypedQuery<NFT> query = null;
//		query=em.createQuery("SELECT n FROM NFT n WHERE id in ( SELECT mynfts_id FROM Users_Mynfts WHERE user_id = :user_id_requested) ",NFT.class);
//		query.setParameter("user_id_requested", id);
//		return query.getResultList();
//	}
	
	@Transactional
	public List<NFT> getAllNFTs() {
		return (List<NFT>) nr.findAll();
	}
	
	/* SELECT n FROM NFT n WHERE user_id = 1 */
	
	@Transactional
	public List<NFT> getAllNFTsFromPerson(Long id) {
		TypedQuery<NFT> query = null;
		query=em.createQuery("SELECT n FROM NFT n WHERE user_id = :user_id_requested",NFT.class);
		query.setParameter("user_id_requested", id);
		return query.getResultList();
	}
	
	/** 
	 Saves a new NFT
	 @param NFT
	 @return void
	 * */
	@Transactional
	public NFT saveNewNFT(NFT nft) {	
		return nr.save(nft);
	}
	
	
	@Transactional
	public List<NFT> saveAllNFT(List<NFT> nfts) {
		return (List<NFT>) nr.saveAll(nfts);
	}
	
	@Transactional
	public NFT replaceNFT(NFT nft,long id) {
		return nr.findById(id).map(newNft -> {
			newNft.setTitle(nft.getTitle());
			newNft.setPrice(nft.getPrice());
			return nr.save(newNft);
		}).orElseGet(()->{
			nft.setId(id);
			return nr.save(nft);
		}) ;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		nr.deleteById((long) id);
		return;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//    @Autowired
//    private ArtworkRepository artworkRepository;
//
//    @Transactional
//    public NFT add(NFT artwork) {
//        return artworkRepository.save(artwork);
//    }
//
//    public List<NFT> findByTitle(String title) {
//        return artworkRepository.findByTitle(title);
//    }
//
//    public List<NFT> findByArtistId(Long id) {
//        return artworkRepository.findByArtist_Id(id);
//    }
//
//    @Transactional
//    public NFT findById(Long id) {
//        Optional<NFT> artwork = artworkRepository.findById(id);
//        return artwork.orElse(null);
//    }
//
//    public List<NFT> getAll() {
//        return (List<NFT>) artworkRepository.findAll();
//    }
//
//    @Transactional
//    public boolean alreadyExists(NFT artwork) {
//        List<NFT> artworks = artworkRepository.findByTitle(artwork.getTitle());
//        return artworks.size() > 0;
//    }

