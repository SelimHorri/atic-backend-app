package tn.cita.app.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import tn.cita.app.validator.TaxRefConstraintValidator;

@Target(value = {
		ElementType.ANNOTATION_TYPE, 
		ElementType.TYPE, 
		ElementType.FIELD, 
		ElementType.METHOD, 
		ElementType.PARAMETER
})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaxRefConstraintValidator.class)
public @interface TaxRefFormat {
	
	String message() default "{TaxRefFormat.invalid}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}




