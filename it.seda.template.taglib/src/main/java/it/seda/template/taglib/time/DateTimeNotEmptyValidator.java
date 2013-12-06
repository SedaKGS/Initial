package it.seda.template.taglib.time;

import it.seda.template.taglib.time.NotEmptyDateTime.DateTimePart;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeNotEmptyValidator implements ConstraintValidator<NotEmptyDateTime,DateTime> {

    private String KeyMessage;
    private DateTimePart dateTimePart;
	
	@Override
	public void initialize(NotEmptyDateTime notEmptyDateTime) {
		this.KeyMessage=notEmptyDateTime.message();
		this.dateTimePart=notEmptyDateTime.part();
	}

	@Override
	public boolean isValid(DateTime dateTime, ConstraintValidatorContext constraintValidatorContext) {

		boolean empty=false;
		switch (dateTimePart) {
		case DATE:
			empty=dateTime.isEmptyDate();
			break;
		case TIME:
			empty=dateTime.isEmptyTime();
			break;			

		default:
			empty=dateTime.isEmptyDateTime();
			break;
		}
		
		if (empty) {
			replaceMessage(constraintValidatorContext);			
		}
		
		return !empty;
	}

	
	public void replaceMessage(ConstraintValidatorContext constraintValidatorContext){
		if(KeyMessage!=null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(KeyMessage)
	            .addConstraintViolation();
		}
	}

}
