package it.seda.sem.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Clienti {
	
	private BigInteger id;
	private String cliente;
	private String descrizione;
    private Timestamp registrazione;
    
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
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Timestamp getRegistrazione() {
		return registrazione;
	}
	public void setRegistrazione(Timestamp registrazione) {
		this.registrazione = registrazione;
	}
	@Override
	public String toString() {
		return "Clienti [id=" + id + ", cliente=" + cliente + ", descrizione="
				+ descrizione + ", registrazione=" + registrazione + "]";
	}
	
	
    
    
    
    
}
