package com.microsec.ycjc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonList implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean success=false;
	private String msg="";
	private List<Object> objList = new ArrayList<Object>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Object> getObjList() {
		return objList;
	}

	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}
}
