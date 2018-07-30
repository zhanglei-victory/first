<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<div id="container" style="width:100%;height:100%;overflow: hidden;"></div>
<script type="text/javascript">
	// 得到信息
	function getInfo() {//alert("ssssssss");
		$.post(
			"${path}/farm/getAllFarmPoint",
			function(data) {//alert(data);
				//数组
				var pointArray = JSON.parse(data);
				//alert(pointArray.length);
				if(pointArray.length <= 0){
					//alert("没有相关定位对象的定位信息。");
					return;
				}
				// 循环遍历所有信息
				for (var i = 0;i < pointArray.length;i++) {
					/** 养殖场id **/
					var farmid = pointArray[i].farmid;
					
					/** 养殖场名**/
					var farmname = pointArray[i].farmname;

					/** 养殖场占地面积**/
					var farmarea = pointArray[i].farmarea;

					/** 养殖场联系人**/
					var farmlinkman = pointArray[i].farmlinkman;
					
					/** 养殖场联系方式**/
					var farmphone = pointArray[i].farmphone;
					
					/** 养殖场地址**/
					var farmaddress = pointArray[i].farmaddress;
					
					/** 温度 **/
					var temp = pointArray[i].temp;
					
					/** 湿度 **/
					var humidity = pointArray[i].humidity;
					
					/** 光照强度 **/
					var light = pointArray[i].light;
					
					/** PH值 **/
					var phvalue = pointArray[i].phvalue;
					
					/** 灯状态 **/
					var lightstatus = pointArray[i].lightstatus;
					
					/** 风扇状态 **/
					var fanstatus = pointArray[i].fanstatus;
					
					/** 养殖场描述 **/
					var remark = pointArray[i].remark;
					
					/** 卫生标准**/
					var healthness = pointArray[i].healthness;
					if(healthness == "1") {
						healthness = "<img src='${path}/images/hege.png' />";
					} else if(healthness == "0") {
						healthness = "<img src='${path}/images/buhege.png' />";
					} else {
						healthness = "还未进行监管"
					}
					
					/** 经度 **/
					var lng = pointArray[i].lng;
					
					/** 纬度 **/
					var lat = pointArray[i].lat;
					
					/** 经营许可证号码 **/
					var licenseid = pointArray[i].licenseid;
					
					/** 登记时间 **/
					var insdatetime = pointArray[i].insdatetime;
					
					/** 修改时间 **/
					var upddatetime = pointArray[i].upddatetime;
					
					/** 喂养数量 **/
					var breednumber = pointArray[i].breednumber;
					
					// 定义一个点
					var point = new BMap.Point(lng,lat);
					
					var myIcon = new BMap.Icon("${path}/images/farm_point.png", new BMap.Size(18,16));

					var marker = new BMap.Marker(point,{icon:myIcon});
					//var marker = new BMap.Marker(point);
					// 定义标题
					var label = new BMap.Label(farmname,{offset:new BMap.Size(20,-10)});
					// 把标题加到点上
					marker.setLabel(label);

				map.addOverlay(marker);
				var content = "<b>养殖场属性</b>" + "<br/>" 
					+ "<table style='border-collapse:collapse' >" 
					+ "<tr>" + "<td>联系人：</td>" + "<td>" + farmlinkman + "</td>" + "</tr>" 
					+ "<tr>" + "<td>联系电话：</td>" + "<td>" + farmphone + "</td>" + "</tr>" 
					+ "<tr>" + "<td>占地面积(m2)：</td>" + "<td>" + farmarea + "</td>" + "</tr>" 
					+ "<tr>" + "<td>温度(摄氏度)：</td>" + "<td>" + temp + "</td>" + "</tr>" 
					+ "<tr>" + "<td>湿度(%)：</td>" + "<td>" + humidity + "</td>" + "</tr>" 
					+ "<tr>" + "<td>光照(勒克斯)：</td>" + "<td>" + light + "</td>" + "</tr>" 
					+ "<tr>" + "<td>经营许可证号码：</td>" + "<td>" + licenseid + "</td>" + "</tr>"
					+ "<tr>" + "<td>卫生情况：</td>" + "<td>" + healthness + "</td>" + "</tr>"
					+ "<tr>" + "<td>登记时间：</td>" + "<td>" + insdatetime + "</td>" + "</tr>"
					+ "<tr>" + "<td>喂养数量(头)：</td>" + "<td>" + breednumber + "</td>" + "</tr>"
					+ "</table>" + "<br/>";

				addClickHandler(farmname,content, marker);
			}
				topPoint = new BMap.Point(pointArray[0].lng, pointArray[0].lat);
				topMap.centerAndZoom(topPoint, 11);
			// 鼠标点击
			function addClickHandler(wpname,content, marker) {
				marker.addEventListener("click", function(e) {
					openInfo(wpname,content, e)
				});
			}
			// 打开信息窗口
			function openInfo(wpname,content, e) {
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng,p.getPosition().lat);
				var searchInfoWindow = new BMapLib.SearchInfoWindow(map,content, {
					title : "<b>" + "<font color='#0000EE'>" + wpname + "</font>" + "</b>", //标题
					//width : 290, //宽度
					//height : 245, //高度
					width : 235, //宽度
					height : 0, //高度(设为0时自适应大小)
					panel : "panel", //检索结果面板
					enableAutoPan : true, //自动平移
					enableSendToPhone:false,//手机图标
// 					searchTypes : [BMAPLIB_TAB_SEARCH, //周边检索
// 						BMAPLIB_TAB_TO_HERE, //到这里去
// 						BMAPLIB_TAB_FROM_HERE //从这里出发
// 					]
					searchTypes : [
					]
				});
				searchInfoWindow.open(point);
			}
		});
	}
	//创建和初始化地图函数：
	function initMap() {
		createMap();//创建地图
		setMapEvent();//设置地图事件
		addMapControl();//向地图添加控件
	}

	//创建地图函数：
	function createMap() {
		var map = new BMap.Map("container");//在百度地图容器中创建一个地图
		topMap = map;
		//alert(map);
		var point = new BMap.Point(113.264655, 35.940464);//定义一个中心点坐标
		map.centerAndZoom(point, 11);//设定地图的中心点和坐标并将地图显示在地图容器中
		window.map = map;//将map变量存储在全局
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	}

	//地图事件设置函数：
	function setMapEvent() {
		map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
		map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		map.enableKeyboard();//启用键盘上下左右键移动地图
	}

	//地图控件添加函数：
	function addMapControl() {
		//向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({
			anchor : BMAP_ANCHOR_TOP_LEFT,
			type : BMAP_NAVIGATION_CONTROL_LARGE
		});
		map.addControl(ctrl_nav);
		//向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({
			isOpen:true,
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT
		});
		map.addControl(ctrl_ove);
		//向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_BOTTOM_LEFT
		});
		map.addControl(ctrl_sca);
		//地图，卫星
		var mapType = new BMap.MapTypeControl({
			mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]
		});
		map.addControl(mapType);
	}


	
	// 以下内容为视频相关模块代码
	function initPage() {//alert("eee");
		// 判断浏览器是否连网
		if (navigator.onLine) {
			//alert('online');
			// 执行地图
			//页面的全局变量
			var topMap;
			var topPoint;
			initMap();//alert("999");
			// 得到闸点信息
			getInfo();
		} else {
			alert("没有连接互联网，不能访问地图！");
		}
	}
	initPage();
</script>
