/**
 * 
 */
package com.microsec.ycjc.farm.dao;

import java.util.List;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Pig;

/**
 * @author Administrator
 *
 */
public interface IOutfarmDao {

	/**
	 * 
	 * @param info
	 * @return
	 */
	public void update(Pig info);
	public void delete(String rfid);
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
	public List<Pig> findAllInfoByCond(PigCond cond);
}
