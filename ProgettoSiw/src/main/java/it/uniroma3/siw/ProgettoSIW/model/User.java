package it.uniroma3.siw.ProgettoSIW.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    public User() {
    	this.name=getRandomString();
    	this.myNFTs = new ArrayList<NFT>();
    	this.myNFTs.add(new NFT((long) 1));
    	this.myNFTs.add(new NFT((long) 2));
    }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
    private String name ="";
	
	@OneToMany(cascade = { CascadeType.ALL,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JoinColumn(name="user_nft")
	private List<NFT> myNFTs;

    public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
	private String getRandomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
}
