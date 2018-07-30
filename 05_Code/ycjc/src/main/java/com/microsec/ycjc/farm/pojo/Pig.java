/**
 * 
 */
package com.microsec.ycjc.farm.pojo;

/**
 * @author Administrator
 *
 */
public class Pig {

	/** noid **/
	private String noid;
	
	/** 猪的编号 **/
	private String pigno;
	
	/** rfid id **/
	private String rfidId;
	
	/** farmid **/
	private String farmid;
	
	/** farmid_search **/
	private String farmid_search;
	
	/** 产地**/
	private String pigfrom;

	/** 种别**/
	private String pigtype;

	/** 入栏重量**/
	private String infarmweight;
	
	/** 性别**/
	private String pigsex;
	
	/** 颜色**/
	private String pigcolor;
	
	/** 出生日期 **/
	private String pigbirthday;
	
	/** 入栏日期 **/
	private String infarmdate;
	
	/** 出栏重量**/
	private String outfarmweight;
	
	/** 出栏日期 **/
	private String outfarmdate;
	
	/** 入栏负责人 **/
	private String infarmlinkman;
	
	/** 出栏负责人 **/
	private String outfarmlinkman;

	/** 健康状况 **/
	private String healthstatus;
	
	/** 是否允许出栏 **/
	private String permitoutfarm;
	
	public String getPigfrom() {
		return pigfrom;
	}

	public void setPigfrom(String pigfrom) {
		this.pigfrom = pigfrom;
	}

	public String getPigtype() {
		return pigtype;
	}

	public void setPigtype(String pigtype) {
		this.pigtype = pigtype;
	}

	public String getPigsex() {
		return pigsex;
	}

	public void setPigsex(String pigsex) {
		this.pigsex = pigsex;
	}

	public String getPigcolor() {
		return pigcolor;
	}

	public void setPigcolor(String pigcolor) {
		this.pigcolor = pigcolor;
	}

	public String getRfidId() {
		return rfidId;
	}

	public void setRfidId(String rfidId) {
		this.rfidId = rfidId;
	}

	public String getInfarmweight() {
		return infarmweight;
	}

	public void setInfarmweight(String infarmweight) {
		this.infarmweight = infarmweight;
	}

	public String getInfarmdate() {
		return infarmdate;
	}

	public void setInfarmdate(String infarmdate) {
		this.infarmdate = infarmdate;
	}

	public String getOutfarmweight() {
		return outfarmweight;
	}

	public void setOutfarmweight(String outfarmweight) {
		this.outfarmweight = outfarmweight;
	}

	public String getFarmid() {
		return farmid;
	}

	public void setFarmid(String farmid) {
		this.farmid = farmid;
	}

	public String getOutfarmdate() {
		return outfarmdate;
	}

	public void setOutfarmdate(String outfarmdate) {
		this.outfarmdate = outfarmdate;
	}

	public String getInfarmlinkman() {
		return infarmlinkman;
	}

	public void setInfarmlinkman(String infarmlinkman) {
		this.infarmlinkman = infarmlinkman;
	}

	public String getOutfarmlinkman() {
		return outfarmlinkman;
	}

	public void setOutfarmlinkman(String outfarmlinkman) {
		this.outfarmlinkman = outfarmlinkman;
	}

	public String getHealthstatus() {
		return healthstatus;
	}

	public void setHealthstatus(String healthstatus) {
		this.healthstatus = healthstatus;
	}

	public String getPermitoutfarm() {
		return permitoutfarm;
	}

	public void setPermitoutfarm(String permitoutfarm) {
		this.permitoutfarm = permitoutfarm;
	}

	public String getPigbirthday() {
		return pigbirthday;
	}

	public void setPigbirthday(String pigbirthday) {
		this.pigbirthday = pigbirthday;
	}

	public String getFarmid_search() {
		return farmid_search;
	}

	public void setFarmid_search(String farmid_search) {
		this.farmid_search = farmid_search;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public String getPigno() {
		return pigno;
	}

	public void setPigno(String pigno) {
		this.pigno = pigno;
	}
}
