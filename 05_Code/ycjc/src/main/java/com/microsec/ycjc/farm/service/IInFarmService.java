/**
 * 
 */
package com.microsec.ycjc.farm.service;

import java.util.List;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IInFarmService {

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

	/**
	 * 
	 * @return
	 */
	public DataGrid findAllInfoByCond(PigCond cond);
	public List<Pig> findAllInfoList();
}
