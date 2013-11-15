package it.seda.sem.security.password;




@NotEqualNewOld(message="{security.messages.notEqualsNewOld}", field = "NewOldError")
//@NotEqualNewOld
public class FormChangePassword {
	String newPassword;
	String confirm;
	String newOldError;
	boolean esito;
	
    
	public String getNewOldError() {
		return newOldError;
	}

	public void setNewOldError(String newOldError) {
		this.newOldError = newOldError;
	}

	public boolean isEsito() {
		return esito;
	}

	public void setEsito(boolean esito) {
		
		this.esito = esito;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		
		this.newPassword = newPassword;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "FormChangePassword [newPassword=" + newPassword + ", confirm="
				+ confirm + ", newOldError=" + newOldError + ", esito=" + esito
				+ "]";
	}

	

	


	

}
