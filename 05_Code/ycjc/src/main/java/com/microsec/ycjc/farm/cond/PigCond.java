/**
 * 
 */
package com.microsec.ycjc.farm.cond;

/**
 * @author Administrator
 *
 */
public class PigCond {

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
	
	/** 出生日期查询开始条件 **/
	private String pigbirthday_searchbegin;
	
	/** 出生日期查询结束条件 **/
	private String pigbirthday_searchend;
	
	/** 入栏日期查询开始条件 **/
	private String infarmdate_searchbegin;
	
	/** 入栏日期查询结束条件 **/
	private String infarmdate_searchend;
	
	/** 出栏日期查询开始条件 **/
	private String outfarmdate_searchbegin;
	
	/** 出栏日期查询结束条件 **/
	private String outfarmdate_searchend;
	
	/** 开始下标 **/
	private int start;
	
	/** 每页条数 **/
	private int singlePagesize;

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

	public String getFarmid_search() {
		return farmid_search;
	}

	public void setFarmid_search(String farmid_search) {
		this.farmid_search = farmid_search;
	}

	public String getFarmid() {
		return farmid;
	}

	public void setFarmid(String farmid) {
		this.farmid = farmid;
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

	public String getInfarmdate_searchbegin() {
		return infarmdate_searchbegin;
	}

	public void setInfarmdate_searchbegin(String infarmdate_searchbegin) {
		this.infarmdate_searchbegin = infarmdate_searchbegin;
	}

	public String getInfarmdate_searchend() {
		return infarmdate_searchend;
	}

	public void setInfarmdate_searchend(String infarmdate_searchend) {
		this.infarmdate_searchend = infarmdate_searchend;
	}

	public String getOutfarmdate_searchbegin() {
		return outfarmdate_searchbegin;
	}

	public void setOutfarmdate_searchbegin(String outfarmdate_searchbegin) {
		this.outfarmdate_searchbegin = outfarmdate_searchbegin;
	}

	public String getOutfarmdate_searchend() {
		return outfarmdate_searchend;
	}

	public void setOutfarmdate_searchend(String outfarmdate_searchend) {
		this.outfarmdate_searchend = outfarmdate_searchend;
	}

	public String getPigbirthday() {
		return pigbirthday;
	}

	public void setPigbirthday(String pigbirthday) {
		this.pigbirthday = pigbirthday;
	}

	public String getPigbirthday_searchbegin() {
		return pigbirthday_searchbegin;
	}

	public void setPigbirthday_searchbegin(String pigbirthday_searchbegin) {
		this.pigbirthday_searchbegin = pigbirthday_searchbegin;
	}

	public String getPigbirthday_searchend() {
		return pigbirthday_searchend;
	}

	public void setPigbirthday_searchend(String pigbirthday_searchend) {
		this.pigbirthday_searchend = pigbirthday_searchend;
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
