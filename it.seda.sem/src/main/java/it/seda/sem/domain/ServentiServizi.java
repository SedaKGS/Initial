package it.seda.sem.domain;

import java.sql.Timestamp;

public class ServentiServizi {
	
	private Timestamp dataIn;
	private String nata;
	private String serventeId;
	private String servizioId;
	private Timestamp timeIn;
	private String utenteId;
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getNata() {
		return nata;
	}
	public void setNata(String nata) {
		this.nata = nata;
	}
	public String getServenteId() {
		return serventeId;
	}
	public void setServenteId(String serventeId) {
		this.serventeId = serventeId;
	}
	public String getServizioId() {
		return servizioId;
	}
	public void setServizioId(String servizioId) {
		this.servizioId = servizioId;
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
		return "ServentiServizi [dataIn=" + dataIn + ", nata=" + nata
				+ ", serventeId=" + serventeId + ", servizioId=" + servizioId
				+ ", timeIn=" + timeIn + ", utenteId=" + utenteId + "]";
	}
	
	

}
