package it.seda.sem.domain;

import java.sql.Timestamp;

public class Utenti {

	private Timestamp dataIn;
	private String id;
	private Timestamp timeIn;
	private String userAuth;
	private String userEmail;
	private String userFirstName;
	private Timestamp userLastaccessDate;
	private String userLastAccessIp;
	private Timestamp userLastAccessTime;
	private String userLastName;
	private String userNickName;
	private String userPsw;
	private Timestamp userPswExp;
	private String utenteId;
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
	public Timestamp getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public Timestamp getUserLastaccessDate() {
		return userLastaccessDate;
	}
	public void setUserLastaccessDate(Timestamp userLastaccessDate) {
		this.userLastaccessDate = userLastaccessDate;
	}
	public String getUserLastAccessIp() {
		return userLastAccessIp;
	}
	public void setUserLastAccessIp(String userLastAccessIp) {
		this.userLastAccessIp = userLastAccessIp;
	}
	public Timestamp getUserLastAccessTime() {
		return userLastAccessTime;
	}
	public void setUserLastAccessTime(Timestamp userLastAccessTime) {
		this.userLastAccessTime = userLastAccessTime;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserPsw() {
		return userPsw;
	}
	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}
	public Timestamp getUserPswExp() {
		return userPswExp;
	}
	public void setUserPswExp(Timestamp userPswExp) {
		this.userPswExp = userPswExp;
	}
	public String getUtenteId() {
		return utenteId;
	}
	public void setUtenteId(String utenteId) {
		this.utenteId = utenteId;
	}
	@Override
	public String toString() {
		return "Utenti [dataIn=" + dataIn + ", id=" + id + ", timeIn=" + timeIn
				+ ", userAuth=" + userAuth + ", userEmail=" + userEmail
				+ ", userFirstName=" + userFirstName + ", userLastaccessDate="
				+ userLastaccessDate + ", userLastAccessIp=" + userLastAccessIp
				+ ", userLastAccessTime=" + userLastAccessTime
				+ ", userLastName=" + userLastName + ", userNickName="
				+ userNickName + ", userPsw=" + userPsw + ", userPswExp="
				+ userPswExp + ", utenteId=" + utenteId + "]";
	}
	
	
	
}
