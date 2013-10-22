package it.seda.sem.domain;

import java.sql.Timestamp;

public class ClientiServizi {
	
	
	private String clienteId;
    private Timestamp dataAttivazione;
	private Timestamp dataIn;
	private String nota;
	private String servizioId;
	private Timestamp timeIn;
	private String utenteId;
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public Timestamp getDataAttivazione() {
		return dataAttivazione;
	}
	public void setDataAttivazione(Timestamp dataAttivazione) {
		this.dataAttivazione = dataAttivazione;
	}
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
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
		return "ClientiServizi [clienteId=" + clienteId + ", dataAttivazione="
				+ dataAttivazione + ", dataIn=" + dataIn + ", nota=" + nota
				+ ", servizioId=" + servizioId + ", timeIn=" + timeIn
				+ ", utenteId=" + utenteId + "]";
	}
	
	
	


}
