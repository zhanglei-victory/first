$().ready(function() {
	$("#userSettingForm").validate({
		rules : {
			userposition : {
				required: true,
				maxlength : 10
			},
			userRealName : {
				required : true,
				maxlength : 10
			},
			userphone: {
				required : true,
				telPhone:true
			},
			userEmail : {
				email : true
			},
			userroleid: {
				required : true
			},
			userSex: {
				required : true
			},
			userstrucid: {
				required : true
			}
		},
		messages : {
			userposition : {
				required : "请输入职务",
				maxlength : "职务不能大于10个字 符"
			},
			userRealName : {
				required : "请输入真实名字",
				maxlength : "真实名字不能大于10个字 符"
			},
			userphone: {
				required : "必须输入联系方式",
				telPhone:"联系方式格式不正确"
			},
			userEmail : {
				email : "请输入正确的电子邮箱格式"
			},
			userroleid: {
				required : "必须选择一个角色"
			},
			userSex: {
				required : "必须选择性别"
			},
			userstrucid: {
				required : "必须选择一个组织机构"
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