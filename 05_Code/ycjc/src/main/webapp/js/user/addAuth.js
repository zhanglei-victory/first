$().ready(function() {
	$("#authAddForm").validate({
		rules : {
			authname : {
				required : true,
				maxlength : 10
			}
		},
		messages : {
			authname : {
				required : "请输入权限名称",
				maxlength : "权限名称不能超过10个字符"
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