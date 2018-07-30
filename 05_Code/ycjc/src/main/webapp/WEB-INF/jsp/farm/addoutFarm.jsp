<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<script type="text/javascript">
    function getCode(){
        $.ajax({
            type: "post",
            dataType:'json', //接受数据格式 
            url: "${path}/code/findcode",
            success: function(data){
                 var code = data.code;
                 $('#rfidId').val(code);//alert(code);
                 
                 $.ajax({
                     async : false,
                     cache : false,
                     type : 'POST',
                     data : {
                         "rfidid" : code
                     },
                     url : "${path}/rfidlib/rfidPigCheck", //请求的action路径  
                     error : function() {//请求失败处理函数  
                         alert('请求失败');
                     },
                     success : function(data) { //请求成功后处理函数。 
                         if(data == "false") {
                        	 $.messager.confirm("提示","rfid标签不是本环节标签，无效，请重新扫描！", function(r){
                                 $('#rfidId').val("");
                             });
                         } else {
                        	//动态增加入库相关项目start
                             $.ajax({
                                async : false,
                                cache : false,
                                type : 'POST',
                                data : {
                                    "rfidid" : code
                                },
                                url : "${path}/outfarm/dynamicAddInFarmContent", //请求的action路径  
                                error : function() {//请求失败处理函数  
                                    alert('请求失败');
                                },
                                success : function(data) { //请求成功后处理函数。 
                                    //alert(data);
                                    //删除
                                    $("#noid").val("");
                                    $("#farmid").val("");
                                    $("#pigno").val("");
                                    $("#pigtype").val("");
                                    $("#pigfrom").val("");
                                    $("#infarmweight").val("");
                                    $("#pigbirthday").val("");
                                    $("#infarmdate").val("");
                                    $("#pigcolor").val("");
                                    $("#infarmlinkman").val("");
                                    
                                    var $pigsex = $("#pigsex");
                                    //增加option内容
                                    //alert("data:"+data);
                                    //删除动态增加的option
                                    $pigsex.children(".dynamic").remove();
                                    if(data == "" || data == undefined || data == null || data == "[null]") {
                                        alert("此猪已经出栏，请不要重复进行出栏操作！");
                                    } else {
                                        var piginout = JSON.parse(data);//alert(piginout.length);
                                        var pigsexid = piginout[0].pigsex;
                                        var pigsexname = "";
                                        if(pigsexid == "0") {
                                            pigsexname = "母";
                                        } if(pigsexid == "1") {
                                            pigsexname = "公";
                                        } 

                                        var $option = $("<option></option>");
                                        $option.attr("value",pigsexid);
                                        $option.addClass("dynamic");
                                        $option.text(pigsexname);
                                        $option.appendTo($pigsex);
                                        
                                        $("#noid").val(piginout[0].noid);
                                        $("#farmid").val(piginout[0].farmid);
                                        $("#pigno").val(piginout[0].pigno);
                                        $("#pigtype").val(piginout[0].pigtype);
                                        $("#pigfrom").val(piginout[0].pigfrom);
                                        $("#infarmweight").val(piginout[0].infarmweight);
                                        
                                        $("#pigbirthday").val(piginout[0].pigbirthday);
                                        $("#infarmdate").val(piginout[0].infarmdate);
                                        $("#pigcolor").val(piginout[0].pigcolor);
                                        $("#infarmlinkman").val(piginout[0].infarmlinkman);
                                    }
                                }
                            });
                           //动态增加入库相关项目end
                         }
                     }
                 });
             },
            error: function(){
            //请求出错处理
                alert("请重新扫描！");
            }
       });
    }
</script>
<form name="outfarm_addForm" id="outfarm_addForm" method="post">
<br>
	<table align="center">
	    <tr>
            <td><font style="color:red;"> * </font>RFID编号:</td>
            <td>
                <input type="text" style="background-color: #EDEDED;width: 255px;" readonly="readonly" id="rfidId" name="rfidId"  maxlength="25" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,32]'" />
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="getCode();">扫描</a>
            </td>
        </tr>
	    <tr>
            <td><font style="color:red;"> * </font>猪编号:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigno" name="pigno" value="${outfarmForModify.pigno }" maxlength="20" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
	    <tr>
            <td><font style="color:red;"> * </font>猪类别:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigtype" name="pigtype" value="${outfarmForModify.pigtype }" maxlength="20" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>猪产地:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigfrom" name="pigfrom" value="${outfarmForModify.pigfrom }" maxlength="50" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,50]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>入栏重量（kg）:</td>
            <td><input readonly="readonly" style="background-color: #EDEDED;" type="text" id="infarmweight" name="infarmweight" value="${outfarmForModify.infarmweight }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>出生日期:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigbirthday" name="pigbirthday" value="${outfarmForModify.pigbirthday }" class="easyui-validatebox pianyi Wdate" data-options="required:true" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>入栏时间:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="infarmdate" name="infarmdate" value="${outfarmForModify.infarmdate }" class="easyui-validatebox pianyi Wdate" data-options="required:true" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>性别:</td>
            <td>
                <select name="pigsex" id="pigsex" style="background-color: #EDEDED;" class="easyui-validatebox pianyi" data-options="required:true"  >
               </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>毛色:</td>
            <td><input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigcolor" name="pigcolor" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
            </td>
        </tr>
        
        <tr>
            <td><font style="color:red;"> * </font>入栏负责人:</td>
            <td><input readonly="readonly" style="background-color: #EDEDED;" type="text" maxlength="20" id="infarmlinkman" name="infarmlinkman" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
		<tr>
			<td><font style="color:red;"> * </font>出栏重量（kg）:</td>
			<td><input type="text" id="outfarmweight" name="outfarmweight" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
			<input type="hidden" name="noid" id="noid" />
			<input type="hidden" id="farmid" name="farmid" />
			<input type="hidden" id="permitoutfarm" name="permitoutfarm" value="1" />
			</td>
		</tr>
        <tr>
            <td><font style="color:red;"> * </font>健康状况:</td>
            <td><input type="text" id="healthstatus" name="healthstatus" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
        
		<tr>
			<td><font style="color:red;"> * </font>出栏负责人:</td>
			<td><input type="text" maxlength="20" id="outfarmlinkman" name="outfarmlinkman" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
	</table>
</form>
