package it.seda.sem.mvc.manager.models;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class FormServer {
    
	
	
	
	@Pattern(regexp="^$|^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
			message="{formServer.ip.pattern}")
	private String ip;
	
	@NotEmpty(message="{formServer.descrizione.notEmpty}")
	private String descrizione;
	
	@NotEmpty(message="{formServer.nome.notEmpty}")
	private String nome;
	
	
	private BigInteger id;
	private String esito;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	@Override
	public String toString() {
		return "FormServer [ip=" + ip
				+ ", descrizione=" + descrizione + ", nome=" + nome + ", id="
				+ id + ", esito=" + esito + "]";
	}
	
	
	

}
