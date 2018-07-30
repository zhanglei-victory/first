$().ready(function() {
	$("#userUpdForm").validate({
		rules : {
			username : {
				required : true,
				maxlength : 20
			},
			userposition : {
				required : true,
				maxlength : 20
			},
			userRealName : {
				required : true,
				maxlength : 10
			},
			userphone: {
				required : true,
				telPhone:true
			},
			userroleid : {
				required : true
			},
			userstrucid : {
				required : true
			},
			userEmail : {
				email : true
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				maxlength : "用户名不能大于20个字 符"
			},
			userposition : {
				required : "请输入职务",
				maxlength : "职务不能大于20个字 符"
			},
			userRealName : {
				required : "请输入真实名字",
				maxlength : "真实名字不能大于10个字 符"
			},
			userphone: {
				required : "必须输入联系方式",
				telPhone:"联系方式格式不正确"
			},
			userroleid : {
				required : "请选择角色"
			},
			userstrucid : {
				required : "请选择组织机构"
			},
			userEmail : {
				email : "请输入正确的电子邮箱格式"
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