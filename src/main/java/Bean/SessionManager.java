package Bean;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ApplicationScoped
public class SessionManager {

    private static final int MAX_INACTIVE_INTERVAL = 900; // 15 minutes

    private HttpSession session;

    public SessionManager() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(true);
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.invalidate();
        }
    }

    // Méthode appelée lors de chaque activité de l'utilisateur pour réinitialiser le délai d'inactivité
    public void resetInactiveInterval() {
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }
}
