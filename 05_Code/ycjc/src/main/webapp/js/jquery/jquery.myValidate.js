jQuery.validator.addMethod("ismobile", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	return (length == 11 && mobile.exec(value)) ? true : false;
}, "请正确填写您的手机号码");
//手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+d{8})$/
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");
//闸点验证
jQuery.validator.addMethod("selectwp", function(value, element) {
	if (value == "none")        // select默认值需要设置为"none"(当然可以自定义其他值）     
    {        
            return false;        
    }        
    else        
    {     
            return true;        
    }  
}, "请选择闸点");
//字符验证 
jQuery.validator.addMethod("stringCheck", function(value, element) {
	var str = /^[\s]{0,}$/
return this.optional(element) || !str.test(value); 
}, "请选择闸点"); 
// 邮编验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return (tel.exec(value)) ? true : false;
}, "请正确填写您的邮编");
//电话号码验证
jQuery.validator.addMethod("phone", function(value, element) {
	var tel = /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$/;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");
// 手机号或座机验证
jQuery.validator
		.addMethod(
				"telPhone",
				function(value, element) {
					//var telPhone = /^(^0\d{2}-?\d{8}$)|(^0\d{3}-?\d{7}$)|(^\(0\d{2}\)-?\d{8}$)|(^\(0\d{3}\)-?\d{7}$)$/;
					var telPhone = /^\d{3,4}-?\d{7,9}$/;
					var telMobile = /^1[3,5,8]\d{9}$/;
					return this.optional(element)
							|| (telPhone.test(value) && value.length == 12)
							|| (telMobile.test(value));
				},"手机号或者座机号格式错误");
// 身份证号码验证
jQuery.validator
		.addMethod(
				"idCard",
				function(value, element) {
					var card = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

					return this.optional(element) || (card.test(value));
				},"身份证号码错误");
//字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

//只是字母的验证
jQuery.validator.addMethod("letter", function(value, element) {
	var letter = /^([A-Z]+)$/;
	return this.optional(element) || (letter.test(value));
}, "只能输入数字和字母(字符A-Z)");

//闸点全屏的验证
jQuery.validator.addMethod("wpfullspell", function(value, element) {
	var wpfullspell =  /^([a-z]+)$/;
	return this.optional(element) || (wpfullspell.test(value));
}, "只能输入数字和字母(字符A-Z)");
// IP地址验证
jQuery.validator
		.addMethod(
				"ip",
				function(value, element) {
					var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
					return this.optional(element)
							|| (ip.test(value) && (RegExp.$1 < 256
									&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
				}, "Ip地址格式错误");
// 字节长度验证
jQuery.validator.addMethod("byteRangeLength",
		function(value, element, param) {
			var length = value.length;
			for (var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
		}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));