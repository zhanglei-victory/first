<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="farm_updateForm" id="farm_updateForm" method="post">
<br>
	<table align="center">
        <tr>
            <td><font style="color:red;"> * </font>养殖场名称:</td>
            <td>
                <input type="text" id="farmname" name="farmname" value="${farmForModify.farmname }" maxlength="25" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,25]'" />
                <input type="hidden" id="farmid" name="farmid" value="${farmForModify.farmid }"/>
                <input type="hidden" id="lightstatus" name="lightstatus" value="${farmForModify.lightstatus }"/>
                <input type="hidden" id="fanstatus" name="fanstatus" value="${farmForModify.fanstatus }"/>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>养殖场占地面积（m2）:</td>
            <td>
                <input type="text" id="farmarea" name="farmarea" value="${farmForModify.farmarea }" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>联系人:</td>
            <td><input type="text" id="farmlinkman" name="farmlinkman" value="${farmForModify.farmlinkman }" maxlength="10" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,10]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>手机:</td>
            <td><input type="text" id="farmphone" name="farmphone" value="${farmForModify.farmphone }" maxlength="15" class="easyui-validatebox pianyi" data-options="required:true,validType:'mobile'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>养殖场地址:</td>
            <td><input type="text" maxlength="30" id="farmaddress" value="${farmForModify.farmaddress }" name="farmaddress" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,50]'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>养殖场描述:</td>
            <td>
            <textarea name="remark" rows="5" cols="20" maxlength="250" id="remark" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,250]'" >
            ${farmForModify.remark }
            </textarea>
            </td>
        </tr>
    </table>
</form>
