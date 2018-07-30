<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="user_addForm" id="user_addForm" method="post">
<br>
	<table align="center">
		<tr>
			<td><font style="color:red;"> * </font>登录名:</td>
			<td>
				<input type="text" id="username" name="username" class="easyui-validatebox pianyi" data-options="required:true,validType:'username'" />
				<input type="hidden" name="userstatus" id="userstatus" value="0" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>职务:</td>
			<td>
				<input type="text" id="userposition" name="userposition" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>真实名字:</td>
			<td><input type="text" id="userRealName" name="userRealName" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>手机:</td>
			<td><input type="text" id="userphone" name="userphone" class="easyui-validatebox pianyi" data-options="required:true,validType:'mobile'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>角色:</td>
			<td><select name="userroleid" id="userroleid" class="easyui-validatebox pianyi" data-options="required:true">
					<c:forEach items="${roleList}" var="role">
						<option value="${role.roleid}">${role.rolename}</option>
					</c:forEach>
			</select>
			</td>
 			</tr>
 		<tr>
			<td><font style="color:red;"> * </font>性别:</td>
			<td><select name="userSex" id="userSex" class="easyui-validatebox pianyi" data-options="required:true">
					<option value="1">
						男
					</option>
					<option value="0">
						女
					</option>
			</select>
			</td>
 			</tr>
		<tr>
			<td><font style="color:red;"> * </font>邮件地址:</td>
			<td><input type="text" maxlength="30" id="userEmail" name="userEmail" class="easyui-validatebox pianyi" data-options="required:true,validType:'email'" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
			
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>密码:</td>
			<td><input type="password" id="userPwd" name="userPwd" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>确认:</td>
			<td>
<!-- 				<input type="password" id="confirmUserPwd" name="confirmUserPwd" class="easyui-validatebox pianyi" validType="equalTo['#userPwd']" invalidMessage="两次输入密码不匹配" /> -->
				<input type="password" id="confirmUserPwd" name="confirmUserPwd" class="easyui-validatebox pianyi" data-options="required:true" validType="eqPwd['#userPwd']" />
			</td>
		</tr>
	</table>
</form>
