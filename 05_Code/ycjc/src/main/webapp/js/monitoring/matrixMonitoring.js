
function ajaxfunction(wpnumber) {
		//alert("path:"+wpnumber);
		//
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			data:{"wpnumber":wpnumber},
			dataType : "json",
			url : path + "/monitoring/toCondMatrixMonitoringAjax", //请求的action路径  
			error : function() {//请求失败处理函数  
				// alert('请求失败');
			},
			success : function(data) { //请求成功后处理函数。    
				//alert(data[0].wgname[2]);
				//得到“matrixMonitoring.jsp”中的告警级别对应的颜色
				var alarmColor1 = $("#alarmColor1").css("background-color");
				var alarmColor2 = $("#alarmColor2").css("background-color");
				var alarmColor3 = $("#alarmColor3").css("background-color");
				//把后台封装好的简单Json格式
				//waterpointGatesArray = JSON.parse(data);
				waterpointGatesArray = data;
				//alert(waterpointGatesArray);
				if(waterpointGatesArray.length <= 0) {
					return;
				}
				// 循环遍历所有信息
				for (var i = 0;i < waterpointGatesArray.length;i++) {
					//div（maincontentinner-alarm）后的同级元素节点删除
					
					var wpnumber = waterpointGatesArray[i].wpnumber;
					var wpname = waterpointGatesArray[i].wpname;
					//闸门数组
					var wgnumberArray = new Array();
					var wgnumbers = waterpointGatesArray[i].wgnumber;
					for(var j = 0;j < wgnumbers.length;j++) {
						wgnumberArray.push(wgnumbers[j]);
					}
					//闸门名称数组
					var wgnameArray = new Array();
					var wgnames = waterpointGatesArray[i].wgname;
					for(var k = 0;k < wgnames.length;k++) {
						wgnameArray.push(wgnames[k]);
					}

					//闸门开启状态数组
					var wgstatusArray = new Array();
					var wgstatuss = waterpointGatesArray[i].wgstatus;
					for(var m = 0;m < wgstatuss.length;m++) {
						wgstatusArray.push(wgstatuss[m]);
					}
					
					//闸门开启参数状态数组
					var wgparameterArray = new Array();
					var wgparemeters = waterpointGatesArray[i].parameter;
					for(var n = 0;n < wgparemeters.length;n++) {
						wgparameterArray.push(wgparemeters[n]);
					}
					
					//上游
					var wpuplevel = waterpointGatesArray[i].wpuplevel;
					var wpuplevelup = waterpointGatesArray[i].wpuplevelup;
					var wpupspeed = waterpointGatesArray[i].wpupspeed;
					var wpupspeedup = waterpointGatesArray[i].wpupspeedup;
					
					//水位告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpupLevelAlarmRank = waterpointGatesArray[i].wpupLevelAlarmRank;
					//水位增速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpupLevelUpAlarmRank = waterpointGatesArray[i].wpupLevelUpAlarmRank;
					//水速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpupspeedAlarmRank = waterpointGatesArray[i].wpupspeedAlarmRank;
					//水速增速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpupspeedUpAlarmRank = waterpointGatesArray[i].wpupspeedUpAlarmRank;
					//下游
					var wpdownlevel = waterpointGatesArray[i].wpdownlevel;
					var wpdownlevelup = waterpointGatesArray[i].wpdownlevelup;
					var wpdownspeed = waterpointGatesArray[i].wpdownspeed;
					var wpdownspeedup = waterpointGatesArray[i].wpdownspeedup;
					
					//最近时间
					var occurtime = waterpointGatesArray[i].occurtime;
					if(occurtime == "") {
						occurtime = "无数据";
					}
					
					// 闸底高程
					var zdheight = waterpointGatesArray[i].zdheight;
					
					//水位告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpdownLevelAlarmRank = waterpointGatesArray[i].wpdownLevelAlarmRank;
					//水位增速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpdownLevelUpAlarmRank = waterpointGatesArray[i].wpdownLevelUpAlarmRank;
					//水速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpdownspeedAlarmRank = waterpointGatesArray[i].wpdownspeedAlarmRank;
					//水速增速告警级别。1：一级告警。2：二级告警。3：3级告警
					var wpdownspeedUpAlarmRank = waterpointGatesArray[i].wpdownspeedUpAlarmRank;
					
					//告警标示。0：无告警。1：告警
					var alarmRank = waterpointGatesArray[i].alarmRank;
					//报警监控父div
					var $divOutElement = $("div.maincontentinner");
					//alert(wgnumber);
					//无告警
					if(alarmRank == '0') {
						var $jqueryOutDivElement = $("<div></div>");
						$jqueryOutDivElement.addClass("bigNormal");
						
						$jqueryOutDivElement.bind("click", {
								"wpnumber": wpnumber,
								"wpname": wpname,
								"wpuplevel": wpuplevel,
								"wpuplevelup": wpuplevelup,
								"wpupspeed": wpupspeed,
								"wpupspeedup": wpupspeedup,
								"wpdownlevel": wpdownlevel,
								"wpdownlevelup": wpdownlevelup,
								"wpdownspeed": wpdownspeed,
								"wpdownspeedup": wpdownspeedup
							},clickHandler);
						//$jqueryOutDivElement.bind("click",{},handler);
						var $waterpointGateElement = $("<div></div>");
						$waterpointGateElement.addClass("top1");
						$waterpointGateElement.text(wpname);
						$waterpointGateElement.appendTo($jqueryOutDivElement);
						//$jqueryOutDivElement.appendTo($divOutElement);
						//alert($jqueryOutDivElement);
						//豆腐窝闸、韩刘闸
						if(wpnumber == "QHDFW" || wpnumber == "QHHL") {
							//上游，下游
							var $jqueryUpDown = $("<div></div>");
							$jqueryUpDown.addClass("shangyou1");
							$jqueryUpDown.text(" ");
							$jqueryUpDown.appendTo($jqueryOutDivElement);
							
							//闸底高程
							var $jqueryzdheightDivElement = $("<div></div>");
							$jqueryzdheightDivElement.addClass("sw");
							
							var $jqueryzdheightDivElement1 = $("<div></div>");
							$jqueryzdheightDivElement1.addClass("swa");
							$jqueryzdheightDivElement1.text("闸底高程（m）");
							
							var $jqueryzdheightDivElement2 = $("<div></div>");
							$jqueryzdheightDivElement2.addClass("swb");
							$jqueryzdheightDivElement2.text(zdheight);
							
							$jqueryzdheightDivElement1.appendTo($jqueryzdheightDivElement);
							$jqueryzdheightDivElement2.appendTo($jqueryzdheightDivElement);
							
							$jqueryzdheightDivElement.appendTo($jqueryOutDivElement);
							
							//水位
							var $jqueryWaterlevelDivElement = $("<div></div>");
							$jqueryWaterlevelDivElement.addClass("sw");
							
							var $jqueryWaterlevelDivElement1 = $("<div></div>");
							$jqueryWaterlevelDivElement1.addClass("swa");
							$jqueryWaterlevelDivElement1.text("水深（m）");
							
							var $jqueryWaterlevelDivElement2 = $("<div></div>");
							$jqueryWaterlevelDivElement2.addClass("swb");
							$jqueryWaterlevelDivElement2.text(wpuplevel);
							
							$jqueryWaterlevelDivElement1.appendTo($jqueryWaterlevelDivElement);
							$jqueryWaterlevelDivElement2.appendTo($jqueryWaterlevelDivElement);
							
							$jqueryWaterlevelDivElement.appendTo($jqueryOutDivElement);

							//水位增速
							var $jqueryWaterlevelupDivElement = $("<div></div>");
							$jqueryWaterlevelupDivElement.addClass("swzs");
							
							var $jqueryWaterlevelupDivElement1 = $("<div></div>");
							$jqueryWaterlevelupDivElement1.addClass("swzsa");
							$jqueryWaterlevelupDivElement1.text("水深增速（m / s）");
							
							var $jqueryWaterlevelupDivElement2 = $("<div></div>");
							$jqueryWaterlevelupDivElement2.addClass("swzsb");
							$jqueryWaterlevelupDivElement2.text(wpuplevelup);
							
							$jqueryWaterlevelupDivElement1.appendTo($jqueryWaterlevelupDivElement);
							$jqueryWaterlevelupDivElement2.appendTo($jqueryWaterlevelupDivElement);
							
							$jqueryWaterlevelupDivElement.appendTo($jqueryOutDivElement);

							//水速
							var $jqueryWaterspeedDivElement = $("<div></div>");
							$jqueryWaterspeedDivElement.addClass("ss");
							
							var $jqueryWaterspeedDivElement1 = $("<div></div>");
							$jqueryWaterspeedDivElement1.addClass("ssa");
							$jqueryWaterspeedDivElement1.text("水速（m/s）");
							
							var $jqueryWaterspeedDivElement2 = $("<div></div>");
							$jqueryWaterspeedDivElement2.addClass("ssb");
							$jqueryWaterspeedDivElement2.text(wpupspeed);
							
							$jqueryWaterspeedDivElement1.appendTo($jqueryWaterspeedDivElement);
							$jqueryWaterspeedDivElement2.appendTo($jqueryWaterspeedDivElement);
							
							$jqueryWaterspeedDivElement.appendTo($jqueryOutDivElement);

							//水速增速
							var $jqueryWaterspeedupDivElement = $("<div></div>");
							$jqueryWaterspeedupDivElement.addClass("sszs");
							
							var $jqueryWaterspeedupDivElement1 = $("<div></div>");
							$jqueryWaterspeedupDivElement1.addClass("sszsa");
							$jqueryWaterspeedupDivElement1.text("水速增速（m/s）");
							
							var $jqueryWaterspeedupDivElement2 = $("<div></div>");
							$jqueryWaterspeedupDivElement2.addClass("swzsb");
							$jqueryWaterspeedupDivElement2.text(wpupspeedup);
							
							$jqueryWaterspeedupDivElement1.appendTo($jqueryWaterspeedupDivElement);
							$jqueryWaterspeedupDivElement2.appendTo($jqueryWaterspeedupDivElement);
							
							$jqueryWaterspeedupDivElement.appendTo($jqueryOutDivElement);
						} else {
							//上游，下游
							var $jqueryUpDown = $("<div></div>");
							$jqueryUpDown.addClass("shangyou");
							$jqueryUpDown.text("上游 | 下游");
							$jqueryUpDown.appendTo($jqueryOutDivElement);
							
							//闸底高程
							var $jqueryzdheightDivElement = $("<div></div>");
							$jqueryzdheightDivElement.addClass("sw");
							
							var $jqueryzdheightDivElement1 = $("<div></div>");
							$jqueryzdheightDivElement1.addClass("swa");
							$jqueryzdheightDivElement1.text("闸底高程（m）");
							
							var $jqueryzdheightDivElement2 = $("<div></div>");
							$jqueryzdheightDivElement2.addClass("swb");
							$jqueryzdheightDivElement2.text(zdheight);
							
							$jqueryzdheightDivElement1.appendTo($jqueryzdheightDivElement);
							$jqueryzdheightDivElement2.appendTo($jqueryzdheightDivElement);
							
							$jqueryzdheightDivElement.appendTo($jqueryOutDivElement);
							
							//水位
							var $jqueryWaterlevelDivElement = $("<div></div>");
							$jqueryWaterlevelDivElement.addClass("sw");
							
							var $jqueryWaterlevelDivElement1 = $("<div></div>");
							$jqueryWaterlevelDivElement1.addClass("swa");
							$jqueryWaterlevelDivElement1.text("水深（m）");
							
							var $jqueryWaterlevelDivElement2 = $("<div></div>");
							$jqueryWaterlevelDivElement2.addClass("swb");
							$jqueryWaterlevelDivElement2.text(wpuplevel + " | " + wpdownlevel);
							
							$jqueryWaterlevelDivElement1.appendTo($jqueryWaterlevelDivElement);
							$jqueryWaterlevelDivElement2.appendTo($jqueryWaterlevelDivElement);
							
							$jqueryWaterlevelDivElement.appendTo($jqueryOutDivElement);

							//水位增速
							var $jqueryWaterlevelupDivElement = $("<div></div>");
							$jqueryWaterlevelupDivElement.addClass("swzs");
							
							var $jqueryWaterlevelupDivElement1 = $("<div></div>");
							$jqueryWaterlevelupDivElement1.addClass("swzsa");
							$jqueryWaterlevelupDivElement1.text("水深增速（m / s）");
							
							var $jqueryWaterlevelupDivElement2 = $("<div></div>");
							$jqueryWaterlevelupDivElement2.addClass("swzsb");
							$jqueryWaterlevelupDivElement2.text(wpuplevelup + " | " + wpdownlevelup);
							
							$jqueryWaterlevelupDivElement1.appendTo($jqueryWaterlevelupDivElement);
							$jqueryWaterlevelupDivElement2.appendTo($jqueryWaterlevelupDivElement);
							
							$jqueryWaterlevelupDivElement.appendTo($jqueryOutDivElement);

							//水速
							var $jqueryWaterspeedDivElement = $("<div></div>");
							$jqueryWaterspeedDivElement.addClass("ss");
							
							var $jqueryWaterspeedDivElement1 = $("<div></div>");
							$jqueryWaterspeedDivElement1.addClass("ssa");
							$jqueryWaterspeedDivElement1.text("水速（m/s）");
							
							var $jqueryWaterspeedDivElement2 = $("<div></div>");
							$jqueryWaterspeedDivElement2.addClass("ssb");
							$jqueryWaterspeedDivElement2.text(wpupspeed + " | " + wpdownspeed);
							
							$jqueryWaterspeedDivElement1.appendTo($jqueryWaterspeedDivElement);
							$jqueryWaterspeedDivElement2.appendTo($jqueryWaterspeedDivElement);
							
							$jqueryWaterspeedDivElement.appendTo($jqueryOutDivElement);

							//水速增速
							var $jqueryWaterspeedupDivElement = $("<div></div>");
							$jqueryWaterspeedupDivElement.addClass("sszs");
							
							var $jqueryWaterspeedupDivElement1 = $("<div></div>");
							$jqueryWaterspeedupDivElement1.addClass("sszsa");
							$jqueryWaterspeedupDivElement1.text("水速增速（m/s）");
							
							var $jqueryWaterspeedupDivElement2 = $("<div></div>");
							$jqueryWaterspeedupDivElement2.addClass("swzsb");
							$jqueryWaterspeedupDivElement2.text(wpupspeedup + " | " + wpdownspeedup);
							
							$jqueryWaterspeedupDivElement1.appendTo($jqueryWaterspeedupDivElement);
							$jqueryWaterspeedupDivElement2.appendTo($jqueryWaterspeedupDivElement);
							
							$jqueryWaterspeedupDivElement.appendTo($jqueryOutDivElement);
						}
						
						
						//最近更新时间
						var $latesttimeDivElement = $("<div></div>");
						$latesttimeDivElement.addClass("sszs");
						
						var $latesttimeDivElement1 = $("<div></div>");
						$latesttimeDivElement1.addClass("sszsa");
						$latesttimeDivElement1.text("更新时间");
						
						var $latesttimeDivElement2 = $("<div></div>");
						$latesttimeDivElement2.addClass("swzsb");
						$latesttimeDivElement2.text(occurtime);
						
						$latesttimeDivElement1.appendTo($latesttimeDivElement);
						$latesttimeDivElement2.appendTo($latesttimeDivElement);
						
						$latesttimeDivElement.appendTo($jqueryOutDivElement);
						
						//闸门状态
						var $watergateStatusElement = $("<div></div>");
						$watergateStatusElement.addClass("status");
						$watergateStatusElement.text("闸口状态");
						for(var x = 0;x < wgstatusArray.length;x++) {
							var wgstatus = wgstatusArray[x];//alert(wgstatus);
							//没有配置闸口
							if(wgstatus == '0') {
								//alert("此闸点还没有配置闸口！");
								var $newLine = $("<br/>");
								$newLine.appendTo($watergateStatusElement);
								var $fontElement = $("<font>此闸点还没有配置闸口！</font>");
								$fontElement.appendTo($watergateStatusElement);
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							} else if(wgstatus == "1") {
								var $imgElement = $("<img></img>");
								$imgElement.attr("src",path + "/images/on.png");
								$imgElement.attr("title","闸口名称：" + wgnameArray[x] + " 开启高度： " + wgparameterArray[x] + "cm");
								$imgElement.addClass("class" + (x * 1 + 1));
								$imgElement.appendTo($watergateStatusElement);
								
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							} else {
								var $imgElement = $("<img></img>");
								$imgElement.attr("src",path + "/images/off.png");
								$imgElement.attr("title","闸口名称：" + wgnameArray[x]);
								$imgElement.addClass("class" + (x * 1 + 1));
								$imgElement.appendTo($watergateStatusElement);
								
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							}
						}
						
						$jqueryOutDivElement.appendTo($divOutElement);
					}// end if
					//告警
					if(alarmRank == '1') {
						var $jqueryOutDivElement = $("<div></div>");
						$jqueryOutDivElement.addClass("bigNormal");
						
						$jqueryOutDivElement.bind("click", {
								"wpnumber": wpnumber,
								"wpname": wpname,
								"wpuplevel": wpuplevel,
								"wpuplevelup": wpuplevelup,
								"wpupspeed": wpupspeed,
								"wpupspeedup": wpupspeedup,
								"wpdownlevel": wpdownlevel,
								"wpdownlevelup": wpdownlevelup,
								"wpdownspeed": wpdownspeed,
								"wpdownspeedup": wpdownspeedup
							},clickHandler);
						//$jqueryOutDivElement.bind("click",{},handler);
						var $waterpointGateElement = $("<div></div>");
						$waterpointGateElement.addClass("top1");
						$waterpointGateElement.text(wpname);
						$waterpointGateElement.appendTo($jqueryOutDivElement);
						//$jqueryOutDivElement.appendTo($divOutElement);
						//alert($jqueryOutDivElement);
						//豆腐窝闸、韩刘闸
						if(wpnumber == "QHDFW" || wpnumber == "QHHL") {
							//上游，下游
							var $jqueryUpDown = $("<div></div>");
							$jqueryUpDown.addClass("shangyou1");
							$jqueryUpDown.text("");
							$jqueryUpDown.appendTo($jqueryOutDivElement);

							//闸底高程
							var $jqueryzdheightDivElement = $("<div></div>");
							$jqueryzdheightDivElement.addClass("sw");
							
							var $jqueryzdheightDivElement1 = $("<div></div>");
							$jqueryzdheightDivElement1.addClass("swa");
							$jqueryzdheightDivElement1.text("闸底高程（m）");
							
							var $jqueryzdheightDivElement2 = $("<div></div>");
							$jqueryzdheightDivElement2.addClass("swb");
							$jqueryzdheightDivElement2.text(zdheight);
							
							$jqueryzdheightDivElement1.appendTo($jqueryzdheightDivElement);
							$jqueryzdheightDivElement2.appendTo($jqueryzdheightDivElement);
							
							$jqueryzdheightDivElement.appendTo($jqueryOutDivElement);
							//水位
							if(wpupLevelAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");//alert(wpdownLevelAlarmRank);
								
								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpuplevel);
								$upFontElement.appendTo($jqueryInnerDivElement2);
									
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "1") {

								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpuplevel);
								$upFontElement.css({"color":alarmColor1});
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpuplevel);
								$upFontElement.css({"color":alarmColor2});
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpuplevel);
								$upFontElement.css({"color":alarmColor3});
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水位

							//水位增速
							if(wpupLevelUpAlarmRank == "0") {
								
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpuplevelup);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelUpAlarmRank == "1") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.css({"color":alarmColor1});
								$upFontElement.text(wpuplevelup);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);

							} else if(wpupLevelUpAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.css({"color":alarmColor2});
								$upFontElement.text(wpuplevelup);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelUpAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.css({"color":alarmColor3});
								$upFontElement.text(wpuplevelup);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水位增速
							
							//水速
							if(wpupspeedAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeed);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "1") {
								
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");
							
								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeed);
								$upFontElement.css({"color":alarmColor1});
								$upFontElement.appendTo($jqueryInnerDivElement2);
										
									
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeed);
								$upFontElement.css({"color":alarmColor2});
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeed);
								$upFontElement.css({"color":alarmColor3});
								$upFontElement.appendTo($jqueryInnerDivElement2);
										
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水速
							
							//水速增速
							if(wpupspeedUpAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeedup);
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "1") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeedup);
								$upFontElement.css({"color":alarmColor1});
								$upFontElement.appendTo($jqueryInnerDivElement2);
										
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeedup);
								$upFontElement.css({"color":alarmColor2});
								$upFontElement.appendTo($jqueryInnerDivElement2);

								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");

								var $upFontElement = $("<font></font>");
								$upFontElement.text(wpupspeedup);
								$upFontElement.css({"color":alarmColor3});
								$upFontElement.appendTo($jqueryInnerDivElement2);
										
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}
						} else {
							//上游，下游
							var $jqueryUpDown = $("<div></div>");
							$jqueryUpDown.addClass("shangyou");
							$jqueryUpDown.text("上游 | 下游");
							$jqueryUpDown.appendTo($jqueryOutDivElement);

							//闸底高程
							var $jqueryzdheightDivElement = $("<div></div>");
							$jqueryzdheightDivElement.addClass("sw");
							
							var $jqueryzdheightDivElement1 = $("<div></div>");
							$jqueryzdheightDivElement1.addClass("swa");
							$jqueryzdheightDivElement1.text("闸底高程（m）");
							
							var $jqueryzdheightDivElement2 = $("<div></div>");
							$jqueryzdheightDivElement2.addClass("swb");
							$jqueryzdheightDivElement2.text(zdheight);
							
							$jqueryzdheightDivElement1.appendTo($jqueryzdheightDivElement);
							$jqueryzdheightDivElement2.appendTo($jqueryzdheightDivElement);
							
							$jqueryzdheightDivElement.appendTo($jqueryOutDivElement);
							//水位
							if(wpupLevelAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");//alert(wpdownLevelAlarmRank);
								//下游
								switch(wpdownLevelAlarmRank) {
									case 0:
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									
									case 1:
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									
									case 2:
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									
									case 3:
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									
								}
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "1") {
								
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");
								//下游
								switch(wpdownLevelAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");
								//下游
								switch(wpdownLevelAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sw");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swa");
								$jqueryInnerDivElement1.text("水深（m）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swb");
								//下游
								switch(wpdownLevelAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevel);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevel);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水位

							//水位增速
							if(wpupLevelUpAlarmRank == "0") {
								
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								
								//下游
								switch(wpdownLevelUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelUpAlarmRank == "1") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								
								//下游
								switch(wpdownLevelUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);

							} else if(wpupLevelUpAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								
								//下游
								switch(wpdownLevelUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupLevelUpAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("swzs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("swzsa");
								$jqueryInnerDivElement1.text("水深增速（m / s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								
								//下游
								switch(wpdownLevelUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpuplevelup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownlevelup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水位增速
							
							//水速
							if(wpupspeedAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");
								//$jqueryInnerDivElement2.text(wpupspeed);
								
								//下游
								switch(wpdownspeedAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "1") {
								
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");
								//$jqueryInnerDivElement2.text(wpupspeed);
								
								//下游
								switch(wpdownspeedAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");
								//$jqueryInnerDivElement2.text(wpupspeed);
								
								//下游
								switch(wpdownspeedAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("ss");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("ssa");
								$jqueryInnerDivElement1.text("水速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("ssb");
								//$jqueryInnerDivElement2.text(wpupspeed);
								
								//下游
								switch(wpdownspeedAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeed);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpupspeed);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeed);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}//end 水速
							
							//水速增速
							if(wpupspeedUpAlarmRank == "0") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								//下游
								switch(wpdownspeedUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "1") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								//下游
								switch(wpdownspeedUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor1});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "2") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								//下游
								switch(wpdownspeedUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor2});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							} else if(wpupspeedUpAlarmRank == "3") {
								var $jqueryInnerDivElement = $("<div></div>");
								$jqueryInnerDivElement.addClass("sszs");
								
								var $jqueryInnerDivElement1 = $("<div></div>");
								$jqueryInnerDivElement1.addClass("sszsa");
								$jqueryInnerDivElement1.text("水速增速（m/s）");
								
								var $jqueryInnerDivElement2 = $("<div></div>");
								$jqueryInnerDivElement2.addClass("swzsb");
								//下游
								switch(wpdownspeedUpAlarmRank) {
									case 0: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 1: {
										var $upFontElement = $("<font></font>");
										$upFontElement.text(wpupspeedup);
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor1});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 2: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor2});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
									case 3: {
										var $upFontElement = $("<font></font>");
										$upFontElement.css({"color":alarmColor3});
										$upFontElement.text(wpupspeedup);
										$upFontElement.appendTo($jqueryInnerDivElement2);
										
										var $jqueryTextElement = $("<font></font>");
										$jqueryTextElement.text(" | ");
										$jqueryTextElement.appendTo($jqueryInnerDivElement2);
										
										var $downFontElement = $("<font></font>");
										$downFontElement.css({"color":alarmColor3});
										$downFontElement.text(wpdownspeedup);
										$downFontElement.appendTo($jqueryInnerDivElement2);
										break;
									}
								}
								
								$jqueryInnerDivElement1.appendTo($jqueryInnerDivElement);
								$jqueryInnerDivElement2.appendTo($jqueryInnerDivElement);
								
								$jqueryInnerDivElement.appendTo($jqueryOutDivElement);
							}
						}
						//end 水速增速
						
						//最近更新时间
						var $latesttimeDivElement = $("<div></div>");
						$latesttimeDivElement.addClass("sszs");
						
						var $latesttimeDivElement1 = $("<div></div>");
						$latesttimeDivElement1.addClass("sszsa");
						$latesttimeDivElement1.text("更新时间");
						
						var $latesttimeDivElement2 = $("<div></div>");
						$latesttimeDivElement2.addClass("swzsb");
						$latesttimeDivElement2.text(occurtime);
						
						$latesttimeDivElement1.appendTo($latesttimeDivElement);
						$latesttimeDivElement2.appendTo($latesttimeDivElement);
						
						$latesttimeDivElement.appendTo($jqueryOutDivElement);
						//闸门状态
						var $watergateStatusElement = $("<div></div>");
						$watergateStatusElement.addClass("status");
						$watergateStatusElement.text("闸口状态");
						for(var x = 0;x < wgstatusArray.length;x++) {
							var wgstatus = wgstatusArray[x];//alert(wgstatus);
							//没有配置闸口
							if(wgstatus == '0') {
								//alert("此闸点还没有配置闸口！");
								var $newLine = $("<br/>");
								$newLine.appendTo($watergateStatusElement);
								var $fontElement = $("<font>此闸点还没有配置闸口！</font>");
								$fontElement.appendTo($watergateStatusElement);
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							} else if(wgstatus == "1") {
								var $imgElement = $("<img></img>");
								$imgElement.attr("src",path + "/images/on.png");
								$imgElement.attr("title","闸口名称：" + wgnameArray[x] + " 开启高度： " + wgparameterArray[x] + "cm");
								$imgElement.addClass("class" + (x * 1 + 1));
								$imgElement.appendTo($watergateStatusElement);
								
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							} else {
								var $imgElement = $("<img></img>");
								$imgElement.attr("src",path + "/images/off.png");
								$imgElement.attr("title","闸口名称：" + wgnameArray[x]);
								$imgElement.addClass("class" + (x * 1 + 1));
								$imgElement.appendTo($watergateStatusElement);
								
								$watergateStatusElement.appendTo($jqueryOutDivElement);
							} 
						}
						$jqueryOutDivElement.appendTo($divOutElement);
					}// end if
				}//end for
			}//end ajax success
		});
	}