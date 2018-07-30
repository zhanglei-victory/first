package com.microsec.ycjc.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.microsec.ycjc.user.dao.IUserInfoDao;
import com.microsec.ycjc.user.pojo.UserInfo;

public class YcjcFilter implements Filter {

	/**
	 * 
	 * Title：doFilter Description: 所有请求都走此过滤器来判断用户是否登录 user: 
	 * 2014年11月3日
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("=========  execute filter  =========");
		// 判断是否是http请求
		if (!(servletRequest instanceof HttpServletRequest)
				|| !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException(
					"OncePerRequestFilter just supports HTTP requests");
		}
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);

		String[] strs = { "quit", "login", "logout", "static","validateCodeImage" }; // 路径中包含这些字符串的,可以不用登录直接访问
		StringBuffer url = httpRequest.getRequestURL();

		/**
		 * 过滤掉根目录以及登录处理action
		 */
		String path = httpRequest.getContextPath();
		String protAndPath = httpRequest.getServerPort() == 80 ? "" : ":"
				+ httpRequest.getServerPort();
		String basePath = httpRequest.getScheme() + "://"
				+ httpRequest.getServerName() + protAndPath + path + "/";
		if (basePath.equalsIgnoreCase(url.toString()) || basePath.concat("userLogin").equalsIgnoreCase(url.toString())) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		// 特殊用途的路径可以直接访问
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (url.indexOf(str) >= 0) {
					filterChain.doFilter(servletRequest, servletResponse);
					return;
				}
			}
		}
		// 从session中获取用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		if (null != userinfo) {
			ServletContext context = httpRequest.getSession().getServletContext();
			WebApplicationContext webApplication = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			userDao = (IUserInfoDao) webApplication.getBean("IUserInfoDao");
			//==================================删除用户过滤开始=====================================
			String userName = userinfo.getUsername();
			UserInfo existinfo = userDao.loginSys2(userName);
			//已被删掉
			if(existinfo == null) {
				String returnUrl = basePath;
				httpRequest.setCharacterEncoding("UTF-8");
				httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
				httpResponse
						.getWriter()
						.println(
								"<script language=\"javascript\">alert(\"您已被删除，请联系管理员为你开户!\");if(window.opener==null){window.top.location.href=\""
										+ returnUrl
										+ "\";}else{window.opener.top.location.href=\""
										+ returnUrl
										+ "\";window.close();}</script>");
				return;
			//==================================删除用户过滤结束=====================================
			} else {
				//==================================锁定用户过滤开始=====================================
				//判断自己是否被锁定
				if("1".equals(existinfo.getUserstatus())) {
					String returnUrl = basePath;
					httpRequest.setCharacterEncoding("UTF-8");
					httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
					httpResponse
							.getWriter()
							.println(
									"<script language=\"javascript\">alert(\"您已被锁定，请联系管理员为你开通!\");if(window.opener==null){window.top.location.href=\""
											+ returnUrl
											+ "\";}else{window.opener.top.location.href=\""
											+ returnUrl
											+ "\";window.close();}</script>");
					return;
				}
				//==================================锁定用户过滤结束=====================================
				//==================================用户修改过滤开始=====================================
				//session中用户信息同刚刚查询到的数据进行比较
				if(!userinfo.getUserroleid().equals(existinfo.getUserroleid())
					|| !userinfo.getUserposition().equals(existinfo.getUserposition()) || !userinfo.getUserSex().equals(existinfo.getUserSex()) 
					|| !userinfo.getUserRealName().equals(existinfo.getUserRealName()) || !userinfo.getUserphone().equals(existinfo.getUserphone())
					|| !userinfo.getUserEmail().equals(existinfo.getUserEmail()) ) {
					String returnUrl = basePath;
					httpRequest.setCharacterEncoding("UTF-8");
					httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
					httpResponse
							.getWriter()
							.println(
									"<script language=\"javascript\">alert(\"您的信息已被修改，请重新登录!\");if(window.opener==null){window.top.location.href=\""
											+ returnUrl
											+ "\";}else{window.opener.top.location.href=\""
											+ returnUrl
											+ "\";window.close();}</script>");
					return;
				}
				if(!userinfo.getUserPwd().equals(existinfo.getUserPwd()) ) {
						String returnUrl = basePath;
						httpRequest.setCharacterEncoding("UTF-8");
						httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
						httpResponse
								.getWriter()
								.println(
										"<script language=\"javascript\">alert(\"密码已修改，请用新密码重新登录!\");if(window.opener==null){window.top.location.href=\""
												+ returnUrl
												+ "\";}else{window.opener.top.location.href=\""
												+ returnUrl
												+ "\";window.close();}</script>");
						return;
				}
					//==================================用户修改过滤结束=====================================
			}
			// 用户存在,可以访问此地址
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			// 用户不存在,踢回登录页面
			String returnUrl = basePath;
			httpRequest.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
			httpResponse
					.getWriter()
					.println(
							"<script language=\"javascript\">alert(\"您长时间没有进行操作或者服务器重启，请重新登录!\");if(window.opener==null){window.top.location.href=\""
									+ returnUrl
									+ "\";}else{window.opener.top.location.href=\""
									+ returnUrl
									+ "\";window.close();}</script>");
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	// 用户dao
	private IUserInfoDao userDao;

	public IUserInfoDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserInfoDao userDao) {
		this.userDao = userDao;
	}
}
