/**
 * 
 */
package com.microsec.ycjc.farm.dao;

import java.util.List;

import com.microsec.ycjc.farm.pojo.Vaccine;

/**
 * @author Administrator
 *
 */
public interface IVaccineDao {
	
	/**
	 * 
	 * @param info
	 */
	public void insert(Vaccine info);
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	public void update(Vaccine info);
	
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
	public Vaccine findById(String id);
	public List<Vaccine> findAllInfoList();
}
