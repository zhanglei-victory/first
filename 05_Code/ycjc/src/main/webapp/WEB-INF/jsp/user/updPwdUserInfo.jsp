<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<form action="${path}/user/userInfo/updPwdInfo" method="post" id="userPwdUpdForm" name="userPwdUpdForm">
	<br>
	<table align="center">
		<tr>
			<td>
				<font style="color:red;"> * </font>旧密码:
			</td>
			<td><input type="hidden" name="userid" id="userid" value="${userinfo.userid }" />
			<input type="hidden" name="username" id="username" value="${userinfo.username }" />
			<input type="hidden" name="userposition" id="userposition" value="${userinfo.userposition }" />
			<input type="hidden" name="userRealName" id="userRealName" value="${userinfo.userRealName }" />
			<input type="hidden" name="userphone" id="userphone" value="${userinfo.userphone }" />
			<input type="hidden" name="userroleid" id="userroleid" value="${userinfo.userroleid }" />
			<input type="hidden" name="userSex" id="userSex" value="${userinfo.userSex }" />
			<input type="hidden" name="userEmail" id="userEmail" value="${userinfo.userEmail }" />
			<input type="hidden" name="userstatus" id="userstatus" value="${userinfo.userstatus }" />
			<input type="password" name="oldUserPwd" id="oldUserPwd" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td>
				<font style="color:red;"> * </font>新密码:
			</td>
			<td>
				<input type="password" name="userPwd" id="userPwd" 
				class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td>
				<font style="color:red;"> * </font>密码确认:
			</td>
			<td>
				<input type="password" name="confirmUserPwd" id="confirmUserPwd"
				class="easyui-validatebox pianyi" validType="equalTo['#userPwd']" required="true" invalidMessage="两次输入密码不匹配" />
			</td>
		</tr>
	</table>
</form>
