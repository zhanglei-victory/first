<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	pageContext.setAttribute("randomparamer", Math.random());
%>
<style>
table{border-collapse:collapse; font-size:14px;}
table td,th{ border:1px solid #98bf10;border-collapse:collapse; padding:5px 8px;}
table td{ text-align:left; padding:3px;}
table td.btn{ text-align:center; padding:3px;}
table th.tableth{background-color:#A7C942;color:#FFF;font-size:15px;}
table .alt{ background-color:#EAF2D3;}
</style>
<form name="scene_farm_detailForm" id="scene_farm_detailForm" method="post">
<br>
	<table align="center" >
        <tr>
            <td>养殖场名称:</td>
            <td>${farmForDetail.farmname }
                <input type="hidden" id="farmid" name="farmid" value="${farmForDetail.farmid }"/>
            </td>
        </tr>
        <tr class="alt">
            <td> 养殖场占地面积（m2）: </td>
            <td>${farmForDetail.farmarea }
            </td>
        </tr>
        <tr>
            <td>联系人: </td>
            <td>${farmForDetail.farmlinkman }
            </td>
        </tr>
        <tr class="alt">
            <td> 手机:</td>
            <td>${farmForDetail.farmphone }
            </td>
        </tr>
        <tr>
            <td> 养殖场地址: </td>
            <td>${farmForDetail.farmaddress }
            </td>
        </tr>
        <tr class="alt">
            <td> 养殖场温度（摄氏度）: </td>
            <td>${farmForDetail.temp }
            </td>
        </tr>
        <tr>
            <td> 养殖场湿度（%）: </td>
            <td>${farmForDetail.humidity }
            </td>
        </tr>
        <tr class="alt">
            <td> 养殖场光照强度（勒克斯）: </td>
            <td>${farmForDetail.light }
            </td>
        </tr>
        <tr>
            <td> PH值: </td>
            <td>${farmForDetail.phvalue }
            </td>
        </tr>
        <tr class="alt">
            <td> 养殖场描述: </td>
            <td>${farmForDetail.remark }
            </td>
        </tr>
        <tr>
            <td class="btn" colspan="2">
                <input type="button" id="openlightid" onclick="openlight();" value="开 灯">
                <input type="button" id="openfanid" onclick="openfan();" value="开 风 扇">
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    //页面加载完成后自动操作
	$(function() {
		var lightstatus = "${farmForDetail.lightstatus}";
		var fanstatus = "${farmForDetail.fanstatus}";
		//灯的状态
		if(lightstatus == "1") {
			$("#openlightid").val("关 灯");
		} else if(lightstatus == "0") {
			$("#openlightid").val("开  灯");
		}
		//风扇的状态
		if(fanstatus == "1") {
            $("#openfanid").val("关 风 扇");
        } else if(fanstatus == "0") {
            $("#openfanid").val("开 风 扇");
        }
	});
	function openlight() {
		var $fontcontent = $("#openlightid");
		var content = $fontcontent.val();
		if(content == "开 灯") {
			$fontcontent.val("关 灯");
			//操作服务端
            $.ajax({
                url : '${path}/farm/openAndCloseLight/${farmForDetail.farmid }/' + "1?tmp=${randomparamer}",
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
		} else {
			$fontcontent.val("开 灯");
			//操作服务端
			$.ajax({
                url : '${path}/farm/openAndCloseLight/${farmForDetail.farmid }/' + "0?tmp=${randomparamer}",
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
	function openfan() {
	    var $fontcontent = $("#openfanid");
	    var content = $fontcontent.val();
	    if(content == "开 风 扇") {
	        $fontcontent.val("关 风 扇");
	        //操作服务端
            $.ajax({
                url : '${path}/farm/openAndCloseFan/${farmForDetail.farmid }/' + "1?tmp=${randomparamer}",
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
	    } else {
	        $fontcontent.val("开 风 扇");
	        //操作服务端
            $.ajax({
                url : '${path}/farm/openAndCloseFan/${farmForDetail.farmid }/' + "0?tmp=${randomparamer}",
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
</script>