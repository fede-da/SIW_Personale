package it.uniroma3.siw.ProgettoSIW.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import it.uniroma3.siw.ProgettoSIW.model.User;


@Entity
@Getter
@Setter
public class Credentials {
    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty
//    @Size(min = 5, max = 250)
    private String username;

	@Column(nullable = false)
	@NotEmpty
//    @Size(min = 8, max = 20)
    private String password;

    @Column(nullable = false)
    private String role = "DEFAULT";

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;
    
    public Credentials() {
	}
    
    public Credentials(String email, String newPassword,long newId) {
    	this.username=email;
    	this.password=newPassword;
    	this.id=newId;
    	this.user= new User();
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return this.role;
	}
}
