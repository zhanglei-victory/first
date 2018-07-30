/**
 * 
 */
package com.microsec.ycjc.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.login.dao.ILoginDao;
import com.microsec.ycjc.login.pojo.UserAuth;
import com.microsec.ycjc.login.service.ILoginService;
import com.microsec.ycjc.user.pojo.UserInfo;
/**
 * @author Administrator
 *
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService{

	@Autowired
	private ILoginDao loginDao;

	public ILoginDao getLoginDao() {
		return loginDao;
	}


	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * 
	 * @return
	 */
	public List<UserAuth> getUserAuth(UserInfo ui){
		return loginDao.getUserAuth(ui);
	}
}
