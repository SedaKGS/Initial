package it.seda.sem.domain;

import java.sql.Timestamp;

public class Responsabili {
	private String cognome;
	private Timestamp dataIn;
	private String email;
	private String esterno;
	private String id;
	private String nome;
	private String recapito;
	private String societaEsterno;
	private Timestamp timeIn;
	private String utenteId;
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Timestamp getDataIn() {
		return dataIn;
	}
	public void setDataIn(Timestamp dataIn) {
		this.dataIn = dataIn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEsterno() {
		return esterno;
	}
	public void setEsterno(String esterno) {
		this.esterno = esterno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRecapito() {
		return recapito;
	}
	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}
	public String getSocietaEsterno() {
		return societaEsterno;
	}
	public void setSocietaEsterno(String societaEsterno) {
		this.societaEsterno = societaEsterno;
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
		return "Responsabili [cognome=" + cognome + ", dataIn=" + dataIn
				+ ", email=" + email + ", esterno=" + esterno + ", id=" + id
				+ ", nome=" + nome + ", recapito=" + recapito
				+ ", societaEsterno=" + societaEsterno + ", timeIn=" + timeIn
				+ ", utenteId=" + utenteId + "]";
	}

}
