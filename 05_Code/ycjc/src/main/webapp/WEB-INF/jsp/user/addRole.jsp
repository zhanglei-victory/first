<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<link rel="stylesheet" href="${path }/css/user/roleCss.css" type="text/css" />
<link rel="stylesheet" href="${path }/css/zTreeStyle/zTreeStyleRole.css" type="text/css"/>
<script type="text/javascript">
	// 方便js文件调用
	var path = "${path}";
</script>

<form name="role_addForm" id="role_addForm" method="post">
<br>
	<table align="center">
		<tr>
			<td><font style="color:red;"> * </font>角色名称:</td>
			<td><input maxlength="10" type="text" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)|( *)/g, '');" id="rolename" name="rolename" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>对应权限:</td>
			<td>
				<ul id="treeDemo" class="ztree" style="height: auto; background:#ffffff; "></ul>
				<input type="hidden" id="authids" name="authids" value=""/>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="${path}/js/user/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${path}/js/user/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	// 树节点被check后的操作
	function zTreeOnCheck(event, treeId, treeNode) {
		//alert(treeNode.id + ", " + treeNode.name + "," + treeNode.checked + "," + treeNode.pId);
		$("#authids").val(treeNode.id);//alert($("#authids").val());
	};
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		callback : {
			onCheck : zTreeOnCheck
		}
	};
// 		var path = "${path}";
// 		var zTreeObj;
// 		var treeNodes;
	
	$(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : path + "/user/auth/getNewTree", //请求的action路径  
			error : function() {//请求失败处理函数  
				alert('请求失败');
			},
			success : function(data) { //请求成功后处理函数。    
				//alert(data);
				treeNodes = data; //把后台封装好的简单Json格式赋给treeNodes  
			}
		});
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, treeNodes);
	});
</script>
