/**
 * 
 */
package com.microsec.ycjc.farm.pojo;

/**
 * @author Administrator
 *
 */
public class Breed {

	/** id **/
	private String id;
	
	/** 猪编号 **/
	private String pigno;
	
	/** rfid id **/
	private String rfidId;
	
	/** rfidIdIllness **/
	private String rfidIdIllness;
	
	/** 编号 **/
	private String noid;
	
	/** 疫苗id **/
	private String vaccid;
	
	/** 记录内容 **/
	private String content;
	
	/** 记录时间 **/
	private String recorddate;

	/** 养殖场id **/
	private String farmid;
	
	/** 养殖场id illness **/
	private String farmidIllness;

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
