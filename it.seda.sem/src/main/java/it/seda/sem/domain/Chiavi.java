package it.seda.sem.domain;

import java.math.BigDecimal;

public class Chiavi {
	
	private BigDecimal anno;
	private String id;
	private BigDecimal identita;
	public BigDecimal getAnno() {
		return anno;
	}
	public void setAnno(BigDecimal anno) {
		this.anno = anno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getIdentita() {
		return identita;
	}
	public void setIdentita(BigDecimal identita) {
		this.identita = identita;
	}
	@Override
	public String toString() {
		return "Chiavi [anno=" + anno + ", id=" + id + ", identita=" + identita
				+ "]";
	}
	
	
	

}
