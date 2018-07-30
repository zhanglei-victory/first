/**
 * 
 */
package com.microsec.ycjc.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.user.cond.RoleCond;
import com.microsec.ycjc.user.dao.IRoleDao;
import com.microsec.ycjc.user.pojo.Role;
import com.microsec.ycjc.user.service.IRoleService;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao roleDao;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 
	 * @param role
	 */
	public void insertInfo(Role role){
		roleDao.insertRole(role);
		
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public void updateInfo(Role role){
		roleDao.updateRole(role);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteInfo(String id){
		roleDao.deleteRole(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Role findInfoById(String roleId){
		Role tmpObject = roleDao.findById(roleId);
		return tmpObject;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Role> findAllInfoList(){
		List<Role> roleList = roleDao.findAllRoleList();
		return roleList;
	}

	/**
	 * 
	 * @return
	 */
	public long findAllCountByCond(RoleCond cond) {
		return roleDao.findAllCountByCond(cond);
	}

	/**
	 * 
	 * @return
	 */
	public DataGrid findAllInfoByCond(RoleCond cond) {
		List<Role> roleList = roleDao.findAllInfoByCond(cond);
		DataGrid j = new DataGrid();
		j.setRows(roleList);
		j.setTotal(this.findAllCountByCond(cond));
		return j;
	}
}
