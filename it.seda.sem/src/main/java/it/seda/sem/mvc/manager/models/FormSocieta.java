package it.seda.sem.mvc.manager.models;

import java.math.BigInteger;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class FormSocieta {

	@NotEmpty(message="{formSocieta.nome.notEmpty}")
	private String nome;
	
	@Pattern(regexp="^$|^[a-zA-Z0-9\\s'!@#%*)(+=._-]+$",message="{formSocieta.descrizione.pattern}")
	private String descrizione;
	
	private String esito;
	
	private BigInteger id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		return "FormSocieta [nome=" + nome + ", descrizione=" + descrizione
				+ ", esito=" + esito + ", id=" + id + "]";
	}
	
	
	
	
}
