<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>食品溯源管理系统--河南理工</title>
<link rel="stylesheet" href="${path }/css/foodindex.css" type="text/css" />
<style type="text/css">
	.logintext img{margin-left:160px;margin-top:5px;}
	.loginfooter {
		  font-size: 11px; color: rgba(45, 39, 39,0.5); position: absolute; position: fixed; bottom:2px; left: 0;
		  width: 100%; text-align: center; font-family: arial, sans-serif !important; padding: 5px 0;font-family:"仿宋体" }
	#validate_a:link { text-decoration: none;background-color: #fff;}
	#validate_a:active { text-decoration: none;background-color: #fff;}
	#validate_a:visited { text-decoration: none;background-color: #fff;}
	#validate_a:hover { text-decoration: none;background-color: #fff;}
</style>
<script type="text/javascript">
	function sumitform() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("userPwd").value;
		var validateCode = document.getElementById("validateCode").value;
		if(username == "") {
			alert("请输入用户名！");
			document.getElementById("username").focus();
			return;
		} else if(password == "") {
			alert("请输入密码！");
			document.getElementById("userPwd").focus();
			return;
		} else if(validateCode == "") {
			alert("请输入验证码！");
			document.getElementById("validateCode").focus();
			return;
		} else {
			document.getElementById("loginForm").submit();
		}
	}
	function setFocus() {
		document.forms[0].elements[0].focus();
	}

	function changeValidateCode() {
		var img = document.getElementById("valiCodeImg");
		img.src = "${path}/validateCodeImage?random=" + Math.random();
	}
	
	function yzmOnfocus() {
		document.onkeyup = function(e) { //onkeyup是javascript的一个事件、当按下某个键弹起时触发  
			if (e == null) { // ie  
				_key = event.keyCode;
			} else { // firefox              //获取你按下键的keyCode  
				_key = e.which; //每个键的keyCode是不一样的
			}

			if (_key == 13) { //判断keyCode是否是13，也就是回车键(回车的keyCode是13)  
				sumitform();
			}
		}
	}
</script>
</head>

<body onload="setFocus();">
	<div class="logintext">
		<img src="${path }/images/login/logo2.png">
	</div>
	<table align="center" width="1000" height="580" border="0"
		cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3"><img align="top" src="${path }/images/login/photo01.png"
				width="1000" height="204" alt="" border="0" /></td>
		</tr>
		<tr>
			<td><img align="top" src="${path }/images/login/photo02.png" width="540"
				height="185" alt=""  border="0"/></td>
			<td background="${path }/images/login/photo03.png"
				width="315" height="185" alt="">
				<form name="loginForm" id="loginForm"
					action="${path}/userLogin" method="post">
					<br><br>
					<table>
						<tr height="30">
							<td width="100" align="right">用户名：</td>
							<td width="215" align="left"><input type="text" name="username" id="username" value=""
								style="width: 184px;" /></td>
						</tr>
						<tr height="30">
							<td width="100" align="right">密&nbsp;&nbsp;码：</td>
							<td width="215" align="left"><input type="password" name="userPwd" id="userPwd"
								value="" style="width: 184px;" /></td>
						</tr>
						<tr height="30">
							<td width="100" align="right">验证码：</td>
							<td width="215" align="left" colspan="2"><input type="text" id="validateCode" name="validateCode" onfocus="yzmOnfocus();"
								value="" size="4" /> 
								<img border="0" align="top" id="valiCodeImg" src="${path}/validateCodeImage" alt="验证码"/>
								<a id= "validate_a" href="javascript:changeValidateCode();">看不清?</a></td>
						</tr>
						<tr height="30">
							<td colspan="2" align="center"><input type="button" onclick="sumitform();" style="width:100px;"
								value="登&nbsp;&nbsp;录" /></td>
						</tr>
					</table>
				</form>

			</td>
			<td align="right"><img align="top" src="${path }/images/login/photo04.png" width="145"
				height="185" alt=""  border="0"/></td>
		</tr>
		<tr>
			<td colspan="3"><img align="top" src="${path }/images/login/photo05.png"
				width="1000" height="192" alt="" border="0" /></td>
		</tr>
	</table>
	<div class="loginfooter">
		<p>食品溯源管理系统&copy; 2015. All Rights Reserved.</p>
	</div>
	<script type="text/javascript">
		//登录不成功信息
		var msg = "${msg}";
		if (msg != "") {
			alert(msg);
			document.getElementById("username").value = "";
			document.getElementById("userPwd").value = "";
			document.getElementById("validateCode").value = "";
		}
	</script>
</body>
</html>
