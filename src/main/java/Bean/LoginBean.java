package Bean;

import Service.UtilisateurService;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@SuppressWarnings("serial")
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    @ManagedProperty("#{utilisateurService}")
    private UtilisateurService utilisateurService;

    private String email;
    private String mot_de_passe;

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

   

    public String login() {
        String outcome = utilisateurService.login(email, mot_de_passe);
        return outcome;
    }
    
    
    
    public String logout() {
        String outcome = utilisateurService.logout();
        return outcome;
    }
}
