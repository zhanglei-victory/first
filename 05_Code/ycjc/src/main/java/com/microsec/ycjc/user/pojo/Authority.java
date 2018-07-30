/**
 * 
 */
package com.microsec.ycjc.user.pojo;

/**
 * @author Administrator
 *
 */
public class Authority {

	/** 权限id **/
	private String authid;
	
	/** 权限名称 **/
	private String authname;

	/** 权限父id **/
	private String priAuthId;
	/** 系统标示 **/
	private String sysflag;
	public String getPriAuthId() {
		return priAuthId;
	}

	public void setPriAuthId(String priAuthId) {
		this.priAuthId = priAuthId;
	}

	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public String getSysflag() {
		return sysflag;
	}

	public void setSysflag(String sysflag) {
		this.sysflag = sysflag;
	}
}
