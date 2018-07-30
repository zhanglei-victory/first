<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div class="easyui-layout" data-options="fit:true" style="width:100%;height:100%;">
	<div data-options="region:'north',split:true" style="height:80px;background:#efefef;" title="查询条件">
		<form id="infarm_searchForm">
			<table style="height: 100%;">
				<tr>
					<th>养殖记录日期：</th>
					<td>
					<input type="text" style="width: 150px;" id="recorddate_searchbegin" value="${recorddate_searchbegin }" readonly
                    onfocus="var recorddate_searchend=$dp.$('recorddate_searchend');WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,onpicked:function(){recorddate_searchend.focus();},maxDate:'#F{$dp.$D(\'recorddate_searchend\')}'})" class="Wdate" />
                    ~ <input type="text" readonly
                                    style="width: 150px;" id="recorddate_searchend" value="${recorddate_searchend }"
                                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'recorddate_searchbegin\')}'})"
                                    class="Wdate"/>
                    <th>所属养殖场：</th>
                    <td>
	                    <select name="farmid" id="farmid" onchange="changeFarmSearch(this.value);">
	                        <option value="">全部养殖场</option>
	                        <c:forEach items="${farmlist}" var="farm">
	                            <option value="${farm.farmid}">${farm.farmname}</option>
	                        </c:forEach>
	                    </select>
	                </td>
	                <th>个体猪：</th>
	                <td>
	                    <select name="rfidId" id="rfidId">
                            <option value="">全部猪</option>
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
		<table id="breed_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
	var dg = $('#breed_datagrid');
	$(function() {
		dg.datagrid({
			title : '养殖记录列表',
			border : false,
			//fitColum : true,
			fitColumns: true,
			fit : true,
			nowarp : true,
			sortName : 'breeddate',
			sortOrder : 'desc',
			pageSize : 20,
			checkOnSelect : true,
			selectOnCheck : true,
			pageList : [ 20, 30, 40 ],
			//iconCls : 'icon-save',
			url : '${path}/breed/findAllInfoByCond',
			idField : 'id',
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
				field : 'id',
				title : '编号',
				width : $(this).width() * 0.05,
				checkbox : true,
				hidden : true
			} ] ],
			columns : [ [  {
                field : 'pigno',
                title : '生猪编号',
                width : $(this).width() * 0.1
            }, {
				field : 'content',
				title : '内容',
				width : $(this).width() * 0.2
			}, {
                title : '记录时间',
                field : 'recorddate',
                width : $(this).width() * 0.1
            }, {
                field : 'farmid',
                title : '所属养殖场',
                width : $(this).width() * 0.15,
                sortable : true
            }] ],
			toolbar : [ {
				text : '防疫记录批次添加',
				iconCls : 'icon-add',
				handler : function() {
					vaccineBatchappend();
				}
			}, '-', {
                text : '其他记录（消毒等）批次添加',
                iconCls : 'icon-add',
                handler : function() {
                	otherBatchappend();
                }
            }, '-', {
				text : '个体记录（病情等）添加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			} ]
		});
	});
	
	function _search() {
		dg.datagrid('load', {
			// 查询条件
			recorddate_searchbegin:$('#recorddate_searchbegin').val(),
			recorddate_searchend:$('#recorddate_searchend').val(),
		    farmid:$('#farmid').val(),
		    rfidId:$('#rfidId').val()
		});
	}
	function cleanSearch() {
		dg.datagrid('load', {});
		$('#recorddate_searchbegin').val(''),
		$('#recorddate_searchend').val(''),
		$('#farmid').val(''),
		$('#rfidId').val('')
	}
	function vaccineBatchappend() {
		//$('#user_addForm').form('clear');
		//parent.$('#user_addDialog').dialog('open');
		//////////////////////////////////////
		var d = $('<div/>').dialog({
			title : '批量添加养殖防疫信息',
			href : "${path}/breed/toBatchVaccineAdd",
			width : 400,
			height : 200,
			modal : true,
			options:[],
			buttons : [ {
				text : '保存',
				//iconCls : 'icon-add',
				handler : function() {
					$('#breedVaccineBatch_addForm').form('submit',{
						url:'${path}/breed/batchVaccineAdd',
						success:function(r){
							obj=$.parseJSON(r);
							if(obj.success){
								d.dialog('close');
								//dg.datagrid('reload');
								//alert(obj.objList[0].rfidId + "      "+obj.objList[1].rfidId);
								for(var i = 0;i < obj.objList.length;i++) {
									dg.datagrid('insertRow',{
	                                    index:0,
	                                    row:obj.objList[i]
	                                });
								}
								
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
	function otherBatchappend() {
        var d = $('<div/>').dialog({
            title : '批量添加其他信息',
            href : "${path}/breed/toBatchOtherAdd",
            width : 400,
            height : 200,
            modal : true,
            options:[],
            buttons : [ {
                text : '保存',
                //iconCls : 'icon-add',
                handler : function() {
                    $('#breedOtherBatch_addForm').form('submit',{
                        url:'${path}/breed/batchOtherAdd',
                        success:function(r){
                            obj=$.parseJSON(r);
                            if(obj.success){
                                d.dialog('close');
                                //dg.datagrid('reload');
                                //alert(obj.objList[0].rfidId + "      "+obj.objList[1].rfidId);
                                for(var i = 0;i < obj.objList.length;i++) {
                                    dg.datagrid('insertRow',{
                                        index:0,
                                        row:obj.objList[i]
                                    });
                                }
                                
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
	function append() {
        var d = $('<div/>').dialog({
            title : '个体记录添加信息',
            href : "${path}/breed/toIllnessAdd",
            width : 400,
            height : 230,
            modal : true,
            options:[],
            buttons : [ {
                text : '保存',
                //iconCls : 'icon-add',
                handler : function() {
                    $('#breedIllness_addForm').form('submit',{
                        url:'${path}/breed/illnessAdd',
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
	function changeFarmSearch(obj) {
		$.ajax({
            async : false,
            cache : false,
            type : 'POST',
            data : {
                "farmid" : obj
            },
            url : "${path}/breed/dynamicAddOption", //请求的action路径  
            error : function() {//请求失败处理函数  
                alert('请求失败');
            },
            success : function(data) { //请求成功后处理函数。 
            	var $rfidId = $("#rfidId");
                //增加option内容
                //alert(data);
               	//删除动态增加的option
               	$rfidId.children(".dynamic").remove();
                var pigs = JSON.parse(data);

	            for(var i = 0;i < pigs.length;i++) {
	            	var pigrfid = pigs[i].rfidId;
	            	var pigno = pigs[i].pigno;
	            	
	            	var $option = $("<option></option>");
	            	$option.attr("value",pigrfid);
	            	$option.addClass("dynamic");
	            	$option.text(pigno);
	            	$option.appendTo($rfidId);
	            }
            }
        });
	}
</script>
