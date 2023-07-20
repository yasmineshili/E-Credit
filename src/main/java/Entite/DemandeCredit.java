package Entite;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class DemandeCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDate dateCredit;
    
    /*NB : should be an enumeration just put is string for speed of dev*/
    /*Possible Values :  En cours,Valide,Rejete*/
    private String etat;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TYPE_CREDIT")
    private Type_Credit typeCredit;
    
    @NotNull(message = "Le montant est obligatoire")
    @Min(value = 0, message = "Le montant doit être un entier positif")
    private Integer montant;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_UNITE")
    private Unite unite;
    
    
    @OneToMany(mappedBy = "demandeCredit", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PieceJointe> piecesJointes;

    
    private int nbreEcheance;

    public DemandeCredit() {
    }

    public DemandeCredit(int id, Compte compte, Type_Credit typeCredit,
            @NotNull(message = "Le montant est obligatoire") @Min(value = 0, message = "Le montant doit être un entier positif") Integer montant,
            Unite unite, int nbreEcheance) {
        super();
        this.id = id;
        this.compte = compte;
        this.typeCredit = typeCredit;
        this.montant = montant;
        this.unite = unite;
        this.nbreEcheance = nbreEcheance;
    }

    public DemandeCredit(Compte compte, Type_Credit typeCredit, Integer montant, Unite unite, int nbreEcheance) {
        this.compte = compte;
        this.typeCredit = typeCredit;
        this.montant = montant;
        this.unite = unite;
        this.nbreEcheance = nbreEcheance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Type_Credit getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(Type_Credit typeCredit) {
        this.typeCredit = typeCredit;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public int getNbreEcheance() {
        return nbreEcheance;
    }

    public void setNbreEcheance(int nbreEcheance) {
        this.nbreEcheance = nbreEcheance;
    }
    
    public List<PieceJointe> getPiecesJointes() {
        return piecesJointes;
    }

    public void setPiecesJointes(List<PieceJointe> piecesJointes) {
        this.piecesJointes = piecesJointes;
    }

	public LocalDate getDateCredit() {
		return dateCredit;
	}

	public void setDateCredit(LocalDate dateCredit) {
		this.dateCredit = dateCredit;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

    
}
