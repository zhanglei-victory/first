$().ready(function() {
	$("#roleUpdForm").validate({
		rules : {
			rolename : {
				required : true,
				maxlength : 10
			},
			authids : {
				required : true
			}
		},
		messages : {
			rolename : {
				required : "请输入角色名称",
				maxlength : "角色名称不能超过10个字符"
			},
			authids : {
				required : "必须选择一个权限"
			}
		},
		submitHandler:function(form){
			//alert("submitted"); 
			//获取树对象中选中的节点
			var nodes = zTreeObj.getCheckedNodes();

			var s = '';//选中节点ids
			for (var i = 0;i < nodes.length;i++) {
				if (s != '') {
					s = s + ',';
				}
				s = s + nodes[i].id;  
			}//alert(s);
			$("#authids").val(s);
			//alert(zTreeObj.getCheckedNodes()[2].id+","+zTreeObj.getCheckedNodes()[2].pId);
			form.submit(); 
		},
		errorPlacement: function(error, element) {
			// 错误标签的父节点的下一个节点
			if(element.parent().next().attr("class") == "msg") {//alert(element.parent().next().html());
				element.parent().next().html("");
				error.appendTo(element.parent().next());
			} else {
				// 错误标签的第一父节点td
				if (element.parent().parent().parent().next().attr("class") == "msg") {
					element.parent().parent().parent().next().html("");
					error.appendTo(element.parent().parent().parent().next());
				}
				//error.appendTo(element.next().next());
			}
		}
	});
});