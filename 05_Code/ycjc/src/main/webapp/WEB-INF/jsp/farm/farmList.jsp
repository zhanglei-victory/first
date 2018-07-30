<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:70px;background:#efefef;" title="查询条件">
		<form id="farm_searchForm">
			<table style="height: 100%;">
				<tr>
					<th>养殖场名称</th>
					<td><input type="text" name="search_farm_name" id="search_farm_name"/>
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
		<table id="farm_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
	var dg = $('#farm_datagrid');
	$(function() {
		dg.datagrid({
			title : '养殖场列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'farmid',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/farm/findAllInfoByCond',
			idField : 'farmid',
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
				field : 'farmid',
				title : '编号',
				width : $(this).width() * 0.05,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'farmname',
				title : '养殖场名称',
				width : $(this).width() * 0.1,
				sortable : true
			}, {
                field : 'temp',
                title : '温度（摄氏度）',
                width : $(this).width() * 0.1,
                sortable : true
            }, {
                field : 'humidity',
                title : '湿度（%）',
                width : $(this).width() * 0.05
            },
            {
                title : 'PH值',
                field : 'phvalue',
                width : $(this).width() * 0.05
            },
            {
                title : '光照强度（勒克斯）',
                field : 'light',
                width : $(this).width() * 0.1
            },{
				field : 'farmarea',
				title : '面积（m2）',
				width : $(this).width() * 0.05
			},
			{
				title : '联系人',
				field : 'farmlinkman',
				width : $(this).width() * 0.05
			}, {
				title : '联系电话',
				field : 'farmphone',
				width : $(this).width() * 0.05
			}, {
				title : '地址',
				field : 'farmaddress',
				width : $(this).width() * 0.1
			}, {
                title : '描述',
                field : 'remark',
                width : $(this).width() * 0.2
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
                text : '详情',
                iconCls : 'icon-detail',
                handler : function() {
                    detailFun();
                }
            }, '-', {
                text : '设定阈值',
                iconCls : 'icon-maxminset',
                handler : function() {
                    setMaxminFun();
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
			search_farm_name:$('#search_farm_name').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#search_farm_name').val('');
	}
	function append() {
		//$('#user_addForm').form('clear');
		//parent.$('#user_addDialog').dialog('open');
		//////////////////////////////////////
		var d = $('<div/>').dialog({
			title : '添加养殖场',
			href : "${path}/farm/toAdd",
			width : 400,
			height : 400,
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
							"farmname" : $("#farmname").val()
						},
						url : "${path}/farm/checkFarmName", //请求的action路径  
						error : function() {//请求失败处理函数  
							alert('请求失败');
						},
						success : function(data) { //请求成功后处理函数。    
							//alert(data);
							if(data == "true") {
								$('#farm_addForm').form('submit',{
									url:'${path}/farm/addFarm',
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
								$.messager.alert("提示","此养殖场名已被占用，请使用其它名称！");
								$("#farmname").focus();
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
            var delId = rows[0].farmid;
            $.messager.confirm('确认删除', '您确定要删除这条信息吗?', function(r){
                if (r){
                    $.ajax({
                        async : false,
                        cache : false,
                        type : 'POST',
                        url: "${path}/farm/deleteFarmCheck/" + rows[0].farmid,
                        error : function() {//请求失败处理函数  
                            alert('请求失败');
                        },
                        success : function(data) { //请求成功后处理函数。    
                            //alert(data);
                            if(data == "true") {
                                $.ajax({
                                    url : '${path}/farm/deleteFarm/' + delId,
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
                                $.messager.alert("提示","此养殖场已经在使用，不能被删除！");
                            }
                        }
                    });
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
				title : '修改养殖场',
				href : '${path}/farm/toUpdate/' + rows[0].farmid,
				width : 400,
				height : 400,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						$('#farm_updateForm').form('submit',{
							url:'${path}/farm/updateFarm',
							success:function(r){
								obj=$.parseJSON(r);
								if(obj.success){
									d.dialog('close');
									dg.datagrid('updateRow',{
										index:dg.datagrid('getRowIndex',rows[0].farmid),
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
	function detailFun() {
        var rows = dg.datagrid('getChecked');
        if (rows.length == 1) {
            var d=$('<div/>').dialog({
                title : '查看养殖场',
                href : '${path}/farm/toDetail/' + rows[0].farmid,
                width : 500,
                height : 430,
                modal : true,
                buttons : [ {
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
            $.messager.alert('提示', '请勾选一条要查看的数据');
        }
    }
	function setMaxminFun() {
		var rows = dg.datagrid('getChecked');
        if (rows.length == 1) {
        	var d=$('<div/>').dialog({
        		title : '设定养殖场阈值',
                href : '${path}/farm/toSetMaxmin/' + rows[0].farmid + "/" + "1",
                width : 400,
                height : 370,
                modal : true,
                buttons : [ {
                    text : '保存',
                    handler : function() {
                        $('#scene_farm_updateForm').form('submit',{
                            url:'${path}/farm/sceneUpdateFarm',
                            success:function(r){
                                obj=$.parseJSON(r);
                                if(obj.success){
                                	d.dialog('close');
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
            $.messager.alert('提示', '请勾选一条要设定阈值的数据');
        }
    }
</script>
