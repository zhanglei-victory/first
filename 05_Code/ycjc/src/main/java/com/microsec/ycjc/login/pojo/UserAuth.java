package com.microsec.ycjc.login.pojo;

import java.io.Serializable;

/********************************************************************
* *Copyright (C) 2013 ɽ��΢�ֵ��ӿƼ����޹�˾��Ȩ����
* *�ļ�����UserAuth.java 
* *�ļ���������������
* *���ߣ�microsec
* *������UserAuth
* *���ܣ��û���ӦȨ��
* *ʱ�䣺2014-12-24
*******************************************************************/
public class UserAuth  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String authid;//Ȩ�ޱ��
	private String priAuthId;//Ȩ�޸����
	private String authname;//Ȩ������
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
