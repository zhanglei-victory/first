/**
 * 
 */
package com.microsec.ycjc.user.pojo;

/**
 * @author Administrator
 *
 */
public class Authority {

	/** Ȩ��id **/
	private String authid;
	
	/** Ȩ������ **/
	private String authname;

	/** Ȩ�޸�id **/
	private String priAuthId;
	/** ϵͳ��ʾ **/
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
