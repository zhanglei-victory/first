/**
 * 
 */
package com.microsec.ycjc.farm.dao;

import java.util.List;

import com.microsec.ycjc.farm.cond.FarmCond;
import com.microsec.ycjc.farm.pojo.Farm;

public interface IFarmDao {

	/**
	 * 
	 * @param farm
	 */
	public void insertFarm(Farm farm);
	
	/**
	 * 
	 * @param farm
	 * @return
	 */
	public void updateFarm(Farm farm);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteFarm(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Farm findById(String id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Farm findByLicenseId(String id);

	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(FarmCond cond);
	public List<Farm> findAllInfoByCond(FarmCond cond);
	public List<Farm> findAllInfoList();
	public List<Farm> findAllInfoByCondd();
	public List<Farm> findAllAuthInfoList(FarmCond cond);
}