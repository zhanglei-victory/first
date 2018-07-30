/**
 * 
 */
package com.microsec.ycjc.login.service;

import java.util.List;

import com.microsec.ycjc.login.pojo.UserAuth;
import com.microsec.ycjc.user.pojo.UserInfo;

/**
 * @author Administrator
 *
 */
public interface ILoginService {

	/**
	 * 
	 * @param auth
	 */
	public List<UserAuth> getUserAuth(UserInfo ui);
}
