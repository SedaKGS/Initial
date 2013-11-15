package it.seda.sem.security.password;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { TYPE,METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ChangePasswordValidator.class)
@Documented


public @interface NotEqualNewOld {
	 String message() default "{it.seda.sem.security.password.notequalsnewold}";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
	    
//	 String value();
}
