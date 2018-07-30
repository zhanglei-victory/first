<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
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
</script>
<form name="infarm_updateForm" id="infarm_updateForm" method="post">
<br>
    <table align="center">
        <tr>
            <td><font style="color:red;"> * </font>RFID编号:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;width:255px;" type="text" id="rfidId" name="rfidId" value="${infarmForModify.rfidId }" maxlength="32" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,32]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>猪编号:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigno" name="pigno" value="${infarmForModify.pigno }" maxlength="20" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>猪类别:</td>
            <td>
                <input type="text" id="pigtype" name="pigtype" value="${infarmForModify.pigtype }" maxlength="20" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
                <input type="hidden" name="infarmdate" id="infarmdate" value="${infarmForModify.infarmdate }" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>猪产地:</td>
            <td>
                <input type="text" id="pigfrom" name="pigfrom" value="${infarmForModify.pigfrom }" maxlength="50" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,50]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>入栏重量（kg）:</td>
            <td><input type="text" id="infarmweight" name="infarmweight" value="${infarmForModify.infarmweight }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
           <td><font style="color:red;"> * </font>所属养殖场：</td>
           <td>
               <select name="farmid" id="farmid" class="easyui-validatebox pianyi" data-options="required:true">
                    <c:forEach items="${farmlist}" var="farm">
                        <c:choose>
                            <c:when
                                test="${infarmForModify.farmid == farm.farmid}">
                                <option value="${farm.farmid}" selected="selected">${farm.farmname}</option>
                            </c:when>
<%--                             <c:otherwise> --%>
<%--                                 <option value="${farm.farmid}">${farm.farmname}</option> --%>
<%--                             </c:otherwise> --%>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>出生日期:</td>
            <td>
                <input type="text" style="width: 150px;" id="pigbirthday" name="pigbirthday" value="${infarmForModify.pigbirthday }" class="easyui-validatebox pianyi Wdate" data-options="required:true" readonly
                    onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',alwaysUseStartDate:true,maxDate:getNowFormatDate()})" />
            </td>
        </tr>
<!--         <tr> -->
<!--             <td><font style="color:red;"> * </font>入栏时间:</td> -->
<!--             <td> -->
<!--             <input type="text" readonly -->
<%--                                     style="width: 150px;" id="infarmdate" name="infarmdate" value="${infarmForModify.infarmdate }" --%>
<!--                                     onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',errDealMode:'1',minDate:'#F{$dp.$D(\'pigbirthday\')}'})" -->
<!--                                     class="Wdate" /> -->
<!--             </td> -->
<!--         </tr> -->
        <tr>
            <td><font style="color:red;"> * </font>性别:</td>
            <td>
	            <select name="pigsex" id="pigsex" class="easyui-validatebox pianyi" data-options="required:true">
	               <c:if test="${infarmForModify.pigsex == '1' }">
	                   <option value="1" selected="selected">公</option>
	                   <option value="0">母</option>
	               </c:if>
	               <c:if test="${infarmForModify.pigsex == '0' }">
	                   <option value="1">公</option>
	                   <option value="0" selected="selected">母</option>
	               </c:if>
	            </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>毛色:</td>
            <td><input type="text" id="pigcolor" name="pigcolor" value="${infarmForModify.pigcolor }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
            </td>
        </tr>
        
        <tr>
            <td><font style="color:red;"> * </font>入栏负责人:</td>
            <td><input type="text" maxlength="20" id="infarmlinkman" name="infarmlinkman" value="${infarmForModify.infarmlinkman }" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
    </table>
</form>
