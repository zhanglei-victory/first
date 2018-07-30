/**
 * 
 */
package com.microsec.ycjc.farm.service;

import java.util.List;

import com.microsec.ycjc.farm.cond.FarmCond;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IFarmService {

	/**
	 * 
	 * @param role
	 */
	public void insertInfo(Farm info);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public void updateInfo(Farm info);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteInfo(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Farm findInfoById(String id);

	public Farm findByLicenseId(String id);
	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(FarmCond cond);

	/**
	 * 
	 * @return
	 */
	public DataGrid findAllInfoByCond(FarmCond cond);
	public List<Farm> findAllInfoByCondd();
	public List<Farm> findAllInfoList();
	public List<Farm> findAllAuthInfoList(FarmCond cond);
}
