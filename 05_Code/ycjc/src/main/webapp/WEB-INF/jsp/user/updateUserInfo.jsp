<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="user_updateForm" id="user_updateForm" method="post">
<br>
	<table align="center">
		<tr>
			<td><font style="color:red;"> * </font>登录名:</td>
			<td><input type="text" name="username" id="username" readonly="readonly" style="background-color: #EDEDED;" class="easyui-validatebox pianyi" data-options="required:true,validType:'username'"
					value="${userInfoForModify.username }" /> <input
					type="hidden" name="userid" id="userid"
					value="${userInfoForModify.userid }" /> <input type="hidden"
					name="userPwd" id="userPwd"
					value="${userInfoForModify.userPwd }" /> <input type="hidden"
					name="userstatus" id="userstatus"
					value="${userInfoForModify.userstatus }" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>职务:</td>
			<td><input type="text" id="userposition" name="userposition" value="${userInfoForModify.userposition }" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>真实名字:</td>
			<td><input type="text" id="userRealName" name="userRealName" value="${userInfoForModify.userRealName }" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>手机:</td>
			<td><input type="text" id="userphone" name="userphone" value="${userInfoForModify.userphone }" class="easyui-validatebox pianyi" data-options="required:true,validType:'mobile'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>角色:</td>
			<td><select name="userroleid" id="userroleid" class="easyui-validatebox pianyi" data-options="required:true">
					<c:forEach items="${roleList}" var="role">
						<c:choose>
							<c:when
								test="${userInfoForModify.userroleid == role.roleid}">
								<option value="${role.roleid}" selected="selected">${role.rolename}</option>
							</c:when>
							<c:otherwise>
								<option value="${role.roleid}">${role.rolename}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				</td>
 			</tr>
 		<tr>
			<td><font style="color:red;"> * </font>性别:</td>
			<td><select name="userSex" id="userSex" class="easyui-validatebox pianyi" data-options="required:true">
					<c:if test="${userInfoForModify.userSex == '1' }">
						<option value="1" selected="selected">男</option>
						<option value="0">女</option>
					</c:if>
					<c:if test="${userInfoForModify.userSex == '0' }">
						<option value="1">男</option>
						<option value="0" selected="selected">女</option>
					</c:if>
			</select>
			</td>
 			</tr>

		<tr>
			<td><font style="color:red;"> * </font>邮件地址:</td>
			<td><input type="text" maxlength="30" id="userEmail" name="userEmail" value="${userInfoForModify.userEmail }" class="easyui-validatebox pianyi" data-options="required:true,validType:'email'" />
			</td>
		</tr>
	</table>
</form>
