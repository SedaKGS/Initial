package it.seda.sem.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class Server implements Serializable{

	
	private String ip;
	private String descrizione;
	private String nome;
	private BigInteger id;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Server [ip=" + ip
				+ ", descrizione=" + descrizione + ", nome=" + nome + ", id="
				+ id + "]";
	} 
	
	
	

}
