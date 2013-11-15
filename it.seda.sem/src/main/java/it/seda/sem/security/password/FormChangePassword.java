package it.seda.sem.security.password;




//@NotEqualNewOld(message="{security.messages.notEqualsNewOld}")
@NotEqualNewOld
public class FormChangePassword {
	String username;
	String oldPassword;
	String newPassword;
	String confirm;
	boolean esito;

	public boolean isEsito() {
		return esito;
	}

	public void setEsito(boolean esito) {
		
		this.esito = esito;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		
		this.oldPassword = oldPassword;
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
		return "FormChangePassword [username=" + username + ", oldPassword="
				+ oldPassword + ", newPassword=" + newPassword + ", confirm="
				+ confirm + ", esito=" + esito + "]";
	}

	


	

}
