package it.seda.sem.mvc.manager.models;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class FormClient {
	
	

	@NotEmpty(message="{formClient.cliente.notEmpty}")
	private String cliente;
	
	@Pattern(regexp="^[a-zA-Z0-9]+$",message="{formClient.descrizione.pattern}")
	private String descrizione;
	
	@Pattern(regexp="[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}",message="{formClient.registrazione.pattern}")
    private String registrazione;
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getRegistrazione() {
		return registrazione;
	}
	public void setRegistrazione(String registrazione) {
		this.registrazione = registrazione;
	}
	@Override
	public String toString() {
		return "FormClient [cliente=" + cliente + ", descrizione="
				+ descrizione + ", registrazione=" + registrazione + "]";
	}
	
    
    
}
