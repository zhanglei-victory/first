/**
 * 
 */
package com.microsec.ycjc.user.service;

import java.util.List;
import java.util.Map;

import com.microsec.ycjc.user.cond.UserInfoCond;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.util.DataGrid;

/**
 * @author Administrator
 *
 */
public interface IUserInfoService {

	/**
	 * 
	 * @param userInfo
	 */
	public void insertInfo(UserInfo userInfo);
	
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public void updateInfo(UserInfo userInfo);
	
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
	public UserInfo findInfoById(String userInfoId);

	/**
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public UserInfo loginSys(Map<String,String> map);

	/**
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public UserInfo loginSys2(String username);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserInfo> findInfoByRoleId(String roleId);

	/**
	 * 
	 * @param strucId
	 * @return
	 */
	public List<UserInfo> findInfoByStrucId(String strucId);
	/**
	 * 
	 * @return
	 */
	public List<UserInfo> findAllInfoList();
	/**
	 * 
	 * @param userId
	 */
	public void resetUserPwd(String userId);

	/**
	 * 
	 * @param userId
	 */
	public void lockUser(String userId);

	/**
	 * 
	 * @param userId
	 */
	public void unlockUser(String userId);
	public DataGrid findAllInfoByCond(UserInfoCond scond);
	public long findAllCountByCond(UserInfoCond scond);
	public UserInfo findLockorUnlockInfoById(String userId);
}
