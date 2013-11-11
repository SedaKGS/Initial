package it.seda.sem.domain;

import java.sql.Timestamp;

public class Servizio {
	private Timestamp dataIn;
	private String descrizione;
	private String dom;
	private String domaA;
	private String domDa;
	private String festivo;
	private String gio;
	private String gioA;
	private String gioDa;
	private String id;
	private String lun;
	private String lunA;
	private String lunDa;
	private String mar;
	private String marA;
	private String marDa;
	private String mer;
	private String merA;
	private String merDa;
	private String sab;
	private String sabA;
	private String sabDa;
	private String servizio;
	private Timestamp timeIn;	
	private String utenteId;
	private String ven;
	private String venA;
	private String venDa;
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
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	public String getDomaA() {
		return domaA;
	}
	public void setDomaA(String domaA) {
		this.domaA = domaA;
	}
	public String getDomDa() {
		return domDa;
	}
	public void setDomDa(String domDa) {
		this.domDa = domDa;
	}
	public String getFestivo() {
		return festivo;
	}
	public void setFestivo(String festivo) {
		this.festivo = festivo;
	}
	public String getGio() {
		return gio;
	}
	public void setGio(String gio) {
		this.gio = gio;
	}
	public String getGioA() {
		return gioA;
	}
	public void setGioA(String gioA) {
		this.gioA = gioA;
	}
	public String getGioDa() {
		return gioDa;
	}
	public void setGioDa(String gioDa) {
		this.gioDa = gioDa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLun() {
		return lun;
	}
	public void setLun(String lun) {
		this.lun = lun;
	}
	public String getLunA() {
		return lunA;
	}
	public void setLunA(String lunA) {
		this.lunA = lunA;
	}
	public String getLunDa() {
		return lunDa;
	}
	public void setLunDa(String lunDa) {
		this.lunDa = lunDa;
	}
	public String getMar() {
		return mar;
	}
	public void setMar(String mar) {
		this.mar = mar;
	}
	public String getMarA() {
		return marA;
	}
	public void setMarA(String marA) {
		this.marA = marA;
	}
	public String getMarDa() {
		return marDa;
	}
	public void setMarDa(String marDa) {
		this.marDa = marDa;
	}
	public String getMer() {
		return mer;
	}
	public void setMer(String mer) {
		this.mer = mer;
	}
	public String getMerA() {
		return merA;
	}
	public void setMerA(String merA) {
		this.merA = merA;
	}
	public String getMerDa() {
		return merDa;
	}
	public void setMerDa(String merDa) {
		this.merDa = merDa;
	}
	public String getSab() {
		return sab;
	}
	public void setSab(String sab) {
		this.sab = sab;
	}
	public String getSabA() {
		return sabA;
	}
	public void setSabA(String sabA) {
		this.sabA = sabA;
	}
	public String getSabDa() {
		return sabDa;
	}
	public void setSabDa(String sabDa) {
		this.sabDa = sabDa;
	}
	public String getServizio() {
		return servizio;
	}
	public void setServizio(String servizio) {
		this.servizio = servizio;
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
	public String getVen() {
		return ven;
	}
	public void setVen(String ven) {
		this.ven = ven;
	}
	public String getVenA() {
		return venA;
	}
	public void setVenA(String venA) {
		this.venA = venA;
	}
	public String getVenDa() {
		return venDa;
	}
	public void setVenDa(String venDa) {
		this.venDa = venDa;
	}
	@Override
	public String toString() {
		return "Servizi [dataIn=" + dataIn + ", descrizione=" + descrizione
				+ ", dom=" + dom + ", domaA=" + domaA + ", domDa=" + domDa
				+ ", festivo=" + festivo + ", gio=" + gio + ", gioA=" + gioA
				+ ", gioDa=" + gioDa + ", id=" + id + ", lun=" + lun
				+ ", lunA=" + lunA + ", lunDa=" + lunDa + ", mar=" + mar
				+ ", marA=" + marA + ", marDa=" + marDa + ", mer=" + mer
				+ ", merA=" + merA + ", merDa=" + merDa + ", sab=" + sab
				+ ", sabA=" + sabA + ", sabDa=" + sabDa + ", servizio="
				+ servizio + ", timeIn=" + timeIn + ", utenteId=" + utenteId
				+ ", ven=" + ven + ", venA=" + venA + ", venDa=" + venDa + "]";
	}
	
	
}
