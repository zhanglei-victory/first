var setting = {
		async : {
			enable : true, // 设置 zTree是否开启异步加载模式
			url : "treedata.jsp", // Ajax 获取数据的 URL 地址
			autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
		},
		data:{ // 必须使用data
 			simpleData : {
				enable : true,
				idKey : "id", // id编号命名 默认
				pIdKey : "pId", // 父id编号命名 默认 
				rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
			}
		},
		 
		view: {
		fontCss: setFontCss,
		 
	 
		},
		// 回调函数
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				// 判断是否父节点
				if(!treeNode.isParent){
					alert("treeId自动编号：" + treeNode.tId + ", 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);	
				}
			},
			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
			onAsyncError : zTreeOnAsyncError,
			onAsyncSuccess : function(event, treeId, treeNode, msg){
				
			}
		}
	};

	// 加载错误提示
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		alert("加载错误：" + XMLHttpRequest);
	};

	// 过滤函数
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	//设置字体的颜色
	
	function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {color:"blue"} : {color:"green"};
	};
 
	
	
	// 渲染
	$(document).ready(function() {
		 
		$.fn.zTree.init($("#treeDemo"), setting);
	});