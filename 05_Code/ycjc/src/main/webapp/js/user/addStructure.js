$().ready(function() {
	$("#strucAddForm").validate({
		rules : {
			strucname : {
				required : true,
				maxlength : 10
			},
			strucprincipal : {
				required : true,
				maxlength : 10
			},
			struccode : {
				required : true,
				digits : true
			},
			structelphone: {
				required : true,
				telPhone:true
			},
			strucsuperior : {
				required : true
			},
			strucaddress : {
				required : true,
				maxlength : 20
			}
		},
		messages : {
			strucname : {
				required : "请输入机构名称"
			},
			strucprincipal : {
				required : "必须输入负责人",
				maxlength : "负责人不能大于10个字 符"
			},
			struccode : {
				required : "请输入机构代码",
				digits : "机构代码必须为数字"
			},
			structelphone: {
				required : "必须输入联系方式",
				telPhone:"联系方式格式不正确"
			},
			strucsuperior : {
				required : "请输入上级机构"
			},
			strucaddress : {
				required : "请输入机构地址",
				maxlength : "机构地址不能大于20个字 符"
			}
		},
		errorPlacement: function(error, element) {
			if(element.parent().next().attr("class") == "msg") {
				element.parent().next().html("");
				error.appendTo(element.parent().next());
			} else {
				if(element.next().next().find("p")) {
					element.next().next().find("p").remove();
				}
				error.appendTo(element.next().next());
			}
		}
	});
});