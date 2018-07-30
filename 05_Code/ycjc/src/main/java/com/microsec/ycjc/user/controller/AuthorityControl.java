package com.microsec.ycjc.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microsec.ycjc.user.pojo.Authority;
import com.microsec.ycjc.user.pojo.AuthorityTree;
import com.microsec.ycjc.user.pojo.RoleAuth;
import com.microsec.ycjc.user.service.IAuthorityService;
import com.microsec.ycjc.user.service.IRoleAuthService;
import com.microsec.ycjc.util.Const;

@Controller
@RequestMapping("/user/auth/")
public class AuthorityControl {

	// 角色权限对应service
	@Resource
	private IRoleAuthService roleAuthService;
	
	public IRoleAuthService getRoleAuthService() {
		return roleAuthService;
	}

	public void setRoleAuthService(IRoleAuthService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}
	@Resource
	private IAuthorityService service;

	public IAuthorityService getService() {
		return service;
	}

	public void setService(IAuthorityService service) {
		this.service = service;
	}

	// 得到角色所有的数据
	@RequestMapping("/getAllAuth")
	public String getAllList(Model model) {
		System.out.println("====== get all auth start ======");
		List<Authority> authList = service.findAllInfoList();
		model.addAttribute("authList", authList);
		System.out.println("====== get all auth end ======");
		return "user/authList";
	}

	// 得到树形结构
	@RequestMapping("/getNewTree")
	public void getNewAuthTree(HttpServletResponse response) {
		List<Authority> authList = service.findAllInfoList();
		//树形结构权限
		List<AuthorityTree> atList = new ArrayList<AuthorityTree>();
		AuthorityTree at;
		for(Authority auth:authList) {
			at = new AuthorityTree();
			at.setId(auth.getAuthid());
			if("0".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname());
			} else if("1".equals(auth.getSysflag())) {
				//at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_WATER).concat("）"));
				at.setName(auth.getAuthname());
			} else if("2".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_WELL).concat("）"));
			} else if("3".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_SOURCE).concat("）"));
			}
			
			//at.setpId(auth.getPriAuthId());
			//父节点
			if(auth.getPriAuthId().equals(Const.ROOT_AUTH_ID)) {
				at.setOpen(true);
				at.setpId("0");
			} else {
				//if(auth.getAuthid().equals("42")){at.setChecked(true);}
				at.setOpen(false);
				at.setpId(auth.getPriAuthId());
			}
			atList.add(at);
		}
		JSONArray json = null;
		json = JSONArray.fromObject(atList);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("***********NEW TREE JSON************\n"+json);
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 得到当前树形结构
	@RequestMapping("/getCurrentTree/{id}")
	public void getCurrentAuthTree(@PathVariable(value="id") String roleId,HttpServletResponse response) {
		//得到当前角色对应的权限
		List<RoleAuth> roleAuthList = roleAuthService.findInfoByRoleId(roleId);
		//得到所有的权限
		List<Authority> authList = service.findAllInfoList();
		//树形结构权限
		List<AuthorityTree> atList = new ArrayList<AuthorityTree>();
		AuthorityTree at;
		for(Authority auth:authList) {
			//权限id
			String authid = auth.getAuthid();
			at = new AuthorityTree();
			// 当前角色对应权限
			for(RoleAuth tmpRoleAuth:roleAuthList) {
				//过滤掉父节点
				if(authid.length() == 1) {
					continue;
				}
				if(authid.equals(tmpRoleAuth.authid)) {
					at.setChecked(true);
				}
			}
			
			at.setId(auth.getAuthid());
			if("0".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname());
			} else if("1".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname());
				//at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_WATER).concat("）"));
			} else if("2".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_WELL).concat("）"));
			} else if("3".equals(auth.getSysflag())) {
				at.setName(auth.getAuthname().concat("（").concat(Const.SYS_NAME_SOURCE).concat("）"));
			}
			//at.setpId(auth.getPriAuthId());
			//父节点
			if(auth.getPriAuthId().equals(Const.ROOT_AUTH_ID)) {
				at.setOpen(true);
				at.setpId("0");
			} else {
				//if(auth.getAuthid().equals("42")){at.setChecked(true);}
				at.setOpen(false);
				at.setpId(auth.getPriAuthId());
			}
			atList.add(at);
		}
		JSONArray json = null;
		json = JSONArray.fromObject(atList);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("***********UPDATE TREE JSON************\n"+json);
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据名称查询
	@RequestMapping("/getAuthByName")
	public String getListByName(@RequestParam(value="authnameSel") String authName, Model model) {
		System.out.println("authName:"+authName);
		List<Authority> authList = service.findInfoListByName(authName);
		model.addAttribute("authList", authList);
		return "user/authList";
	}
	//添加角色
	@RequestMapping("/addAuth")
	public String addAuth(Authority auth) {
		service.insertInfo(auth);
		return "redirect:/user/auth/getAllAuth";
	}
	//跳转到添加角色页面
	@RequestMapping("/toAdd")
	public String toAddAuth() {
		return "user/addAuth";
	}
	
	// 修改角色
	@RequestMapping("/toUpdate/{id}")
	public String toUpdateAuth(@PathVariable(value="id") String authId,Model model) {
		Authority tmpObj = service.findInfoById(authId);
		model.addAttribute("authForModify", tmpObj);
		return "user/updateAuth";
	}

	// 修改角色
	@RequestMapping("/updateAuth")
	public String updateAuth(Authority auth) {
		service.updateInfo(auth);
		return "redirect:/user/auth/getAllAuth";
	}
	// 删除角色
	@RequestMapping("/deleteAuth/{id}")
	public String deleteAuth(@PathVariable(value="id") String authId) {
		service.deleteInfo(authId);
		return "redirect:/user/auth/getAllAuth";
	}
}
