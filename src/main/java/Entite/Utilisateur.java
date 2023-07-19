package Entite;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Utilisateur {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	  private Long id;
	  
	    @Column(name = "email")
        private String email;
	    
    @Column(name = "mot_de_passe")
    private String mot_de_passe;
    private boolean loggedIn;
    
    
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(String email, String mot_de_passe, boolean loggedIn, Role role) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.loggedIn = loggedIn;
        this.role = role;
    }

    
    
    public Utilisateur(Long id, String email, String mot_de_passe, boolean loggedIn, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
		this.loggedIn = loggedIn;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
