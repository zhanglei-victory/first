package com.microsec.ycjc.util;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {
	private Long total=0L;
	private List rows=new ArrayList();
	private List footer = new ArrayList();
	
	
	public List getFooter() {
		return footer;
	}
	public void setFooter(List footer) {
		this.footer = footer;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
