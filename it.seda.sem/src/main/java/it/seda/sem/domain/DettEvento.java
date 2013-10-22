package it.seda.sem.domain;

import java.sql.Timestamp;

public class DettEvento {
	
	
	private Timestamp dataEvento;
	private String eventoId;
	private String oraA;
	private String oraDa;
	public Timestamp getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Timestamp dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getEventoId() {
		return eventoId;
	}
	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}
	public String getOraA() {
		return oraA;
	}
	public void setOraA(String oraA) {
		this.oraA = oraA;
	}
	public String getOraDa() {
		return oraDa;
	}
	public void setOraDa(String oraDa) {
		this.oraDa = oraDa;
	}
	@Override
	public String toString() {
		return "DettEvento [dataEvento=" + dataEvento + ", eventoId="
				+ eventoId + ", oraA=" + oraA + ", oraDa=" + oraDa + "]";
	}



}
