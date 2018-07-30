<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<form name="scene_farm_updateForm" id="scene_farm_updateForm" method="post">
<br>
    <table align="center">
        <tr>
            <td><font style="color:red;"> * </font> 阈值开关: </td>
            <td>
                <input type="hidden" id="sceneid" name="sceneid" value="${info.sceneid }">
                <!-- 养殖场场景类型 -->
                <input type="hidden" id="scenetype" name="scenetype" value="${info.scenetype }">
                <select id="openflag" name="openflag" class="easyui-validatebox pianyi" data-options="required:true">
                    <c:if test="${info.openflag == '1' }">
                        <option value="1" selected="selected">开 启</option>
                        <option value="0">关 闭</option>
                    </c:if>
                    <c:if test="${info.openflag == '0' }">
                        <option value="1">开 启</option>
                        <option value="0" selected="selected">关 闭</option>
                    </c:if>
                    <c:if test="${empty info.openflag }">
                        <option value="0" >关 闭</option>
                        <option value="1">开 启</option>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>温度最大阈值（摄氏度）:</td>
            <td>
                <input type="text" value="${info.tempmax }" id="tempmax" name="tempmax" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>温度最小阈值（摄氏度）:</td>
            <td>
                <input type="text" value="${info.tempmin }" id="tempmin" name="tempmin" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>湿度最大阈值（%）:</td>
            <td>
                <input type="text" value="${info.humiditymax }" id="humiditymax" name="humiditymax" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>湿度最小阈值（%）:</td>
            <td>
                <input type="text" value="${info.humiditymin }" id="humiditymin" name="humiditymin" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>光照强度最大阈值（勒克斯）:</td>
            <td>
                <input type="text" value="${info.lightmax }" id="lightmax" name="lightmax" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
        <tr>
            <td><font style="color:red;"> * </font>光照强度最小阈值（勒克斯）:</td>
            <td>
                <input type="text" value="${info.lightmin }" id="lightmin" name="lightmin" maxlength="8" class="easyui-validatebox pianyi" data-options="required:true,validType:'intOrFloat'" />
            </td>
        </tr>
    </table>
</form>