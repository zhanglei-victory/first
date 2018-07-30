$().ready(function() {
	$("#addwaterpoint").validate({
		rules : {
			wpnumber : {
				required : true,
				letter : true,
				remote : {
					type : "GET",
					url : path + "/waterpoint/isExsit",
					data : {
						wpnumber : function() {
							return $("#wpnumber").val()
						}
					}
				}
			},
			wpname : {
				required : true,
				maxlength : 10,

			},
			wpbelong : {
				required : true,
				maxlength : 10
			},
			wprank : {
				required : true,
				maxlength : 10
			},
			wpbase : {
				required : true,
				maxlength : 10
			},
			wplng : {
				required : true,

			},
			wplat : {
				required : true,

			},
			wpposition : {
				required : true,
				maxlength : 20
			},
			wptelphone : {
				required : true,
				telPhone : true
			}
		},
		messages : {
			wpnumber : {
				required : "闸点编号有误",
				letter : "只能输入A-Z的大写字母",
				remote : "闸点编号已存在"
			},
			wpname : {
				required : "输入闸点名称",
				maxlength : "闸点不能大于10个字 符"
			},

			wpbelong : {
				required : "必须输入闸点隶属",
				maxlength : "闸点隶属不能大于10个字 符"
			},
			wprank : {
				required : "必须输入闸点级别",
				maxlength : "闸点级别不能大于10个字 符"
			},
			wpbase : {
				required : "必须输入闸点材料",
				maxlength : "闸点材料不能大于10个字 符"
			},
			wplng : {
				required : "点击地图输入闸点位置",

			},
			wplat : {
				required : "点击地图输入闸点位置",
			},
			wpposition : {
				required : "必须输入闸点地址",
				maxlength : "闸点材料不能大于10个字 符"
			},

			wptelphone : {
				required : "必须输入联系方式",
				telPhone : "联系方式格式不正确"
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