package tn.cita.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import tn.cita.app.config.annotation.TaxRefFormat;

@Component
public class TaxRefConstraintValidator implements ConstraintValidator<TaxRefFormat, String> {
	
	@Override
	public boolean isValid(@NonNull String taxRef, ConstraintValidatorContext context) {
		return !taxRef.isBlank() 
				&& taxRef.length() == 8
				&& taxRef.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]");
	}
	
}





