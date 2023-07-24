package Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
	private String repaymentPDF;
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
    public void calculateRepaymentAmount() {

String pdfContent ="hi";

        // Generate the actual PDF content here using a PDF generation library like iText
        // Let's assume the variable "pdfContent" contains the PDF content

String pdfFilePath = "/path/to/generated/credit_repayments.pdf";
// Code to save the PDF content to the file location

// Store the URL to the PDF file in the repaymentPDF property
repaymentPDF = pdfFilePath;
// Show the pop-up for downloading the PDF
PrimeFaces.current().executeScript("PF('downloadPopup').show();");
    }
	public  void redirectToDemandeView(int id) {
	try {
        // Obtain the ExternalContext
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        // Construct the redirect URL with the input id
        String redirectURL = "http://localhost:8091/DemandeEcredit/DemandeView.xhtml?id=" + id;

        // Redirect to the constructed URL
        externalContext.redirect(redirectURL);
    } catch (IOException e) {
        // Handle the IOException, for example, show an error message
        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirect Failed", "Redirect to URL failed.");
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }}
	 public List<DemandeCredit> getDemandes() {
	        return demandes;
	    }
	 public void AccepterCredit(DemandeCredit demande) {
		 demandeService.accepterDemande(demande.getId());
		 demande.setEtat("Valide");
		 calculateRepaymentAmount();
		 
	 }
	 public void RejeterCredit(DemandeCredit demande) {
		 demandeService.rejeterDemande(demande.getId());
		 demande.setEtat("Rejete");
		
	 }
	 public String getRepaymentPDF() {
	        return repaymentPDF;
	    }

	    public void setRepaymentPDF(String repaymentPDF) {
	        this.repaymentPDF = repaymentPDF;
	    }
}
