$().ready(function() {
	$("#waterEventModifyForm").validate({
		rules : {
			/*wevedioid : {
				required : true,
				maxlength : 10
			},*/
			westarttime : {
				required : true,
				maxlength : 20
			},
			weendtime : {
				required : true,
				maxlength : 20
			},
			weaddress : {
				required : true,
				maxlength : 20
			},
			wereason : {
				required : true,
				maxlength : 100
			},
			weprocess : {
				required : true,
				maxlength : 100
			},
			result : {
				required : true,
				maxlength : 100
			}
		},
		messages : {
			/*wevedioid : {
				required : "请输入视频编号",
				maxlength : "视频编号不能大于10个字符"
			},*/
			westarttime : {
				required : "请输入开始日期",
				maxlength : "开始日期不能超过20个字符"
			},
			weendtime : {
				required : "请输入结束日期",
				maxlength : "结束日期不能超过20个字符"
			},
			weaddress : {
				required : "必须输入事件发生地址",
				maxlength : "地址长度不能超过20个字符"
			},
			wereason : {
				required : "请输入事件发生原因",
				maxlength : "事件发生原因不能超过100个字 符"
			},
			weprocess : {
				required : "请输入事件经过",
				maxlength : "事件经过不能超过100个字符"
			},
			result : {
				required : "请输入事件结果",
				maxlength : "事件结果不能超过100个字 符"
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