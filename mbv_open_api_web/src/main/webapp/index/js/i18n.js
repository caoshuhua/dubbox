var __FILE__ = $("script").last().attr("src");
//JS文件国际化支持
var i18nControl = {
	init : function() {
		var language = cookie.get("language");
		jQuery.i18n.properties({
			name : 'js_messages',
			path : __FILE__.substring(0, __FILE__.lastIndexOf("/") + 1),
			mode : 'map',
			language : language
		});
	}
};
var cookie = {
	get : function(objName) {// 获取指定名称的cookie的值
		var arrStr = document.cookie.split("; ");
		for (var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == objName)
				return unescape(temp[1]);
		}
		return null;
	}
};
$(function(){
	i18nControl.init();
});