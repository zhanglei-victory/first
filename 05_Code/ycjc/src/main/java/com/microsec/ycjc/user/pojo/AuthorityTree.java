/**
 * 
 */
package com.microsec.ycjc.user.pojo;

/**
 * @author Administrator
 *
 */
public class AuthorityTree {

	/** Ȩ��id **/
	private String id;
	
	/** Ȩ�޸�id **/
	private String pId;
	
	/** Ȩ������ **/
	private String name;
	
	/** checkbox**/
	private boolean checked = false;

	/** open**/
	private boolean open = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
