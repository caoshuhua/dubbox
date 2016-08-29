$(function(){
	/*页头*/
	$("#toplinks li,.p-icon").hover(function(){
		$(this).find("div").show();
		},function(){
		$(this).find("div").hide();
			})
	$(".searchInput").focus(function(){
		$(this).addClass("searchDark");
		if($(this).val()=="请输入您要搜索的内容..."){
			$(this).val("");
			return false;
			}		
		}).blur(function(){
		if($(this).val()==""){
			$(this).val("请输入您要搜索的内容...")
			.removeClass("searchDark");
			return false;
			}
		})
	
	/*右侧*/
	$(".rightArea:even").addClass("grayBg")
	$(".josList h3").click(function(){
		var nextItem=$(this).next("div,ul");
		if(nextItem.is(":visible")){
			nextItem.hide();			
		}else{
			nextItem.show();
			
			var codeJsonHeight=$("#txt")[0].scrollHeight;
			var codeXmlHeight=$("#xml")[0].scrollHeight;
			document.getElementById("txt").style.height = codeJsonHeight + "px";
			document.getElementById("xml").style.height = codeXmlHeight +"px";
		}	
	});
	
	/*$(".articleCon").find(".josTable tr:even").addClass("grayBlueBg");*/
/*********************************************************首页样式*************************************************************/	
	/*文档左导航*/
/*	$(".docLeft dl:not(:first)").find("dd").hide()
	$(".docLeft dt:not('.resultNav dt')").click(function(){
		var _this=$(this);
		if(_this.hasClass("current")){
			_this.removeClass("current").parent().find("dd").hide();
			}
			else{
		_this.addClass("current")
		.parent().find("dd").show();
		_this.parent().siblings().find("dt").removeClass("current")
		_this.parent().siblings().find("dd").hide();
			}
		})
*/
 //$(".docLeft > ul > li").eq(1).find("ul").show().end().siblings().find("ul").hide(); 
	
 //$(".docLeft > ul > li:not(:first)").find("ul").hide(); //默认展示第一个
 $(".docLeft > ul > li").click(function(event){
	var _this=$(this);
	if (_this.hasClass("selected")){
		_this.find("p").removeClass("current")
		_this.removeClass("selected").find(".level2").hide().removeClass("hasSub");
	 }else{
		 _this.find("p").addClass("current")
		 _this.addClass("selected").find(".level2").show().addClass("hasSub");	
		 _this.parent().siblings('ul').find('li').removeClass("selected").find(".level2").hide().removeClass("hasSub");
	 }   
  })
  
  
  $(".level2 > li").bind("click",function(event){
	 _this=$(this);
	 if (_this.hasClass("showSub")){
	 _this.removeClass("showSub").find(".level3").hide();
	 _this.removeClass("hasSub");
	 event.stopPropagation();
	 	}
		else{
		_this.addClass("showSub").find(".level3").show();
		_this.addClass("hasSub");
		event.stopPropagation();
			}
	 })
	 
	 
	$(".level3 > li").bind("click",function(event){
		_this=$(this);
		if(_this.hasClass("current")){
			_this.removeClass("current")
			}
		else{
			$(this).addClass("current");
			}
		event.stopPropagation();
	})
  
	/**************************************************************************************/

	/*热门问题去掉第一行的线*/
	$(".faqBox").find(".faqItem:first").css("border-top","none");
	
	/*内容页右导航*/
	$(".rightNav .icon").toggle(function(){
		$(".sideCategory").hide();
		},function(){
		$(".sideCategory").show();
			})
	
			$(".toolC:first").find("h4").addClass("open");
	$(".toolC:not(:first)").find(".codeBox").hide();
	$(".toolC").find("h4").click(function(){
	if($(this).hasClass("open")){
	$(this).removeClass("open");
	$(this).parent().find(".codeBox").hide();
	}else{
	$(this).addClass("open");
	$(this).parent().find(".codeBox").show();
	};
	})

		
			
			
})
//BACK TO TOP
function goTopEx(){
  var obj=document.getElementById("BackTop");
  var btn=document.getElementById("backBtn");
  function getScrollTop(){
    return document.documentElement.scrollTop || document.body.scrollTop;
  }
  function setScrollTop(value){
    document.documentElement.scrollTop=value;
    document.body.scrollTop = value;
  }
  window.onscroll=function(){getScrollTop()>0?obj.style.display="":obj.style.display="none";}
  btn.onclick=function(){
    var goTop=setInterval(scrollMove,10);
    function scrollMove(){
      setScrollTop(getScrollTop()/1.2);
      if(getScrollTop()<1)clearInterval(goTop);
    }
  }
}
//json格式化
function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}
