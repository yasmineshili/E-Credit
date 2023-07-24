package Bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import Entite.DemandeCredit;
import Service.DemandeService;
import Service.DemandeServiceImpl;




@SuppressWarnings("serial")
@ManagedBean(name = "DemandeViewBean")
@ViewScoped
public class DemandeViewBean implements Serializable {

	DemandeCredit demande = new DemandeCredit();
	DemandeService demandeService  = new DemandeServiceImpl();
	@PostConstruct
	public void init() {
	}
	public void populateView() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest();

		String id = request.getParameter("id");
		this.demande=demandeService.getDemande(Integer.parseInt(id));
	}
	public void redirectBack() {
		 try {
	            // Obtain the ExternalContext
	            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

	            // Construct the redirect URL with the input id
	            String redirectURL = "http://localhost:8091/DemandeEcredit/Admin.xhtml";

	            // Redirect to the constructed URL
	            externalContext.redirect(redirectURL);
	        } catch (IOException e) {
	            // Handle the IOException, for example, show an error message
	            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirect Failed", "Redirect to URL failed.");
	            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
	        }
	}
	public DemandeCredit getDemande() {
        return demande;
    }

    public void setDemande(DemandeCredit demande) {
        this.demande = demande;
    }


}
	