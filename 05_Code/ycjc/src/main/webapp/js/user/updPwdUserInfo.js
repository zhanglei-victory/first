$().ready(function() {
	$("#userPwdUpdForm").validate({
		rules : {
			oldUserPwd : {
				required : true,
				maxlength: 10,
				remote:	{
					type: "GET",
					url: path + "/user/userInfo/checkOldPwd",
					cache: "false",
					data:{
						oldUserPwd :function(){return $("#oldUserPwd").val()}
					}
				}
			},
			userPwd : {
				required : true,
				maxlength: 10
			},
			confirmUserPwd : {
				required : true,
				equalTo: "#userPwd"
			}
		},
		messages : {
			oldUserPwd : {
				required : "旧密码不能为空",
				maxlength: "旧密码长度不能超过10",
				remote: "旧密码输入不正确"
			},
			userPwd : {
				required : "请输入密码",
				maxlength: "密码长度不能超过10"
			},
			confirmUserPwd : {
				required : "请输入确认密码",
				equalTo: "确认密码必须与密码一致"
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