package it.seda.template.taglib.time;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateTimeNotEmptyValidator.class)
@Documented
public @interface NotEmptyDateTime {
	
	public enum DateTimePart {
		DATE,TIME,DATETIME;
	}
	
	 String message() default "it.seda.template.validation.notemptydatetime";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
	 
	 DateTimePart part() default DateTimePart.DATE;
}
