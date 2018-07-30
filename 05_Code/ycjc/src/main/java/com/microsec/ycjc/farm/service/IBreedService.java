/**
 * 
 */
package com.microsec.ycjc.farm.service;

import java.util.List;

import com.microsec.ycjc.farm.cond.BreedCond;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IBreedService {

	/**
	 * 
	 * @param info
	 */
	public void insert(Breed info);
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	public void update(Breed info);
	
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
	public Breed findById(String id);

	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(BreedCond cond);
	public DataGrid findAllInfoByCond(BreedCond cond);
	public List<Breed> findAllInfoList();
	public List<Breed> findVaccineList(BreedCond cond);
}
