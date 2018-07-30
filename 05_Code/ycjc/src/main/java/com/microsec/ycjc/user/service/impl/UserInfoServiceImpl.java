/**
 * 
 */
package com.microsec.ycjc.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.user.cond.UserInfoCond;
import com.microsec.ycjc.user.dao.IUserInfoDao;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.user.service.IUserInfoService;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private IUserInfoDao userInfoDao;

	public IUserInfoDao getRoleDao() {
		return userInfoDao;
	}

	public void setRoleDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	/**
	 * 
	 * @param userInfo
	 */
	public void insertInfo(UserInfo userInfo) {
		userInfoDao.insertUserInfo(userInfo);
		
	}
	
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public void updateInfo(UserInfo userInfo) {
		userInfoDao.updateUserInfo(userInfo);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteInfo(String id) {
		userInfoDao.deleteUserInfo(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo findInfoById(String userInfoId) {
		UserInfo tmpObject = userInfoDao.findById(userInfoId);
		return tmpObject;
	}

	/**
	 * 
	 */
	public UserInfo loginSys(Map<String,String> map) {
		return userInfoDao.loginSys(map);
	}
	/**
	 * 
	 */
	public UserInfo loginSys2(String username) {
		return userInfoDao.loginSys2(username);
	}
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserInfo> findInfoByRoleId(String roleId) {
		return userInfoDao.findUserInfoByRoleId(roleId);
	}

	public List<UserInfo> findInfoByStrucId(String strucId) {
		return userInfoDao.findUserInfoByStrucId(strucId);
	}
	/**
	 * 
	 * @return
	 */
	public List<UserInfo> findAllInfoList() {
		List<UserInfo> userInfoList = userInfoDao.findAllUserInfoList();
		return userInfoList;
	}
	/**
	 * 
	 * @param userId
	 */
	public void resetUserPwd(String userId) {
		userInfoDao.resetUserInfoPwd(userId);
	}

	/**
	 * 
	 * @param userId
	 */
	public void lockUser(String userId) {
		userInfoDao.lockUserInfo(userId);
	}

	/**
	 * 
	 * @param userId
	 */
	public void unlockUser(String userId) {
		userInfoDao.unlockUserInfo(userId);
	}
	
	public DataGrid findAllInfoByCond(UserInfoCond scond) {
		List<UserInfo> userinfoList = userInfoDao.findAllInfoByCond(scond);
		DataGrid j = new DataGrid();
		j.setRows(userinfoList);
		j.setTotal(this.findAllCountByCond(scond));
		return j;
	}
	public UserInfo findLockorUnlockInfoById(String userId) {
		return userInfoDao.findLockorUnlockInfoById(userId);
	}
	/**
	 * 
	 */
	public long findAllCountByCond(UserInfoCond scond) {
		long count = userInfoDao.findAllCountByCond(scond);
		return count;
	}
}
