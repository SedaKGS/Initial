/**
 * 
 */
package it.seda.sem.security.domain;

/**
 * @author f.ricci
 *
 */
public class GroupMember {

	private int groupId;
	private String username;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "GroupMember [groupId=" + groupId + ", username=" + username
				+ "]";
	}

}
