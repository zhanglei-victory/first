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
	 * Title��doFilter Description: ���������ߴ˹��������ж��û��Ƿ��¼ user: 
	 * 2014��11��3��
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
		// �ж��Ƿ���http����
		if (!(servletRequest instanceof HttpServletRequest)
				|| !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException(
					"OncePerRequestFilter just supports HTTP requests");
		}
		// ��������������Ҫ�õ�request,response,session����
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);

		String[] strs = { "quit", "login", "logout", "static","validateCodeImage" }; // ·���а�����Щ�ַ�����,���Բ��õ�¼ֱ�ӷ���
		StringBuffer url = httpRequest.getRequestURL();

		/**
		 * ���˵���Ŀ¼�Լ���¼����action
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
		// ������;��·������ֱ�ӷ���
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (url.indexOf(str) >= 0) {
					filterChain.doFilter(servletRequest, servletResponse);
					return;
				}
			}
		}
		// ��session�л�ȡ�û���Ϣ
		UserInfo userinfo = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		if (null != userinfo) {
			ServletContext context = httpRequest.getSession().getServletContext();
			WebApplicationContext webApplication = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			userDao = (IUserInfoDao) webApplication.getBean("IUserInfoDao");
			//==================================ɾ���û����˿�ʼ=====================================
			String userName = userinfo.getUsername();
			UserInfo existinfo = userDao.loginSys2(userName);
			//�ѱ�ɾ��
			if(existinfo == null) {
				String returnUrl = basePath;
				httpRequest.setCharacterEncoding("UTF-8");
				httpResponse.setContentType("text/html; charset=UTF-8"); // ת��
				httpResponse
						.getWriter()
						.println(
								"<script language=\"javascript\">alert(\"���ѱ�ɾ��������ϵ����ԱΪ�㿪��!\");if(window.opener==null){window.top.location.href=\""
										+ returnUrl
										+ "\";}else{window.opener.top.location.href=\""
										+ returnUrl
										+ "\";window.close();}</script>");
				return;
			//==================================ɾ���û����˽���=====================================
			} else {
				//==================================�����û����˿�ʼ=====================================
				//�ж��Լ��Ƿ�����
				if("1".equals(existinfo.getUserstatus())) {
					String returnUrl = basePath;
					httpRequest.setCharacterEncoding("UTF-8");
					httpResponse.setContentType("text/html; charset=UTF-8"); // ת��
					httpResponse
							.getWriter()
							.println(
									"<script language=\"javascript\">alert(\"���ѱ�����������ϵ����ԱΪ�㿪ͨ!\");if(window.opener==null){window.top.location.href=\""
											+ returnUrl
											+ "\";}else{window.opener.top.location.href=\""
											+ returnUrl
											+ "\";window.close();}</script>");
					return;
				}
				//==================================�����û����˽���=====================================
				//==================================�û��޸Ĺ��˿�ʼ=====================================
				//session���û���Ϣͬ�ող�ѯ�������ݽ��бȽ�
				if(!userinfo.getUserroleid().equals(existinfo.getUserroleid())
					|| !userinfo.getUserposition().equals(existinfo.getUserposition()) || !userinfo.getUserSex().equals(existinfo.getUserSex()) 
					|| !userinfo.getUserRealName().equals(existinfo.getUserRealName()) || !userinfo.getUserphone().equals(existinfo.getUserphone())
					|| !userinfo.getUserEmail().equals(existinfo.getUserEmail()) ) {
					String returnUrl = basePath;
					httpRequest.setCharacterEncoding("UTF-8");
					httpResponse.setContentType("text/html; charset=UTF-8"); // ת��
					httpResponse
							.getWriter()
							.println(
									"<script language=\"javascript\">alert(\"������Ϣ�ѱ��޸ģ������µ�¼!\");if(window.opener==null){window.top.location.href=\""
											+ returnUrl
											+ "\";}else{window.opener.top.location.href=\""
											+ returnUrl
											+ "\";window.close();}</script>");
					return;
				}
				if(!userinfo.getUserPwd().equals(existinfo.getUserPwd()) ) {
						String returnUrl = basePath;
						httpRequest.setCharacterEncoding("UTF-8");
						httpResponse.setContentType("text/html; charset=UTF-8"); // ת��
						httpResponse
								.getWriter()
								.println(
										"<script language=\"javascript\">alert(\"�������޸ģ��������������µ�¼!\");if(window.opener==null){window.top.location.href=\""
												+ returnUrl
												+ "\";}else{window.opener.top.location.href=\""
												+ returnUrl
												+ "\";window.close();}</script>");
						return;
				}
					//==================================�û��޸Ĺ��˽���=====================================
			}
			// �û�����,���Է��ʴ˵�ַ
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			// �û�������,�߻ص�¼ҳ��
			String returnUrl = basePath;
			httpRequest.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html; charset=UTF-8"); // ת��
			httpResponse
					.getWriter()
					.println(
							"<script language=\"javascript\">alert(\"����ʱ��û�н��в������߷����������������µ�¼!\");if(window.opener==null){window.top.location.href=\""
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

	// �û�dao
	private IUserInfoDao userDao;

	public IUserInfoDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserInfoDao userDao) {
		this.userDao = userDao;
	}
}
