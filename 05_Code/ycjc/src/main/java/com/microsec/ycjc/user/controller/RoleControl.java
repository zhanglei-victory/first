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

	// ��ɫservice
	@Resource
	private IRoleService roleService;

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	// Ȩ��service
	@Resource
	private IAuthorityService authService;
	
	public IAuthorityService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthorityService authService) {
		this.authService = authService;
	}

	// ��ɫȨ�޶�Ӧservice
	@Resource
	private IRoleAuthService roleAuthService;
	
	public IRoleAuthService getRoleAuthService() {
		return roleAuthService;
	}

	public void setRoleAuthService(IRoleAuthService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}

	// �û�service
	@Resource
	private IUserInfoService userService;

	public IUserInfoService getUserService() {
		return userService;
	}

	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}

	// ���ݲ�ѯ�����õ�������֯��������
	@RequestMapping("/findAllRoleInfoByCond")
	public void findAllStrucInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
		String name = request.getParameter("search_role_name");
		if(name == null) {
			name = "";
		}
		//��ҳ����ÿҳ����
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//��ǰҳ  
		int intPage = Integer.parseInt((page == null || page == "0") ? Const.START_PAGE_NUMBER: page);
		// ÿҳ��ʾ����
		int number = Integer.parseInt((rows == null || rows == "0") ? Const.SINGLE_PAGE_SIZE: rows);
		// ÿҳ�Ŀ�ʼ��¼ ��һҳΪ1 �ڶ�ҳΪnumber +1
		int start = (intPage - 1) * number;
		RoleCond cond = new RoleCond();
		cond.setRolename(name);
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = roleService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}
	// �õ���ɫ���е�����
	@RequestMapping("/toRoleList")
	public String toRoleList(Model model) {
		return "user/roleList";
	}
	//��ӽ�ɫ
	@RequestMapping("/addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response,Role role) {
		String checkValues = request.getParameter("authids");
		// ���Ȩ��
		String[] authids = checkValues.split(",");
		Json j = new Json();
		try {
			//��������
			roleService.insertInfo(role);
			RoleAuth roleAuth;
			for (String authId:authids) {
				// �Ѹ��ڵ���˵�
				if (authId.length() == 1) {
					continue;
				}
				roleAuth = new RoleAuth();
				roleAuth.setRoleid(role.getRoleid());
				roleAuth.setAuthid(authId);
				// �����ɫȨ�޶�Ӧ��
				roleAuthService.insertInfo(roleAuth);
			}
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			j.setObj(role);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//��ת����ӽ�ɫҳ��
	@RequestMapping("/toAdd")
	public String toAddRole(Model model) {
		return "user/addRole";
	}
	
	// ��ת���޸Ľ�ɫҳ��
	@RequestMapping("/toUpdate/{id}")
	public String toUpdateRole(@PathVariable(value="id") String roleId,Model model) {
		// �õ���ǰ��ɫ
		Role tmpObj = roleService.findInfoById(roleId);
		model.addAttribute("roleForModify", tmpObj);
		//�õ���ǰ��ɫ��Ӧ��Ȩ��
		List<RoleAuth> roleAuthList = roleAuthService.findInfoByRoleId(roleId);
		model.addAttribute("currentAuthList", roleAuthList);
		// �õ�����Ȩ��
//		List<Authority> authList = authService.findAllInfoList();
//		model.addAttribute("authList", authList);
		return "user/updateRole";
	}

	// �޸Ľ�ɫ
	@RequestMapping("/updateRole")
	public void updateRole(HttpServletRequest request,HttpServletResponse response,Role role) {
		//�޸ĵ�ǰ��ɫ
		Json j = new Json();
		try {
			//�޸�����
			//�޸ĵ�ǰ��ɫ
			roleService.updateInfo(role);
			
			//ɾ����ǰ��ɫ��ӦȨ��
			roleAuthService.deleteInfo(role.getRoleid());
			//�õ�ѡ��Ȩ��
			String checkValues = request.getParameter("authids");
			// ���Ȩ��
			String[] authids = checkValues.split(",");
			
			RoleAuth roleAuth;
			for (String authId:authids) {
				// �Ѹ��ڵ���˵�
				if (authId.length() == 1) {
					continue;
				}
				roleAuth = new RoleAuth();
				roleAuth.setRoleid(role.getRoleid());
				roleAuth.setAuthid(authId);
				// �����ɫȨ�޶�Ӧ��
				roleAuthService.insertInfo(roleAuth);
			}
			//�趨
			j.setSuccess(true);
			j.setMsg("�޸ĳɹ�");
			j.setObj(role);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("�޸�ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// ɾ����ɫ
	@RequestMapping("/deleteRole/{id}")
	public void deleteRole(@PathVariable(value="id") String roleId,HttpServletResponse response) {
		Json j = new Json();
		try {
			//ɾ����ǰ��ɫ��ӦȨ��
			roleAuthService.deleteInfo(roleId);
			//ɾ����ɫ��
			roleService.deleteInfo(roleId);
			j.setSuccess(true);
			j.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("ɾ��ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	// ɾ����ɫ
	@RequestMapping("/deleteRoleCheck")
	public void deleteRoleCheck(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleid");
		//�жϽ�ɫ�Ƿ��û�ʹ��
		List<UserInfo> userInfoList = userService.findInfoByRoleId(roleId);
		try {
			if(userInfoList != null) {
				if (userInfoList.size() > 0) {
					response.getWriter().print("false");
				} else {
					// ɾ��
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
	// ��֤��ɫ�����Ƿ�ռ��
	@RequestMapping("/checkRoleName")
	public void checkRoleName(HttpServletRequest request,HttpServletResponse response) {
		// �õ���¼��
		String rolename = request.getParameter("rolename");
		List<Role> roleList = roleService.findAllInfoList();
		//Ĭ����֤ͨ��
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
