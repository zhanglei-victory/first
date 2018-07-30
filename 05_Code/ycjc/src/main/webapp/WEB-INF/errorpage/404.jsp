<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	System.out.println("path:" + path);
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ERROR</title>
<link rel="stylesheet" href="${path}/css/waterindex.css" type="text/css" />
</head>

<body class="errorpage">

<div class="mainwrapper">
    
  
    <div class="errortitle">
        <h4 class="animate0 fadeInUp">您请求的界面不存在</h4>
        <span class="animate1 bounceIn">4</span>
        <span class="animate2 bounceIn">0</span>
        <span class="animate3 bounceIn">4</span>
        <div class="errorbtns animate4 fadeInUp">
            <a onClick="history.back()" class="btn btn-primary btn-large">去前一页</a>
            <a  onClick="history.back()"class="btn btn-large">取消</a>
        </div>
    </div>
    
</div><!--mainwrapper-->

</body>
</html>
