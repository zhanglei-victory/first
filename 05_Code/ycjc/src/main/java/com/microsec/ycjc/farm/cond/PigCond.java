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
	
	/** ��ı�� **/
	private String pigno;
	
	/** rfid id **/
	private String rfidId;
	
	/** farmid **/
	private String farmid;
	
	/** farmid_search **/
	private String farmid_search;
	
	/** ����**/
	private String pigfrom;

	/** �ֱ�**/
	private String pigtype;

	/** ��������**/
	private String infarmweight;
	
	/** �Ա�**/
	private String pigsex;
	
	/** ��ɫ**/
	private String pigcolor;
	
	/** �������� **/
	private String pigbirthday;
	
	/** �������� **/
	private String infarmdate;
	
	/** ��������**/
	private String outfarmweight;
	
	/** �������� **/
	private String outfarmdate;
	
	/** ���������� **/
	private String infarmlinkman;
	
	/** ���������� **/
	private String outfarmlinkman;

	/** ����״�� **/
	private String healthstatus;
	
	/** �Ƿ�������� **/
	private String permitoutfarm;
	
	/** �������ڲ�ѯ��ʼ���� **/
	private String pigbirthday_searchbegin;
	
	/** �������ڲ�ѯ�������� **/
	private String pigbirthday_searchend;
	
	/** �������ڲ�ѯ��ʼ���� **/
	private String infarmdate_searchbegin;
	
	/** �������ڲ�ѯ�������� **/
	private String infarmdate_searchend;
	
	/** �������ڲ�ѯ��ʼ���� **/
	private String outfarmdate_searchbegin;
	
	/** �������ڲ�ѯ�������� **/
	private String outfarmdate_searchend;
	
	/** ��ʼ�±� **/
	private int start;
	
	/** ÿҳ���� **/
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
