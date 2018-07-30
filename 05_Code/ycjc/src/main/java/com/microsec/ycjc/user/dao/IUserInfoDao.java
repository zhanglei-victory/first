/**
 * 
 */
package com.microsec.ycjc.user.dao;

import java.util.List;
import java.util.Map;

import com.microsec.ycjc.user.cond.UserInfoCond;
import com.microsec.ycjc.user.pojo.UserInfo;

/**
 * @author Administrator
 *
 */
public interface IUserInfoDao {

	/**
	 * 
	 * @param userInfo
	 */
	public void insertUserInfo(UserInfo userInfo);
	
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public void updateUserInfo(UserInfo userInfo);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteUserInfo(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo findById(String id);
	
	/**
	 * 
	 * @return
	 */
	public List<UserInfo> findAllUserInfoList();

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UserInfo> findUserInfoByRoleId(String roleId);

	/**
	 * 
	 * @param strucId
	 * @return
	 */
	public List<UserInfo> findUserInfoByStrucId(String strucId);

	/**
	 * 用户登录
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public UserInfo loginSys(Map<String,String> map);
	/**
	 * 用户登录
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public UserInfo loginSys2(String username);
	/**
	 * 重置用户密码
	 * @param userId
	 */
	public void resetUserInfoPwd(String userId);

	/**
	 * 锁定用户
	 * @param userId
	 */
	public void lockUserInfo(String userId);

	/**
	 * 解锁用户
	 * @param userId
	 */
	public void unlockUserInfo(String userId);
	
	public List<UserInfo> findAllInfoByCond(UserInfoCond scond);
	public long findAllCountByCond(UserInfoCond scond);
	public UserInfo findLockorUnlockInfoById(String userId);
}
