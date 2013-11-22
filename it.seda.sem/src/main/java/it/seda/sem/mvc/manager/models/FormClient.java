package it.seda.sem.mvc.manager.models;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


public class FormClient {
	
	

	@NotEmpty(message="{formClient.cliente.notEmpty}")
	private String nome;
	
	@Pattern(regexp="^[a-zA-Z0-9\\s'!@#%*)(+=._-]+$",message="{formClient.descrizione.pattern}")
	private String descrizione;
	
	
	private Timestamp registrazione;
	
	private String esito;
	
	private BigInteger id;
	
	public String getCliente() {
		return nome;
	}
	public void setCliente(String cliente) {
		this.nome = cliente;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Timestamp getRegistrazione() {
		return registrazione;
	}
	public void setRegistrazione(Timestamp registrazione) {
		this.registrazione = registrazione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FormClient [nome=" + nome + ", descrizione=" + descrizione
				+ ", registrazione=" + registrazione + ", esito=" + esito
				+ ", id=" + id + "]";
	}
	
	
	
	
    
    
}
