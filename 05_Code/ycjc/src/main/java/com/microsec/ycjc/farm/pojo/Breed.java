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
