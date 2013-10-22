package it.seda.sem.domain;

import java.sql.Timestamp;

public class Azioni {
	
	private String azione;
	private Timestamp dataAzione;
	private Timestamp dataControllo;
	private Timestamp dataIn;
	private String esito;
	private String eventoId;
	private String respAzioneId;
	private String respControId;
	private Timestamp timeIn;
	private String utenteId;
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public Timestamp getDataAzione() {
		return dataAzione;
	}
	public void setDataAzione(Timestamp dataAzione) {
		this.dataAzione = dataAzione;
	}
	public Timestamp getDataControllo() {
		return dataControllo;
	}
	public void setDataControllo(Timestamp dataControllo) {
		this.dataControllo = dataControllo;
	}
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getEventoId() {
		return eventoId;
	}
	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}
	public String getRespAzioneId() {
		return respAzioneId;
	}
	public void setRespAzioneId(String respAzioneId) {
		this.respAzioneId = respAzioneId;
	}
	public String getRespControId() {
		return respControId;
	}
	public void setRespControId(String respControId) {
		this.respControId = respControId;
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
		return "Azione [azione=" + azione + ", dataAzione=" + dataAzione
				+ ", dataControllo=" + dataControllo + ", dataIn=" + dataIn
				+ ", esito=" + esito + ", eventoId=" + eventoId
				+ ", respAzioneId=" + respAzioneId + ", respControId="
				+ respControId + ", timeIn=" + timeIn + ", utenteId="
				+ utenteId + "]";
	}
	
	
	
	

}
