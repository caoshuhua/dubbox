var listener = {
	sidebar: ".J_toTop",
	init: function() {
		if(VIPSHOP.isIE6){
			$(this.sidebar).addClass('hidden');
			return;
		}
		listener.controler();
		$(window).resize(function() {
			listener.controler();
		});
		$(this.sidebar).on("click", this, function() {
			$("html, body").stop(false, true).animate({
				scrollTop: 0
			}, 600);
		});
		$(window).on('scroll.totop', function() {
			listener.controler();
		});
	},
	controler: function() {

		var footerHeight = $('.footer').outerHeight(),
			fixedBottom = 180,
			windowHeight = $(window).height(),
			documentHeight = $(document).height(),
			windowScrollTop = $(window).scrollTop(),
			sidebarHeight = $(this.sidebar).outerHeight(),
			sidebarTop = documentHeight - footerHeight - sidebarHeight;
		// if ($(window).width() <= 1390)
		// 	$(this.sidebar).removeClass("sidebar_block").addClass(
		// 			"sidebar_position");
		// else
		// 	$(this.sidebar).removeClass("sidebar_position").addClass(
		// 			"sidebar_block");
		if (windowHeight < documentHeight && windowScrollTop) {
			$(this.sidebar).removeClass('hidden');
			if (documentHeight - windowScrollTop - windowHeight < footerHeight - fixedBottom) {
				$(this.sidebar).css({
					position: 'absolute',
					top: sidebarTop,
					bottom: "auto"
				});
			} else {
				$(this.sidebar).css({
					position: 'fixed',
					bottom: fixedBottom,
					top: "auto"
				});
			}
		} else {
			$(this.sidebar).addClass('hidden');
		}
	}
};
$(function() {
	listener.init();
});