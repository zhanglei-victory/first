<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path",path);
    pageContext.setAttribute("randomparamer", Math.random());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>食品溯源管理中心</title>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="${path}/js/jquery/jquery-easyui-1.3.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${path}/js/jquery/jquery-easyui-1.3.1/themes/icon.css"/>
<link href="${path }/css/nav.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path }/css/index.css" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${path}/js/jquery/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<%-- <script type="text/javascript" src="${path}/js/jquery/jquery-easyui-1.3.1/jquery.easyui.min_132.js"></script> --%>
<script type="text/javascript" src="${path}/js/jquery/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/js/jquery/syUtil.js"></script>

<script type="text/javascript" src="${path }/My97DatePicker/WdatePicker.js"></script>

<!-- 以下为地图资源 -->
<link rel="stylesheet" href="${path }/css/user/gisMonitoring.css" type="text/css"/>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=mcsxdUieW6WCPj1o9GM9Y1rk"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.js"></script>
<!--9.02 start-->
<style type="text/css">
    .first-nav{
        position: absolute;
        right: 100px;
        bottom: 1px;
    }
    .first-nav li{
        float: left;
        /*margin-right: 20px;*/
        list-style: none;
        font-size: 14px;
        
    }
    .first-nav li:hover a{
        background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#e0ecff), to(#0664c4));
        color: #fff;
    }
    .first-nav li.active a{
        background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#e0ecff), to(#0664c4));
        color: #fff;
    }
    .first-nav li a{
        font-weight: bold;
        display: inline-block;
        padding: 6px 30px;
        border:1px solid #0664c4;
        border-bottom: none;
        border-radius: 8px 8px 0px 0px;
        text-decoration: none;
        background: -webkit-gradient(linear, 0% 20%, 0% 100%,from(#dbe7fd), to(#649ad7));
        color: #fff;
    }
    .second-nav{
        display: none;
        list-style: none;
        margin-top: 0px;
    }
    .second-nav li{
        border-bottom:1px solid #0664c4;
        height: 35px;
        background: -webkit-gradient(linear, 0% 20%, 0% 100%,from(#dbe7fd), to(#78a7dd));
        margin-left: -40px;
        line-height: 35px;
        text-align: center;
        
    }
    .second-nav li:hover{
        height: 35px;
        background: -webkit-gradient(linear, 0% 20%, 0% 100%,from(#dbe7fd), to(#0664c4));
        margin-left: -40px;
        line-height: 35px;
        text-align: center;
        
    }
    .second-nav li a{
        text-decoration: none;
        color: #fff;
        font-size: 14px;
        font-weight: bold;
    }
    
    .second-nav:first-child{
        display: block;
    }
</style>
<script type="text/javascript">
    $(function(){
        $('.first-nav li').click(function(){
            $('.first-nav li').removeClass('active');
            $(this).addClass('active');
            $('.second-nav').css('display','none');
            $('.second-nav').eq($('.first-nav li').index(this)).css('display','block');
            console.log(1111);
            console.log($('.first-nav li').index(this));
            console.log(2222);
        });
    });
</script>
<!--9.02 end-->

<script type="text/javascript">
        var timer;
        var timer2;
        function tomenu(menuid) {
            switch(menuid) {
                case "11":
                    $("body").layout("panel","center").panel("setTitle","实时监控 >> GIS监控");
                    $("#centerdiv").panel({
                        href:'${path}/farm/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "12":
                    $("body").layout("panel","center").panel("setTitle","实时监控 >> 告警监控");
                    $("#centerdiv").panel({
                        href:'${path}/farm/toFarmList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                    
                case "21":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM2.5曲线");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "22":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM10曲线");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;  
                case "23":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM2.5报表");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "24":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM10报表");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
				case "25":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> 历史数据");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "31":
                    $("body").layout("panel","center").panel("setTitle","配置管理 >> 扬尘点管理");
                    $("#centerdiv").panel({
                        href:'${path}/divisionhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "41":
                    $("body").layout("panel","center").panel("setTitle","权限管理 >> 用户管理");
                    $("#centerdiv").panel({
                    	href:'${path}/user/userInfo/toUserList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "42":
                    $("body").layout("panel","center").panel("setTitle","权限管理 >> 角色管理");
                    $("#centerdiv").panel({
                    	href:'${path}/user/role/toRoleList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "51":
                    $("body").layout("panel","center").panel("setTitle","网关配置 >> 网关绑定");
                    $("#centerdiv").panel({
                        href:'${path}/storage/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
            }
        }

        //清除定时
        function clear(){
            clearInterval(timer);
            clearInterval(timer2);
        }
        var flag = 0;
        
    </script>
</head>
<body class="easyui-layout" style="width:100%;height:100%" id="matrix">
    <div data-options="region:'north',split:true" title="食品溯源监控系统" style="height:119px;" id="logo">
    <div class="logo" >
        <div class="logobg">
            <img src="${path}/images/login/logo6_1.png"/>
        </div>
        <div class="topbg">
            <img src="${path}/images/login/topbg5_1.png"/>
        </div>
        <div class="text"><a>食品溯源监控系统</a></div>
        <div class="text2">
            <a>PIG BREED MANAGER SYSTEM</a>
        </div>
        <!--9.02start-->
        <div class="first-nav">
            <ul>
                <li><a href="#">导航一</a></li>
                <li><a href="#">导航二</a></li>
                <li><a href="#">导航三</a></li>
                <li><a href="#">导航四</a></li>
                <li><a href="#">导航五</a></li>
            </ul>
        </div>
        <!--9.02end-->
        <div class="top_right" >
            <div class="linksys1" >
        <!--                 <a href="javascript:void(0);" onclick="toinfoinitindex()">RFID初始化</a>  -->
<!--            <a href="javascript:void(0);" onclick="tosearchindex()">溯源查询</a>  -->
<!--            <a href="javascript:void(0);" onclick="toroleauthindex()">用户角色设置</a> -->
        </div>
        
        <div class="tubiao" >
            <a>欢迎您，${loginUser.userRealName}</a>
            <a href="javascript:void(0);" onclick="personalSetting()">个人设置</a> 
            <a href="javascript:void(0);" onclick="passwordUpdate()">密码修改</a> 
            <a href="javascript:void(0);" onclick="logout()">注销</a>
         </div>
        </div>
    </div>
    </div><!-- end north -->
    <div data-options="region:'south',split:false" style="height:30px;" id="footer">
        <div class="footer-right">
            <center>
                <span>&copy; 2015. 山东微分电子科技有限公司. All Rights Reserved.V2.0</span>
            </center>
        </div>
    </div><!-- end south -->
    <div data-options="region:'west',split:true" title="导航" style="width:150px;color: #0866C6;" id="left">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <c:if test="${auth1 == '1' }">
                <div title="&nbsp;实时监控" style="background-color:#ffffff;" data-options="iconCls:'icon1-farm'" >
                    <%-- <ul id="auth1_ui" class="firstmenu" >
                        <c:forEach items="${userAuthList }" var="userAuth">
                            <c:if test="${userAuth.authid == '11' }">
                                <li class="child_li_class" id="li11">
                                    <a href="javascript:void(0);" onclick="tomenu('11')" 
                                    class="gisjiankong"><font class="menu_next">GIS监控</font></a></li>
                            </c:if>
                            <c:if test="${userAuth.authid == '12' }">
                                <li class="child_li_class" id="li12">
                                    <a href="javascript:void(0);" onclick="tomenu('12')" class="farminfo"><font class="menu_next">告警监控</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul> --%>
                    <!--9.02 start-->
			        <ul class="second-nav">
			            <li><a href="">子导航一</a></li>
			            <li><a href="">子导航一</a></li>
			            <li><a href="">子导航一</a></li>
			            <li><a href="">子导航一</a></li>
			        </ul>
			        <!--9.02 end-->
                </div>
            </c:if>
            <c:if test="${auth2 == '1' }">
                <div title="&nbsp;统计分析" style="background-color:#ffffff;" data-options="iconCls:'icon1-slaughter'">
                    <%-- <ul id="auth2_ui" class="firstmenu">
                        <c:forEach items="${userAuthList }" var="userAuth">
                            <c:if test="${userAuth.authid == '21' }">
                                <li class="child_li_class" id="li21">
                                    <a href="javascript:void(0);" class="gisjiankong" onclick="tomenu('21')"><font class="menu_next">PM2.5曲线</font></a></li>
                            </c:if>
                            <c:if test="${userAuth.authid == '22' }">
                                <li class="child_li_class" id="li22">
                                    <a href="javascript:void(0);" class="slaughterhouse" onclick="tomenu('22')"><font class="menu_next">PM10曲线</font></a></li>
                            </c:if>
							<c:if test="${userAuth.authid == '23' }">
                                <li class="child_li_class" id="li23">
                                    <a href="javascript:void(0);" class="slaughterhouse" onclick="tomenu('23')"><font class="menu_next">PM2.5报表</font></a></li>
                            </c:if>
                            <c:if test="${userAuth.authid == '24' }">
                                <li class="child_li_class" id="li24">
                                    <a href="javascript:void(0);" class="gisjiankong" onclick="tomenu('24')"><font class="menu_next">PM10报表</font></a></li>
                            </c:if>
                            <c:if test="${userAuth.authid == '25' }">
                                <li class="child_li_class" id="li25">
                                    <a href="javascript:void(0);" class="slaughterhouse" onclick="tomenu('25')"><font class="menu_next">历史数据</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul> --%>
                    <!--9.02 start-->
                    <ul class="second-nav">
                        <li><a href="">子导航二</a></li>
                        <li><a href="">子导航二</a></li>
                        <li><a href="">子导航二</a></li>
                        <li><a href="">子导航二</a></li>
                    </ul>
                    <!--9.02 end-->
                </div>
            </c:if>
            <c:if test="${auth3 == '1' }">
                <div title="&nbsp;配置管理" style="background-color:#ffffff;" data-options="iconCls:'icon1-divisionprocess'">
                    <%-- <ul id="auth3_ui" class="firstmenu">
                        <c:forEach items="${userAuthList }" var="userAuth">
                            <c:if test="${userAuth.authid == '31' }">
                                <li class="child_li_class" id="li31">
                                <a href="javascript:void(0);" class="gisjiankong" onclick="tomenu('31')"><font class="menu_next">扬尘点管理</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul> --%>
                    <!--9.02 start-->
                    <ul class="second-nav">
                        <li><a href="">子导航三</a></li>
                        <li><a href="">子导航三</a></li>
                        <li><a href="">子导航三</a></li>
                        <li><a href="">子导航三</a></li>
                    </ul>
                    <!--9.02 end-->
                </div>
            </c:if>
            <c:if test="${auth4 == '1' }">
                <div title="&nbsp;权限管理" style="background-color:#ffffff;" data-options="iconCls:'icon1-storage'">
                    <%-- <ul id="auth5_ui" class="firstmenu">
                        <c:forEach items="${userAuthList }" var="userAuth">
                            <c:if test="${userAuth.authid == '41' }">
                                <li class="child_li_class" id="li41">
                                    <a href="javascript:void(0);" class="gisjiankong" onclick="tomenu('41')"><font class="menu_next">用户管理</font></a></li>
                            </c:if>
                            <c:if test="${userAuth.authid == '42' }">
                                <li class="child_li_class" id="li42">
                                    <a href="javascript:void(0);" class="storagecenter" onclick="tomenu('42')"><font class="menu_next">角色管理</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul> --%>
                    <!--9.02 start-->
                    <ul class="second-nav">
                        <li><a href="">子导航四</a></li>
                        <li><a href="">子导航四</a></li>
                        <li><a href="">子导航四</a></li>
                        <li><a href="">子导航四</a></li>
                    </ul>
                    <!--9.02 end-->
                </div>
            </c:if>
            <c:if test="${auth5 == '1' }">
                <div title="&nbsp;网关绑定" style="background-color:#ffffff;" data-options="iconCls:'icon1-storage'">
                    <%-- <ul id="auth5_ui" class="firstmenu">
                        <c:forEach items="${userAuthList }" var="userAuth">
                            <c:if test="${userAuth.authid == '51' }">
                                <li class="child_li_class" id="li51">
                                    <a href="javascript:void(0);" class="gisjiankong" onclick="tomenu('51')"><font class="menu_next">网关绑定</font></a></li>
                            </c:if>
                        </c:forEach>
                    </ul> --%>
                    <!--9.02 start-->
                    <ul class="second-nav">
                        <li><a href="">子导航五</a></li>
                        <li><a href="">子导航五</a></li>
                        <li><a href="">子导航五</a></li>
                        <li><a href="">子导航五</a></li>
                    </ul>
                    <!--9.02 end-->
                </div>
            </c:if>
        </div>
    </div><!-- end west -->
    <div id="centerdiv" data-options="region:'center',split:true" title="管理中心" >
    </div><!-- end center -->
    <script type="text/javascript">
        var timer;
        var timer2;
        $(function() {
            //得到left中第一个可以执行的菜单链接
            var linkId = $("li.child_li_class").first().attr("id");//alert(linkId);
            //根据id来选择跳转的链接地址
            switch(linkId) {
                case "li11":
                    $("body").layout("panel","center").panel("setTitle","实时监控 >> GIS监控");
                    $("#centerdiv").panel({
                        href:'${path}/farm/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li12":
                    $("body").layout("panel","center").panel("setTitle","实时监控 >> 告警监控");
                    $("#centerdiv").panel({
                        href:'${path}/farm/toFarmList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                    
                case "li21":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM2.5曲线");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li22":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM10曲线");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;  
                case "li23":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM2.5报表");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li24":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> PM10报表");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
				case "li25":
                    $("body").layout("panel","center").panel("setTitle","统计分析 >> 历史数据");
                    $("#centerdiv").panel({
                        href:'${path}/slauhouse/toSlauhouseList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li31":
                    $("body").layout("panel","center").panel("setTitle","配置管理 >> 扬尘点管理");
                    $("#centerdiv").panel({
                        href:'${path}/divisionhouse/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li41":
                    $("body").layout("panel","center").panel("setTitle","权限管理 >> 用户管理");
                    $("#centerdiv").panel({
                    	href:'${path}/user/userInfo/toUserList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li42":
                    $("body").layout("panel","center").panel("setTitle","权限管理 >> 角色管理");
                    $("#centerdiv").panel({
                    	href:'${path}/user/role/toRoleList?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
                case "li51":
                    $("body").layout("panel","center").panel("setTitle","网关配置 >> 网关绑定");
                    $("#centerdiv").panel({
                        href:'${path}/storage/toGisMonitoring?tmp=${randomparamer}',
                        cache:false
                    });
                    clear();
                    break;
            }
        });
        
      //退出系统  
        function logout() {
            $.messager.confirm('确认退出', '您确实要注销本系统吗?', function(r){
                if (r){
                    this.location.href = "${path}/quit";
                }
            });
        }
        function personalSetting() {
            var d=$('<div/>').dialog({
                title : '个人设置',
                href : '${path}/user/userInfo/toSetting/${loginUser.userid}',
                width : 400,
                height : 220,
                modal : true,
                buttons : [ {
                    text : '保存',
                //  iconCls : 'icon-add',
                    handler : function() {
                            $('#userSettingForm').form('submit',{
                            url:'${path}/user/userInfo/settingUserInfo',
                            success:function(r){
                                obj=$.parseJSON(r);
                                if(obj.success){
                                    d.dialog('close');
                                     $.messager.show({
                                         title:'提示',
                                         msg:obj.msg
                                     });
                                } else {
                                    $.messager.alert('提示', obj.msg);
                                }
                            }
                            });
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        d.dialog('close');
                    }
                } ],
                onClose : function() {//alert("00");
                    $(this).dialog('destroy');
                },
                onLoad : function() {
                }
            });
        }
        function passwordUpdate() {
            var d=$('<div/>').dialog({
                title : '密码修改',
                href : '${path}/user/userInfo/toUpdPwd/${loginUser.userid}',
                width : 400,
                height : 220,
                modal : true,
                buttons : [ {
                    text : '保存',
                //  iconCls : 'icon-add',
                    handler : function() {
                        //var oldUserPwd = $("#oldUserPwd").val();alert("oldUserPwd"+oldUserPwd);
                            $('#userPwdUpdForm').form('submit',{
                            url:'${path}/user/userInfo/updPwdInfo/' + $("#oldUserPwd").val(),
                            success:function(r){
                                obj=$.parseJSON(r);
                                if(obj.success){
                                    d.dialog('close');
                                     $.messager.show({
                                         title:'提示',
                                         msg:obj.msg
                                     });
                                } else {
                                    $.messager.alert('提示', obj.msg);
                                    $("#userPwd").val("");
                                    $("#confirmUserPwd").val("");
                                    $("#oldUserPwd").val("");
                                    $("#oldUserPwd").focus();
                                }
                            }
                        });
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        d.dialog('close');
                    }
                } ],
                onClose : function() {//alert("00");
                    $(this).dialog('destroy');
                },
                onLoad : function() {
                    $("#oldUserPwd").focus();
                }
            });
        }
       
    </script>
</body>
</html>
