/**
 * 
 */
package com.microsec.ycjc.farm.service;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IOutFarmService {

	/**
	 * 
	 * @param info
	 * @return
	 */
	public void update(Pig info);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Pig findByRfid(String id);
	public Pig checkOutfarm(String id);
	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(PigCond cond);
	public DataGrid findAllInfoByCond(PigCond cond);
	public void delete(String rfid);
}
