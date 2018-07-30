/**
 * 
 */
package com.microsec.ycjc.user.service;

import java.util.List;

import com.microsec.ycjc.user.pojo.Authority;

/**
 * @author Administrator
 *
 */
public interface IAuthorityService {

	/**
	 * 
	 * @param auth
	 */
	public void insertInfo(Authority auth);
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	public void updateInfo(Authority auth);
	
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
	public Authority findInfoById(String authId);
	
	/**
	 * 
	 * @return
	 */
	public List<Authority> findAllInfoList();

	/**
	 * 
	 * @param authName
	 * @return
	 */
	public List<Authority> findInfoListByName(String authName);

}
