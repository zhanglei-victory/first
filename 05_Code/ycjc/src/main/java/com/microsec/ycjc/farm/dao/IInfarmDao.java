/**
 * 
 */
package com.microsec.ycjc.farm.dao;

import java.util.List;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.farm.pojo.Pig;

/**
 * @author Administrator
 *
 */
public interface IInfarmDao {

	/**
	 * 
	 * @param info
	 */
	public void insert(Pig info);
	
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
	public void delete(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Pig findByRfid(String id);
	public List<Breed> findBreedByRfid(String id);
	public List<Pig> findByFarmId(String id);
	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(PigCond cond);
	public List<Pig> findAllInfoByCond(PigCond cond);
	public List<Pig> findAllInfoList();
}
