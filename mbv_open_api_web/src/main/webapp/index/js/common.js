var navControler = {
	init : function() {
		var url = location.pathname.toLowerCase();
		var index = $(".nav_index"), api = $(".nav_api"), help = $(".nav_support"), doc = $(".nav_doc"), manage = $(".nav_manage"), wiki_edit = $('.nav_edit'), wiki_new = $('.nav_new'), wiki_category = $(".nav_category");
		if (url.search("api") != -1) {
			api.addClass("curr");
		} else if (url.search("support") != -1 || url.search("search") != -1) {
			help.addClass("curr");
		} else if (url.search("doc") != -1) {
			doc.addClass("curr");
		} else if (url.search("member") != -1 || url.search("login") != -1 || url.search("application") != -1) {
			manage.addClass("curr");
		} else if (url.search('list') != -1 || url.search('toedit') != -1) {
			wiki_edit.addClass('curr');
		} else if (url.search('new') != -1) {
			wiki_new.addClass('curr');
		} else if (url.search('category') != -1) {
			wiki_category.addClass("curr");
		} else {
			index.addClass("curr");
		}
	}
};
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
var util = {
	getQueryString : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	getMethod : function() {
		var url = window.location.href;
		var beginPos = url.lastIndexOf('/') + 1;
		if(beginPos == url.length){
			url = url.substring(0, url.length - 1);
			beginPos = url.lastIndexOf('/') + 1;
		}
		var endPos = url.indexOf('?')>=0 ? url.indexOf('?'):url.length;
		return url.substring(beginPos, endPos);
	}
};
var hrefUtil = {
	init : function() {
		$('a').each(function() {
			var href = $(this).attr('href');
			if (href != '#' && href != null && href != '' && href.indexOf('javascript') == -1) {
				$(this).attr('href', '#');
				var qstr = href.split("?");
				var form = $("<form method='POST' action='" + qstr[0] + "'></form>");
				if (qstr.length > 1) {
					var param = qstr[1].split('&');
					$(param).each(function() {
						var kv = this.split("=");
						form.append($("<input name =" + kv[0] + " value=" + kv[1] + " />"));
					});
				}
				$(this).click(function() {
					$(document).append(form);
					form.submit();
				});
			}
		});
	}
};
var datepicker = {
	single : "#datepicker",
	range : "#J_date_range",
	init : function() {
		if ($(this.single).length > 0) {
			$(this.single).val(new Date().Format("yyyy-MM-dd "));
			$(this.single).datepicker();
		} else if ($(this.range).length > 0) {
			$("#startDate").datepicker({
				maxDate : "+0D",
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 1,
				onClose : function(selectedDate) {
					$("#endDate").datepicker("option", "minDate", selectedDate);
				}
			});
			$("#endDate").datepicker({
				maxDate : "+0D",
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 1,
				onClose : function(selectedDate) {
					$("#startDate").datepicker("option", "maxDate", selectedDate);
				}
			});
		}
	}
};
var ajaxMod = {
	getSearch : function(data) {
		var url = '/search/tips';
		var ajaxData = {};
		ajaxData.word = data;
		return $.get(url, ajaxData, null, 'json');
	}
};
var header = {
	init : function() {
		var that = header;
		that.move($('.J_head_nav .curr').parent(), true);
		$('.J_head_nav li:not(.J_bot)').on('mouseenter', function() {
			that.move($(this));
		});
		$('.J_head_nav').on('mouseleave', function() {
			that.move($('.J_head_nav .curr').parent());
		});
		$('.J_search_input').on('focus', function() {
			$('.J_search').addClass('focus');
			var val = $('.J_search_input').val();
			if (val != '') {
				that.search();
			}
		}).on('blur', function() {
			var JQsearch = $('.J_search');
			var val = $('.J_search_input').val();
			if (val != '') {
				JQsearch.addClass('active');
			} else {
				JQsearch.removeClass('active');
			}
			JQsearch.removeClass('focus');
		}).on('keyup', function() {
			that.search();
		});
		$('body').on('click', function(e) {
			e = e || window.event;
			var elem = e.srcElement || e.target;
			if ($(elem).closest('.J_search_list')[0] && elem.tagName == 'LI') {
				var word = $(elem).html();
				$('.J_search_list').hide();
				$('.J_search_input').val(word);
			} else if ($(elem).closest('.J_search')[0]) {
			} else {
				$('.J_search_list').hide();
			}
		}).on('submit', '.J_search', function() {
			var word = $('.J_search_input').val();
			if (word == '') {
				return false;
			}
		});
	},
	move : function($elem, init) {
		if (!$elem[0]) {
			return false;
		}
		var c_width = $elem.width();
		var c_left = $elem.offset().left, p_left = $elem.parent().offset().left;
		var _left = c_left - p_left;
		if (init) {
			$('.J_bot').css({
				left : _left,
				width : c_width
			});
			return this;
		}
		$('.J_bot').stop().animate({
			left : _left,
			width : c_width
		}, 100);
		return this;
	},
	delay : null,
	search : function() {
		var that = header;
		clearTimeout(that.delay);
		that.delay = setTimeout(function() {
			var word = $('.J_search_input').val();
			if (word == '') {
				$('.J_search_list').hide();
				return false;
			}
			ajaxMod.getSearch(word).done(function(data) {
				if (!data.data || data.data.length == 0) {
					$('.J_search_list').hide();
					return false;
				}
				var reformData = that.dataReform(data.data);
				$('.J_search_list').Template({
					templateElement : $('#searchList_tmp'),
					replace : true
				}).Template('process', {
					item : reformData
				}).show();
			});
		}, 200);
	},
	dataReform : function(data) {
		var newData = [];
		for (var i = 0, l = data.length; i < l; i++) {
			newData.push({
				'value' : data[i]
			});
		}
		return newData;
	}
};
$(function() {
	navControler.init();
	// hrefUtil.init();
	datepicker.init();
	header.init();
	$(".J_search_form").on("submit",function() {
		var word = $(".J_search_input").val();
		$(".J_keyword").val(encodeURIComponent(word));
	});
	$(".J_language").on("click", function() {
		$(".J_language_form").submit();
	});
});