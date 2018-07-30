package com.microsec.ycjc.user.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.user.cond.RoleCond;
import com.microsec.ycjc.user.pojo.Role;
import com.microsec.ycjc.user.pojo.RoleAuth;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.user.service.IAuthorityService;
import com.microsec.ycjc.user.service.IRoleAuthService;
import com.microsec.ycjc.user.service.IRoleService;
import com.microsec.ycjc.user.service.IUserInfoService;
import com.microsec.ycjc.util.Const;
import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.YcjcJsonUtil;

@Controller
@RequestMapping("/user/role/")
public class RoleControl {

	// 角色service
	@Resource
	private IRoleService roleService;

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	// 权限service
	@Resource
	private IAuthorityService authService;
	
	public IAuthorityService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthorityService authService) {
		this.authService = authService;
	}

	// 角色权限对应service
	@Resource
	private IRoleAuthService roleAuthService;
	
	public IRoleAuthService getRoleAuthService() {
		return roleAuthService;
	}

	public void setRoleAuthService(IRoleAuthService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}

	// 用户service
	@Resource
	private IUserInfoService userService;

	public IUserInfoService getUserService() {
		return userService;
	}

	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}

	// 根据查询条件得到所有组织机构数据
	@RequestMapping("/findAllRoleInfoByCond")
	public void findAllStrucInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// 查询条件
		String name = request.getParameter("search_role_name");
		if(name == null) {
			name = "";
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
		RoleCond cond = new RoleCond();
		cond.setRolename(name);
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = roleService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}
	// 得到角色所有的数据
	@RequestMapping("/toRoleList")
	public String toRoleList(Model model) {
		return "user/roleList";
	}
	//添加角色
	@RequestMapping("/addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response,Role role) {
		String checkValues = request.getParameter("authids");
		// 拆分权限
		String[] authids = checkValues.split(",");
		Json j = new Json();
		try {
			//插入数据
			roleService.insertInfo(role);
			RoleAuth roleAuth;
			for (String authId:authids) {
				// 把父节点过滤掉
				if (authId.length() == 1) {
					continue;
				}
				roleAuth = new RoleAuth();
				roleAuth.setRoleid(role.getRoleid());
				roleAuth.setAuthid(authId);
				// 插入角色权限对应表
				roleAuthService.insertInfo(roleAuth);
			}
			//设定
			j.setSuccess(true);
			j.setMsg("添加成功");
			j.setObj(role);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//跳转到添加角色页面
	@RequestMapping("/toAdd")
	public String toAddRole(Model model) {
		return "user/addRole";
	}
	
	// 跳转到修改角色页面
	@RequestMapping("/toUpdate/{id}")
	public String toUpdateRole(@PathVariable(value="id") String roleId,Model model) {
		// 得到当前角色
		Role tmpObj = roleService.findInfoById(roleId);
		model.addAttribute("roleForModify", tmpObj);
		//得到当前角色对应的权限
		List<RoleAuth> roleAuthList = roleAuthService.findInfoByRoleId(roleId);
		model.addAttribute("currentAuthList", roleAuthList);
		// 得到所有权限
//		List<Authority> authList = authService.findAllInfoList();
//		model.addAttribute("authList", authList);
		return "user/updateRole";
	}

	// 修改角色
	@RequestMapping("/updateRole")
	public void updateRole(HttpServletRequest request,HttpServletResponse response,Role role) {
		//修改当前角色
		Json j = new Json();
		try {
			//修改数据
			//修改当前角色
			roleService.updateInfo(role);
			
			//删除当前角色对应权限
			roleAuthService.deleteInfo(role.getRoleid());
			//得到选中权限
			String checkValues = request.getParameter("authids");
			// 拆分权限
			String[] authids = checkValues.split(",");
			
			RoleAuth roleAuth;
			for (String authId:authids) {
				// 把父节点过滤掉
				if (authId.length() == 1) {
					continue;
				}
				roleAuth = new RoleAuth();
				roleAuth.setRoleid(role.getRoleid());
				roleAuth.setAuthid(authId);
				// 插入角色权限对应表
				roleAuthService.insertInfo(roleAuth);
			}
			//设定
			j.setSuccess(true);
			j.setMsg("修改成功");
			j.setObj(role);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// 删除角色
	@RequestMapping("/deleteRole/{id}")
	public void deleteRole(@PathVariable(value="id") String roleId,HttpServletResponse response) {
		Json j = new Json();
		try {
			//删除当前角色对应权限
			roleAuthService.deleteInfo(roleId);
			//删除角色表
			roleService.deleteInfo(roleId);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	// 删除角色
	@RequestMapping("/deleteRoleCheck")
	public void deleteRoleCheck(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleid");
		//判断角色是否被用户使用
		List<UserInfo> userInfoList = userService.findInfoByRoleId(roleId);
		try {
			if(userInfoList != null) {
				if (userInfoList.size() > 0) {
					response.getWriter().print("false");
				} else {
					// 删除
					//deleteRole(roleId);
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
	// 验证角色名名是否被占用
	@RequestMapping("/checkRoleName")
	public void checkRoleName(HttpServletRequest request,HttpServletResponse response) {
		// 得到登录名
		String rolename = request.getParameter("rolename");
		List<Role> roleList = roleService.findAllInfoList();
		//默认验证通过
		String flag = "true";
		try {
			if (roleList != null) {
				for(Role role:roleList) {
					if(role.getRolename().equals(rolename)) {
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
