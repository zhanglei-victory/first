$().ready(function() {
	$("#userAddForm").validate({
		rules : {
			username : {
				required : true,
				maxlength : 20,
				remote: {
					type: "GET",
					url: path + "/user/userInfo/checkLoginUserName",
					cache: "false",
					data:{
						username :function(){return $("#username").val()}
					}
				}
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
			},
			userPwd : {
				required : true,
				maxlength: 20
			},
			confirmUserPwd : {
				required : true,
				equalTo: "#userPwd"
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				maxlength : "用户名不能大于20个字 符",
				remote:"此用户名已经被占用"
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
			},
			userPwd : {
				required : "请输入密码",
				maxlength: "密码长度不能超过20"
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