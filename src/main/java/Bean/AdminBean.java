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
import Entite.DemandeCredit;
import Entite.Document;
import Service.ClientService;
import Service.ClientServiceImpl;
import Service.CompteService;
import Service.CompteServiceImpl;
import Service.DemandeService;
import Service.DemandeServiceImpl;

@SuppressWarnings("serial")
@ManagedBean(name = "AdminBean")
@ViewScoped
public class AdminBean implements Serializable {
	
	List<DemandeCredit> demandes=new ArrayList<>();
	DemandeService demandeService  = new DemandeServiceImpl();
	String nom;
	@PostConstruct
	public void init() {

	try {
		demandes=demandeService.getAllDemandedServices();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	 public List<DemandeCredit> getDemandes() {
	        return demandes;
	    }
	 public void AccepterCredit(DemandeCredit demande) {
		 demandeService.accepterDemande(demande.getId());
		 
	 }
	 public void RejeterCredit(DemandeCredit demande) {
		 demandeService.rejeterDemande(demande.getId());
	 }
}
