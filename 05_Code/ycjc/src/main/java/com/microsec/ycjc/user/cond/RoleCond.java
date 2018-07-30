/**
 * 
 */
package com.microsec.ycjc.user.cond;

/**
 * @author Administrator
 *
 */
public class RoleCond {

	/** 角色id **/
	public String roleid;
	
	/** 角色名称 **/
	public String rolename;

	/** 开始下标 **/
	public int start;
	
	/** 每页条数 **/
	public int singlePagesize;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSinglePagesize() {
		return singlePagesize;
	}

	public void setSinglePagesize(int singlePagesize) {
		this.singlePagesize = singlePagesize;
	}
}
