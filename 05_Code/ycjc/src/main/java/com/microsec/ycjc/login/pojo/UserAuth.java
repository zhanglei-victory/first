package com.microsec.ycjc.login.pojo;

import java.io.Serializable;

/********************************************************************
* *Copyright (C) 2013 山东微分电子科技有限公司版权所有
* *文件名：UserAuth.java 
* *文件功能描述：工具
* *作者：microsec
* *类名：UserAuth
* *功能：用户对应权限
* *时间：2014-12-24
*******************************************************************/
public class UserAuth  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String authid;//权限编号
	private String priAuthId;//权限父编号
	private String authname;//权限名称
	public String getAuthid() {
		return authid;
	}
	public String getPriAuthId() {
		return priAuthId;
	}
	public void setPriAuthId(String priAuthId) {
		this.priAuthId = priAuthId;
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
}
