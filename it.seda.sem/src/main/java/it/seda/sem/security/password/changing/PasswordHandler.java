package it.seda.sem.security.password.changing;

import org.springframework.stereotype.Component;


public interface PasswordHandler {
	
	boolean changePassword(FormChangePassword formChangePassword);

}
