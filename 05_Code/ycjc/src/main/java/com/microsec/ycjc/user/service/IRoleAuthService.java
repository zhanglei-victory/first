/**
 * 
 */
package com.microsec.ycjc.user.service;

import java.util.List;

import com.microsec.ycjc.user.pojo.RoleAuth;

/**
 * @author Administrator
 *
 */
public interface IRoleAuthService {

	/**
	 * 
	 * @param roleAuth
	 */
	public void insertInfo(RoleAuth roleAuth);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public void deleteInfo(String roleId);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleAuth> findInfoByRoleId(String roleId);
}
