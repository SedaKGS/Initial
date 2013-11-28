package it.seda.sem.mvc.manager;

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
@Constraint(validatedBy = DateTimeValidator.class)
@Documented
public @interface ValidDateTime {
	 String message() default "{it.seda.template.validation.validdatetime}";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
	    
}
