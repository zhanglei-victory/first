<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:100px;background:#efefef;" title="查询条件">
		<form id="user_searchForm">
			<table style="height: 100%;">
				<tr>
					<th>用户名</th>
					<td><input type="text" name="search_user_name" id="search_user_name"/>
					</td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" name="search_user_realname" id="search_user_realname"/>
					</td>
					<td>
                    <a href="javascript:void(0);"
                        class="easyui-linkbutton" onclick="_search();">查询</a> <a
                        href="javascript:void(0);" class="easyui-linkbutton"
                        onclick="cleanSearch();">重置</a>
                    </td>
				</tr>
			</table>
		</form>
	</div><!-- end north -->
	<div id="centerdiv" data-options="region:'center',split:true">
		<table id="user_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
	var dg = $('#user_datagrid');
	$(function() {
		dg.datagrid({
			title : '用户列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'userid',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/user/userInfo/findAllUserInfoByCond',
			idField : 'userid',
			pagePosition : 'bottom',
			pagination : true,
			singleSelect:true,//只允许选择一行
			loadMsg : '正在加载数据当中....',
			onLoadSuccess : 
				function (data) {
				if (data.total==0) {
					var body = $(this).data().datagrid.dc.body2;
					body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">没有相关数据</td></tr>');
				}
			},
			frozenColumns : [ [ {
				field : 'userid',
				title : '编号',
				width : $(this).width() * 0.05,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : $(this).width() * 0.15,
				sortable : true
			}, {
				field : 'userroleid',
				title : '角色',
				width : $(this).width() * 0.1
			}, {
				field : 'userposition',
				title : '职务',
				width : $(this).width() * 0.15
			},
			{
				title : '联系方式',
				field : 'userphone',
				width : $(this).width() * 0.15/*,
				hidden : true*/
			}, {
				title : '真实姓名',
				field : 'userRealName',
				width : $(this).width() * 0.1
			}, {
				title : '锁定状态',
				field : 'userstatus',
				width : $(this).width() * 0.1
			}] ],
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editFun();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '密码重置',
				iconCls : 'icon-resetpwd',
				handler : function() {
					resetpwd();
				}
			},  '-', {
				text : '锁定用户',
				iconCls : 'icon-lockuser',
				handler : function() {
					lockUser();
				}
			},  '-', {
				text : '解锁用户',
				iconCls : 'icon-unlockuser',
				handler : function() {
					unlockUser();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-redo',
				handler : function() {
					dg.datagrid('unselectAll');
				}
			} ]
		});
	});
	
	function _search() {
		dg.datagrid('load', {
			// 查询条件
			search_user_name:$('#search_user_name').val(),
			search_user_realname:$('#search_user_realname').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#search_user_name').val('');
		$('#search_user_realname').val('');
	}
	function append() {
		//$('#user_addForm').form('clear');
		//parent.$('#user_addDialog').dialog('open');
		//////////////////////////////////////
		var d = $('<div/>').dialog({
			title : '添加用户',
			href : "${path}/user/userInfo/toAdd",
			width : 400,
			height : 510,
			modal : true,
			options:[],
			buttons : [ {
				text : '保存',
				//iconCls : 'icon-add',
				handler : function() {
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						data : {
							"username" : $("#username").val()
						},
						url : "${path}/user/userInfo/checkLoginUserName", //请求的action路径  
						error : function() {//请求失败处理函数  
							alert('请求失败');
						},
						success : function(data) { //请求成功后处理函数。    
							//alert(data);
							if(data == "true") {
								$('#user_addForm').form('submit',{
									url:'${path}/user/userInfo/addUserInfo',
									success:function(r){
										obj=$.parseJSON(r);
										if(obj.success){
											d.dialog('close');
											//dg.datagrid('reload');
											dg.datagrid('insertRow',{
												index:0,
												row:obj.obj
											});
											 $.messager.show({
												 title:'提示',
												 msg:obj.msg
											 });
										} else {
											$.messager.alert('提示', obj.msg);
										}
									}
								});
							} else if(data == "false") {
								$.messager.alert("提示","此用户名已被占用，请使用其它用户名！");
								$("#username").focus();
							}
						}
					});
					
				}
			},{
				text:'关闭',
				handler:function(){
					d.dialog('close');
				}
			} ],
			onClose : function() {//alert("00");
				$(this).dialog('destroy');
				
			},
			onLoad : function() {//alert('111');
			}
		});
		//parent.appendChild(d);
		///////////////////////////////////////
		//$('#user_addDialog').dialog('open');
	}
	function remove() {
		var rows = dg.datagrid('getChecked');
		//var ids = [];
		if (rows.length == 1) {
			var seluserid = rows[0].userid;
			$.messager.confirm('确认删除', '您确定要删除这条信息吗?', function(r){
				if (r){
					//根节点不让修改
					if(rows[0].username == 'admin') {
						$.messager.alert('提示', '本用户为超级管理员，不能删除！');
					} else {
						$.ajax({
							url : '${path}/user/userInfo/deleteUserInfo/' + seluserid,
							success : function(r) {
								obj=$.parseJSON(r);
								if(obj.success){
									dg.datagrid('load');
									dg.datagrid('unselectAll');
									$.messager.show({
										title:'提示',
										msg:obj.msg
									});
								} else{
									$.messager.alert('提示', obj.msg);
								}
							}
						});
					}
				}
			});
		} else {
			$.messager.alert('提示', '请勾选一条要删除的数据！');
		}
	}
	function editFun() {
		var rows = dg.datagrid('getChecked');
		if (rows.length == 1) {
			var d=$('<div/>').dialog({
				title : '修改用户',
				href : '${path}/user/userInfo/toUpdate/' + rows[0].userid,
				width : 400,
				height : 440,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						//根节点不让修改
						if(rows[0].username == 'admin') {
							$.messager.alert('提示', '本用户为超级管理员，您不能进行修改！');
						} else {
							$('#user_updateForm').form('submit',{
								url:'${path}/user/userInfo/updateUserInfo',
								success:function(r){
									obj=$.parseJSON(r);
									if(obj.success){
										d.dialog('close');
										dg.datagrid('updateRow',{
											index:dg.datagrid('getRowIndex',rows[0].userid),
											row:obj.obj
										}); 
										$.messager.show({
											title:'提示',
											msg:obj.msg
										});
									} else{
										$.messager.alert('提示', obj.msg);
									}
								}
							});
						}
					}
				},{
					text:'关闭',
					handler:function(){
						d.dialog('close');
					}
				}],
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
				}
			});
		} else {
			$.messager.alert('提示', '请勾选一条要修改的数据');
		}
	}
	function resetpwd() {
		var rows = dg.datagrid('getChecked');
		if (rows.length == 1) {
			$.messager.confirm('确认重置密码', '您确定要重置此用户的密码吗?', function(r){
				if (r){
					//根节点不让修改
					if(rows[0].username == 'admin') {
						$.messager.alert('提示', '本用户为超级管理员，不能重置密码！');
					} else {
						$.ajax({
							url : '${path}/user/userInfo/resetUserPwd/' + rows[0].userid + "?number=" + new Date().getTime(),
							success : function(r) {
								obj=$.parseJSON(r);
								if(obj.success){
									//dg.datagrid('load');
									$.messager.show({
										title:'提示',
										msg:obj.msg
									});
								} else{
									$.messager.alert('提示', obj.msg);
								}
							}
						});
					}
				}
			});
		} else {
			$.messager.alert('提示', '请勾选一条要重置密码的数据！');
		}
	}
	function lockUser() {
		var rows = dg.datagrid('getChecked');
		if (rows.length == 1) {
			var seluserid = rows[0].userid;
			var status = rows[0].userstatus;
			if(status == "未锁定") {
				$.messager.confirm('确认锁定用户', '您确定要锁定此用户吗?', function(r){
					if (r){
						//根节点不让修改
						if(rows[0].username == 'admin') {
							$.messager.alert('提示', '本用户为超级管理员，不能锁定！');
						} else {
							$.ajax({
								url : '${path}/user/userInfo/lockUser/' + seluserid + "?number=" + new Date().getTime(),
								success : function(r) {//alert(r);
									obj=$.parseJSON(r);
									if(obj.success){
										dg.datagrid('updateRow',{
											index:dg.datagrid('getRowIndex',seluserid),
											row:obj.obj
										}); 
										$.messager.show({
											title:'提示',
											msg:obj.msg
										});
									} else{
										$.messager.alert('提示', obj.msg);
									}
								}
							});
						}
					}
				});
			} else {
				$.messager.alert('提示', '此用户已被锁定！');
			}
		} else {
			$.messager.alert('提示', '请勾选一条要锁定的数据！');
		}
	}
	function unlockUser() {
		var rows = dg.datagrid('getChecked');
		if (rows.length == 1) {
			var seluserid = rows[0].userid;
			var status = rows[0].userstatus;
			if(status == "锁定") {
				$.messager.confirm('确认删除', '您确定要为此用户解锁吗?', function(r){
					if (r){
						//根节点不让修改
						if(rows[0].username == 'admin') {
							$.messager.alert('提示', '本用户为超级管理员，不能解锁！');
						} else {
							$.ajax({
								url : '${path}/user/userInfo/unlockUser/' + seluserid + "?number=" + new Date().getTime(),//时间戳（防止缓存）
								success : function(r) {
									obj=$.parseJSON(r);
									if(obj.success){
										dg.datagrid('updateRow',{
											index:dg.datagrid('getRowIndex',seluserid),
											row:obj.obj
										}); 
										$.messager.show({
											title:'提示',
											msg:obj.msg
										});
									} else{
										$.messager.alert('提示', obj.msg);
									}
								}
							});
						}
					}
				});
			} else {
				$.messager.alert('提示', '此用户未被锁定！');
			}
		} else {
			$.messager.alert('提示', '请勾选一条要解锁的用户！');
		}
	}
</script>
