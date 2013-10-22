package it.seda.sem.domain;

import java.sql.Timestamp;

public class Serventi {
	
	private Timestamp dataIn;
	private String descrizione;
	private String id;
	private String indirizzoIp;
	private String servente;
	private Timestamp timeIn;
	private String utenteId;
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
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
	public Timestamp getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}
	public String getUtenteId() {
		return utenteId;
	}
	public void setUtenteId(String utenteId) {
		this.utenteId = utenteId;
	}
	@Override
	public String toString() {
		return "Serventi [dataIn=" + dataIn + ", descrizione=" + descrizione
				+ ", id=" + id + ", indirizzoIp=" + indirizzoIp + ", servente="
				+ servente + ", timeIn=" + timeIn + ", utenteId=" + utenteId
				+ "]";
	}
	
	

}
