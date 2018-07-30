/**
 * 
 */
package com.microsec.ycjc.user.dao;

import java.util.List;

import com.microsec.ycjc.user.pojo.RoleAuth;

/**
 * @author Administrator
 *
 */
public interface IRoleAuthDao {

	/**
	 * 
	 * @param roleAuth
	 */
	public void insertRoleAuth(RoleAuth roleAuth);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public void deleteRoleAuth(String roleId);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleAuth> findByRoleId(String roleId);
}
