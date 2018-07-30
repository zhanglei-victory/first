/**
 * 
 */
package com.microsec.ycjc.user.service;

import java.util.List;

import com.microsec.ycjc.user.cond.RoleCond;
import com.microsec.ycjc.user.pojo.Role;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IRoleService {

	/**
	 * 
	 * @param role
	 */
	public void insertInfo(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public void updateInfo(Role role);
	
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
	public Role findInfoById(String roleId);

	/**
	 * 
	 * @return
	 */
	public List<Role> findAllInfoList();

	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(RoleCond cond);

	/**
	 * 
	 * @return
	 */
	public DataGrid findAllInfoByCond(RoleCond cond);

}
