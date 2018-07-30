$().ready(function() {
	$("#addwaterdataalarm").validate({
		rules : {
			 wpnumber:{
				 required : true
				 
			 },
			alarmType : {
				required : true,
				maxlength : 10
			},
			firstAlarmWater : {
				required : true,
				digits: true
			},
			secondAlarmWater : {
				required : true,
				digits: true
			},
			thirdAlarmWater : {
				required : true,
				digits: true
			} 
		},
		messages : {
			 wpnumber:{
				 required : "没有闸点不能提交哦"
				 
			 },
			alarmType : {
				required : "必须选择告警类型",
				maxlength : "告警类型不能大于10个字 符"
			},

			firstAlarmWater : {
				required : "必须输入一级告警水文",
				digits : "请输入整数  "
			},
			secondAlarmWater : {
				required : "必须输入二级告警水文",
				digits : "请输入整数  "
			},
			thirdAlarmWater : {
				required : "必须输入三级告警水文",
				digits : "请输入整数  "
			} 
		},
		errorPlacement : function(error, element) {
			if (element.parent().next().attr("class") == "msg") {
				element.parent().next().html("");
				error.appendTo(element.parent().next());
			} else {
				if (element.next().next().find("p")) {
					element.next().next().find("p").remove();
				}
				error.appendTo(element.next().next());
			}
		}
	});
});