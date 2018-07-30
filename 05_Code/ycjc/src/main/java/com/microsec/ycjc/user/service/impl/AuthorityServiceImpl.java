/**
 * 
 */
package com.microsec.ycjc.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.user.dao.IAuthorityDao;
import com.microsec.ycjc.user.pojo.Authority;
import com.microsec.ycjc.user.service.IAuthorityService;

/**
 * @author Administrator
 *
 */
@Service("authService")
public class AuthorityServiceImpl implements IAuthorityService{

	@Autowired
	private IAuthorityDao authDao;

	public IAuthorityDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(IAuthorityDao authDao) {
		this.authDao = authDao;
	}

	/**
	 * 
	 * @param auth
	 */
	public void insertInfo(Authority auth){
		authDao.insertAuth(auth);
	}
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	public void updateInfo(Authority auth){
		authDao.updateAuth(auth);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteInfo(String id){
		authDao.deleteAuth(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Authority findInfoById(String authId){
		Authority tmpObject = authDao.findById(authId);
		return tmpObject;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Authority> findAllInfoList(){
		List<Authority> authList = authDao.findAllAuthList();
		return authList;
	}

	/**
	 * 
	 * @param authName
	 * @return
	 */
	public List<Authority> findInfoListByName(String authName){
		List<Authority> authList = authDao.findAuthListByName(authName);
		return authList;
	}
}
