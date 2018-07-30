<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path",path);
%>
<form name="userSettingForm" id="userSettingForm" action="${path}/user/userInfo/settingUserInfo" method="post">
<br>
	<table align="center">
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				职务： -->
<!-- 			</td> -->
<%-- 			<td>${userInfoForModify.userposition } --%>
<%-- 				<input type="hidden" name="userid" id="userid" value="${userInfoForModify.userid }" /> --%>
<%-- 				<input type="hidden" name="userPwd" id="userPwd" value="${userInfoForModify.userPwd }"/> --%>
<%-- 				<input type="hidden" name="userstatus" id="userstatus" value="${userInfoForModify.userstatus }"/> --%>
<%-- 				<input type="hidden" name="username" id="username" value="${userInfoForModify.username }"/> --%>
<!-- 				超级管理员不能修改对应信息 -->
<%-- 				<c:if test="${userInfoForModify.username == 'admin' }"> --%>
<%-- 					<input readonly="readonly" class="pianyi" type="text" name="userposition" id="userposition" value="${userInfoForModify.userposition }"/> --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${userInfoForModify.username != 'admin' }"> --%>
<%-- 					<input type="text" name="userposition" id="userposition" value="${userInfoForModify.userposition }"  --%>
<!-- 					class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'"/> -->
<%-- 				</c:if> --%>
				
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td>
				<font style="color:red;"> * </font>真实名字:
				<input type="hidden" name="userid" id="userid" value="${userInfoForModify.userid }" />
				<input type="hidden" name="userPwd" id="userPwd" value="${userInfoForModify.userPwd }"/>
				<input type="hidden" name="userstatus" id="userstatus" value="${userInfoForModify.userstatus }"/>
				<input type="hidden" name="username" id="username" value="${userInfoForModify.username }"/>
				<input type="hidden" name="userroleid" id="userroleid" value="${userInfoForModify.userroleid }"/>
				<input type="hidden" name="userSex" id="userSex" value="${userInfoForModify.userSex }"/>
				<input type="hidden" name="userposition" id="userposition" value="${userInfoForModify.userposition }"/>
			</td>
			<td>
				<input type="text" name="userRealName" id="userRealName" value="${userInfoForModify.userRealName }" 
				class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td>
				<font style="color:red;"> * </font>手机：
			</td>
			<td>
				<input type="text" name="userphone" id="userphone" value="${userInfoForModify.userphone }" class="easyui-validatebox pianyi"
					data-options="required:true,validType:'mobile'" />
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				角色: -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<select name="userroleid" id="userroleid" class="easyui-validatebox pianyi" data-options="required:true"> -->
<%-- 					<c:forEach items="${roleList}" var="role"> --%>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${userInfoForModify.userroleid == role.roleid}"> --%>
<%-- 								<option value="${role.roleid}" selected="selected">${role.rolename}</option> --%>
<%-- 							</c:when> --%>
<%-- 						</c:choose> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				性别： -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<select name="userSex" id="userSex" class="easyui-validatebox pianyi" data-options="required:true"> -->
<%-- 					<c:if test="${userInfoForModify.userSex == '1' }"> --%>
<!-- 						<option value="1" selected="selected">男</option> -->
<%-- 					</c:if> --%>
<%-- 					<c:if test="${userInfoForModify.userSex == '0' }"> --%>
<!-- 						<option value="0" selected="selected">女</option> -->
<%-- 					</c:if> --%>
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				所属机构: -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<select name="userstrucid" id="userstrucid" class="easyui-validatebox pianyi" data-options="required:true"> -->
<%-- 					<c:forEach items="${strucList}" var="struc"> --%>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${struc.strucid == userInfoForModify.userstrucid}"> --%>
<%-- 								<option value="${struc.strucid}" selected="selected">${struc.strucname}</option> --%>
<%-- 							</c:when> --%>
<%-- 						</c:choose> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td>
				<font style="color:red;"> * </font>邮件地址：
			</td>
			<td>
				<input type="text" name="userEmail" id="userEmail" value="${userInfoForModify.userEmail }" maxlength="30"
				class="easyui-validatebox pianyi" data-options="required:true,validType:'email'" />
			</td>
		</tr>
	</table>
	<br/>
</form>
