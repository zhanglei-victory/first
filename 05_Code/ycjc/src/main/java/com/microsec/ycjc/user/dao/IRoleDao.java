/**
 * 
 */
package com.microsec.ycjc.user.dao;

import java.util.List;

import com.microsec.ycjc.user.cond.RoleCond;
import com.microsec.ycjc.user.pojo.Role;

/**
 * @author Administrator
 *
 */
public interface IRoleDao {

	/**
	 * 
	 * @param role
	 */
	public void insertRole(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public void updateRole(Role role);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteRole(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Role findById(String id);
	
	/**
	 * 
	 * @return
	 */
	public List<Role> findAllRoleList();

	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(RoleCond cond);
	public List<Role> findAllInfoByCond(RoleCond cond);
}
