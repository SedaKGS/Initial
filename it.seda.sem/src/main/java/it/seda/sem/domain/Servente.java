package it.seda.sem.domain;

import java.sql.Timestamp;

public class Servente {
	
	private Timestamp registrazione;
	private String descrizione;
	private String id;
	private String indirizzoIp;
	private String servente;
	
	
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndirizzoIp() {
		return indirizzoIp;
	}
	public void setIndirizzoIp(String indirizzoIp) {
		this.indirizzoIp = indirizzoIp;
	}
	public String getServente() {
		return servente;
	}
	public void setServente(String servente) {
		this.servente = servente;
	}
	public Timestamp getRegistrazione() {
		return registrazione;
	}
	public void setRegistrazione(Timestamp registrazione) {
		this.registrazione = registrazione;
	}
	@Override
	public String toString() {
		return "Serventi [registrazione=" + registrazione + ", descrizione="
				+ descrizione + ", id=" + id + ", indirizzoIp=" + indirizzoIp
				+ ", servente=" + servente + "]";
	}
	
	
	

}
