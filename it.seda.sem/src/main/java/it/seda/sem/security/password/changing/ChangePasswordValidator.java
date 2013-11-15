package it.seda.sem.security.password.changing;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChangePasswordValidator implements ConstraintValidator<NotEqualNewOld,FormChangePassword> {

	
	boolean compare(String pa,String pr){
		if(pa.equals(pr)){
			return true;
		}
		return false;
	}

	
    private String KeyMessage;
	@Override
	public void initialize(NotEqualNewOld changePasswordValidation) {
		this.KeyMessage=changePasswordValidation.value();
		
	}
    
	
	


	@Override
	public boolean isValid(FormChangePassword formChangePassword,
			ConstraintValidatorContext constraintValidatorContext) {
		String username=formChangePassword.getUsername();
		String oldPassword=formChangePassword.getOldPassword();
		String newPassword=formChangePassword.getNewPassword();
		String confirm=formChangePassword.getConfirm();
		replaceMessage(constraintValidatorContext);
		return compare(newPassword,confirm);
	}

	
	public void replaceMessage(ConstraintValidatorContext constraintValidatorContext){
		
		if(KeyMessage!=null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(
					KeyMessage
	            )
	            .addConstraintViolation();
			
		}
	}

}
