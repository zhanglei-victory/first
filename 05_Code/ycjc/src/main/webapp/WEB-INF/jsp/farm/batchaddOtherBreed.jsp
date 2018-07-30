<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="breedOtherBatch_addForm" id="breedOtherBatch_addForm" method="post">
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
           <td><font style="color:red;"> * </font>其他养殖记录情况：</td>
           <td>
              <input type="text" id="content" name="content" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,500]'" />
            </td>
        </tr>
	</table>
</form>