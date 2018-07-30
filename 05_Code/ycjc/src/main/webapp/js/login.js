$().ready(function() {
	$("#loginForm").validate({
		rules : {
			username : {
				required : true,
				maxlength : 20
			},
			userPwd : {
				required : true,
				maxlength : 20
			},
			validateCode:{
				required : true,
				maxlength : 4,
				remote:	{
					type: "GET",
					url: path + "/checkValidateCode",
					data:{
						validateCode :function(){return $("#validateCode").val()}
					}
				}
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				maxlength : "用户名不能超过20个字符"
			},
			userPwd : {
				required : "请输入密码",
				maxlength : "密码长度不能超过20个字符"
			},
			validateCode:{
				required : "请输入验证码",
				maxlength : "验证码长度不能超过4个字符",
				remote:"验证码输入不正确"
			}
		},
		errorPlacement: function(error, element) {
			if(element.parent().next().attr("class") == "msg") {//alert("999");
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