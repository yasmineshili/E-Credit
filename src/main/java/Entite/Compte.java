package Entite;

import java.time.LocalDate;

import javax.persistence.*;



@Entity
public class Compte {
	
	@Id
	private int id;
    private int idTypeCompte;
    private String devise;
    private LocalDate dateOuverture;
    private int idClient;

    
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    
    
    public Compte() {
    }

    public Compte(int id, int idTypeCompte, String devise, LocalDate dateOuverture, int idClient) {
        this.id = id;
        this.idTypeCompte = idTypeCompte;
        this.devise = devise;
        this.dateOuverture = dateOuverture;
        this.idClient = idClient;
    }

    public Compte(int idTypeCompte, String devise, LocalDate dateOuverture, int idClient) {
        this.idTypeCompte = idTypeCompte;
        this.devise = devise;
        this.dateOuverture = dateOuverture;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTypeCompte() {
        return idTypeCompte;
    }

    public void setIdTypeCompte(int idTypeCompte) {
        this.idTypeCompte = idTypeCompte;
    }

    public String getDevise() {
        return devise;
    }


    public void setDevise(String devise) {
        this.devise = devise;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
