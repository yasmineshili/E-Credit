package Bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
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
	public DemandeCredit getDemande() {
        return demande;
    }

    public void setDemande(DemandeCredit demande) {
        this.demande = demande;
    }


}
	