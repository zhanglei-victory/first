/**
 * 
 */
package com.microsec.ycjc.farm.cond;

/**
 * @author Administrator
 *
 */
public class BreedCond {

	/** id **/
	private String id;
	
	/** ���� **/
	private String pigno;
	
	/** rfid id **/
	private String rfidId;
	
	/** rfidIdIllness **/
	private String rfidIdIllness;
	
	/** ��� **/
	private String noid;
	
	/** ����id **/
	private String vaccid;
	
	/** ��¼���� **/
	private String content;
	
	/** ��¼ʱ�� **/
	private String recorddate;

	/** ��ֳ��id **/
	private String farmid;
	
	/** ��ֳ��id illness **/
	private String farmidIllness;
	
	/** ��ѯʱ�俪ʼ **/
	private String recorddate_searchbegin;
	
	/** ��ѯʱ����� **/
	private String recorddate_searchend;

	/** ��ʼ�±� **/
	private int start;
	
	/** ÿҳ���� **/
	private int singlePagesize;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRfidId() {
		return rfidId;
	}

	public void setRfidId(String rfidId) {
		this.rfidId = rfidId;
	}

	public String getFarmid() {
		return farmid;
	}

	public void setFarmid(String farmid) {
		this.farmid = farmid;
	}

	public String getRecorddate_searchbegin() {
		return recorddate_searchbegin;
	}

	public void setRecorddate_searchbegin(String recorddate_searchbegin) {
		this.recorddate_searchbegin = recorddate_searchbegin;
	}

	public String getRecorddate_searchend() {
		return recorddate_searchend;
	}

	public void setRecorddate_searchend(String recorddate_searchend) {
		this.recorddate_searchend = recorddate_searchend;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public String getVaccid() {
		return vaccid;
	}

	public void setVaccid(String vaccid) {
		this.vaccid = vaccid;
	}

	public String getRfidIdIllness() {
		return rfidIdIllness;
	}

	public void setRfidIdIllness(String rfidIdIllness) {
		this.rfidIdIllness = rfidIdIllness;
	}

	public String getFarmidIllness() {
		return farmidIllness;
	}

	public void setFarmidIllness(String farmidIllness) {
		this.farmidIllness = farmidIllness;
	}

	public String getPigno() {
		return pigno;
	}

	public void setPigno(String pigno) {
		this.pigno = pigno;
	}
}
