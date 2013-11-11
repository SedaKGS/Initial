package it.seda.sem.domain;

import java.sql.Timestamp;

public class TipoEvento {
	private String categoria;
	private Timestamp dataIn;
	private String evento;
	private String fermo;
	private String id;
	private String insieme;
	private Timestamp timeIn;
	private String utenteId;
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getFermo() {
		return fermo;
	}
	public void setFermo(String fermo) {
		this.fermo = fermo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInsieme() {
		return insieme;
	}
	public void setInsieme(String insieme) {
		this.insieme = insieme;
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
		return "TipiEvento [categoria=" + categoria + ", dataIn=" + dataIn
				+ ", evento=" + evento + ", fermo=" + fermo + ", id=" + id
				+ ", insieme=" + insieme + ", timeIn=" + timeIn + ", utenteId="
				+ utenteId + "]";
	}
	
	
}
