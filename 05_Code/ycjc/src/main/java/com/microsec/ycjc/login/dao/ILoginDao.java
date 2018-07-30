/**
 * 
 */
package com.microsec.ycjc.login.dao;

import java.util.List;

import com.microsec.ycjc.login.pojo.UserAuth;
import com.microsec.ycjc.user.pojo.UserInfo;

/**
 * @author Administrator
 *
 */
public interface ILoginDao {

	/**
	 * 
	 * @param auth
	 */
	public List<UserAuth> getUserAuth(UserInfo ui);
}
