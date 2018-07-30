$().ready(function() {
	$("#settingmail").validate({

		onsubmit : true,// 是否在提交是验证
		rules : {

			server : {
				required : true,
				maxlength : 10
			},
			port : {
				required : true,
				digits : true
			},
			mailusername : {
				required : true,
				chrnum : true
			},
			mailuserpassword : {
				required : true,
			// maxlength : 10
			}
		},
		messages : {
			server : {
				required : "选择邮件服务器地址",
				maxlength : "不能大于10个字 符"
			},
			port : {
				required : "请输入端口号",
				digits : "端口号必须为数字"
			},
			mailusername : {
				required : "请输入邮箱",
				chrnum : "只能输入数字和字母(字符A-Z, a-z, 0-9)"
			},
			mailuserpassword : {
				required : "输入密码",

			}
		},
		// //////////

		// ////////////
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
		},

		submitHandler : function(form) { // 通过之后回调
			var param = $("#settingmail").serialize();
			$.ajax({
				url : path + "/watersetting/set",
				type : "post",
				dataType : "json",
				data : param,
				success : function(result) {
					$("#errorMessage").html("1");
					//alert(result);
					if (result == 'success') {	
					//	alert("success");
						$("#errorMessage").html( "2");
						//location.href = 'allRequisitionList.action';
					} else {
						$("#errorMessage").html("3");
						var jsonObj = eval('(' + result + ')');
						//alert(jsonObj);
					}
				}
			});
		},
		invalidHandler : function(form, validator) { // 不通过回调
			$("#errorMessage").html("4");
			return false;
		}

	});
});