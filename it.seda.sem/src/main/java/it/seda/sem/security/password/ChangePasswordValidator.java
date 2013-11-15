package it.seda.sem.security.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChangePasswordValidator implements ConstraintValidator<NotEqualNewOld,FormChangePassword> {

    private String KeyMessage;
	
	boolean compare(String pa,String pr){
		if(pa.equals(pr)){
			return true;
		}
		return false;
	}

	@Override
	public void initialize(NotEqualNewOld changePasswordValidation) {
		this.KeyMessage=changePasswordValidation.message();
	}

	@Override
	public boolean isValid(FormChangePassword formChangePassword,
			ConstraintValidatorContext constraintValidatorContext) {

		String newPassword=formChangePassword.getNewPassword();
		String confirm=formChangePassword.getConfirm();
		
		boolean valid = compare(newPassword,confirm);
		
//		if (!valid) {
//			replaceMessage(constraintValidatorContext);			
//		}
		
		return valid;
	}

	
	public void replaceMessage(ConstraintValidatorContext constraintValidatorContext){
		if(KeyMessage!=null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(
					"{it.seda.sem.security.password.NotEqualNewOld.message}"
	            )
//	            .addNode("confirm")
	            .addConstraintViolation();
			
		}
	}

}
