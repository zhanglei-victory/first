<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:70px;background:#efefef;" title="查询条件">
		<form id="outfarm_searchForm">
			<table style="height: 100%;">
				<tr>
				    <th>生猪出栏日期：</th>
                    <td>
                    <input type="text" style="width: 150px;" id="outfarmdate_searchbegin" value="${outfarmdate_searchbegin }" readonly
                    onfocus="var outfarmdate_searchend=$dp.$('outfarmdate_searchend');WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,onpicked:function(){outfarmdate_searchend.focus();},maxDate:'#F{$dp.$D(\'outfarmdate_searchend\')}'})" class="Wdate" />
                    ~ <input type="text" readonly
                                    style="width: 150px;" id="outfarmdate_searchend" value="${outfarmdate_searchend }"
                                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'outfarmdate_searchbegin\')}'})"
                                    class="Wdate"/>
                    </td>
                    <th>所属养殖场：</th>
                    <td>
                        <select name="farmid_search" id="farmid_search">
                            <option value="">全部养殖场</option>
                            <c:forEach items="${farmlist}" var="farm">
                                <option value="${farm.farmid}">${farm.farmname}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>&nbsp;</td>
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
		<table id="outfarm_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
    var path = "${path}";
	var dg = $('#outfarm_datagrid');
	$(function() {
		dg.datagrid({
			title : '生猪出栏列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'outfarmdate',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/outfarm/findAllInfoByCond',
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
                width : $(this).width() * 0.15,
                sortable : true
            },{
				field : 'pigno',
				title : '猪的编号',
				width : $(this).width() * 0.07,
				sortable : true
			}, {
                field : 'pigtype',
                title : '类别',
                width : $(this).width() * 0.05,
                sortable : true
            }, {
				field : 'pigfrom',
				title : '来源',
				width : $(this).width() * 0.07
			}, {
				title : '出栏重量（kg）',
				field : 'outfarmweight',
				width : $(this).width() * 0.06
			}, {
                title : '出栏负责人',
                field : 'outfarmlinkman',
                width : $(this).width() * 0.05
            }, {
                title : '性别',
                field : 'pigsex',
                width : $(this).width() * 0.05
            }, {
                title : '体色',
                field : 'pigcolor',
                width : $(this).width() * 0.05
            }, {
                field : 'farmid',
                title : '所属养殖场',
                width : $(this).width() * 0.1
            }, {
                field : 'healthstatus',
                title : '健康状况',
                width : $(this).width() * 0.05
            }, {
                title : '出栏日期',
                field : 'outfarmdate',
                width : $(this).width() * 0.08
            }] ],
			toolbar : [ {
				text : '出栏',
				iconCls : 'icon-add',
				handler : function() {
					appendOutfarm();
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
		    outfarmdate_searchbegin:$('#outfarmdate_searchbegin').val(),
		    outfarmdate_searchend:$('#outfarmdate_searchend').val(),
		    farmid_search:$('#farmid_search').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#outfarmdate_searchbegin').val(''),
		$('#outfarmdate_searchend').val(''),
		$('#farmid_search').val('')
	}
	function appendOutfarm() {
		var d=$('<div/>').dialog({
            title : '添加出栏信息',
            href : '${path}/outfarm/toAdd',
            width : 500,
            height : 610,
            modal : true,
            buttons : [ {
                text : '保存',
                handler : function() {
                    $('#outfarm_addForm').form('submit',{
                        url:'${path}/outfarm/addOutFarm',
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
            }],
            onClose : function() {
                $(this).dialog('destroy');
            },
            onLoad : function() {
            }
        });
    }
	
	function editFun() {
		var rows = dg.datagrid('getChecked');
		if (rows.length == 1) {
			$.ajax({
                async : false,
                cache : false,
                type : 'POST',
                data : {
                    "rfidId" : rows[0].rfidId
                },
                url: path + "/outfarm/checkOutfarm",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",//防止中文出现乱码
                error : function() {//请求失败处理函数  
                    alert('请求失败');
                },
                success : function(data) { //请求成功后处理函数。    
                    //alert(data);
                    if(data == "false") {//出栏后的处理
                    	var d=$('<div/>').dialog({
                            title : '修改出栏信息',
                            href : '${path}/outfarm/toUpdate/' + rows[0].rfidId,
                            width : 500,
                            height : 610,
                            modal : true,
                            buttons : [ {
                                text : '保存',
                                handler : function() {
                                    $('#outfarm_updateForm').form('submit',{
                                        url:'${path}/outfarm/updateOutFarm',
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
                    } else if(data == "true") {//未出栏猪
                        $.messager.alert("提示","此猪还没有出栏，不能修改其出栏信息！");
                        $("#rolename").focus();
                    }
                }
            });
			
		} else {
			$.messager.alert('提示', '请勾选一头已经出栏要修改的生猪');
		}
	}
	function remove() {
        var rows = dg.datagrid('getChecked');
        //var ids = [];
        if (rows.length == 1) {
            var delId = rows[0].rfidId;
            $.messager.confirm('确认删除', '您确定要删除这条信息吗?', function(r){
            	$.ajax({
                    url : '${path}/outfarm/delete/' + delId,
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
            });
        } else {
            $.messager.alert('提示', '请勾选一条要删除的数据！');
        }
    }
</script>
