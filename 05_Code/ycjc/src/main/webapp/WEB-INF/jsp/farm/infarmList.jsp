<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:100px;background:#efefef;" title="查询条件">
		<form id="infarm_searchForm">
			<table style="height: 100%;">
				<tr>
					<th>生猪出生日期：</th>
					<td>
					<input type="text" style="width: 150px;" id="pigbirthday_searchbegin" value="${pigbirthday_searchbegin }" readonly
                    onfocus="var pigbirthday_searchend=$dp.$('pigbirthday_searchend');WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,onpicked:function(){pigbirthday_searchend.focus();},maxDate:'#F{$dp.$D(\'pigbirthday_searchend\')}'})" class="Wdate" />
                    ~ <input type="text" readonly
                                    style="width: 150px;" id="pigbirthday_searchend" value="${pigbirthday_searchend }"
                                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'pigbirthday_searchbegin\')}'})"
                                    class="Wdate"/>
					</td>
				    <th>生猪入栏日期：</th>
                    <td>
                    <input type="text" style="width: 150px;" id="infarmdate_searchbegin" value="${infarmdate_searchbegin }" readonly
                    onfocus="var infarmdate_searchend=$dp.$('infarmdate_searchend');WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,onpicked:function(){infarmdate_searchend.focus();},maxDate:'#F{$dp.$D(\'infarmdate_searchend\')}'})" class="Wdate" />
                    ~ <input type="text" readonly
                                    style="width: 150px;" id="infarmdate_searchend" value="${infarmdate_searchend }"
                                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'infarmdate_searchbegin\')}'})"
                                    class="Wdate"/>
                    </td>
                 </tr>
                 <tr>
                    <th>所属养殖场：</th>
                    <td colspan="3">
	                    <select name="farmid_search" id="farmid_search">
	                        <option value="">全部养殖场</option>
	                        <c:forEach items="${farmlist}" var="farm">
	                            <option value="${farm.farmid}">${farm.farmname}</option>
	                        </c:forEach>
	                    </select>
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
		<table id="infarm_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
	var dg = $('#infarm_datagrid');
	$(function() {
		dg.datagrid({
			title : '生猪入栏列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'infarmdate',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/infarm/findAllInfoByCond',
			idField : 'rfidId',
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
				field : 'noid',
				title : '编号',
				width : $(this).width() * 0.05,
				checkbox : true
			} ] ],
			columns : [ [ {
                field : 'rfidId',
                title : 'RFID编号',
                width : $(this).width() * 0.18,
                sortable : true
            },{
                field : 'pigno',
                title : '猪的编号',
                width : $(this).width() * 0.1,
                sortable : true
            },{
				field : 'pigtype',
				title : '类别',
				width : $(this).width() * 0.08,
				sortable : true
			}, {
				field : 'pigfrom',
				title : '来源',
				width : $(this).width() * 0.08
			},
			{
				title : '入栏重量（kg）',
				field : 'infarmweight',
				width : $(this).width() * 0.07
			}, {
				title : '性别',
				field : 'pigsex',
				width : $(this).width() * 0.05
			}, {
				title : '体色',
				field : 'pigcolor',
				width : $(this).width() * 0.05
			}, {
                title : '出生日期',
                field : 'pigbirthday',
                width : $(this).width() * 0.07
            }, {
                title : '入栏日期',
                field : 'infarmdate',
                width : $(this).width() * 0.13
            }, {
                title : '入栏负责人',
                field : 'infarmlinkman',
                width : $(this).width() * 0.08
            }, {
                field : 'farmid',
                title : '所属养殖场',
                width : $(this).width() * 0.15
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
			pigbirthday_searchbegin:$('#pigbirthday_searchbegin').val(),
		    pigbirthday_searchend:$('#pigbirthday_searchend').val(),
		    infarmdate_searchbegin:$('#infarmdate_searchbegin').val(),
		    infarmdate_searchend:$('#infarmdate_searchend').val(),
		    farmid_search:$('#farmid_search').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#pigbirthday_searchbegin').val(''),
		$('#pigbirthday_searchend').val(''),
		$('#infarmdate_searchbegin').val(''),
		$('#infarmdate_searchend').val(''),
		$('#farmid_search').val('')
	}
	function append() {
		//$('#user_addForm').form('clear');
		//parent.$('#user_addDialog').dialog('open');
		//////////////////////////////////////
		var d = $('<div/>').dialog({
			title : '添加入栏信息',
			href : "${path}/infarm/toAdd",
			width : 600,
			height : 490,
			modal : true,
			options:[],
			buttons : [ {
				text : '保存',
				//iconCls : 'icon-add',
				handler : function() {
					$('#infarm_addForm').form('submit',{
						url:'${path}/infarm/addInFarm',
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
	}
    function remove() {
        var rows = dg.datagrid('getChecked');
        if (rows.length == 1) {
            var delId = rows[0].rfidId;
            $.messager.confirm('确认删除', '您确定要删除这条信息吗?', function(r){
                if (r){
                    $.ajax({
                        async : false,
                        cache : false,
                        type : 'POST',
                        data : {
                            "rfidId" :delId
                        },
                        url: "${path}/infarm/deleteCheck",
                        error : function() {//请求失败处理函数  
                            alert('请求失败');
                        },
                        success : function(data) { //请求成功后处理函数。    
                            //alert(data);
                            if(data == "true") {
                                $.ajax({
                                    url : '${path}/infarm/deleteinFarm/' + delId,
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
                                $.messager.alert("提示","此生猪已经在养殖，不能被删除！");
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
				title : '修改入栏信息',
				href : '${path}/infarm/toUpdate/' + rows[0].rfidId,
				width : 500,
				height : 490,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						$('#infarm_updateForm').form('submit',{
							url:'${path}/infarm/updateinFarm',
							success:function(r){
								obj=$.parseJSON(r);
								if(obj.success){
									d.dialog('close');
									dg.datagrid('updateRow',{
										index:dg.datagrid('getRowIndex',rows[0].rfidId),
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
</script>
