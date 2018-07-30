/**
 * 
 */
package com.microsec.ycjc.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.user.dao.IRoleAuthDao;
import com.microsec.ycjc.user.pojo.RoleAuth;
import com.microsec.ycjc.user.service.IRoleAuthService;

/**
 * @author Administrator
 *
 */
@Service("roleAuthService")
public class RoleAuthServiceImpl implements IRoleAuthService{

	@Autowired
	private IRoleAuthDao roleAuthDao;
	
	public IRoleAuthDao getRoleAuthDao() {
		return roleAuthDao;
	}

	public void setRoleAuthDao(IRoleAuthDao roleAuthDao) {
		this.roleAuthDao = roleAuthDao;
	}

	/**
	 * 
	 * @param roleAuth
	 */
	public void insertInfo(RoleAuth roleAuth){
		roleAuthDao.insertRoleAuth(roleAuth);
	}

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public void deleteInfo(String roleId){
		roleAuthDao.deleteRoleAuth(roleId);
	}
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleAuth> findInfoByRoleId(String roleId){
		List<RoleAuth> tmpList = roleAuthDao.findByRoleId(roleId);
		return tmpList;
	}
}
