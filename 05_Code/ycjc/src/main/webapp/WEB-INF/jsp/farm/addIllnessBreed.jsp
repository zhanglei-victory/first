<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<form name="breedIllness_addForm" id="breedIllness_addForm" method="post">
<br>
	<table align="center">
	    <tr>
           <td><font style="color:red;"> * </font>所属养殖场：</td>
           <td>
               <select name="farmidIllness" id="farmidIllness" class="easyui-validatebox pianyi" data-options="required:true" onchange="changeFarm(this.value);">
                   <option value="">--请选择养殖场--</option>
                   <c:forEach items="${farmlist}" var="farm">
                       <option value="${farm.farmid}">${farm.farmname}</option>
                   </c:forEach>
               </select>
            </td>
        </tr>
		<tr>
           <td><font style="color:red;"> * </font>个体猪：</td>
           <td>
              <select name="rfidIdIllness" id="rfidIdIllness" class="easyui-validatebox pianyi" data-options="required:true">
                  <option value="">--请选择猪--</option>
              </select>
            </td>
        </tr>
        <tr>
           <td><font style="color:red;"> * </font>情况（病情等）：</td>
           <td>
              <input type="text" id="content" name="content" class="easyui-validatebox pianyi" data-options="required:true,validType:'length[0,500]'" />
            </td>
        </tr>
	</table>
</form>
<script type="text/javascript">
	function changeFarm(obj) {//alert(obj);
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
	            var $rfidIdIllness = $("#rfidIdIllness");
	            //增加option内容
	           // alert(data);
	            //删除动态增加的option
	            $rfidIdIllness.children(".dynamic").remove();
	            var pigs = JSON.parse(data);
	           // alert(pigs);
	            for(var i = 0;i < pigs.length;i++) {
	                var pigrfid = pigs[i].rfidId;
	                var pigno = pigs[i].pigno;
	                
	                var $option = $("<option></option>");
	                $option.attr("value",pigrfid);
	                $option.addClass("dynamic");
	                $option.text(pigno);
	                $option.appendTo($rfidIdIllness);
	            }
	        }
	    });
	}
</script>
