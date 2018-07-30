<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
// 	String rfidid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
// 	pageContext.setAttribute("rfidid", rfidid);
%>
<script type="text/javascript">
	var myDate = new Date();
	myDate.getYear();        //获取当前年份(2位)
	myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	myDate.getMonth();       //获取当前月份(0-11,0代表1月)
	myDate.getDate();        //获取当前日(1-31)
	myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
	myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
	myDate.getHours();       //获取当前小时数(0-23)
	myDate.getMinutes();     //获取当前分钟数(0-59)
	myDate.getSeconds();     //获取当前秒数(0-59)
	myDate.getMilliseconds();    //获取当前毫秒数(0-999)
	myDate.toLocaleDateString();     //获取当前日期
	var mytime=myDate.toLocaleTimeString();     //获取当前时间
	myDate.toLocaleString( );        //获取日期与时间
	
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = year + seperator1 + month + seperator1 + strDate;
	           // + " " + date.getHours() + seperator2 + date.getMinutes()
	           // + seperator2 + date.getSeconds();
	    return currentdate;
	}
// 	function getRandomValue() {
//         var randomvalue = "${rfidid}";//alert(randomvalue);
//         $("#rfidId").val(randomvalue);
//     }
	
    function getRandomPignoValue() {
        var randomPignoValue = "PN" + Math.round(Math.random() * 1000000);//alert(randomvalue);
        $("#pigno").val(randomPignoValue);
    }
    function getCode(){
        $.ajax({
            type: "post",
            dataType:'json', //接受数据格式 
            url: "${path}/code/findcode",
            success: function(data){
                 var code = data.code;
                 $('#rfidId').val(code);

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
                        	//动态验证RFID是否在同一环节进行多次操作
                            $.ajax({
                               async : false,
                               cache : false,
                               type : 'POST',
                               data : {
                                   "rfidid" : code
                               },
                               url : "${path}/infarm/checkDuplicate", //请求的action路径  
                               error : function() {//请求失败处理函数  
                                   alert('请求失败');
                               },
                               success : function(data) { //请求成功后处理函数。 
                                   if(data == "true") {
                                       $.messager.alert("提示","此生猪已经入栏，不能重复进行入栏操作！");
                                   }
                               }
                           });
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
<form name="infarm_addForm" id="infarm_addForm" method="post">
<br>
	<table align="center">
	    <tr>
            <td><font style="color:red;"> * </font>RFID编号:</td>
            <td>
                <input type="text" style="background-color: #EDEDED;width: 255px;" readonly="readonly" id="rfidId" name="rfidId"  maxlength="25" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,32]'" />
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="getCode();">扫描</a>
            </td>
<!--             <td> -->
<!--                 <input type="button" value="获取RFID编号" onclick="getRandomValue();" /> -->
<!--             </td> -->
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>猪编号:</td>
            <td>
                <input type="text" style="background-color: #EDEDED;width: 185px;" readonly="readonly" id="pigno" name="pigno" maxlength="25" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,25]'" />
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="getRandomPignoValue();">获取猪编号</a>
            </td>
<!--             <td> -->
<!--                 <input type="button" value="获取猪编号" onclick="getRandomPignoValue();" /> -->
<!--             </td> -->
        </tr>
		<tr>
			<td><font style="color:red;"> * </font>猪类别:</td>
			<td>
				<input type="text" id="pigtype" name="pigtype" maxlength="20" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>猪产地:</td>
			<td>
				<input type="text" id="pigfrom" name="pigfrom" maxlength="50" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,50]'" />
			</td>
		</tr>
		<tr>
			<td><font style="color:red;"> * </font>入栏重量（kg）:</td>
			<td><input type="text" id="infarmweight" name="infarmweight" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
			</td>
		</tr>
		<tr>
           <td><font style="color:red;"> * </font>所属养殖场：</td>
           <td>
               <select name="farmid" id="farmid" class="easyui-validatebox pianyi" data-options="required:true">
                   <option value="">--请选择养殖场--</option>
                   <c:forEach items="${farmlist}" var="farm">
                       <option value="${farm.farmid}">${farm.farmname}</option>
                   </c:forEach>
               </select>
            </td>
        </tr>
		<tr>
            <td><font style="color:red;"> * </font>出生日期:</td>
            <td>
                <input type="text" style="width: 150px;" id="pigbirthday" name="pigbirthday" value="${pigbirthday }" class="easyui-validatebox pianyi Wdate" data-options="required:true" readonly
                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,maxDate:getNowFormatDate()})" />
            </td>
        </tr>
<!-- 		<tr> -->
<!--             <td><font style="color:red;"> * </font>入栏时间:</td> -->
<!--             <td> -->
<!--             <input type="text" readonly -->
<%--                                     style="width: 150px;" id="infarmdate" name="infarmdate" value="${infarmdate }" --%>
<!--                                     onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'pigbirthday\')}'})" -->
<!--                                     class="Wdate"/> -->
<!--             </td> -->
<!--         </tr> -->
        <tr>
            <td><font style="color:red;"> * </font>性别:</td>
            <td><select name="pigsex" id="pigsex" class="easyui-validatebox pianyi" data-options="required:true">
                    <option value="1">
                        公
                    </option>
                    <option value="0">
                        母
                    </option>
            </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>毛色:</td>
            <td><input type="text" id="pigcolor" name="pigcolor" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
            </td>
        </tr>
        
		<tr>
			<td><font style="color:red;"> * </font>入栏负责人:</td>
			<td><input type="text" maxlength="20" id="infarmlinkman" name="infarmlinkman" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
	</table>
</form>
