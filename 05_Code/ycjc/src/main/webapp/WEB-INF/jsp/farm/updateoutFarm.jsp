<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="outfarm_updateForm" id="outfarm_updateForm" method="post">
<br>
	<table align="center">
	    <tr>
            <td><font style="color:red;"> * </font>RFID编号:</td>
            <td>
                <input readonly="readonly" style="background-color: #EDEDED;width:255px;" type="text" id="rfidId" name="rfidId" value="${outfarmForModify.rfidId }" maxlength="32" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,32]'" />
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
                <select style="background-color: #EDEDED;" name="pigsex" id="pigsex" class="easyui-validatebox pianyi" data-options="required:true">
                   <c:if test="${outfarmForModify.pigsex == '1' }">
                       <option value="1" selected="selected">公</option>
                   </c:if>
                   <c:if test="${outfarmForModify.pigsex == '0' }">
                       <option value="0" selected="selected">母</option>
                   </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>毛色:</td>
            <td><input readonly="readonly" style="background-color: #EDEDED;" type="text" id="pigcolor" name="pigcolor" value="${outfarmForModify.pigcolor }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
            </td>
        </tr>
        
        <tr>
            <td><font style="color:red;"> * </font>入栏负责人:</td>
            <td><input readonly="readonly" style="background-color: #EDEDED;" type="text" maxlength="20" id="infarmlinkman" name="infarmlinkman" value="${outfarmForModify.infarmlinkman }" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
		<tr>
			<td><font style="color:red;"> * </font>出栏重量（kg）:</td>
			<td><input type="text" id="outfarmweight" value="${outfarmForModify.outfarmweight }" name="outfarmweight" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
			<input type="hidden" name="rfidId" id="rfidId" value="${outfarmForModify.rfidId }" />
			<input type="hidden" name="noid" id="noid" value="${outfarmForModify.noid }" />
<%-- 			<input type="hidden" id="pigfrom" name="pigfrom" value="${outfarmForModify.pigfrom }"/> --%>
<%-- 			<input type="hidden" id="pigtype" name="pigtype" value="${outfarmForModify.pigtype }" /> --%>
<%-- 			<input type="hidden" id="infarmweight" name="infarmweight" value="${outfarmForModify.infarmweight }" /> --%>
			<input type="hidden" id="farmid" name="farmid" value="${outfarmForModify.farmid }" />
<%-- 			<input type="hidden" id="pigbirthday" name="pigbirthday" value="${outfarmForModify.pigbirthday }" /> --%>
<%-- 			<input type="hidden" id="infarmdate" name="infarmdate" value="${outfarmForModify.infarmdate }" /> --%>
<%-- 			<input type="hidden" id="pigsex" name="pigsex" value="${outfarmForModify.pigsex }" /> --%>
<%-- 			<input type="hidden" id="pigcolor" name="pigcolor" value="${outfarmForModify.pigcolor }" /> --%>
<%-- 			<input type="hidden" id="infarmlinkman" name="infarmlinkman" value="${outfarmForModify.infarmlinkman }" /> --%>
			<input type="hidden" id="permitoutfarm" name="permitoutfarm" value="${outfarmForModify.permitoutfarm }" />
			<input type="hidden" id="outfarmdate" name="outfarmdate" value="${outfarmForModify.outfarmdate }" />
			</td>
		</tr>
        <tr>
            <td><font style="color:red;"> * </font>健康状况:</td>
            <td><input type="text" id="healthstatus" name="healthstatus" value="${outfarmForModify.healthstatus }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
            </td>
        </tr>
        
		<tr>
			<td><font style="color:red;"> * </font>出栏负责人:</td>
			<td><input type="text" maxlength="20" id="outfarmlinkman" value="${outfarmForModify.outfarmlinkman }" name="outfarmlinkman" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,20]'" />
			</td>
		</tr>
	</table>
</form>
