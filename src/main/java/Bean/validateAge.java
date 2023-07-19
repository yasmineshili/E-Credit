package Bean;

import java.time.LocalDate;
import java.time.Period;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

@FacesValidator("ageValidator")
public class validateAge implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    if (value == null || !(value instanceof LocalDate)) {
	        return; // Validation réussie, laisser les validateurs standard gérer cela
	    }

	    LocalDate dateNaissance = (LocalDate) value;
	    LocalDate currentDate = LocalDate.now();

	    // Calculer l'âge en années
	    Period age = Period.between(dateNaissance, currentDate);

	    // Vérifier si la date de naissance est dans le futur
	    if (dateNaissance.isAfter(currentDate)) {
	        String errorMessage = "La date de naissance ne peut pas être dans le futur.";
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
	        throw new ValidatorException(message);
	    }
	    // Vérifier si l'âge est inférieur à 18 ans
	    else if (age.getYears() < 18) {
	        String errorMessage = "Vous devez avoir au moins 18 ans.";
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
	        throw new ValidatorException(message);
	    }
	}


	} 