/**
 * 
 */
package com.microsec.ycjc.user.dao;

import java.util.List;

import com.microsec.ycjc.user.pojo.Authority;

/**
 * @author Administrator
 *
 */
public interface IAuthorityDao {

	/**
	 * 
	 * @param auth
	 */
	public void insertAuth(Authority auth);
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	public void updateAuth(Authority auth);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteAuth(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Authority findById(String id);
	
	/**
	 * 
	 * @return
	 */
	public List<Authority> findAllAuthList();

	/**
	 * 
	 * @param authName
	 * @return
	 */
	public List<Authority> findAuthListByName(String authName);
}
