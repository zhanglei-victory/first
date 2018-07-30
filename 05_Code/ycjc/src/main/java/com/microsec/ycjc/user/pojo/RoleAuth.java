/**
 * 
 */
package com.microsec.ycjc.user.pojo;

/**
 * @author Administrator
 *
 */
public class RoleAuth {

	/** 唯一标示id **/
	public String roleAuthId;

	/** 角色id **/
	public String roleid;
	
	/** 权限id **/
	public String authid;

	public String getRoleAuthId() {
		return roleAuthId;
	}

	public void setRoleAuthId(String roleAuthId) {
		this.roleAuthId = roleAuthId;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}
}
