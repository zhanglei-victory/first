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

	// �û�service
	@Resource
	private IUserInfoService userService;

	public IUserInfoService getUserService() {
		return userService;
	}

	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}

	//��ɫservice
	@Resource
	private IRoleService roleService;
	
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	// �õ��û����е�����
	@RequestMapping("/findAllUserInfoByCond")
	public void findAllUserInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
		//�û���
		String username = request.getParameter("search_user_name");
		if(username == null) {
			username = "";
		}
		// ��ʵ����
		String realname = request.getParameter("search_user_realname");
		if(realname == null) {
			realname = "";
		}
		// ��������
		String belongStructid = request.getParameter("search_belong_structid");
		if(belongStructid == null) {
			belongStructid = "";
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
		UserInfoCond scond = new UserInfoCond();
		scond.setUsername(username);
		scond.setUserRealName(realname);
		scond.setStart(start);
		scond.setSinglePagesize(number);
		
		DataGrid d = userService.findAllInfoByCond(scond);
		YcjcJsonUtil.writeJson(d, response);
	}
	
	// �õ��û����е�����
	@RequestMapping("/toUserList")
	public String toUserList(Model model) {
		return "user/userInfoList";
	}
	//����û�
	@RequestMapping("/addUserInfo")
	public void addUserInfo(UserInfo userInfo,HttpServletResponse response) {
		Json j = new Json();
		try {
			//��������
			String userPwd = userInfo.getUserPwd();
			// md5����
			// ת��Ϊ��д��MD5��
			String upCaseMd5Pwd = DigestUtils.md5Hex(userPwd).toUpperCase();
			userInfo.setUserPwd(upCaseMd5Pwd);
			userService.insertInfo(userInfo);
			//����״̬
			userInfo = userService.findLockorUnlockInfoById(userInfo.getUserid());
			//��ɫ��
			Role role = roleService.findInfoById(userInfo.getUserroleid());
			userInfo.setUserroleid(role.getRolename());
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			j.setObj(userInfo);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//��ת������û�ҳ��
	@RequestMapping("/toAdd")
	public String toAddUserInfo(Model model) {
		//�õ���ɫ�б�
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		return "user/addUserInfo";
	}
	
	// �޸��û�
	@RequestMapping("/toUpdate/{id}")
	public String toUpdateUserInfo(@PathVariable(value="id") String userInfoId,Model model) {
		//�õ���ǰ�޸ĵ��û�
		UserInfo tmpObj = userService.findInfoById(userInfoId);
		model.addAttribute("userInfoForModify", tmpObj);

		//�õ���ɫ�б�
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		
		return "user/updateUserInfo";
	}
	
	// �����޸�
	@RequestMapping("/toSetting/{id}")
	public String toSettingUserInfo(@PathVariable(value="id") String userInfoId,Model model) {
		//�õ���ǰ�޸ĵ��û�
		UserInfo tmpObj = userService.findInfoById(userInfoId);
		model.addAttribute("userInfoForModify", tmpObj);

		//�õ���ɫ�б�
		List<Role> roleList = roleService.findAllInfoList();
		model.addAttribute("roleList", roleList);
		
		return "user/settingUserInfo";
	}

	// �޸ĸ�������
	@RequestMapping("/settingUserInfo")
	public void settingUserInfo(UserInfo userInfo,HttpServletResponse response) {
		userService.updateInfo(userInfo);
		// ��ת����ҳ��
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("�޸ĳɹ�");
		} catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("�޸�ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// �޸ĸ�����������
	@RequestMapping("/toUpdPwd/{id}")
	public String toUpdPwd(@PathVariable(value="id") String userId,Model model) {
		UserInfo ui = userService.findInfoById(userId);
		model.addAttribute("userinfo", ui);
		return "user/updPwdUserInfo";
	}

	// �޸��û�
	@RequestMapping("/updateUserInfo")
	public void updateUserInfo(UserInfo userInfo,HttpServletResponse response) {
		//�޸ĺ���Ҫչʾ��ʵ��
		//UserInfo user = new UserInfo();
		Json j = new Json();
		try {
			//�޸�����
			userService.updateInfo(userInfo);
			//����״̬
			userInfo = userService.findLockorUnlockInfoById(userInfo.getUserid());
			
			//��ɫ��
			Role role = roleService.findInfoById(userInfo.getUserroleid());
			userInfo.setUserroleid(role.getRolename());
			//�趨
			j.setSuccess(true);
			j.setMsg("�޸ĳɹ�");
			j.setObj(userInfo);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("�޸�ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// ɾ���û�
	@RequestMapping("/deleteUserInfo/{id}")
	public void deleteUserInfo(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			userService.deleteInfo(userInfoId);
			j.setSuccess(true);
			j.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("ɾ��ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//��������
	@RequestMapping("/resetUserPwd/{id}")
	public void resetUserPwd(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			userService.resetUserPwd(userInfoId);
			j.setSuccess(true);
			j.setMsg("�������óɹ�");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("��������ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//�����û�
	@RequestMapping("/lockUser/{id}")
	public void lockUser(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		UserInfo user = new UserInfo();
		try {
			userService.lockUser(userInfoId);
			user = userService.findLockorUnlockInfoById(userInfoId);
			
			//��ɫ��
			Role role = roleService.findInfoById(user.getUserroleid());
			user.setUserroleid(role.getRolename());
			j.setSuccess(true);
			j.setMsg("�����ɹ�");
			j.setObj(user);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("����ʧ��");
			j.setObj(user);
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	//�����û�
	@RequestMapping("/unlockUser/{id}")
	public void unlockUser(@PathVariable(value="id") String userInfoId,HttpServletResponse response) {
		Json j = new Json();
		UserInfo user = new UserInfo();
		try {
			userService.unlockUser(userInfoId);
			user = userService.findLockorUnlockInfoById(userInfoId);
			//��ɫ��
			Role role = roleService.findInfoById(user.getUserroleid());
			user.setUserroleid(role.getRolename());
			j.setSuccess(true);
			j.setMsg("�����ɹ�");
			j.setObj(user);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("����ʧ��");
			j.setObj(user);
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	// �޸ĸ�������
	@RequestMapping("/updPwdInfo/{oldpwd}")
	public void updPwdInfo(@PathVariable(value="oldpwd") String oldPwd ,UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) {
		//������;�����һ��
		if(oldPwd.equals(userInfo.getUserPwd())) {
			Json j = new Json();
			j.setSuccess(false);
			j.setMsg("�����벻�ܺ;������ظ���");
			YcjcJsonUtil.writeJson(j, response);
		}
		// ת��Ϊmd5����
		String md5OldPwd = DigestUtils.md5Hex(oldPwd);
		//��д
		String upCaseMd5OldPwd = md5OldPwd.toUpperCase();
		HttpSession session = request.getSession();
		// ȡ�õ�¼�û�
		UserInfo ui = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);	
		try {
			if (ui != null) {
				
				if (upCaseMd5OldPwd.equals(ui.getUserPwd())) {
					//������ת����md5��������
					String md5Pwd = DigestUtils.md5Hex(userInfo.getUserPwd());
					// ��д
					String upCaseMd5Pwd = md5Pwd.toUpperCase();
					userInfo.setUserPwd(upCaseMd5Pwd);
					userService.updateInfo(userInfo);
					
					Json j = new Json();
					j.setSuccess(true);
					j.setMsg("�����޸ĳɹ�");
					YcjcJsonUtil.writeJson(j, response);
				} else {
					Json j = new Json();
					j.setSuccess(false);
					j.setMsg("�����޸�ʧ�ܣ����������벻��ȷ");
					YcjcJsonUtil.writeJson(j, response);
				}
			} else {
				Json j = new Json();
				j.setSuccess(false);
				j.setMsg("�����޸�ʧ��");
				YcjcJsonUtil.writeJson(j, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ��֤�û���¼���Ƿ�ռ��
	@RequestMapping("/checkLoginUserName")
	public void checkLoginUserName(HttpServletRequest request,HttpServletResponse response) {
		// �õ���¼��
		String username = request.getParameter("username");
		List<UserInfo> userinfoList = userService.findAllInfoList();
		//Ĭ����֤ͨ��
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
