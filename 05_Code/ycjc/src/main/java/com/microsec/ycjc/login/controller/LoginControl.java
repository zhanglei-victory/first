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

	// 用户service
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

	// to登录
	@RequestMapping({"/","/login"})
	public String toUserLogin() {
		return "login/login";
	}
	// 登录
	@RequestMapping("/userLogin")
	public String userLogin(HttpServletRequest request,Model model) {
		System.out.println("====== user login start ======");
		String userName = request.getParameter("username");
		String password = request.getParameter("userPwd");
		if(userName == null || userName.equals("")) {
			model.addAttribute("msg", "请输入用户名！");
			return "login/login";
		}
		if(password == null || password.equals("")) {
			model.addAttribute("msg", "请输入密码！");
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
		// 得到输入的文本验证码
		String validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession();
		// 取得系统生成的验证码
		String code = (String)session.getAttribute("validateCodeInSession");
		if (code != null) {
			if (!code.equals(validateCode)) {
				model.addAttribute("msg", "验证码输入不正确，请重新输入！");
				return "login/login";
			}
		} else {
			model.addAttribute("msg", "验证码不能为空！");
			return "login/login";
		}
		
		// md5密码
		String md5Pwd = "";
		//大写
		String upCaseMd5Pwd = "";
		if (password != null) {
			md5Pwd = DigestUtils.md5Hex(password);
			// 全部转换为大写
			upCaseMd5Pwd = md5Pwd.toUpperCase();
		}
		
		// 查询条件设定
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("username", userName);
		paramMap.put("userPwd", upCaseMd5Pwd);
		UserInfo userinfo = userService.loginSys(paramMap);

		System.out.println("====== user login end ======");
		
		// 权限菜单
		List<UserAuth> authList;
		
		//登录成功
		if (userinfo != null) {
			// 把用户信息放入session
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
			//权限根节点
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
			//善意提醒
//			if(upCaseMd5Pwd.equals(Const.PWD_MD5)) {
//				model.addAttribute("msg", "您的密码为默认密码，不安全，建议您修改密码");
//			}
			return "login/index";
		} else {
			model.addAttribute("msg", Const.LOGIN_ERR_MSG);
			return "login/login";
		}
	}
	// 退出系统
	@RequestMapping("/quit")
	public String quit(HttpSession session) {
		// 把session中的用户移除
		UserInfo userInfo = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		if (userInfo != null) {
			session.removeAttribute(Const.LOGIN_USER_ATTRIBUTE);
			session.invalidate();
		}
		// 跳转到主页面
		return "login/login";
	}
	// 返回主页
	@RequestMapping("/returnindex")
	public String returnindex(HttpSession session,Model model) {
		//正常进入页面无提示语
		model.addAttribute("msg", "");
		// 跳转到主页面
		return "login/index";
	}
	// 养殖场
	@RequestMapping("/tofarmindex")
	public String tofarmindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth1");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/farmindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// 屠宰场
	@RequestMapping("/toslaughterindex")
	public String toslaughterindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth2");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/slaughterindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// 分割加工场
	@RequestMapping("/todivisionindex")
	public String todivisionindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth3");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/divisionindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// 加油站
	@RequestMapping("/tocheerupindex")
	public String tocheerupindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth4");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/cheerupindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// 仓储批发市场
	@RequestMapping("/tostoredispatchindex")
	public String tostoredispatchindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth5");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/storedispatchindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// 零售超市
	@RequestMapping("/tomarketindex")
	public String tomarketindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth6");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/marketindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您没有进入该系统的权限，请联系管理员给您分配！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}
	// RFID信息初始化
	@RequestMapping("/toinfoinitindex")
	public String toinfoinitindex(HttpSession session) {
		return "login/infoinitindex";
	}
	// 溯源终端查询
	@RequestMapping("/tosearchindex")
	public String tosearchindex(HttpSession session) {
		return "login/searchindex";
	}
	// 角色权限设定
	@RequestMapping("/toroleauthindex")
	public String toroleauthindex(HttpSession session,Model model) {
		String page = "";
		String auth = (String)session.getAttribute("auth8");
		if(auth != null) {
			if("1".equals(auth)) {
				page = "login/roleauthindex";
			} else if("0".equals(auth)) {
				page = "login/index";
				model.addAttribute("msg", "您不是超级管理员，没有此权限！");
			}
		} else {
			page = "login/login";
		}
		return page;
	}

	// 验证用户验证码
	@RequestMapping("/checkValidateCode")
	public void checkValidateCode(HttpServletRequest request,HttpServletResponse response) {
		// 得到输入的文本验证码
		String validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession();
		// 取得系统生成的验证码
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
