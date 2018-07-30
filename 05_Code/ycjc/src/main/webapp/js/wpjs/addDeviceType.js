$().ready(function() {
	$("#adddevicetype").validate({
		rules : {
			 
			devtypeName : {
				required : true,
				maxlength : 10
			} 
		},
		messages : { 
			devtypeName : {
				required : "输入传感器名称",
				maxlength : "传感器名称不能大于10个字 符"
			} 
		},
		errorPlacement : function(error, element) {
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