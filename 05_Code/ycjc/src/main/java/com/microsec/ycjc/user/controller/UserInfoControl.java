package com.microsec.ycjc.user.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.YcjcJsonUtil;
import com.microsec.ycjc.user.cond.UserInfoCond;
import com.microsec.ycjc.user.pojo.Role;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.user.service.IRoleService;
import com.microsec.ycjc.user.service.IUserInfoService;
import com.microsec.ycjc.util.Const;

@Controller
@RequestMapping("/user/userInfo/")
public class UserInfoControl {

	// 用户service
	@Resource
	private IUserInfoService userService;

	public IUserInfoService getUserService() {
		return userService;
	}

	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}

	//角色service
	@Resource
	private IRoleService roleService;
	
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	// 得到用户所有的数据
	@RequestMapping("/findAllUserInfoByCond")
	public void findAllUserInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// 查询条件
		//用户名
		String username = request.getParameter("search_user_name");
		if(username == null) {
			username = "";
		}
		// 真实姓名
		String realname = request.getParameter("search_user_realname");
		if(realname == null) {
			realname = "";
		}
		// 所属机构
		String belongStructid = request.getParameter("search_belong_structid");
		if(belongStructid == null) {
			belongStructid = "";
		}
		//分页及其每页条数
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//当前页  
		int intPage = Integer.parseInt((page == null || page == "0") ? Const.START_PAGE_NUMBER: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? Const.SINGLE_PAGE_SIZE: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		UserInfoCond scond = new UserInfoCond();
		scond.setUsername(username);
		scond.setUserRealName(realname);
		scond.setStart(start);
		scond.setSinglePagesize(number);
		
		DataGrid d = userService.findAllInfoByCond(scond);
		YcjcJsonUtil.writeJson(d, response);
	}
	
	// 得到用户所有的数据
	@RequestMapping("/toUserList")
	public String toUserList(Model model) {
		return "user/userInfoList";
	}
	//添加用户
	@RequestMapping("/addUserInfo")
	public void addUserInfo(UserInfo userInfo,HttpServletResponse response) {
		Json j = new Json();
		try {
			//插入数据
			String userPwd = userInfo.getUserPwd();
			// md5加密
			// 转换为大写的MD5码
			String upCaseMd5Pwd = DigestUtils.md5Hex(userPwd).toUpperCase();
			userInfo.setUserPwd(upCaseMd5Pwd);
			userService.insertInfo(userInfo);
			//锁定状态
			userInfo = userService.findLockorUnlockInfoById(userInfo.getUserid());
			//角色名
			Role role = roleService.findInfoById(userInfo.getUserroleid());
			userInfo.setUserroleid(role.getRolename());
			//设定
			j.setSuccess(true);
			j.setMsg("添加成功");
			j.setObj(userInfo);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//跳转到添加用户页面
	@RequestMapping("/toAdd")
	public String toAddUserInfo(Model model) {
		//得到角色列表
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		return "user/addUserInfo";
	}
	
	// 修改用户
	@RequestMapping("/toUpdate/{id}")
	public String toUpdateUserInfo(@PathVariable(value="id") String userInfoId,Model model) {
		//得到当前修改的用户
		UserInfo tmpObj = userService.findInfoById(userInfoId);
		model.addAttribute("userInfoForModify", tmpObj);

		//得到角色列表
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		
		return "user/updateUserInfo";
	}
	
	// 个人修改
	@RequestMapping("/toSetting/{id}")
	public String toSettingUserInfo(@PathVariable(value="id") String userInfoId,Model model) {
		//得到当前修改的用户
		UserInfo tmpObj = userService.findInfoById(userInfoId);
		model.addAttribute("userInfoForModify", tmpObj);

		//得到角色列表
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		
		return "user/settingUserInfo";
	}

	// 修改个人设置
	@RequestMapping("/settingUserInfo")
	public void settingUserInfo(UserInfo userInfo,HttpServletResponse response) {
		userService.updateInfo(userInfo);
		// 跳转到主页面
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("修改成功");
		} catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// 修改个人密码设置
	@RequestMapping("/toUpdPwd/{id}")
	public String toUpdPwd(@PathVariable(value="id") String userId,Model model) {
		UserInfo ui = userService.findInfoById(userId);
		model.addAttribute("userinfo", ui);
		return "user/updPwdUserInfo";
	}

	// 修改用户
	@RequestMapping("/updateUserInfo")
	public void updateUserInfo(UserInfo userInfo,HttpServletResponse response) {
		//修改后需要展示的实体
		//UserInfo user = new UserInfo();
		Json j = new Json();
		try {
			//修改数据
			userService.updateInfo(userInfo);
			//锁定状态
			userInfo = userService.findLockorUnlockInfoById(userInfo.getUserid());
			
			//角色名
			Role role = roleService.findInfoById(userInfo.getUserroleid());
			userInfo.setUserroleid(role.getRolename());
			//设定
			j.setSuccess(true);
			j.setMsg("修改成功");
			j.setObj(userInfo);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// 删除用户
	@RequestMapping("/deleteUserInfo/{id}")
	public void deleteUserInfo(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			userService.deleteInfo(userInfoId);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//重置密码
	@RequestMapping("/resetUserPwd/{id}")
	public void resetUserPwd(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			userService.resetUserPwd(userInfoId);
			j.setSuccess(true);
			j.setMsg("密码重置成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("密码重置失败");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//锁定用户
	@RequestMapping("/lockUser/{id}")
	public void lockUser(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		UserInfo user = new UserInfo();
		try {
			userService.lockUser(userInfoId);
			user = userService.findLockorUnlockInfoById(userInfoId);
			
			//角色名
			Role role = roleService.findInfoById(user.getUserroleid());
			user.setUserroleid(role.getRolename());
			j.setSuccess(true);
			j.setMsg("锁定成功");
			j.setObj(user);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("锁定失败");
			j.setObj(user);
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//解锁用户
	@RequestMapping("/unlockUser/{id}")
	public void unlockUser(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		UserInfo user = new UserInfo();
		try {
			userService.unlockUser(userInfoId);
			user = userService.findLockorUnlockInfoById(userInfoId);
			//角色名
			Role role = roleService.findInfoById(user.getUserroleid());
			user.setUserroleid(role.getRolename());
			j.setSuccess(true);
			j.setMsg("解锁成功");
			j.setObj(user);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("解锁失败");
			j.setObj(user);
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	// 修改个人密码
	@RequestMapping("/updPwdInfo/{oldpwd}")
	public void updPwdInfo(@PathVariable(value="oldpwd") String oldPwd ,UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) {
		//新密码和旧密码一样
		if(oldPwd.equals(userInfo.getUserPwd())) {
			Json j = new Json();
			j.setSuccess(false);
			j.setMsg("新密码不能和旧密码重复！");
			YcjcJsonUtil.writeJson(j, response);
		}
		// 转换为md5密码
		String md5OldPwd = DigestUtils.md5Hex(oldPwd);
		//大写
		String upCaseMd5OldPwd = md5OldPwd.toUpperCase();
		HttpSession session = request.getSession();
		// 取得登录用户
		UserInfo ui = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);	
		try {
			if (ui != null) {
				
				if (upCaseMd5OldPwd.equals(ui.getUserPwd())) {
					//把密码转换成md5加密密码
					String md5Pwd = DigestUtils.md5Hex(userInfo.getUserPwd());
					// 大写
					String upCaseMd5Pwd = md5Pwd.toUpperCase();
					userInfo.setUserPwd(upCaseMd5Pwd);
					userService.updateInfo(userInfo);
					
					Json j = new Json();
					j.setSuccess(true);
					j.setMsg("密码修改成功");
					YcjcJsonUtil.writeJson(j, response);
				} else {
					Json j = new Json();
					j.setSuccess(false);
					j.setMsg("密码修改失败，旧密码输入不正确");
					YcjcJsonUtil.writeJson(j, response);
				}
			} else {
				Json j = new Json();
				j.setSuccess(false);
				j.setMsg("密码修改失败");
				YcjcJsonUtil.writeJson(j, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 验证用户登录名是否被占用
	@RequestMapping("/checkLoginUserName")
	public void checkLoginUserName(HttpServletRequest request,HttpServletResponse response) {
		// 得到登录名
		String username = request.getParameter("username");
		List<UserInfo> userinfoList = userService.findAllInfoList();
		//默认验证通过
		String flag = "true";
		try {
			if (userinfoList != null) {
				for(UserInfo ui:userinfoList) {
					if(ui.getUsername().equals(username)) {
						flag = "false";
						break;
					}
				}
				if(flag.equals("false")) {
					response.getWriter().print("false");
				} else if(flag.equals("true")) {
					response.getWriter().print("true");
				}
			} else {
				response.getWriter().print("true");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
