package it.seda.sem.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Cliente implements Serializable {
	
	private BigInteger id;
	private String nome;
	private String descrizione;
    private Timestamp registrazione;
    
	
    
    
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
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Clienti [id=" + id + ", cliente=" + nome + ", descrizione="
				+ descrizione + ", registrazione=" + registrazione + "]";
	}
	
	
    
    
    
    
}
