/**
 * 
 */
package it.seda.security.domain;

/**
 * @author f.ricci
 *
 */
public class GroupMember {

	private String groupName;
	private String username;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public GroupMember(String groupName, String username) {
		this.groupName = groupName;
		this.username = username;
	}
	public GroupMember() {
	}
	@Override
	public String toString() {
		return "GroupMember [groupName=" + groupName + ", username=" + username
				+ "]";
	}

}
