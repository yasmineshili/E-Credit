package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        
        if (email == null || email.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La saisie de l'adresse e-mail est obligatoire !", null));
        }
        
        if (!email.matches(EMAIL_PATTERN)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "L'adresse e-mail est invalide,", null));
        }
    }
}
