package it.seda.security.domain;

public class Group {

	private int id;
	private String groupName;
	private String groupDescription;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", groupName=" + groupName
				+ ", groupDescription=" + groupDescription + "]";
	}
	
}
