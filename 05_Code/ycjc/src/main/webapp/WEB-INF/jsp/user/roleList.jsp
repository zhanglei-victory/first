<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:70px;background:#efefef;" title="查询条件">
		<form id="role_searchForm">
			<table style="height: 100%;">
				<tr>
					<th>角色名称</th>
					<td><input type="text" name="search_role_name" id="search_role_name"/>
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
	<div id="centerdiv" data-options="region:'center',split:true" >
		<table id="role_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
//先期准备(树)
var path = "${path}";
var zTreeObj;
var treeNodes;
//	$(window).resize(function(){
//	$('#role_datagrid').datagrid('resize');
//});
	var dg = $('#role_datagrid');
	$(function() {
		dg.datagrid({
			title : '角色列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'rolename',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/user/role/findAllRoleInfoByCond',
			idField : 'roleid',
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
				field : 'roleid',
				title : '编号',
				width : $(this).width() * 0.05,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'rolename',
				title : '角色名称',
				width : $(this).width() * 0.95,
				sortable : true
			}] ],
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '修改权限',
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
			search_role_name:$('#search_role_name').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#search_role_name').val('');
	}
	function append() {
		//$('#role_addForm').form('clear');
		//parent.$('#role_addDialog').dialog('open');
		//////////////////////////////////////
		var d = $('<div/>').dialog({
			title : '添加角色',
			href : "${path}/user/role/toAdd",
			width : 400,
			height : 500,
			modal : true,
			options:[],
			buttons : [ {
				text : '保存',
				handler : function() {
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						data : {
							"rolename" : $("#rolename").val()
						},
						url: path + "/user/role/checkRoleName",
						contentType: "application/x-www-form-urlencoded; charset=utf-8",//防止中文出现乱码
						error : function() {//请求失败处理函数  
							alert('请求失败');
						},
						success : function(data) { //请求成功后处理函数。    
							//alert(data);
							if(data == "true") {
								$('#role_addForm').form('submit',{
									url:'${path}/user/role/addRole',
									onSubmit: function() {
										var inputrolename = $('#rolename').val();//alert(inputrolename);
										if(inputrolename == '' || inputrolename == 'undefined') {
											//$.messager.alert('提示', '请输入角色名称！');
											$('#rolename').focus();
											return false;
										}
										//树的处理
										var nodes = zTreeObj.getCheckedNodes();

										var s = '';//选中节点ids
										for (var i = 0;i < nodes.length;i++) {
											if (s != '') {
												s = s + ',';
											}
											s = s + nodes[i].id;  
										}//alert(s);
										$("#authids").val(s);
										if(s == '') {
											$.messager.alert('提示', '您必须要选择一个权限！');
											return false;
										} else {
											return true;
										}
									},
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
								$.messager.alert("提示","此角色名已被占用，请使用其它名称！");
								$("#rolename").focus();
							}
						}
					});
				}
			},{
				text:'关闭',
				//iconCls : 'icon-no',
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
		//$('#role_addDialog').dialog('open');
	}
	function remove() {
		var rows = dg.datagrid('getChecked');
		//var ids = [];
		if (rows.length == 1) {
			var delId = rows[0].roleid;
			$.messager.confirm('确认删除', '您确定要删除这条信息吗?', function(r){
				if (r){
					//根节点不让修改
					if(rows[0].roleid == '1') {
						$.messager.alert('提示', '系统管理员，不能删除！');
					} else {
						$.ajax({
							async : false,
							cache : false,
							type : 'POST',
							data : {
								"roleid" : delId
							},
							url : "${path}/user/role/deleteRoleCheck", //请求的action路径  
							error : function() {//请求失败处理函数  
								alert('请求失败');
							},
							success : function(data) { //请求成功后处理函数。    
								//alert(data);
								if(data == "true") {
									$.ajax({
										url : '${path}/user/role/deleteRole/' + delId,
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
								} else if(data == "false") {
									$.messager.alert("提示","此角色已经被用户使用，不能删除！");
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
				title : '修改权限',
				href : '${path}/user/role/toUpdate/' + rows[0].roleid,
				width : 400,
				height : 500,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						//根节点不让修改
						if(rows[0].roleid == '1') {
							$.messager.alert('提示', '系统管理员角色不能被修改！');
						} else {
							$('#role_updateForm').form('submit',{
								url:'${path}/user/role/updateRole',
								onSubmit: function() {
									var inputrolename = $('#rolename').val();//alert(inputrolename);
									if(inputrolename == '' || inputrolename == 'undefined') {
										$.messager.alert('提示', '请输入角色名称！');
										$('#rolename').focus();
										return false;
									}
									//树的处理
									var nodes = zTreeObj.getCheckedNodes();

									var s = '';//选中节点ids
									for (var i = 0;i < nodes.length;i++) {
										if (s != '') {
											s = s + ',';
										}
										s = s + nodes[i].id;  
									}//alert(s);
									$("#authids").val(s);
									if(s == '') {
										$.messager.alert('提示', '您必须要选择一个权限！');
										return false;
									} else {
										return true;
									}
								},
								success:function(r){
									obj=$.parseJSON(r);
									if(obj.success){
										d.dialog('close');
										//dg.datagrid('reload');
										dg.datagrid('updateRow',{
											index:dg.datagrid('getRowIndex',rows[0].roleid),
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
						}
					}
				},{
					text:'关闭',
					handler:function(){
						d.dialog('close');
					}
				} ],
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
</script>
