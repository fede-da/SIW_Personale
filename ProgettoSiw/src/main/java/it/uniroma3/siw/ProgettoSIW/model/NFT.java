package it.uniroma3.siw.ProgettoSIW.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



@Getter
@Setter
@Entity
public class NFT {
	
	//Manca roba
    public NFT(Long new_user_id) {
    	this.title=getRandomString();
    	this.price=0;
    	this.description="Niente";
    	this.image="";
    	this.user_id=new_user_id;
    }
    
    public NFT() {
    	this.title=getRandomString();
    	this.price=0;
    	this.description="Niente";
    	this.image="";
    	this.user_id=(long) 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
	private Long user_id;
    
   // @NotEmpty
    public String title;
    
    public String image;
    
    //@OneToOne
    //private User user;
    
  //  @NotEmpty
    public float price;
    
    public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
    
   public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	// @NotEmpty
    public String description;
    
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
    
    public String getTitle() {
    	return this.title;
    }
    
    public void setTitle(String newTitle) {
    	this.title=newTitle;
    	return;
    }
    
}
