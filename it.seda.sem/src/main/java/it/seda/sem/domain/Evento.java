package it.seda.sem.domain;

import java.sql.Timestamp;

public class Evento {
	private String attrezzatura;
	private String clienteId;
	private Timestamp dataA;
	private Timestamp dataDa;
	private Timestamp dataIn;
	private String id;
	private String nota;
	private String oraA;
	private String oraDa;
	private String serventeId;
	private String servizioId;
	private Timestamp timeIn;
	private String tipoId;
	private String utenteId;
	public String getAttrezzatura() {
		return attrezzatura;
	}
	public void setAttrezzatura(String attrezzatura) {
		this.attrezzatura = attrezzatura;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public Timestamp getDataA() {
		return dataA;
	}
	public void setDataA(Timestamp dataA) {
		this.dataA = dataA;
	}
	public Timestamp getDataDa() {
		return dataDa;
	}
	public void setDataDa(Timestamp dataDa) {
		this.dataDa = dataDa;
	}
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
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
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	public String getUtenteId() {
		return utenteId;
	}
	public void setUtenteId(String utenteId) {
		this.utenteId = utenteId;
	}
	@Override
	public String toString() {
		return "Eventi [attrezzatura=" + attrezzatura + ", clienteId="
				+ clienteId + ", dataA=" + dataA + ", dataDa=" + dataDa
				+ ", dataIn=" + dataIn + ", id=" + id + ", nota=" + nota
				+ ", oraA=" + oraA + ", oraDa=" + oraDa + ", serventeId="
				+ serventeId + ", servizioId=" + servizioId + ", timeIn="
				+ timeIn + ", tipoId=" + tipoId + ", utenteId=" + utenteId
				+ "]";
	}
	
	
}
