package it.seda.template.taglib.time;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime,DateTime> {

    private String KeyMessage;
	
	@Override
	public void initialize(ValidDateTime changePasswordValidation) {
		this.KeyMessage=changePasswordValidation.message();
	}

	@Override
	public boolean isValid(DateTime dateTime, ConstraintValidatorContext constraintValidatorContext) {

		
		boolean valid = dateTime.isValid();
		
		if (!valid) {
			replaceMessage(constraintValidatorContext);			
		}
		
		return valid;
	}

	
	public void replaceMessage(ConstraintValidatorContext constraintValidatorContext){
		if(KeyMessage!=null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(KeyMessage)
	            .addConstraintViolation();
		}
	}

}
