var setting = {
		async : {
			enable : true, // ���� zTree�Ƿ����첽����ģʽ
			url : "treedata.jsp", // Ajax ��ȡ���ݵ� URL ��ַ
			autoParam : [ "id" ]	// �첽����ʱ�Զ��ύ���ڵ����ԵĲ���,���踸�ڵ� node = {id:1, name:"test"}���첽����ʱ���ύ���� zId=1
		},
		data:{ // ����ʹ��data
 			simpleData : {
				enable : true,
				idKey : "id", // id������� Ĭ��
				pIdKey : "pId", // ��id������� Ĭ�� 
				rootPId : 0	// �����������ڵ㸸�ڵ����ݣ��� pIdKey ָ��������ֵ
			}
		},
		 
		view: {
		fontCss: setFontCss,
		 
	 
		},
		// �ص�����
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				// �ж��Ƿ񸸽ڵ�
				if(!treeNode.isParent){
					alert("treeId�Զ���ţ�" + treeNode.tId + ", �ڵ�id�ǣ�" + treeNode.id + ", �ڵ��ı��ǣ�" + treeNode.name);	
				}
			},
			//�����첽���س����쳣������¼��ص����� �� �ɹ��Ļص�����
			onAsyncError : zTreeOnAsyncError,
			onAsyncSuccess : function(event, treeId, treeNode, msg){
				
			}
		}
	};

	// ���ش�����ʾ
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		alert("���ش���" + XMLHttpRequest);
	};

	// ���˺���
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	//�����������ɫ
	
	function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {color:"blue"} : {color:"green"};
	};
 
	
	
	// ��Ⱦ
	$(document).ready(function() {
		 
		$.fn.zTree.init($("#treeDemo"), setting);
	});