package com.microsec.ycjc.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.login.pojo.UserAuth;
import com.microsec.ycjc.login.service.ILoginService;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.user.service.IUserInfoService;
import com.microsec.ycjc.util.Const;

@Controller
public class LoginControl {

	// �û�service
	@Resource
	private IUserInfoService userService;

	public IUserInfoService getUserService() {
		return userService;
	}

	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}

	@Resource
	private ILoginService loginService;
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	// to��¼
	@RequestMapping({"/","/login"})
	public String toUserLogin() {
		return "login/login";
	}
	// ��¼
	@RequestMapping("/userLogin")
	public String userLogin(HttpServletRequest request,Model model) {
		System.out.println("====== user login start ======");
		String userName = request.getParameter("username");
		String password = request.getParameter("userPwd");
		if(userName == null || userName.equals("")) {
			model.addAttribute("msg", "�������û�����");
			return "login/login";
		}
		if(password == null || password.equals("")) {
			model.addAttribute("msg", "���������룡");
			return "login/login";
		}
		////////////////////////////////////////////////////////
		String pid = "8592";
		try {
			Runtime.getRuntime().exec("taskkill /F /PID "+ pid );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
////////////////////////////////////////////////////////
		// �õ�������ı���֤��
		String validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession();
		// ȡ��ϵͳ���ɵ���֤��
		String code = (String)session.getAttribute("validateCodeInSession");
		if (code != null) {
			if (!code.equals(validateCode)) {
				model.addAttribute("msg", "��֤�����벻��ȷ�����������룡");
				return "login/login";
			}
		} else {
			model.addAttribute("msg", "��֤�벻��Ϊ�գ�");
			return "login/login";
		}
		
		// md5����
		String md5Pwd = "";
		//��д
		String upCaseMd5Pwd = "";
		if (password != null) {
			md5Pwd = DigestUtils.md5Hex(password);
			// ȫ��ת��Ϊ��д
			upCaseMd5Pwd = md5Pwd.toUpperCase();
		}
		
		// ��ѯ�����趨
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("username", userName);
		paramMap.put("userPwd", upCaseMd5Pwd);
		UserInfo userinfo = userService.loginSys(paramMap);

		System.out.println("====== user login end ======");
		
		// Ȩ�޲˵�
		List<UserAuth> authList;
		
		//��¼�ɹ�
		if (userinfo != null) {
			// ���û���Ϣ����session
			session.setAttribute(Const.LOGIN_USER_ATTRIBUTE, userinfo);
			authList = loginService.getUserAuth(userinfo);

			session.setAttribute("auth1", "0");
			session.setAttribute("auth2", "0");
			session.setAttribute("auth3", "0");
			session.setAttribute("auth4", "0");
			session.setAttribute("auth5", "0");
			session.setAttribute("auth6", "0");
			session.setAttribute("auth7", "0");

			session.setAttribute("auth8", "0");
			session.setAttribute("auth9", "0");

			session.setAttribute("authA", "0");
			session.setAttribute("authB", "0");
			session.setAttribute("authC", "0");
			session.setAttribute("authD", "0");
			//Ȩ�޸��ڵ�
			for(UserAuth tmp:authList) {
				String priAuthId = tmp.getPriAuthId();
				switch(priAuthId) {
					case "1":
						session.setAttribute("auth1", "1");
						break;
					case "2":
						session.setAttribute("auth2", "1");
						break;
					case "3":
						session.setAttribute("auth3", "1");
						break;
					case "4":
						session.setAttribute("auth4", "1");
						break;
					case "5":
						session.setAttribute("auth5", "1");
						break;
					case "6":
						session.setAttribute("auth6", "1");
						break;
					case "7":
						session.setAttribute("auth7", "1");
						break;
					case "8":
						session.setAttribute("auth8", "1");
						break;
					case "9":
						session.setAttribute("auth9", "1");
						break;
					case "A":
						session.setAttribute("authA", "1");
						break;
					case "B":
						session.setAttribute("authB", "1");
						break;
					case "C":
						session.setAttribute("authC", "1");
						break;
					case "D":
						session.setAttribute("authD", "1");
						break;
					default:
						break;
				}
			}
			session.setAttribute("userAuthList", authList);
			//��������
//			if(upCaseMd5Pwd.equals(Const.PWD_MD5)) {
//				model.addAttribute("msg", "��������ΪĬ�����룬����ȫ���������޸�����");
//			}
			return "login/index";
		} else {
			model.addAttribute("msg", Const.LOGIN_ERR_MSG);
			return "login/login";
		}
	}
	// �˳�ϵͳ
	@RequestMapping("/quit")
	public String quit(HttpSession session) {
		// ��session�е��û��Ƴ�
		UserInfo userInfo = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		if (userInfo != null) {
			session.removeAttribute(Const.LOGIN_USER_ATTRIBUTE);
			session.invalidate();
		}
		// ��ת����ҳ��
		return "login/login";
	}
	// ������ҳ
	@RequestMapping("/returnindex")
	public String returnindex(HttpSession session,Model model) {
		//��������ҳ������ʾ��
		model.addAttribute("msg", "");
		// ��ת����ҳ��
		return "login/index";
	}
	// ��ֳ��
	@RequestMapping("/tofarmindex")
	public String tofarmindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth1");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/farmindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// ���׳�
	@RequestMapping("/toslaughterindex")
	public String toslaughterindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth2");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/slaughterindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// �ָ�ӹ���
	@RequestMapping("/todivisionindex")
	public String todivisionindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth3");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/divisionindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// ����վ
	@RequestMapping("/tocheerupindex")
	public String tocheerupindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth4");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/cheerupindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// �ִ������г�
	@RequestMapping("/tostoredispatchindex")
	public String tostoredispatchindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth5");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/storedispatchindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// ���۳���
	@RequestMapping("/tomarketindex")
	public String tomarketindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth6");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/marketindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "��û�н����ϵͳ��Ȩ�ޣ�����ϵ����Ա�������䣡");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// RFID��Ϣ��ʼ��
	@RequestMapping("/toinfoinitindex")
	public String toinfoinitindex(HttpSession session) {
		return "login/infoinitindex";
	}
	// ��Դ�ն˲�ѯ
	@RequestMapping("/tosearchindex")
	public String tosearchindex(HttpSession session) {
		return "login/searchindex";
	}
	// ��ɫȨ���趨
	@RequestMapping("/toroleauthindex")
	public String toroleauthindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth8");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/roleauthindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "�����ǳ�������Ա��û�д�Ȩ�ޣ�");
			}
		} else {
			page = "login/login";
		}
		return page;
	}

	// ��֤�û���֤��
	@RequestMapping("/checkValidateCode")
	public void checkValidateCode(HttpServletRequest request,HttpServletResponse response) {
		// �õ�������ı���֤��
		String validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession();
		// ȡ��ϵͳ���ɵ���֤��
		String code = (String)session.getAttribute("validateCodeInSession");
		try {
			if (code != null) {
				if (code.equals(validateCode)) {
					response.getWriter().print("true");
				} else {
					response.getWriter().print("false");
				}
			} else {
				response.getWriter().print("false");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
