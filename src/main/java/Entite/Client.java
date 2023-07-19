package Entite;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private LocalDate dateNaissance;
    private String situationFamiliale;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;


    public Client(Long id, String nom, String prenom, String cin, LocalDate dateNaissance, String situationFamiliale,
			Utilisateur utilisateur) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.dateNaissance = dateNaissance;
		this.situationFamiliale = situationFamiliale;
		this.utilisateur = utilisateur;
	}

	public Client() {
    }

    public Client(String nom, String prenom, String cin, LocalDate dateNaissance, String situationFamiliale) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.situationFamiliale = situationFamiliale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
