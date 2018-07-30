<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="breedVaccineBatch_addForm" id="breedVaccineBatch_addForm" method="post">
<br>
	<table align="center">
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
           <td><font style="color:red;"> * </font>疫苗信息：</td>
           <td>
               <select name="vaccid" id="vaccid" class="easyui-validatebox pianyi" data-options="required:true">
                   <option value="">--请选择疫苗--</option>
                   <c:forEach items="${vacclist}" var="vacc">
                       <option value="${vacc.vaccid}">${vacc.vaccname}</option>
                   </c:forEach>
               </select>
            </td>
        </tr>
	</table>
</form>
