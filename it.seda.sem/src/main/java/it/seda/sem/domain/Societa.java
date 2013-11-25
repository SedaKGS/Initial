package it.seda.sem.domain;

import java.io.Serializable;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class Societa implements Serializable {

	private BigInteger id;
	private String nome;
	private String descrizione;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Societa [id=" + id + ", nome=" + nome + ", descrizione="
				+ descrizione + "]";
	}

	
	
}
