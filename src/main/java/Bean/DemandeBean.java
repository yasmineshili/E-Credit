package Bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import Entite.Client;
import Entite.Compte;

import Entite.Document;
import Service.ClientService;
import Service.ClientServiceImpl;
import Service.CompteService;
import Service.CompteServiceImpl;
import Service.DemandeService;
import Service.DemandeServiceImpl;

@SuppressWarnings("serial")
@ManagedBean(name = "DemandeBean")
@ViewScoped
public class DemandeBean implements Serializable {
	



	@PostConstruct
	public void init() {
	  
		fetchTypesCredit();
        fetchUnites();

	      
	    if (!isUserAuthorized()) {
	        redirectToAccessDenied();
	    }
	    

	}

	
	private boolean isUserAuthorized() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String loggedInRole = (String) externalContext.getSessionMap().get("role");

		// Vérifier si l'utilisateur est connecté en tant qu'utilisateur ou en tant
		// qu'administrateur
		if ("User".equals(loggedInRole)) {
			return true;
		}

		// Vérifier si l'utilisateur a passé par la page de connexion
		String referringPage = externalContext.getRequestHeaderMap().get("referer");
		if (referringPage != null && referringPage.contains("Login.xhtml")) {
			return true;
		}

		return false;
	}

	private void redirectToAccessDenied() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, "/access-denied.xhtml?faces-redirect=true");
	}

	
	
	// partie client et compte 
	
	private String cin;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String situationFamiliale;

	private LocalDate dateOuverture;
	private String numeroCompte;
	private String devise;
	
    private boolean disableNomInput;
    private boolean disablePrenomInput;
    private boolean disableDateNaissanceInput;
    private boolean disableDateOuvertureInput;
    private boolean disableSituationInput;
    private boolean disableDeviseInput;
    
    
	

	private List<String> numerosComptes; 
	
	
	private List<String> typesCredit; 
	private String typecredit;
	
	 private List<String> unites;
	    private String unite;
	
	



	ClientService clientService = new ClientServiceImpl();
	CompteService compteService = new CompteServiceImpl();
	DemandeService demandeService  = new DemandeServiceImpl();
	




	public DemandeBean() {
		numerosComptes = new ArrayList<>();
	

		 disableNomInput = true;
		 disablePrenomInput = true;
		 disableDateNaissanceInput = true;
		 disableDateOuvertureInput = true;
		 disableSituationInput = true;
		 setDisableDeviseInput(true);

		 
	}
	
    private boolean checkBoxDevise;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
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

	public List<String> getNumerosComptes() {
		return numerosComptes;
	}

	public void setNumerosComptes(List<String> numerosComptes) {
		this.numerosComptes = numerosComptes;
	}

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}
	
	public boolean isCheckBoxDevise() {
		return checkBoxDevise;
	}

	public void setCheckBoxDevise(boolean checkBoxDevise) {
		this.checkBoxDevise = checkBoxDevise;
	}


	
	
    public void onCinSelect(AjaxBehaviorEvent event) throws SQLException {
        try {
            String selectedCin = (String) ((UIInput) event.getSource()).getValue();
            if (selectedCin != null && !selectedCin.isEmpty()) {
                Client client = clientService.getClientByCIN(selectedCin);
                if (client != null) {
                    nom = client.getNom();
                    prenom = client.getPrenom();
                    dateNaissance = client.getDateNaissance();
                    situationFamiliale = client.getSituationFamiliale();

                    // Récupérer la liste des numéros de compte pour le client sélectionné
                    numerosComptes = compteService.getAllNumeroComptesForClient(selectedCin);

              
                    if (numerosComptes.size() == 1) {
                        numeroCompte = numerosComptes.get(0);
                        Compte compte = compteService.getCompteByNumero(numeroCompte);
                        if (compte != null) {
                            dateOuverture = compte.getDateOuverture();
                            devise = compte.getDevise();
                            System.out.println("Devise: " + devise);
                            System.out.println("Date d'ouverture: " + dateOuverture);
                        } else {
                            dateOuverture = null;
                            devise = null;
                        }
                    } else {
                        numeroCompte = null;
                        dateOuverture = null;
                        devise = null;
                    }


                } else {
                  
                    nom = null;
                    prenom = null;
                    dateNaissance = null;
                    situationFamiliale = null;
                    numerosComptes = new ArrayList<>();
                    numeroCompte = null;
                    dateOuverture = null;
                    devise = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun client trouvé avec le CIN donné.", null));
                }
            } else {
                // Réinitialiser les champs si le CIN est vide
                nom = null;
                prenom = null;
                dateNaissance = null;
                situationFamiliale = null;
                numerosComptes = new ArrayList<>();
                numeroCompte = null;
                dateOuverture = null;
                devise = null;
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si le CIN n'est pas un entier valide
            // Réinitialiser les champs
            nom = null;
            prenom = null;
            dateNaissance = null;
            situationFamiliale = null;
            numerosComptes = new ArrayList<>();
            numeroCompte = null;
            dateOuverture = null;
            devise = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numéro de CIN invalide.", null));
        }
    }




    public void onCompteSelect(AjaxBehaviorEvent event) throws SQLException {
        try {
            String selectedCompte = numeroCompte; 
            if (selectedCompte != null && !selectedCompte.isEmpty()) {
                Compte compte = compteService.getCompteByNumero(selectedCompte);
                if (compte != null) {
                    dateOuverture = compte.getDateOuverture();
                    devise = compte.getDevise();
                    checkBoxDevise = "Y".equals(devise); // Mettez à jour la valeur de checkBoxDevise en fonction de la valeur de devise
                } else {
                    dateOuverture = null;
                    devise = null;
                    checkBoxDevise = false;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun compte trouvé avec le numéro donné.", null));
                }
            } else {
                dateOuverture = null;
                devise = null;
                checkBoxDevise = false;
            }
        } catch (NumberFormatException e) {
            dateOuverture = null;
            devise = null;
            checkBoxDevise = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numéro de compte invalide.", null));
        }
    }
    
    
    
    //Partie Dossier
    
    

    
    
    public void fetchTypesCredit() {
        try {
            typesCredit = demandeService.getAllTypesCredit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void fetchUnites() {
        try {
            unites = demandeService.getAllUnites();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  

    private int getTypeCreditIdByName(String typeName) {
        int typeCreditId = 0;

        try {
            typeCreditId = demandeService.getTypeCreditIdByName(typeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeCreditId;
    }

    public void onTypeCreditSelect() throws SQLException{
        if (selectedTypeCredit != null) {
            int typeCreditId = getTypeCreditIdByName(selectedTypeCredit);
            nbreEcheances = demandeService.getNbreEcheancesByTypeCreditId(typeCreditId);
            selectedUnite = "Mensuelle"; // Sélectionne l'unité "Mensuelle" par défaut
        } else {
            nbreEcheances = 0;
            selectedUnite = null;
        }
    }

    public void onUniteChange() {
        if (selectedUnite != null && Objects.nonNull(nbreEcheances)) {
            int multiplicateur = 1;
            if (selectedUnite.equals("Trimestrielle")) {
                multiplicateur = 3;
            } else if (selectedUnite.equals("Semestrielle")) {
                multiplicateur = 6;
            }
            nbreEcheances = nbreEcheances * multiplicateur;
        }
    }





    
    private String selectedTypeCredit;
    private String selectedUnite;   
    private Integer montant;
    private int nbreEcheances;



 



    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

  

    public int getNbreEcheances() {
        return nbreEcheances;
    }

    public void setNbreEcheances(int nbreEcheances) {
        this.nbreEcheances = nbreEcheances;
    }
    
	/*
	 * public void onTypeCreditChange() { if (selectedTypeCredit != null) { int
	 * typeCreditId = selectedTypeCredit.getId(); nbreEcheances =
	 * demandeService.getNbreEcheancesByTypeCreditId(typeCreditId); selectedUnite =
	 * unites.get(0); // Sélectionne l'unité "Mensuelle" par défaut } else {
	 * nbreEcheances = 0; selectedUnite = null; } }
	 */

    
    
	/*
	 * public void onUniteChange() { if (selectedUnite != null) { if
	 * (!selectedUnite.getNom_unite().equalsIgnoreCase("Mensuelle")) { if
	 * (selectedUnite.getNom_unite().equalsIgnoreCase("Trimestrielle")) {
	 * nbreEcheances *= 3; } else if
	 * (selectedUnite.getNom_unite().equalsIgnoreCase("Semestrielle")) {
	 * nbreEcheances *= 6; } } } }
	 */
    
    




    
    //partie suivi 
    
    private LocalDate dateRelation;
    private String par;


    public LocalDate getDateRelation() {
        return dateRelation;
    }

    public void setDateRelation(LocalDate dateRelation) {
        this.dateRelation = dateRelation;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }
    
    
    //Partie Piece Jointe
    
    private List<Document> documents;
 /*   private List<Type_Credit> typesCredit;
    private Type_Credit selectedTypeCredit;*/

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

	/*
	 * public List<Type_Credit> getTypesCredit() { return typesCredit; }
	 * 
	 * public void setTypesCredit(List<Type_Credit> typesCredit) { this.typesCredit
	 * = typesCredit; }
	 * 
	 * public Type_Credit getSelectedTypeCredit() { return selectedTypeCredit; }
	 * 
	 * public void setSelectedTypeCredit(Type_Credit selectedTypeCredit) {
	 * this.selectedTypeCredit = selectedTypeCredit; }
	 */

	/*
	 * public void onTypeCreditSelect(AjaxBehaviorEvent event) {
	 * System.out.println("Méthode onTypeCreditSelect appelé "); selectedTypeCredit
	 * = (Type_Credit) ((UIInput) event.getSource()).getValue(); if
	 * (selectedTypeCredit != null) { documents =
	 * getDocumentsNecessairesByTypeCredit(selectedTypeCredit);
	 * System.out.println("Données récupérées depuis la base de données : " +
	 * documents.size() + " documents"); } else { documents = new ArrayList<>();
	 * System.out.println("Type de crédit non sélectionné"); } }
	 */

	/*
	 * private List<Document> getDocumentsNecessairesByTypeCredit(Type_Credit
	 * typeCredit) { if (typeCredit != null) { List<Document> documents =
	 * demandeService.getDocumentsNecessairesByTypeCredit(typeCredit);
	 * System.out.println("Méthode getDocumentsNecessairesByTypeCredit appelé");
	 * return documents; } else { return new ArrayList<>(); } }
	 */



	
    
    //upload
	
	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void fileUploadListener(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.file = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("File Uploaded Successfully"));
	}
    

	

    
    //Partie Observation
    
    private String observation;


    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

	
	public void submitForm() {
		// Code de traitement du formulaire
	}

	public boolean isDisableNomInput() {
		return disableNomInput;
	}

	public void setDisableNomInput(boolean disableNomInput) {
		this.disableNomInput = disableNomInput;
	}

	public boolean isDisablePrenomInput() {
		return disablePrenomInput;
	}

	public void setDisablePrenomInput(boolean disablePrenomInput) {
		this.disablePrenomInput = disablePrenomInput;
	}

	public boolean isDisableDateNaissanceInput() {
		return disableDateNaissanceInput;
	}

	public void setDisableDateNaissanceInput(boolean disableDateNaissanceInput) {
		this.disableDateNaissanceInput = disableDateNaissanceInput;
	}

	public boolean isDisableDateOuvertureInput() {
		return disableDateOuvertureInput;
	}

	public void setDisableDateOuvertureInput(boolean disableDateOuvertureInput) {
		this.disableDateOuvertureInput = disableDateOuvertureInput;
	}

	public boolean isDisableSituationInput() {
		return disableSituationInput;
	}

	public void setDisableSituationInput(boolean disableSituationInput) {
		this.disableSituationInput = disableSituationInput;
	}

	public boolean isDisableDeviseInput() {
		return disableDeviseInput;
	}

	public void setDisableDeviseInput(boolean disableDeviseInput) {
		this.disableDeviseInput = disableDeviseInput;
	}



	public String getSelectedTypeCredit() {
		return selectedTypeCredit;
	}


	public void setSelectedTypeCredit(String selectedTypeCredit) {
		this.selectedTypeCredit = selectedTypeCredit;
	}


	public String getSelectedUnite() {
		return selectedUnite;
	}


	public void setSelectedUnite(String selectedUnite) {
		this.selectedUnite = selectedUnite;
	}


	public String getTypecredit() {
		return typecredit;
	}


	public void setTypecredit(String typecredit) {
		this.typecredit = typecredit;
	}


	public List<String> getTypesCredit() {
		return typesCredit;
	}


	public void setTypesCredit(List<String> typesCredit) {
		this.typesCredit = typesCredit;
	}


	public List<String> getUnites() {
		return unites;
	}


	public void setUnites(List<String> unites) {
		this.unites = unites;
	}


	public String getUnite() {
		return unite;
	}


	public void setUnite(String unite) {
		this.unite = unite;
	}

}