/**
 * 核心组件库
 * @module VIPSHOP
 * @author eason<eason.chen@vipshop.com>
 * @version 2.5.4.20131214
 */
if (typeof VIPSHOP == "undefined") {
    var VIPSHOP = {};
}

(function ($) {
    /**
     * 全站配置
     * @class VIPSHOP
     * @static
     */
    VIPSHOP.config = {
        pmsHost : 'http://pms.vip.com'
    };
    /**
     * 会员相关
     * @class VIPSHOP
     * @static
     */

    /*
     * 添加方法到类的原型链上
     * @method
     * @param {String} name 方法名
     * @param {Function} fn 对应的方法
     * @return {function}
     */
    Function.prototype.method = function(name, fn) {
        if (typeof this.prototype[name] == "undefined") {
            this.prototype[name] = fn;
        }
        return this;
    };
    /*
     * @desc 字符串的encode与decode
     * @method
     * @param {Boolean} noCom true为encodeURI或decodeURI, false为encodeURIComponent或decodeURIComponent
     * @return {String}
     */
    String.method('encode', function(noCom) {
        return noCom ? encodeURI(this) : encodeURIComponent(this);
    }).method('decode', function(noCom) {
        return noCom ? decodeURI(this) : decodeURIComponent(this);
    });

    /**
     * 判断是否为移动设备
     * @property isMobile
     * @type {Boolean}
     */
    VIPSHOP.isMobile = 'createTouch' in document && !('onmousemove' in document.documentElement) || /(iPhone|iPad|iPod)/i.test(navigator.userAgent);
    /**
     * 判断是否为IE6
     * @property isIE6
     * @type {Boolean}
     */
    VIPSHOP.isIE6 = navigator.appVersion.indexOf("MSIE 6") > -1;

    /**
     * 脚本错误报告，用于捕捉脚本出错或异常时报障
     * @method errorReport
     * @param {String} msg
     * @param {String} fileUrl
     * @param {Number} fileLine
     * @todo 若错误在JS文件中，则无法准确报错
     */ 
    VIPSHOP.errorReport = function(msg, fileUrl, fileLine) {
        var _ua = navigator.userAgent,                  //获取UA
             target, mouseX, mouseY, 
             docST = $(document).scrollTop(),           //获取页面滚动过的高度和宽度
             docSL = $(document).scrollLeft(),
             errorContent;

        //获取经过的dom和鼠标坐标 
        $(function () {
            $(document).mousemove(function (e) {
                target = e.target;
                mouseX = e.pageX;
                mouseY = e.pageY;
            });  
            setTimeout(function () {
                errorContent = {
                    _ua : _ua,
                    url : location.href,
                    fileUrl : fileUrl,
                    fileLine : fileLine,
                    msg : msg,
                    docST : docST,
                    docSL : docSL,
                    target : target,
                    mouseX : mouseX,
                    mouseY : mouseY
                };
                //$.ajax({}); ......
            }, 10);
        });
    };
    window.onerror = VIPSHOP.errorReport;
    /**
     * 返回一个唯一ID
     * @method guid
     * @param {String} pre 自定义前缀
     * @return {String} 唯一ID
     */
    VIPSHOP.guid = function (pre) {
        return (pre || 'VIPSHOP_') + (+new Date()) + (Math.random() + '').slice(-8);
    };
    /**
     * zIndex层级管理器
     * @method zIndexManager
     * @return {Number} 返回最大层级管理值
     */
    VIPSHOP.zIndexManager = function() {
        //检查是否存在VIPSHOP.maxIndex,如果存在则加1并返回,如果没则开始创建
        if (!VIPSHOP.maxIndex) {
            //遍历页面DOM元素,缓存zIndex队列
            var zIndexArr = $.map($('body > *'), function(i, n) {
                var obj = $(i),
                    zIndexVal;
                if (obj.css('position') != 'static') {
                    zIndexVal = obj.css('zIndex');
                    return zIndexVal == 'auto' ? 0 : zIndexVal - 0;
                }
            });
            if (zIndexArr.length == 0) { zIndexArr.push(1); }
            //取出最大值
            var MaxZ = Math.max.apply(null, zIndexArr);
            //for Firefox zIndex bug
            if (MaxZ >= 2147480000) {
                VIPSHOP.maxIndex = 2147480000;
                VIPSHOP.log('zIndex已经超过最大值');
            }
            else {
                VIPSHOP.maxIndex = MaxZ;
            }
        }
        return ++VIPSHOP.maxIndex;
    };
    /**
     * 声明一个命名空间
     * @method namespace
     * @param {String} str 对象名称
     * @return {object} 根据给出的字符串创建的对象
     * @example
     *  VIPSHOP.namespace("VIPSHOP.a.b"); //将会创建a和b两个命名空间,并返回最后的对象b <br />
     *  VIPSHOP.a.b.Drag=function(){}
     */
    VIPSHOP.namespace = function(str) {
        var arr = str.split("."),
            o = VIPSHOP;

        for (var i = (arr[0] == "VIPSHOP") ? 1 : 0; i < arr.length; i++) {
            o[arr[i]] = o[arr[i]] || {};
            o = o[arr[i]];
        }

        return o;
    };

    VIPSHOP.parentCls = function () {};
    VIPSHOP.parentCls.prototype = {
        get : function (args) {
            var prop = this[args];
            return $.isFunction(prop) ? prop.apply(this, Array.prototype.slice.call(args, 1)) : prop;
        }
    };

    /**
     * 兼容处理console.log
     * @method log
     * @param {String, Object, Boolean} msg
     * @chainable
     */
    VIPSHOP.log = function(msg) {
        if (window["console"]) {
            try {
                console.log.apply(console, arguments);
            }
            catch (e) {
                console.log(msg);
            }
        }

        return this;
    };
    /**
     * HTML标签自定义属性调用组件
     * @method JSS
     * @param {String, Object} jQueryObj
     * @chainable
     */
    VIPSHOP.JSS = function(jQueryObj){
        var targetDom = jQueryObj ? jQueryObj : $('.J_widget'),
            widget;

        targetDom.each(function () {
            var value, _eval, nsCrackArr, s,
                $data = {},
                dataRes = $(this).data();

            for (var i in dataRes) {
                value = dataRes[i];
                if (typeof value == 'string') {
                    nsCrackArr = value.split('.');
                    //转换 /^(?:\$.*|\{.*\}|\[.*\]|null|false|true|NaN)$/
                    //注意某字符和某函数名不能一样
                    if((/^(?:\$.*|\{.*\}|\[.*\]|null|false|true|NaN)$/.test(value) ||
                         +value + "" === value ||
                          window[nsCrackArr[0]])) {
                        _eval = true;
                    }
                    else {
                        _eval = false;
                    }

                    try {
                        if (!window[nsCrackArr[0]]) {
                            data = _eval ? eval("0,"+ value ) : value;    
                        }
                        else {
                            s = window;
                            for (var j = 0, len = nsCrackArr.length; j < len; j++) {
                                s = s[nsCrackArr[j]];
                            }
                            data = s;
                        }
                    }
                    catch(e) {
                        data = value;
                    }
                    $data[i] = data;
                }
            }

            if ((widget = $(this)[$data.widget])) {
                widget.call($(this), $data);
            }
        });

        return this;
    };
    $(function () {
        VIPSHOP.JSS();
    });

    /**
     * 转换query为JSON对象
     * @param  {[String]} url query字符串，如果不填，则默认取location.search
     * @return {[Object]}     转换后的对象
     */
    VIPSHOP.queryStringToJSON = function (url) {
        if (url === '')
            return '';
        var pairs = (url || location.search).replace(/^\?/,'').split('&');
        var result = {};
        for (var i = 0, len = pairs.length; i < len; i++) {
            var pair = pairs[i].split('=');
            if (!!pair[0]) {
                result[pair[0]] = decodeURIComponent(pair[1] || '');
            }
        }
        return result;
    };

    /**
     * 设置统计钩子
     * @method setSead
     * @param {Object} settings 自定义属性设定
     * @chainable
     * @example
     * <code>
     *  var settings = {
     *   target : '.s_info_story', //需要监控的dom节点
     *      attr : {   //设置dom节点属性
     *          'mars_sead' : 'm_shop_like_button',  
     *          'mars_sead2' : 'm_shop_like_button2'  
     *      },
     *      data : {  //设置dom节点的的data值, 如果是函数，则this指向到需要监控的dom节点
     *          'id' : function () {
     *            return $(this).parents('li').data('id');
     *          },
     *        'abc' : 123
     *      }
     * }
     * VIPSHOP.setSead(settings);
     * </code>
     */
    VIPSHOP.setSead = function (settings) {
        for (var cur = 0, len = settings.length; cur < len; cur++) {
            var target = $(settings[cur].target),
                attr = settings[cur].attr,
                data = settings[cur].data;

            target.each(function (i, n) {
                for (var k in attr) {
                    var attrVal = attr[k];
                    
                    if ($.type(attr[k]) === 'function') {
                        attrVal = attr[k].call($(n), i) || '';
                    }
                    $(n).attr(k, attrVal);
                }

                for (var j in data) {
                    var dataVal = data[j];
                    if ($.type(data[j]) === 'function') {
                        dataVal = data[j].call($(n), i) || '';
                    }
                    $(n).data(j, dataVal);
                }
            });
        }
        
        $.Listeners.pub('setSeadDone').success();

        return this;
    };

    (function() {
        /*
         * 分拆字符串，返回对象
         * @param {String} name
         * @return {Object}
         */
        var _crackName = function(name){
            var index = name.lastIndexOf("."), clsName, o, args;
            if (index != -1) {
                args = name.substring(0, index);
                o = VIPSHOP.namespace(args);
                clsName = name.substring(index + 1, name.length);
            } else {
                clsName = name;
                o = VIPSHOP;
            }
            return {
                clsName: clsName,
                o: o
            };
        };

        $.extend(VIPSHOP, {
            /**
             * 用于继承的静态方法
             * @method extend
             * @param {object} subCls 用于继承的子类
             * @param {object} superCls 被继承的父类
             * @return {object} subCls 返回子类
             */
            extend: function(subCls, superCls){
                if (!$.isFunction(subCls) ||
                 !$.isFunction(superCls)) {return false;}
                var F = function(){}, subClsProp;
                F.prototype = superCls.prototype;
                subClsProp = subCls.prototype = new F();
                subCls.prototype.constructor = subCls;
                subCls.superclass = superCls.prototype;
                return subCls;
            },
            /**
             * 定义组件的约定方法declare
             * @method declare
             * @param {Function} subCls 用于继承的子类
             * @param {Function} superCls 被继承的父类
             * @param {Object} 方法集
             * @return {function}
             */
            declare: function(subCls, superCls, prop){
                var o = _crackName(subCls);
                var subCls = o.o[o.clsName] = function(opts){
                    //合并配置选项到类
                    //如果有继承父类，则先执行父类的构造函数
                    if (superCls) {
                        if ($.isFunction(superCls) && superCls.prototype._init) {
                            superCls._init = superCls.prototype._init;
                        }
                        superCls._init && superCls._init.call(this, opts);
                    }

                    prop._init.call(this, opts);
                    if (opts && opts.plugins) {
                        for (var i = 0; i < opts.plugins.length; i++) {
                            this.usePlugin(opts.plugins[i]);
                        }
                    }
                };
                
                if (superCls) {
                    VIPSHOP.extend(subCls, superCls);
                }

                for (var key in prop) {
                    subCls.prototype[key] = prop[key];
                    if (typeof prop[key] == "function" && key != "_init") {
                        subCls.prototype[key].supername = key;
                    }
                }
                subCls.prototype._meta = {};
                subCls.prototype._meta.className = o.clsName;
                if (superCls) {
                    /**
                     * 调用父类的方法
                     *@param {array} 传入的参数
                     */
                    subCls.prototype.supermethod = function(args){
                        if (args.callee.supername) {
                            return subCls.superclass[args.callee.supername]();
                        }
                    };
                    //取得父类的方法
                    subCls.prototype.getSuperMethod = function(name){
                        if (subCls.superclass[name]) {
                            return subCls.superclass[name];
                        }
                    };
                }
                subCls.prototype._plugins = {};
                subCls.prototype.addPlugin = function(name, callback){
                    this._plugins[name] = callback;
                    if (callback.methods) {
                        $.extend(subCls.prototype, callback.methods);
                    }
                };
                subCls.prototype.usePlugin = function(name){
                    if (this._plugins[name] && typeof this._plugins[name] === "function") {
                        this._plugins[name]();
                    } else if (this._plugins[name]._init) {
                        this._plugins[name]._init.call(this);
                    } else {
                        VIPSHOP.log("还没有为 " + o.clsName + " 定义 " + name + " 插件");
                    }
                };
                //使用已经声明的对象
                subCls.prototype.useObject = function(name, opts){
                    var config;
                    if (typeof obj == "function") {
                        try {
                            var node = this.node;
                            if (this.node instanceof jQuery) {
                                node = (this.node)[0]
                            }
                            if (opts) {
                                config = $.extend({}, opts, {
                                    node: node
                                });
                            } else {
                                config = {
                                    node: node
                                }
                            }
                            new obj(config);
                        } catch (e) {
                            VIPSHOP.log(e, "貌似没有为其作为插件接口");
                        }
                    } else {
                        VIPSHOP.log(obj, "不存在这个构造函数！");
                    }
                };
            },
            /**
             * 组件所依赖的插件
             * @method plugin
             * @param {String} name 插件名
             * @param {Object} obj 对象
             * @param {Function} 回调方法
             * @return {function}
             */
            plugin: function(name, obj, callback){
                obj.prototype.addPlugin(name, callback);
            }
        });
    })();

    /**
     * 把组件添加到jQuery的fn上
     * @method bridgeTojQuery
     * @param {string} methodName 用于定义组件名字
     * @param {object} widget 对应的类
     * @return {function}
     */
    VIPSHOP.bridgeTojQuery = function(methodName, widget){
        var methodArray = $.trim(methodName).split(",");
        jQuery.each(methodArray, function(i, n){
            jQuery.fn[n] = function(config, args){
                var args = Array.prototype.slice.call(arguments, 1),
                    Obj,
                    R,
                    _widget,
                    method,
                    getWidgetData;

                //调用方法时,如果DOM对象之前有绑定相同组件,则销毁前一个
                var hasWidget = jQuery(this).data("widget_" + n);
                if(typeof config != 'string' && hasWidget){
                    hasWidget.destroy && hasWidget.destroy();
                    jQuery(this).removeData("widget_" + n);
                }
                if (config == "destroy") {
                    getWidgetData = jQuery(this).data('widget_' + n);
                    if (getWidgetData) {
                        getWidgetData.destroy && getWidgetData.destroy();
                        $(this).removeData("widget_" + n);
                        return;
                    } else {
                        return true;
                    }
                } else if (typeof config == 'string'){
                    if(config.indexOf('_') == 0){
                        VIPSHOP.log('不能调用私有方法');
                        return false;
                    }
                    _widget = $(this).data('widget_' + n);
                    method = _widget ? _widget[config] : null;
                    if(method){
                        R = method.apply(_widget, args);
                        if (R === _widget || typeof R == 'undefined') {
                            return this;
                        }
                        else {
                            return R;
                        }
                     }
                } else {
                    config = $.extend({}, config, {
                        node: this
                    });
                        Obj = new widget(config);
                    //增强链式调用
                    Obj[n] = function (func) {
                        var args = Array.prototype.slice.call(arguments, 1);
                        var R = Obj[func](args);
                        return R;
                    };
                    $(this).data('widget_' + n, Obj);
                    return this;
                }
            };
            $[n] = function(options){
                return new widget(options);
            };
        });
    };

    /*
     * @desc 兼容jQuery1.9.1的浏览器类型判断
     */
    if (!jQuery.browser) {
        var uaMatch = function(ua) {
                var ua = ua.toLowerCase();

                var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
                    /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
                    /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
                    /(msie) ([\w.]+)/.exec( ua ) ||
                    ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
                    [];

                return {
                    browser: match[ 1 ] || "",
                    version: match[ 2 ] || "0"
                };
            },
            matched = uaMatch( navigator.userAgent ),
            browser = {};

        if (matched.browser) {
            browser[ matched.browser ] = true;
            browser.version = matched.version;
        }

        // Chrome is Webkit, but Webkit is also Safari.
        if (browser.chrome) {
            browser.webkit = true;
        }
        else if (browser.webkit) {
            browser.safari = true;
        }

        jQuery.browser = browser;
    }
})(jQuery);
/**
 * @module CORE
 * @author eason<eason.chen@vipshop.com>
 * @version 1.0.0.20130717
 */

 /**
 * Loader组件<br />
 * <br />
 * 基于jQuery的loader组件，主要实现：<br />
 *  1、以包的方式批量、异步、并行加载css+html+json+js<br />
 *  2、批量加载css<br />
 *  3、批量加载js<br />
 *  4、存在相互依赖关系的队列式js加载<br />
 * @class CORE.LOADER
 */
jQuery.Loader = {
    /**
     * 以key > value方式保存等待加载的队列
     * @property _queue
     * @type {Array}
     * @default []
     */
    _queue : [],
    /**
     * 以key > value方式保存模块结构定义
     * @property _modules
     * @type {Array}
     * @default []
     */
    _modules : [],
    /**
     * 以key > value方式保存已加载模块
     * @property _modules
     * @type {Array}
     * @default []
     */
    _loaded : [],

    /**
     * 以pagelet的方式批量加载资源<br />
     * pagelet化的加载css及js，并在js内对html进行模版式渲染，最终填充内容到pid<br />
     *  加载顺序为css>json>html>js<br />
     *  json数据会存放在pid的data方法里<br />
     *  pid为空则默认为$('body')<br />
     * @method pagelet
     * @param {Object} pars
     * @chainable
     * @example
     * <code>
     * pagelet({
     *          name : 'jquery',        //string
     *          html : '',              //url
     *          css : [],               //array or string
     *          js : [],                //array or function
     *          json : [],              //array or object
     *          pid : ''                //string or jquery dom
     *      });
     * </code>
     */
    pagelet : function (pars) {
        //批量加载css文件
        if (typeof pars.css != 'undefined' && pars.css.length) {
            this.style(pars.css);
        }

        //获得目标容器
        if (typeof pars.pid != 'undefined' && pars.pid != '') {
            pars.pid = $(pars.pid);
        }
        else {
            pars.pid = $('body');
        }

        //批量加载数据文件
        var loadQueue = [];
        if (typeof pars.json != 'undefined') {
            if ($.isArray(pars.json)) {
                //数组的话则默认为url
                for (var i = 0, j = pars.json.length;i < j ;i++ ) {
                    //将数据存入到pars.pid的data里
                    loadQueue.push('$.getJSON("' + pars.json[i] + '", function (d) { $.extend(pars.pid.data(), d); })');
                }
            }
            else if (typeof pars.json == 'object') {
                //对象
                loadQueue.push('$.extend(pars.pid.data(), pars.json);');
            }
        }

        //加载html
        if (typeof pars.html != 'undefined' && pars.html != '') {
            loadQueue.push('pars.pid.load("' + pars.html + '")');
        }
        
        //执行队列
        var q = eval('$.when(' + loadQueue.join(',') + ')');
        q.done( function () {
            //事成后执行js代码
            if (typeof pars.js == 'function') { 
                pars.js();
            }
            else {
                $.Loader.script(pars.js);
            }
        } );

        return this;
    },

    /**
     * 加载css文件<br />
     * 支持单个或批量加载，支持链式调用
     * @method style
     * @param {Array|String} herf
     * @chainable
     * @example
     * <code>
     * Loader.style('');
     * Loader.style(['', '']);
     * </code>
     */
    style : function (herf) {
        if (typeof herf === 'string') {
            var styleTag = document.createElement('link');
            //herf = herf.indexOf('?') > 0 ? herf + '&t=' : herf + '?&t=';
            //herf+= (new Date()).getTime();
            styleTag.setAttribute('rel', 'stylesheet');
            styleTag.setAttribute('href', herf);
            $('head')[0].appendChild(styleTag);
        }
        else if (herf.length > 0) {
            for (var i = 0, j = herf.length;i < j;i++) {
                this.style(herf[i]);
            }
        }

        return this;
    },

    /**
     * 加载js文件<br />
     * 支持单个或批量加载，支持链式调用<br />
     * 这个是基础方法，不考虑依赖关系
     * @method script
     * @param {Array|String} src
     * @chainable
     * @example
     * <code>
     * Loader.script('');
     * Loader.script(['', '']);
     * </code>
     */
    script : function (src) {
        if (typeof src === 'string') {
            $.ajax({
                url: src,
                dataType: 'script',
                cache : true
            });
        }
        else if (src.length > 0) {
            for (var i = 0, j = src.length;i < j;i++) {
                this.script(src[i]);
            }
        }

        return this;
    },

    /**
     * 扩展加载js文件方法<br />
     * 以树的方式构建js文件间的依存关系<br />
     * 如设定requires，则存在依赖关系，会顺序执行，否则为并行<br />
     * 支持多重依赖关系
     * @method advScript
     * @param {Object} *
     * @chainable
     * @example
     * <code>
     * advScript({
     *          name : 'jquery',
     *          url : 'http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'
     *      }, {
     *          name : 'local',
     *          def : function () {
     *              $('#test').html($('#test').html() + 'local > ');
     *          }
     *      }, {
     *          name : 'index',
     *          def : function () {
     *              $('#test').html($('#test').html() + 'index > ');
     *          },
     *          requires : ['jquery', 'local']
     *      });
     * </code>
     */
    advScript : function () {
        for (var i = 0, j = arguments.length; i < j; i++) {
            var module = $.extend({}, arguments[i], { req : 0});

            //查找是否已加载
            if (this._loaded[module.name]) {
                continue;
            }
            this._modules[module.name] = module;

            //是否依赖其他模块
            if ($.isArray(module.requires) && module.requires.length > 0) {
                module.req = module.requires.length;
            }

            this._queue.push(module);
        }
        this._Execute();

        return this;
    },

    /*
        执行js模块加载队列

        @access private
    */
    _Execute : function () {
        if (this._queue.length <= 0) { return ; }

        var index = 0;
        //分析队列
        while (src = this._queue[index]) {
            //已加载
            if (this._loaded[src.name]) {
                this._queue.splice(index, 1);
                continue;
            }

            //是否存在依赖关系，并且依赖尚未加载
            if ($.isArray(src.requires) && src.requires.length > 0) {
                for (var i = 0, j = src.requires.length;i < j ;i++ ) {
                    if (this._loaded[src.requires[i]]) {
                        //依赖计数-1
                        src.req--;

                        //删除依赖
                        src.requires.splice(i, 1);

                        i--;
                    }
                }
            }

            if (src.req > 0) {
                //尚存依赖，跳过
                index++;
                continue;
            }

            if (src.url) {
                //外部脚本
                this._queue.splice(index, 1);

                $.ajax({
                    url: src.url,
                    dataType: 'script',
                    context : { name : src.name },
                    cache : true,
                    success: function(){
                        //标记模块已加载
                        $.Loader._loaded[this.name] = 1;

                        //回调，检查是否有存在依赖关系的模块
                        $.Loader._Execute();
                    }
                });
            }
            else {
                //自定义脚本
                src.def();

                this._loaded[src.name] = 1;
                this._queue.splice(index, 1);
            }
        }
    }
};
/*
 * stringifyJSON
 * http://github.com/flowersinthesand/stringifyJSON
 * 
 * Copyright 2011, Donghwan Kim 
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
// This plugin is heavily based on Douglas Crockford's reference implementation
(function() {
    
    "use strict";
    
    var escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, 
        meta = {
            '\b' : '\\b',
            '\t' : '\\t',
            '\n' : '\\n',
            '\f' : '\\f',
            '\r' : '\\r',
            '"' : '\\"',
            '\\' : '\\\\'
        };
    
    function quote(string) {
        return '"' + string.replace(escapable, function(a) {
            var c = meta[a];
            return typeof c === "string" ? c : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"';
    }
    
    function f(n) {
        return n < 10 ? "0" + n : n;
    }
    
    function str(key, holder) {
        var i, v, len, partial, value = holder[key], type = typeof value;
                
        if (value && typeof value === "object" && typeof value.toJSON === "function") {
            value = value.toJSON(key);
            type = typeof value;
        }

        switch (type) {
        case "string":
            return quote(value);
        case "number":
            return isFinite(value) ? String(value) : "null";
        case "boolean":
            return String(value);
        case "object":
            if (!value) {
                return "null";
            }
            
            switch (Object.prototype.toString.call(value)) {
            case "[object Date]":
                return isFinite(value.valueOf()) ? 
                    '"' + value.getUTCFullYear() + "-" + f(value.getUTCMonth() + 1) + "-" + f(value.getUTCDate()) + 
                    "T" + f(value.getUTCHours()) + ":" + f(value.getUTCMinutes()) + ":" + f(value.getUTCSeconds()) + "Z" + '"' : 
                    "null";
            case "[object Array]":
                len = value.length;
                partial = [];
                for (i = 0; i < len; i++) {
                    partial.push(str(i, value) || "null");
                }
                
                return "[" + partial.join(",") + "]";
            default:
                partial = [];
                for (i in value) {
                    if (Object.prototype.hasOwnProperty.call(value, i)) {
                        v = str(i, value);
                        if (v) {
                            partial.push(quote(i) + ":" + v);
                        }
                    }
                }
                
                return "{" + partial.join(",") + "}";
            }
        }
    }
    
    function stringifyJSON(value) {
        /*if (window.JSON && window.JSON.stringify) {
            return window.JSON.stringify(value);
        }*/
        
        return str("", {"": value});
    }
    
    // Expose stringifyJSON to the global object
    jQuery.stringifyJSON = stringifyJSON;
    
}());
/**
 * @module CORE
 * @author eason<eason.chen@vipshop.com>
 * @version 0.9.5.20121011
 */

/**
 * cookie组件
 * @class CORE.COOKIE
 */
jQuery.Cookie = {
    set : function(name, value, domain, path, hour) {
        if (hour) {
            var today = new Date();
            var expire = new Date();
            expire.setTime(today.getTime() + 3600000 * hour);
        }

        document.cookie = name + "=" + escape(value) + "; " + (hour ? ("expires=" + expire.toGMTString() + "; ") : "") + (path ? ("path=" + path + "; ") : "path=/; ") + (domain ? ("domain=" + domain + ";") : '.vip.com;');
        return true;
    },

    get : function(name) {
        var r = new RegExp("(?:^|;+|\\s+)" + name + "=([^;]*)");
        var m = document.cookie.match(r);
        return unescape(decodeURIComponent(!m ? "" : m[1]));
    },

    del : function(name, domain, path) {
        document.cookie = name + "=; expires=Mon, 26 Jul 1997 05:00:00 GMT; " + (path ? ("path=" + path + "; ") : "path=/; ") + (domain ? ("domain=" + domain + ";") : '.vip.com;');
    }
};

/**
 * @module CORE
 * @author eason<eason.chen@vipshop.com>
 * @version 1.0.0.20130717
 */

/**
 * 本地存储组件
 * @class CORE.STORAGE
 */
jQuery.Storage = (function () {
    var rule1 = !jQuery.browser.msie // 非IE
    var rule2 = jQuery.browser.msie && jQuery.browser.version - 0 >= 8 //IE版本大于等于8
    if (rule1 || rule2) {
        try {
            localStorage.setItem('localStorageTest', 1);
        } 
        catch (e) {
            return {
                set : $.noop,
                get : $.noop,
                remove : $.noop
            };
        }
        localStorage.removeItem('localStorageTest'); //删除测试
    }
    
    if (window.localStorage) {
        //suport localStorage
        return (function () {
            var method = {
                /**
                 * 保存<br />
                 * @method set
                 * @param {String} key
                 * @param {String|Object|Number} value
                 */
                set : function(key, value, expires) {
                    var v = [];
                    if (expires) {
                        var d = new Date().getTime();
                        v.push({'expires' : (d + expires*1000)});
                    }
                    v.push(value);
                    localStorage.setItem(key, $.stringifyJSON(v));
                },
                /**
                 * 获取<br />
                 * @method get
                 * @param {String} key
                 */
                get : function(key) {
                    var value = localStorage.getItem(key);
                    if (value == null || value == undefined) {
                        value = '';
                        return value;
                    }

                    try{value = $.parseJSON(value)}catch(e){};
                    if(typeof value != 'object') return value;

                    //兼容旧业务代码
                    if ($.type(value[0]) == 'string') {
                        return value[0];
                    }

                    //兼容旧业务代码
                    if ($.type(value) == 'object') {
                        return value;
                    }

                    var expires = value[0].expires;
                    if (expires && /^\d{13,}$/.test(expires)) {
                        var d = new Date().getTime();
                        if (expires <= d) {
                            localStorage.removeItem(key);
                            return '';
                        }
                        value.shift();
                    }
                    return value[0];
                },
                /**
                 * 移除<br />
                 * @method remove
                 * @param {String} key
                 */
                remove : function(key) {
                    localStorage.removeItem(key);
                }
            };

            //判断是否过期，如果过期则先删除
            var d = new Date().getTime();
            for(key in localStorage){
                var v = localStorage.getItem(key);
                try{v = $.parseJSON(v)}catch(e){};
                if(Object.prototype.toString.call(v).toLowerCase().indexOf('array') > 0){
                    var expires = v[0].expires;
                    if(expires && /^\d{13,}$/.test(expires) && expires <= d) localStorage.removeItem(key);
                }
            }

            return method;
        })();
    }
    else {
        //ie,userData
        return (function () {
            var storage = null;
            var hostName = 'vip.com';

            $(function () {
                try {
                    storage = document.createElement('INPUT');
                    storage.type = "hidden";
                    storage.style.display = "none";
                    storage.addBehavior ("#default#userData");
                    document.body.appendChild(storage);
                    var expires = new Date();
                    expires.setDate(expires.getDate() + 365);
                    storage.expires = expires.toUTCString();
                } catch(e) {
                    VIPSHOP.log(e);
                    return;
                }
            });

            return {
                set : function(key, value, expires) {
                    var v = [];
                    if (expires) {
                        var d = new Date().getTime();
                        v.push({'expires' : (d + expires*1000)});
                        /*d.setTime(d.getTime() + expires * 1000);
                        storage.expires = d.toUTCString();*/
                    }
                    v.push(value);
                    storage.load(hostName);
                    storage.setAttribute(key, $.stringifyJSON(v));
                    storage.save(hostName);
                },
                get : function(key) {
                    storage.load(hostName);
                    var value = storage.getAttribute(key);

                    if (value == null || value == undefined) {
                        value = '';
                        return value;
                    }

                    try{value = $.parseJSON(value)}catch(e){};

                    //兼容旧业务代码
                    if ($.type(value[0]) == 'string') {
                        return value[0];
                    }

                    //兼容旧业务代码
                    if ($.type(value) == 'object') {
                        return value;
                    }

                    if(typeof value != 'object') return value;
                    var expires = value[0].expires;
                    if (expires && /^\d{13,}$/.test(expires)) {
                        var d = new Date().getTime();

                        if (expires <= d) {
                            storage.load(hostName);
                            storage.removeAttribute(key);
                            storage.save(hostName);
                            return '';
                        }
                        value.shift();
                    }
                    return value[0];
                },
                remove : function(key) {
                    storage.load(hostName);
                    storage.removeAttribute(key);
                    storage.save(hostName);
                }
            };
        })();
    }
})();
/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.0.0.20130717
 */

/*
 * 主题类
 * @type {Class}
 * @param {String} 归属的主题名
 * @param {Object} 主题对象集合
 * @returns {Object} Subject Object
 */
function Subject (subName, subNameSpace, data) {
    this.subName = subName;
    this.subNameSpace = subNameSpace;

    this.allowSuccessFlag = true;
    this.allowErrorFlag = true;

    this.hasSuccess =  subNameSpace[subName]['hasSuccess'] || false;
    this.hasError = subNameSpace[subName]['hasError'] || false;
    this.callbacks = {
        'onsuccess' : new Callbacks(),
        'onerrors' : new Callbacks()
    };

    this.data = data || {};
}

Subject.prototype = {
    /*
     * 成功时执行的事件
     * @method
     * @access public
     * @param {Function} 订阅成功时执行的事件
     * @returns {Object} Subject Object
     */
    onsuccess : function(callbackFunc) {
        this._when('onsuccess', callbackFunc);
        return this;
    },
    /*
     * 错误时执行的事件
     * @method
     * @access public
     * @param {Function} 订阅错误时执行的事件
     * @returns {Object} Subject Object
     */
    onerror : function(callbackFunc) {
        this._when('onerrors', callbackFunc);
        return this;
    },
    /*
     * 触发事件
     * @method
     * @access public
     * @param {String} 要触发的事件类型
     */
    trigger : function (type, data) {
        var callbacks = this.callbacks,
            that = this;

        switch(type) {
        case 'onsuccess':
            status = 'hasSuccess';
            break;
        case 'onerrors':
            status = 'hasError';
            break;
        default:
            break;
        }

        that[status] = true;
        if (that[status] && this.allowSuccessFlag) {
            callbacks[type].fire(data);
        }
    },
    /*
     * 取消触发事件
     * @method
     * @access public
     * @param {String} 要触发的事件类型('success'或'error')
     */
    unsub : function (type) {
        switch (type) {
        case 'success':
            this.allowSuccessFlag = false;
            break;
        case 'error':
            this.allowErrorFlag = false;
            break;
        default:
            this.allowSuccessFlag = false;
            this.allowErrorFlag = false;
        }
    },
    /*
     * 取消触发事件
     * @method
     * @access private
     * @param {String} 要触发的事件类型('onsuccess'或'onerrors')
     * @param {Function} 要触发的事件
     */
    _when : function (type, callbackFunc) {
        var callbacks = this.callbacks,
            status,
            allow,
            that = this;
  
        switch (type) {
        case 'onsuccess':
            callbacks['onsuccess'].add(callbackFunc);
            status = 'hasSuccess';
            allow = 'allowSuccessFlag';
            break;
        case 'onerrors':
            callbacks['onerrors'].add(callbackFunc);
            status = 'hasError';
            allow = 'allowErrorFlag';
            break;
        default:
            break;
        }

        //检测订阅之前，是否有执行过
        var deps = that.subName.split(',');
        var doneCount = 0;
        var extData = {};
        for (var i = 0, len = deps.length; i < len; i++) {
            if (that.subNameSpace[deps[i]] && that.subNameSpace[deps[i]][status] == true) {
                extData = $.extend(extData, that.subNameSpace[deps[i]]['data'][type]);
                doneCount++;
            }
        }

        //如果已经主题发布，则立即执行
        if (doneCount == len && that[allow]) {
            callbackFunc(extData);
        }
    }
};

/*
 * 事件类(可惜jQuery里的$.callbacks没list返回, 在此实现简易callbacks)
 * @method
 * @access public
 */
function Callbacks() {
    this.list = [];
}

Callbacks.prototype = {
    /*
     * 添加事件到队列
     * @method
     * @access public
     * @param {Function} 要触发的事件
     */
    add : function(func) {
        var list = this.list;
        var type = $.isFunction(func);
        for (var i = 0, len = list.length + 1; i < len; i++) {
            if (type && func != list[i]) {
                list.push(func);
            }
        }
    },
    /*
     * 执行事件
     * @method
     * @access public
     */
    fire : function() {
        var list = this.list;
        var args = arguments;
        for (var i = 0, len = list.length; i < len; i++) {
            list[i].apply(null, args);
        }
    }
}

/**
 * 订阅与发布事件组件
 * @class CORE.LISTENERS
 */
$.Listeners = {
    /**
     * 组件版本号
     * @property version
     * @type {String}
     */
    version : '1.0.1.20130109',
    /**
     * 主题集合的命名空间
     * @property subNameSpace
     * @type {Object}
     * @default {}
     */
    subNameSpace : {},
    hasPubList : [],
    subNameList : [],

    /**
     * 订阅主题
     * @method sub
     * @param {String} subName 主题名
     * @return {Object} Subject Object
     */
    sub : function (subName) {
        if (typeof subName != 'string') { return ; }

        var subNameSpace = this.subNameSpace,
             _t = Math.floor(Math.random() * new Date().getTime() + 1),
             args = Array.prototype.slice.call(arguments),
             subjectObj;

        subName = args.join(',');
        if (!subNameSpace[subName]) {
            subNameSpace[subName] = {
                subNameList : {}
            };
        }

        subjectObj = new Subject(subName, subNameSpace, subNameSpace[subName].data);
        subjectObj['_t'] = _t;
        subNameSpace[subName]['subNameList']['subjectObj_' + _t] = subjectObj;

        return subjectObj;
    },
    /*
     * 兼容处理
     * @method
     * @access private
     * @param {String} subName 主题名
     * @param {String} type 事件类型('onsuccess'或'onerros')
     */
    _facade : function(subName, type, data) {
        var subNameSpace = this.subNameSpace,
            hasState;

        switch (type) {
        case 'onsuccess':
            hasState = 'hasSuccess';
            break;
        case 'onerrors':
            hasState = 'hasError';
            break;
        default:
            break;
        }

        !subNameSpace[subName]['data'] && (subNameSpace[subName]['data'] = {});
        subNameSpace[subName]['data'][type] = $.extend({}, subNameSpace[subName]['data'][type], data);

        //标记已发布状态
        subNameSpace[subName][hasState] = true;

        //加入已发布队列
        if ($.inArray(subName, this.hasPubList) == -1) {
            this.hasPubList.push(subName);
        }

        //[subName]['subNameList']
        for (var i in subNameSpace) {
            var arr = i.split(',');
            var len = count = arr.length;
            var extData = {};

            if ($.inArray(subName, arr) != -1) {
                for (var j = 0; j < len; j++) {
                    if ($.inArray(arr[j], this.hasPubList) != -1) {
                        extData = $.extend(extData, subNameSpace[arr[j]]['data'][type]);
                        count--;
                    }
                }
                
                if (count == 0) {
                    for (var k in subNameSpace[i]['subNameList']) {
                        subNameSpace[i]['subNameList'][k].trigger(type, extData);       
                    }
                }
            }
        }
    },
    /**
     * 发布事件
     * @method pub
     * @param {String} subName 主题名
     * @return {Object} 返回状态的操作方法集
     */
    pub : function (subName)  {
        var subNameSpace = this.subNameSpace,
            callbackType,
            args = Array.prototype.slice.call(arguments),
            that = this;

        for (var i = 0, len = args.length; i < len; i++) {
            subName = args[i];
            if (!subNameSpace[subName]) {
                //若主题不存在，则创建;
                subNameSpace[subName] = {
                    subNameList : {}
                };
            }
        }

        return {
            success : function (data) {
                for (var i = 0, len = args.length; i < len; i++) {
                    that._facade(args[i], 'onsuccess', data);
                }
                return this;
            },
            error : function (data) {
                for (var i = 0, len = args.length; i < len; i++) {
                    that._facade(args[i], 'onerrors', data);    
                }
                return this;
            }
        };
    },
    /**
     * 重置主题
     * @method reset
     * @param {String} subName 主题名
     * @return {Object} 返回状态的操作方法集
     */
    reset : function (subName) {
        var hasPubList = this.hasPubList;
        var subNameSpace = this.subNameSpace;
        var index = $.inArray(subName, hasPubList);

        //删除已经发布列表中的对应主题名，并且删除
        if (index != -1) {
            hasPubList.splice(index, 1);
            delete subNameSpace[subName];

            for (var i in subNameSpace) {
                var tempArr = i.split(',');
                if ($.inArray(subName, tempArr) != -1) {
                    delete subNameSpace[i]['data'];
                }
            }
        }
        return this;
    },
    /**
     * 取消发布事件
     * @method unsub
     * @param {String} subName 主题名
     * @return {Object} 返回状态的操作方法集
     */
    unsub : function (subName) {
        var subNameSpace = this.subNameSpace,
            facade;

        if (subNameSpace[subName]) {
            facade = function (type, key) {
                if (key) {
                    var _t = key._t;
                    var subjectObj = subNameSpace[subName]['subNameList']['subjectObj_' + _t];
                    subjectObj.unsub(type);
                }
                else {
                    for (var i in subNameSpace[subName]['subNameList']) {
                        subNameSpace[subName]['subNameList'][i].unsub(type);
                    }
                }
            };

            return {
                success : function (key) {
                    facade('success', key);
                    return this;
                },
                error : function (key) {
                    facade('error', key);
                    return this;
                },
                all : function () {
                    facade();
                    return this;
                }
            };
        }
    },
    /**
     * debug专用
     * @method show
     * @param {String} subName 主题名
     * @returns {Object} 返回状态的操作方法集
     */
    show : function (subName) {
        var _cacheObj = {}. cacheObj;
        var subNameSpace = this.subNameSpace;

        function CreateCacheObj(subName) {
            var i, conditionStart = '', conditionEnd = '';
            if (subName) {
                i = subName;
                if (!subNameSpace[subName]) {
                    console.log('can not find the subject!');
                    return;
                }
            }
            else {
                conditionStart = 'for (var i in subNameSpace) {';
                conditionEnd = '}';
            }

            var property = '_cacheObj[i] = {};'
                        + '_cacheObj[i]["onsuccess"] = [];'
                        + '_cacheObj[i]["onerrors"] = [];'
                        + 'for (var j in subNameSpace[i]["subNameList"]) {'
                        + '_cacheObj[i]["onsuccess"] = _cacheObj[i]["onsuccess"].concat(subNameSpace[i]["subNameList"][j]["callbacks"]["onsuccess"]["list"]);'
                        + '_cacheObj[i]["onerrors"] = _cacheObj[i]["onerrors"].concat(subNameSpace[i]["subNameList"][j]["callbacks"]["onerrors"]["list"]);'
                        + '}';
            returns = 'return _cacheObj';

            var func = new Function('subNameSpace', '_cacheObj', 'i', conditionStart + property + conditionEnd + returns);
            _cacheObj = func(subNameSpace, {}, i);
            
            return _cacheObj;
        }

        cacheObj = CreateCacheObj(subName);
        return  {
            success : function () {
                if (subName) {
                    console.log(cacheObj[subName]['onsuccess']);
                }
                else {
                    console.log(cacheObj);
                }
            },
            error : function () {
                if (subName) {
                    console.log(cacheObj[subName]['onerrors']);
                }
                else {
                    console.log(cacheObj);
                }
            },
            all : function () {
                console.log(cacheObj);
            }
        };
    }
};

/**
 * @author eason<eason.chen@vipshop.com>
 * @version 1.0.2.20140423
 **/

/**
 * 会员相关
 * @class VIPSHOP.member
 */
(function ($) {
    VIPSHOP.member = {
        /**
         * 用户信息
         * @type {Object}
         * @default null
         * @property info
         */
        info : null,

        /**
         * 检查会员身份状态
         * @method chk
         * @static
         */
        chk : function () {
            var loginID = $.Cookie.get('VipLID'),
                account = $.Cookie.get('login_username');

            if (loginID) {
                //验证成功，已登录
                var afterTxt = $('#J_header_logAfter').html();
                var userName = $.Cookie.get('VipRNAME');

                $('#J_head_log')
                    .addClass('login_after')
                    .removeClass('login_before')
                    .html( afterTxt.replace('{$J_header_account}', fn_cutString(userName, 9)));

                $('#J_header_lnkLogOut').on('click', function () {
                    location.href = VIPSHOP.userHost +'/logout?src='+ encodeURIComponent(window.location.href);
                });

				this.info = {
					'account' : account,
					'level' : parseInt($.Cookie.get('VipMonopoly')),
                    'nickname' : userName
				};

                $.Listeners.pub('loginSuccess').success();

                return ;
            }
            
            //未登陆
            $('#J_head_log')
                .addClass('login_before')
                .removeClass('login_after')
                .html($('#J_header_logBefor').html());

            $('#J_header_lnkLogin').on('click', function () {
                location.href = VIPSHOP.userHost +'/login?src='+ encodeURIComponent(window.location.href);
            });
            $('#J_header_lnkRegister').on('click', function () {
                location.href = VIPSHOP.userHost +'/register?src='+ encodeURIComponent(window.location.href);
            });

            return ;
        },

        /**
         * 设置最近浏览记录
         * @method viewed
         * @static
         * @param {String} cookie_name cookie名
         * @param {Number} merchandise_id 商品id
         * @deprecated 1.0
         */
        viewed : function (cookie_name, merchandise_id) {
            //获取已浏览
            var viewed = $.Cookie.get(cookie_name);
            if (viewed == '') {
                var arViewed = [];
            }
            else {
                var arViewed = viewed.split(',');
            }

            //id填充
            var viewed = -1;
            for (var i = 0;i < arViewed.length;i++ ) {
                if (arViewed[i] == merchandise_id) { viewed = i; }
            }
            if (viewed == -1 && arViewed.length >= 5) {
                arViewed.shift();
            }
            else if (viewed > -1) {
                arViewed.splice(viewed, 1);
            }
            arViewed.push(merchandise_id);

            //获取根域
            var domain = (document.domain).split('.');
            domain.shift();
            var rootDomain = domain.join('.');

            $.Cookie.set(cookie_name, arViewed.join(','), rootDomain);
        },

        /**
         * 设置最近浏览记录
         * @method setViewed
         * @static
         * @param {String} channel 频道名称
         * @param {Object} merchandise 商品信息
         * @param {Number} [length] 商品信息数组长度
         */
        setViewed : function (channel, merchandise) {
            //获取数据
            var data = $.Storage.get(channel),
                arrMer = !!data ? data : [],
                newMer = [];

            if (arrMer) {
                //设定长度
                var arrLength = arguments.length > 2 ? arguments[2] : 5,
                    forLength = arrLength;
                
                if (arrMer.length < arrLength) {
                    forLength = arrMer.length;
                }

                //过滤之前浏览过
                for (var i = 0;i < forLength;i++ ) {
                    if (arrMer[i].id != merchandise.id) {
                        newMer.push(arrMer[i]);
                    }
                }

                //先进先出
                if (newMer.length >= arrLength) {
                    newMer.shift();
                }
            }

            //转换数组
            newMer.push(merchandise);
            $.Storage.set(channel, newMer);
        },

        /**
         * 获取最近浏览记录
         * @method getViewed
         * @static
         * @param {String} channel 频道名称
         * @return {Array}
         */
        getViewed : function (channel) {
            var data = $.Storage.get(channel);
            return !!data ? data : [];
        }
    };
})(jQuery);/**
 * @author zhenbo.zheng<zhenbo.zheng.chen@vipshop.com>
 * @version 1.0.2.20140423
 **/

(function ($) {
    /**
     * 登录弹窗
     * @method loginDialog
     * @static
     * @param {String} channel 频道名称
     * @return {Array}
     */
    VIPSHOP.login = function (options) {
        this.msgPlugin = VIPSHOP.staticJs + '/plugins/messenger.js';
        this.loginFrameUrl = 'https://passport.vip.com/login?gotype=2';
        this.dialogIsFixed = false;
        // 扩展配置
        $.extend(this, options);
        this._init();
    }
    $.extend(VIPSHOP.login.prototype, {
        /**
         * 设置dialog高度
         * @param {[type]} height [description]
         */
        setDialogHeight : function (height) {
            var that = this;
            that.loginDialog
                .size(430, height)
                .getElem('.login_iframe')
                .attr('height', height);
        },
        /**
         * 关闭dialog
         * @return {[type]} [description]
         */
        closeDialog : function () {
            var that = this;
            that.loginDialog.close();
        },
        /**
         * 登录成功
         * @return {[type]} [description]
         */
        loginSuccess : function () {
            var that = this;
            that.closeDialog.call(that);
            that.loginEvent.call(that);
        },
        /**
         * [loginEvent description]
         * @type {[type]}
         */
        loginEvent : $.noop,
        /**
         * 初始化
         * @return {[type]} [description]
         */
        _init : function () {
            var that = this;
            //加载messenger插件
            $.ajax({
                url: that.msgPlugin,
                dataType: 'script',
                cache : true,
                success : function () {
                    //执行回调
                    that.openDialog();
                }
            });
        },
        /**
         * 打开登录弹窗
         * @return {[type]} [description]
         */
        openDialog : function () {
            var that = this;
            var iframeHtml = '<div>'+
                                '<iframe class="login_iframe" frameborder="0" width="430" height="610" src="' + that.loginFrameUrl + '">' +
                             '</div>';
            //登录弹窗
            var loginDialog = $.Dialog({
                model : true,
                elStyle : 'login_dialog',
                opacity : 0.3,
                size : [430, 610],
                content : iframeHtml,
                isFixed : that.dialogIsFixed,
                showEvent : function () {
                    //跨域
                    var messenger = new Messenger('loginDialog', 'vip.com');
                    //监听事件
                    messenger.listen(function (msg) {
                        VIPSHOP.log(msg);
                        var queryObj = VIPSHOP.queryStringToJSON(msg);
                        var method = queryObj['method'];
                        var args = queryObj.args;
                        that[method].call(that, args);
                    });
                }
            }).open();
            that.loginDialog = loginDialog;
        }
    });
    /**
     * 登录弹窗
     * @param  {[type]} options [配置参数]
     * @return {[type]}         [loginDialog对象]
     */
    VIPSHOP.login.init = function (options) {
        return new VIPSHOP.login(options);
    }

})(jQuery);
/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.2.1.20130717
 */

 /**
 * 表单异步组件
 * @class CORE.FORM
 * @constructor
 */
(function($){
    VIPSHOP.declare('Form', null, {
        /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  trigger : '',
         *  timeout : 5000,
         *  errorF : function () {},
         *  submitBefor : function () { return true; },
         *  callbackAfter : function () {}
         * }
         */
        defaults: {
            trigger : '',
            timeout : 6000,
            errorF : function () {},
            submitBefor : function () { return true; },
            callbackAfter : function () {}
        },
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init : function(option) {
            var that = this,
                options;
            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, this.defaults, option);

            if (typeof options.trigger == 'string' && options.trigger.length) {
                options.trigger = $(options.trigger);
            }

            if (options.trigger) {
                this._set(options);
            }

            return that;
        },
        /*
         * 绑定表单提交逻辑
         * @method set
         * @param {Object} parms 表单组件配置
         */
        _set : function(parms) {
            //捆绑事件
            parms.trigger.on('submit', function () {
                var objF = $(this);

                //提交前外调校验函数
                if (parms.submitBefor.call(objF)) {
                    //禁用按钮
                    objF.find('button[type=submit]').prop('disabled', true);

                    //jsonp提交
                    $.ajax ({
                        url : objF.attr('action'),
                        data : objF.serialize(),
                        timeout : parms.timeout,
                        dataType : 'jsonp',
                        error : function () {
                            //恢复按钮
                            if (objF.find('button[type=submit]').length) {
                                objF.find('button[type=submit]')
                                    .html(objF.find('button[type=submit]').val())
                                    .prop('disabled', false);
                            }

                            parms.errorF();
                        },
                        success : function (re) {
                            //恢复按钮
                            if (objF.find('button[type=submit]').length) {
                                objF.find('button[type=submit]')
                                    .html(objF.find('button[type=submit]').val())
                                    .prop('disabled', false);
                            }

                            parms.callbackAfter.call(objF, re);

                            objF[0].reset();
                        }
                    });
                }

                return false;
            });
        }
    });

    VIPSHOP.bridgeTojQuery("form,Form", VIPSHOP.Form);
})(jQuery);

/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.1.0.20121126
 */

 /*
    重构系统函数
*/
var __originals = {
    st: setTimeout,
    si: setInterval
};

eval("var setTimeout, setInterval;");

setTimeout = __originals.st;
setInterval = __originals.si;

__originals = undefined;

var __si = setInterval;
window.setInterval = function(callback, timeout, param) {
    var _cb = function() {
        callback.apply(null, [param]);
    }

    return __si(_cb, timeout);
};
var __st = setTimeout;
window.setTimeout = function(callback, timeout, param) {
    var _cb = function() {
        callback.apply(null, [param]);
    }

    return __st(_cb, timeout);
};

 /**
 * 走马灯/幻灯片组件
 * @class CORE.SWITCHABLE
 * @constructor
 */
(function($){
    VIPSHOP.declare("Switchable", null, {
        /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  _triggers : [],
         *  _panels : [],
         *  _prev : null,
         *  _next : null,
         *  nowOn : 0,
         *  count : 0,
         *  multiple : false,
         *  effect : '',
         *  activeCls : 'active',
         *  autoPlay : false,
         *  interval : 1000,
         *  overPause : false,
         *  activeIndex : 0,
         *  delay : 500
         * }
         */
        defaults:{
            /*
             * 是否多选
             * @type {Array}
             */
            _triggers : [],
            _panels : [],
            _prev : null,
            _next : null,
            nowOn : 0,
            count : 0,
            multiple : false,       //是否可同时打开多个
            effect : '',            //切换效果
            activeCls : 'active',   //触发器选中样式
            autoPlay : false,       //自动播放
            interval : 1000,        //自动播放时间间隔
            overPause : false,      //覆盖暂停
            activeIndex : 0,        //开始步点
            delay : 500,             //触发延迟时间
            pervCls : null,          //上一步class
            nextCls : null,         //下一步class
            trigger : 'click',       //触发事件类型
            triggerCls : '',        //触发器class
            switchEvent : null,     //每次切换时执行的事件
            firstCls : 'switchable-first',  //第一张触发按钮添加的类名
            lastCls : 'switchable-last'     //最后一张触发按钮添加的类名
        },
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                options,
                node;

            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, that.defaults, option);
            node = that.node = $(options.node);

            options.owner = node;
            options._panels = node.find(options.panelCls);
            options.count = options._panels.length;

            this.bindTriggerEvent();

            node.data({
                setting : options
            });

            //左右滑动效果时, 创建滑动层
            if (options.effect == 'slideX') {
                this.setWrapWidth();
            }

            options.prevBtn = node.find(options.pervCls)
            options.nextBtn = node.find(options.nextCls);

            if (options.pervCls) {
                options.prevBtn.on('click', function(){
                    that.prev(options);
                });
            }
            if (options.nextCls) {
                options.nextBtn.on('click', function(){
                    that.next(options);
                });
            }

            //自动播放
            if (options.autoPlay) {
                that.start();
                if (options.overPause) {
                    node.on({
                        'mouseenter' : function () {that.stop()},
                        'mouseleave' : function () {that.start()}
                    });
                }
            }

            //默认选中状态
            if (options.activeIndex >= 0) { 
                that.switchTo.call(null, options, options.activeIndex);
            }

            return this;
        },
        /**
         * 设置滚动层的宽度
         * @chiainable
         */
        setWrapWidth : function () {
            var options = this.options;
            var _panels = options._panels = options.node.find(options.panelCls);
            var panelOuterWidth = options._panels.outerWidth();
            var panelMargin = parseInt(options._panels.css('marginLeft')) + parseInt(options._panels.css('marginRight'));
            var count = options.count = options.node.find(options.panelCls).length;
            var width = (panelOuterWidth + panelMargin) * count;

            options.scroller && $(options.scroller, options.node).width(width);
            return this;
        },
        /**
         * 绑定按钮事件
         * @chiainable
         */
        bindTriggerEvent : function () {
            var that = this,
                options = this.options,
                node = $(options.node); 

            if (options.triggerCls) {
                options._triggers = node.find(options.triggerCls);
                options._triggers.each(function (n) {
                    $(this).data({index : n});
                });

                if (options.trigger == 'click') {
                    //解除
                    options._triggers.off('click.switchable');
                    //绑定
                    options._triggers.on('click.switchable', options, function(){
                        that.switchTo($(this));
                    });
                }
                if (options.trigger == 'over') {
                    var hoverTimer;
                    //解除
                    options._triggers
                        .off('mouseenter.switchable')
                        .off('mouseleave.switchable');
                    //绑定
                    options._triggers.on('mouseenter.switchable', options, function (e) {
                        clearTimeout(hoverTimer);
                        hoverTimer = window.setTimeout(function(e){
                            that.switchTo($(e.currentTarget));
                        }, e.data.delay, e);
                    })
                    .on('mouseleave.switchable', function () {
                        clearTimeout(hoverTimer);
                    });
                }
            }
            return this;
        },
        /**
         * 直接跳到某一帧
         * @method switchTo
         * @param {Number}* 对象, 序号
         * @chiainable
         */
        switchTo: function() {
            var setting,
                index,
                flag,
                prevBtn,// = node.find(setting.pervCls),
                nextBtn;// = node.find(setting.nextCls);

            if (arguments.length == 1) {
                setting = this.options;
                if (typeof arguments[0] == 'number') {
                    index = arguments[0];
                    if (index < 0) {index = 0}
                    if (index > setting.count) {index = setting.count - 1}
                }
                else {
                    index = arguments[0].data("index");
                }
            }
            else {
                setting = arguments[0];
                index = arguments[1];
            }
            
            if (!setting.multiple) {
                //隐藏所有内容    
                if (setting.effect != 'slideX' && setting.effect != 'slideY') {
                    $(setting._panels, this.node).hide();
                }
            }

            prevBtn = setting.owner.find(setting.pervCls);
            nextBtn = setting.owner.find(setting.nextCls);

            //实现多种切换效果
            if (setting.effect != '') {
                switch(setting.effect) {
                case 'slideX':
                    /*if (index == -1) {
                        flag = setting.firstEvent && setting.firstEvent();
                        prevBtn.addClass('first');
                        nextBtn.removeClass('last');
                    }
                    else if (index == setting.count - setting.visiNum + 1) {
                        flag = setting.lastEvent && setting.lastEvent();
                        nextBtn.addClass('last');
                        prevBtn.removeClass('first');
                    }
                    else {
                        prevBtn.removeClass('first');
                        nextBtn.removeClass('last');
                    }*/

                    if (flag !== false) {
                        $(setting.scroller, setting.owner).stop(true, true).animate({
                            'marginLeft' : - setting.slideXWidth * index
                        });
                    }
                    else {
                        return false;
                    }
                    break;
                case 'slideY':
                    $(setting.scroller, setting.owner).stop(true, true).animate({
                        'marginTop' : - setting.slideYHeight * index
                    });
                    break;
                default:
                    eval('$(setting._panels[index]).' + setting.effect + 'Toggle();');
                }
            }
            else {
                $(setting._panels[index]).toggle();
            }

            //检查是否有异步加载内容
            var lazyload = $(setting._panels[index]).find('.datalazyload');
            if (lazyload.length > 0) {
                $(setting._panels[index]).html(lazyload.val());
            }

            //移除触发器选中样式
            $(setting._triggers).removeClass(setting.activeCls);

            //触发器绑定样式
            $(setting._triggers[index]).toggleClass(setting.activeCls);
            setting.nowOn = index;

            //添加结束样式
            if (index + setting.visiNum >= setting.count) {
                setting.nextBtn.addClass(setting.lastCls);
                setting.prevBtn.removeClass(setting.firstCls);
            }
            else if (index == 0) {
                setting.prevBtn.addClass(setting.firstCls);
                setting.nextBtn.removeClass(setting.lastCls);
            }
            else {
                setting.prevBtn.removeClass(setting.firstCls);
                setting.nextBtn.removeClass(setting.lastCls);
            }

            setting.switchEvent && setting.switchEvent($(setting._triggers[index]), $(setting._panels[index]));

            return this;
        },
        /**
         * 上一个
         * @method prev
         * @chiainable
         */
        prev: function() {
            var that = this,
                options = that.options,
                n,
                slideNum = options.slideNum || 1,
                flag = true;

            if (options.nowOn - slideNum < 0) {
                n = options.count - slideNum;
                flag = options.firstEvent && options.firstEvent.call(options.node);
            }
            else {
                n = options.nowOn - slideNum
            }

            if (flag !== false) {
                return that.switchTo.call(null, options, n);
            }
            
            /*var that = this,
                options = that.options,
                n = options.nowOn - 1 < 0 ? options.count - 1 : options.nowOn - 1;
            return that.switchTo.call(null, options, n);*/
        },
        /**
         * 下一个
         * @method next
         * @chiainable
         */
        next: function() {
            var that = this,
                options = that.options,
                n,
                flag = true,
                slideNum = options.slideNum || 1,
                j = options.visiNum ? options.visiNum - slideNum : 0;

            if (options.nowOn + slideNum >= options.count - j) {
                n = 0;
                flag = options.lastEvent && options.lastEvent.call(options.node);
            }
            else {
                n = options.nowOn + slideNum
            }
            
            if (flag !== false) {
                return that.switchTo.call(null, options, n);
            }
            /*var that = this,
                options = that.options,
                n = options.nowOn + 1 >= options.count ? 0 : options.nowOn + 1;
            return that.switchTo.call(that.node, options, n);*/
        },
        /**
         * 开始自动播放
         * @method start
         * @chiainable
         */
        start: function () {
            var that = this,
                v_data = $(that.options.node).data();

            clearInterval(v_data.timing);
            v_data.timing = window.setInterval(function(){
                that.next();
            }, v_data.setting.interval, {data : v_data.setting});

            return this;
        },
        /**
         * 暂停自动播放
         * @method stop
         * @chiainable
         */
        stop: function () {
            clearInterval($(this.node).data('timing'));
            return this;
        }
    });

    VIPSHOP.bridgeTojQuery("switchable,Switchable", VIPSHOP.Switchable);
})(jQuery);
/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.1.0.20121126
 */

 /**
 * 走马灯/幻灯片组件
 * @class CORE.SWITCHABLE
 * @constructor
 */
(function($){
    VIPSHOP.declare("Switchable2", null, {
        /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  _triggers : [],
         *  _panels : [],
         *  _prev : null,
         *  _next : null,
         *  nowOn : 0,
         *  count : 0,
         *  multiple : false,
         *  effect : '',
         *  activeCls : 'active',
         *  autoPlay : false,
         *  interval : 1000,
         *  overPause : false,
         *  activeIndex : 0,
         *  delay : 500
         * }
         */
        defaults:{
            /*
             * 是否多选
             * @type {Array}
             */
            _triggers : [],
            _panels : [],
            _prev : null,
            _next : null,
            nowOn : 0,
            count : 0,
            visiNum : 1,
            multiple : false,       //是否可同时打开多个
            effect : '',            //切换效果
            activeCls : 'active',   //触发器选中样式
            autoPlay : false,       //自动播放
            interval : 1000,        //自动播放时间间隔
            overPause : false,      //覆盖暂停
            activeIndex : 0,        //开始步点
            delay : 500,             //触发延迟时间
            pervCls : null,          //上一步DOM元素
            nextCls : null,         //下一步DOM元素
            trigger : 'click',       //触发事件类型
            switchEvent : $.noop,
            panelCls : '.bd',         //滚动层DOM
            triggerCls : ''        //触发器DOM
        },
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                options,
                node;

            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, that.defaults, option);
            node = that.node = $(options.node);
            options.pannelDom = $(options.panelCls);
            options.triggerDom = $(options.triggerCls);
            options.prevDom = $(options.pervCls);
            options.nextDom = $(options.nextCls);

            that.setStyle();            //预设罢样式
            that.setWrapWidth();        //设置滚动层的宽度
            that.bindTriggerEvent();    //绑定按钮事件

            //是否自动播放
            if (options.autoPlay) {
                var proxyEvt = $.proxy(that.next, that);
                options._interval = setInterval(proxyEvt, options.interval);
                that.node.on({
                    'mouseenter' : function () {
                        clearInterval(options._interval);
                    },
                    'mouseleave' : function () {
                        clearInterval(options._interval);
                        options._interval = setInterval(proxyEvt, options.interval);
                    }
                });
            }

            //绑定事件
            switch(options.effect) {
                case 'slideXLoop':
                case 'slideX':
                    options.childs = options.pannelDom.children();
                    var childML = options.childs.eq(0).css('marginLeft');
                    var childMR = options.childs.eq(0).css('marginRight');

                    options.childWidth = options.childs.eq(0).outerWidth() 
                        + parseInt(childML != 'auto' ? childML : 0)
                        + parseInt(childMR != 'auto' ? childMR : 0);
                    options.childLen = options.childs.length;
                    options.triggerDom.children().on('click.switchable2', function () {
                        var index = $(this).index();
                        $(this).addClass(options.activeCls).siblings()
                            .removeClass(options.activeCls)
                        that.switchTo(index);
                    })
                    .eq(options.activeIndex)
                    .trigger('click.switchable2');

                    that.lazyload(options.childs.eq(0));
                    break;
                default:
                    break;
            }

            return this;
        },
        /**
         * 预设必要样式
         * @chiainable
         */
        setStyle : function () {
            var that = this;
            var options = that.options;

            switch(options.effect) {
                case 'slideXLoop':
                case 'slideX':
                    var pannelDomParent = options.pannelDom.parent();
                    //给滚动层的父元素设置相对定位
                    if (pannelDomParent.css('position') != 'relative') {
                        pannelDomParent.css('position', 'relative');
                    }
                    //给滚动层的设置绝对定位
                    if (options.pannelDom.css('position') != 'absolute') {
                        options.pannelDom.css('position', 'absolute');
                    }
                    //给子元素设置左浮动
                    options.pannelDom.children().css('float', 'left');
                    break;
                default:
                    break;
            }
        },
        /**
         * 设置滚动层的宽度
         * @chiainable
         */
        setWrapWidth : function () {
            var that = this;
            var options = that.options;
            
            switch(options.effect) {
                case 'slideXLoop':
                case 'slideX':
                    //给滚动设置大宽度
                    options.pannelDom.width(999999);
                    break;
                default:
                    break;
            }

            return this;
        },
        /**
         * 绑定按钮事件
         * @chiainable
         */
        bindTriggerEvent : function () {
            var that = this;
            var options = that.options;
            
            options.nextDom.on('click.switchable2', $.proxy(that.next, that));
            options.prevDom.on('click.switchable2', $.proxy(that.prev, that));
            return this;
        },
        /**
         * 直接跳到某一帧
         * @method switchTo
         * @param {Number}* 对象, 序号
         * @chiainable
         */
        switchTo: function(i) {
            var that = this;
            var options = that.options;
            var target = i % options.childLen;

            //执行效果
            switch(options.effect) {
                case 'slideXLoop':
                    //前进
                    if (i > options.nowOn) {
                        //重新统计节点的长度
                        var childLen = options.pannelDom.children().length;
                        //超过子节点个数时，把第一个相对定位到最后
                        if (i >= childLen - options.visiNum + 1) {
                            options.childs.clone().addClass('loop_clone_next')
                                .appendTo(options.pannelDom);
                        }

                        options.pannelDom.stop(true, false).animate({
                            'left' : -i * options.childWidth
                        }, 300, function () {
                            options.pannelDom.css({
                                'left' : -target * options.childWidth
                            });
                            options.nowOn = target;
                            return;
                        });

                        //给触发按钮加上class
                        that.triggerAddCls(Math.abs(target));

                        options.nowOn = i;
                    }
                    //后退
                    else if (i < options.nowOn) {
                        if (i < 0) {
                            $('.loop_clone_prev').remove();
                            options.childs.clone().addClass('loop_clone_prev')
                                .prependTo(options.pannelDom);

                            options.pannelDom.css({
                                'left' : -options.childLen * options.childWidth
                            });

                            options.pannelDom.stop(true, false).animate({
                                'left' : -(i + options.childLen) * options.childWidth
                            }, 300, function () {
                                var target = i % options.childLen;
                                options.pannelDom.css({
                                    'left' : -(options.childLen + target) * options.childWidth
                                });
                                //$('.loop_clone_prev').remove();
                            });
                            options.nowOn = Math.abs(options.childLen + target);
                            //给触发按钮加上class
                            that.triggerAddCls(options.nowOn);
                        }
                        else {
                            options.pannelDom.stop(true, false).animate({
                                'left' : -i * options.childWidth
                            }, 300);
                            options.nowOn = i;

                            //给触发按钮加上class
                            that.triggerAddCls(Math.abs(target));
                        }
                    }
                    break;
                case 'slideX':
                    var proxyEvt = $.proxy(that.next, that);
                    options._interval && clearInterval(options._interval);
                    if (i < 0) {return;}
                    if (i == options.childLen) {return;}

                    //前进
                    if (i > options.nowOn) {
                        options.pannelDom.stop(true, false).animate({
                            'left' : -target * options.childWidth
                        }, 300);
                    }
                    else if (i < options.nowOn) {
                        options.pannelDom.stop(true, false).animate({
                            'left' : -target * options.childWidth
                        }, 300);
                    }

                    if (options.autoPlay && options.interval) {
                        options._interval = setInterval(proxyEvt, options.interval);
                    }
                    that.triggerAddCls(target);
                    options.nowOn = i;
                    break;
                default:
                    break;
            }

            // ----- lazyload -----
            var curPannel = options.pannelDom.children().eq(i);
            that.lazyload(curPannel);

            // ----- switchEvent -----
            var I = i % options.childLen;
            if (I < 0) {
                I = options.childLen - 1;
            }
            options.switchEvent(I);

            return this;
        },
        /**
         * 图片延迟加载
         * @param  {jQuery Object} target 目标窗口
         * @chiainable
         */
        lazyload : function (target) {
            $(target).find('img.lazy').each(function (i, n) {
                var jqN = $(n);
                jqN.attr('src', jqN.data('original'));
                jqN.removeClass('lazy');
            });

            return this;
        },
        /**
         * 上一个
         * @method prev
         * @chiainable
         */
        prev: function() {
            var that = this;
            var options = that.options;
            var i = options.nowOn;            

            i--;
            switch (options.effect) {
                case 'slideXLoop':
                case 'slideX':
                    that.switchTo(i);
                    break;
                default:
                    options.nowOn = i;
                    break;
            }

            return this;
        },
        /**
         * 下一个
         * @method next
         * @chiainable
         */
        next: function() {
            var that = this;
            var options = that.options;
            var childLen =  options.pannelDom.children().length;
            var i = options.nowOn;

            i++;
            switch (options.effect) {
                case 'slideXLoop':
                case 'slideX':
                    that.switchTo(i);
                    break;
                default:
                    options.nowOn = i;
                    break;
            }

            return this;
        },
        /**
         * 开始自动播放
         * @method start
         * @chiainable
         */
        start: function () {
            var that = this;
            var options = that.options;

            return this;
        },
        /**
         * 暂停自动播放
         * @method stop
         * @chiainable
         */
        stop: function () {
            return this;
        },
        /**
         * 给触发按钮加上class
         * @method stop
         * @chiainable
         */
        triggerAddCls : function (i) {
            var that = this;
            var options = that.options;
            options.triggerDom
                .children()
                .eq(i)
                .addClass(options.activeCls)
                .siblings()
                .removeClass(options.activeCls);
        }
    });

    VIPSHOP.bridgeTojQuery("switchable2,Switchable2", VIPSHOP.Switchable2);
})(jQuery);
/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.0.0.20130717
 */

 /**
 * 模拟对话框组件
 * @class CORE.DIALOG
 * @constructor
 */
(function($){
    VIPSHOP.declare('Dialog', VIPSHOP.parentCls, {
        id : null,
        /**
         * 模态对话框组件缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *   size : [500, 300],
         *   position : [],
         *   content : null,
         *   title : null,
         *   effect : { effect:"slide", duration:500 },
         *   model : false,
         *   elStyle : '',
         *   button : [],
         *   trigger : null,
         *   triggerType : '',
         *   showEvent : null,
         *   zIndex : null,
         *   opacity : 0.1,
         *   maskColor : 'black',
         *   isFixed : true,
         *   mars_pop : ''
         * }
         */
        defaults:{
            /*
             * 对话框的宽高，[]则自适应
             */
            size : [500, 300],
            /*
             * 窗口位置，[]则屏幕居中
             */
            position : [],
            /*
             * 窗口内容，html代码或jq对象
             */
            content : null,
            /*
             * 窗口标题
             */
            title : null,
            /*
             * 窗口过渡效果及显示时间长度
             * @param {String} effect 过渡效果
             * @param {String} duration 过渡时间
             */
            effect : { effect:"slide", duration:500 },
            /*
             * 是否模式窗口
             */
            model : false,
            /*
             * 窗口外层样式，不同窗口样式以该样式为根选择器设置
             */
            elStyle : '',
            /*
             * 窗口按钮，每个按钮格式为['type', 'text', fn]
             * @param {String} 按钮类型
             * @param {String} 按钮文字
             * @param {Function} 点击后所触发的方法
             */
            button : [],
            /*
             * 是否依赖对象触发
             * @type {DOM, jQuery Object}
             */
            trigger : null,
            /*
             * 触发的方式(可选jQuery常用事件方法,如'click', 'mouseenter'等);
             * @type {String}
             */
            triggerType : '',
            /*
             * 展示后回调函数
             * @type {Function}
             */
            showEvent : null,
            /*
             * 窗口层级数
             * @type {Number}
             */
            zIndex : null,
            /*
             * 遮罩层的透明度
             * @type {Float}
             */
            opacity : 0.1,
            /**
             * 遮罩层的颜色
             * @type {String}
             */
            maskColor : 'black',
            /*
             * 是否悬浮弹窗
             * @type {Boolean}
             */
            isFixed : true,
            /**
             * 添加埋点
             * @type {String}
             */
            mars_pop : '',
            /**
             * 是否缓存弹窗内容
             */
            isCache : false,
            /**
             * 是否自适应宽高
             * @type {Boolean}
             */
            autoResize : false
        },
        /**
         * 页面上所有select控件的集合
         * @property selects
         * @type {Array}
         * @default []
         */
        selects : [],
        /**
         * 模态对话框所有部件的缓存对象(组件默认收集)
         * @property buffObj
         * @type {Object}
         * @default null
         */
        buffObj : null,
        /**
         * 记录对话框是否打开的状态
         * @property isOpenFlag
         * @type {Boolean}
         * @default false
         */
        isOpenFlag : false,
        /**
         * 打开对话框标记的集合
         * @property dialogColletion
         * @type {Object}
         * @default { count:0 }
         */
        dialogColletion : {
            count : 0
        },
        /**
         * 模态对话框默认模板
         * @property dialogTemplates
         * @type {String}
         * @default null
         */
        dialogTemplates: '<div style="display:none;">'
        +   '<div class="_diaTitle"></div>'
        +   '<div class="_diaContent"></div>'
        +   '<div class="_diaButton"></div>'
        +'</div>',
        /**
         * 初始化
         * @method _init
         * @param {Object} option 对话框配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                contentStyle = {},
                posType,
                options,
                dialogTemplates, 
                buffObj;

            this.id = VIPSHOP.guid();

            //初始化还原
            this.selects = [];
            this.buffObj = null;
            this.tag = 0;
            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, this.defaults, option);
            that.dialogTemplates = dialogTemplates = $(that.dialogTemplates);

            //缓存窗口对象
            buffObj = that.buffObj = {
                dialog : dialogTemplates,
                diaTitle : dialogTemplates.find('> div:eq(0)'),
                diaContent : dialogTemplates.find('> div:eq(1)'),
                diaButton : dialogTemplates.find('> div:eq(2)')
            };

            //设置定位类型
            if (options.isFixed) {
                posType = 'fixed';
            }
            else {
                posType = 'absolute';
            }
            dialogTemplates.css({'position' : posType});

            that
                .title(options.title)   //初始化标题
                .size(options.size[0], options.size[1])     //初始化宽高
                .button();  //初始化按钮

            //自定义样式
            if (options.elStyle) {
                buffObj.dialog.removeClass().addClass(options.elStyle);
            }

            if (options.node) {
                options.trigger = options.node || options.trigger;
            }
            if (options.trigger) {
                $(options.trigger).on(options.triggerType + '.dialog', function(){that.open()});
            }

            return this;
        },
        /*
         * 创建遮罩层
         * @return {Object} Dialog Object
         */
        _createMask: function() {
            var that = this, 
                options = that.options,
                selects = that.selects =$("select:not(#_diaWrap select):visible");

            if ($('#_diaBackground').length == 0) {
                var maskDiv = that.maskDiv = $("<div id='_diaBackground'></div>");
                var sizeCss = 'width:100%; '
                + 'height:100%;'
                + 'background:' + options.maskColor + ';'
                + 'opacity:' + options.opacity + ';'
                + 'filter:alpha(opacity=' + Number(options.opacity) * 100 + ')' + ';'
                + 'z-index:' + (options.zIndex || VIPSHOP.zIndexManager()) + ';'
                + 'display:none';

                /*var ie6Css = VIPSHOP.isIE6 ? 'position:absolute;left:expression(' + domTxt + '.scrollLeft);top:expression('
                    + domTxt + '.scrollTop);width:expression(' + domTxt
                    + '.clientWidth);height:expression(' + domTxt + '.clientHeight)'
                : '';*/
                var ie6Css = VIPSHOP.isIE6 ? 'position:absolute; width:100%; height:' + $(document).height() + 'px;': '';
                maskDiv[0].style.cssText = sizeCss + ';position:fixed;top:0; left:0; overflow:hidden;'
                    + ie6Css;

                $('body').append(maskDiv);
                
                //修复IE6屏幕闪动bug
                maskDiv.show();
            } 
            else {
                that.maskDiv = $('#_diaBackground').show();
            }
            //修复IE6下,select控件穿透层问题
            VIPSHOP.isIE6 && selects.css({visibility: 'hidden'});
            return this;
        },
        /**
         * 移除遮罩层
         * @method removeMask
         * @chiainable
         */
        removeMask: function() {
            var that = this;
            that.maskDiv.animate({opacity: 0}, 300, function(){
                $(this).remove();
            });
            //恢复IE6下的,select按件
            VIPSHOP.isIE6 && selects.css({visibility: 'hidden'});
            return this;
        },
        /**
         * 设置对话框的标题
         * @method title
         * @param {String, Boolean}   标题内容. 为false则隐藏标题栏
         * @chiainable
         */
        title: function(text) {
            var titleWrap = this.buffObj.diaTitle;
            var text = text || this.options.title;
            if(!text){
                titleWrap.remove();
            } 
            else {
                titleWrap.html(text).show();
            }
            return this;
        },
        /**
        * 设置对话框的内容
        * @method content
        * @param {HTML, jQuery Object}   标题内容或jQuery Object
        * @chiainable
        */
        content: function(msg) {
            var options = this.options;
            var contentWrap = this.buffObj.diaContent;
            
            if (typeof msg == 'undefined') {
                return this;
            }
            else {
                //如果传入的是jQuery对象,则提取里面的元素作为内容
                if($.type(msg) === 'object'){
                    msg = $(msg).html();
                }
                contentWrap.html(msg).show();

                //是否为适应宽高
                if (options.autoResize) {
                    var cnt = contentWrap.children().eq(0);
                    var w = cnt.outerWidth(true);
                    var h = cnt.outerHeight(true);
                    this.size(w, h).position();
                }
            }
            return this;
        },
        /**
        * 设置对话框的按钮
        * @method button
        * @param {Array}  [['按钮类型','显示文字',事件]]
        * @chiainable
        */
        button: function(arr) {
            var that = this,
                options = that.options,
                buffObj = that.buffObj;

            if ($.isArray(arr)) {
                options.button = arr;
            }

            //是否显示按钮
            if (options.button  && $.isArray(options.button) && options.button.length > 0) {
                buffObj.diaButton.show();
                for (var i = 0, len = options.button.length; i < len; i++ ) {
                    var btn = options.button[i];
                    var btnType = btn[0].length == 0 ? 'button' : btn[0];   //按钮类型
                    var btnVal = btn[1].length == 0 ? '按钮' : btn[1];       //按钮显示的文字
                    var btnListener = that.btnListener = that.btnListener || {};       //按钮监听事件空间
                    var btnTag = $('<button type="' + btnType +'" id="_btnDialog_' + i + '" value="' + btnVal + '">' + btnVal + '</button>');
                    btnListener[btnVal] = btn[2];      //通过用命名空间来保存事件的引用，从而避免使用闭包来绑定事件
                    btnTag.on('click', function(){
                        btnListener[this.value] && btnListener[this.value].call(that);
                    });
                    buffObj.diaButton.append(btnTag);
                }
            } 
            else {
                buffObj.diaButton.remove();
            }

            return this;
        },
        /**
         * 设置对话框的宽高
         * @method size
         * @param {Number} width 宽度
         * @param {Number} height 高度
         * @chiainable
         */
        size: function(width, height) {
            var that = this, options = that.options;
            var w = width || options.size[0];
            var h = height || options.size[1];
            this.buffObj.dialog.width(w).height(h);
            return this;
        },
        /*
         * 获取水平居中位置
         * @return {String} 返回水平居中位置
         */
        getMid : function () {
            var scrollLeft = $(document).scrollLeft(),
                dW = $(document).width(),
                buffObj = this.buffObj,
                mid = (dW - buffObj.dialog.outerWidth(true)) / 2 - scrollLeft + "px";

            return mid;
        },
        /*
         * 获取垂直居中位置
         * @return {String} 返回水平居中位置
         */
        getVertical : function () {
            var scrollTop = $(document).scrollTop(),
                cH = $(window).height(),
                buffObj = this.buffObj,
                vertical = (cH - buffObj.dialog.outerHeight(true)) / 2 + "px";

            return vertical;
        },
        /**
        * 设置对话框的显示位置
        * @method position
        * @param {Number}   距页面左边距离
        * @param {Number}   距页面顶部距离
        * @chiainable
        */
        position: function(left, top) {
            var that = this,
                options = that.options,
                contentStyle = {},
                pos,
                buffObj = that.buffObj;

            //公共方法权重优先
            if (options.position.length) {
                switch (options.position.length) {
                case 3:
                    pos = $(options.position[0]).position();
                    contentStyle = {
                        top : pos.top + options.position[1],
                        left : pos.left + options.position[2]
                    };
                    break;
                case 2:
                    contentStyle['left'] = options.position[1];
                    contentStyle['top'] = options.position[0];
                case 1:
                    contentStyle['top'] = options.position[0];
                    contentStyle['left'] = that.getMid();
                    break;
                }
            } 
            else {
                //position为缺省值时,则dialog自动居中
                /*var scrollLeft = $(document).scrollLeft(),
                    scrollTop = $(document).scrollTop(),
                    dW = $(document).width();
                    cH = $(window).height();
                contentStyle['left'] = (dW - buffObj.dialog.outerWidth(true)) / 2 - scrollLeft + "px";
                contentStyle['top'] = cH / 2  - options.size[1] / 2 + "px";*/
                contentStyle['left'] = that.getMid();
                contentStyle['top'] = that.getVertical();
            }

            if (!options.isFixed) {
                contentStyle['top'] = $(window).scrollTop() + parseInt(contentStyle['top']);
            }

            that.contentStyle = contentStyle;
            buffObj.dialog.css({
                left: contentStyle['left'],
                top: contentStyle['top']
            });

            //IE6下用absolute来定位
            if (VIPSHOP.isIE6) {
                buffObj.dialog.css({position: 'absolute'});
                if (options.isFixed) {
                    $(window).scroll(function(){
                        buffObj.dialog[0].style.top = Number((contentStyle['top']+'').replace(/px/,'')) + $(document).scrollTop() + 'px'
                    }).trigger('scroll');
                }
            }

            return this;
        },
        /**
         * 打开对话框
         * @method open
         * @chiainable
         */
        open: function () {
            var that = this,
                options = that.options,
                dialogTop,
                buffObj = that.buffObj;

            if (!that.isOpenFlag) {
                that.dialogColletion.count++;
                that.isOpenFlag = true;
            }
            else {
                return this;
            }

            if($('body').find(that.buffObj.dialog).length == 0){
                $('body').append(that.dialogTemplates);
                //初始化内容
                that.content(options.content);
            }
            else {
                options.isCache ? buffObj.dialog.show() : that.content(options.content);
            }

            options.model && that._createMask();   //创建遮罩层
            //设置层级  
            that.buffObj.dialog[0].style.zIndex = options.zIndex ? options.zIndex + 1 : VIPSHOP.zIndexManager();

            //初始化dialog位置
            that.position();
            
             //窗体显示效果
            switch (options.effect.effect) {
            case 'fade':
                buffObj.dialog.stop().fadeIn(options.effect.duration);
                break;
            case 'slide':
                dialogTop = buffObj.dialog.css('top').replace(/px/,'');
                buffObj.dialog.css({
                    opacity: 0,
                    display: 'block',
                    top: (dialogTop - 0 + 10) + 'px'
                });
                setTimeout(function(){
                    buffObj.dialog.stop().animate({
                        top: dialogTop,
                        opacity: 1
                    }, options.effect.duration)
                },0);
                
                break;
            default:
                buffObj.dialog.stop().show(options.effect.duration);
            }

            //埋点
            if (options.mars_pop != '') {
                $.Listeners.pub('mars.pop').success({'mars_pop': options.mars_pop});
            }

            //窗口切换调整遮罩
            $(window)
                .off('resize.' + that.id)
                .on('resize.' + that.id, function () {
                    that.resize();
                });
            that.resize();

            options.showEvent && options.showEvent.call(options.node);
            return this;
        },
        /**
         * 重置对话框位置
         * @method resize
         * @chiainable
         */
        resize: function(left, top){
            this.position(left, top);
            return this;
        },
        /**
         * 移除对话框
         * @method remove
         */
        remove: function(){
            this.buffObj.dialog.html('').remove();
        },
        /**
         * 销毁对话框事件，并解除触发对象的对应事件
         * @method destroy
         */
        destroy: function(){
            var that = this,
                options = that.options;
            $(options.trigger).off(options.triggerType + '.dialog');
            that.remove();
        },
        /**
         * 关闭对话框
         * @method close
         * @chiainable
         */
        close: function () {
            var that = this,
                options = that.options,
                buffObj = that.buffObj,
                contentStyle = that.contentStyle,
                dialogTop;

            if (typeof selects != 'undefined') {
                selects.show();
            }

            //窗体显示效果
            switch (options.effect.effect) {
            case 'fade':
                buffObj.dialog.stop().animate({
                    'opacity' : 0
                }, options.effect.duration, function () {
                    $(this).css({
                        'display' : 'none',
                        'opacity' : 1
                    });
                    that.isOpenFlag = false;
                });
                //buffObj.dialog.fadeOut(options.effect.duration);
                break;
            case 'slide':
                dialogTop = buffObj.dialog.css('top').replace(/px/,'');
                buffObj.dialog.stop().animate({
                    top: dialogTop - 10,
                    opacity: 0
                }, options.effect.duration, function(){
                    $(this).css({
                        top: dialogTop - 0 + 'px',
                        display: 'none'
                    });
                    that.isOpenFlag = false;
                });
                break;
            default:
                buffObj.dialog.stop().hide(options.effect.duration, function () {
                    that.isOpenFlag = false;
                });
            }

            if (that.dialogColletion.count > 0) {
                that.dialogColletion.count--;    
            }

            //隐藏遮罩
            if (that.maskDiv && that.isOpenFlag && that.dialogColletion.count <= 0) {
                that.maskDiv.hide();
            }

            //解除遮罩事件
            $(window).off('resize.' + that.id);

            return this;
        },
        /**
         * 获取对话框内部元素
         * @method getElem
         * @param {String} str 选择符
         * @return {jQuery Object}
         */ 
        getElem : function (str) {
            return this.dialogTemplates.find(str);
        }
    });

    VIPSHOP.bridgeTojQuery("dialog,Dialog", VIPSHOP.Dialog);
})(jQuery);

/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.1.0.20130313
 */

 /**
 * 下拉选框组件
 * @class CORE.SELECTOR
 * @constructor
 */
(function($){
    VIPSHOP.declare('Selector', null, {
        /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  everyLoad : false,
         *  multiple : false,
         *  target : null,
         *  targetType : 'click',
         *  classPre : '',
         *  loaded : false,
         *  defTxt : '',
         *  evtChoose : null
         * }
         */
        defaults:{
            /*
             * 是否重新加载
             * @type {Boolean}
             */
            everyLoad : false,
            /*
             * 是否多选
             * @type {Boolean}
             */
            multiple : false,
            /*
             * 指定目标元素
             * @type {jQuery Object}
             */
            target : null,
            /*
             * 触发事件类型
             * @type {String}
             */
            targetType : 'click',
            /*
             * 样式类名前缀
             * @type {String}
             */
            classPre : '',
            /*
             * 样式类名前缀
             * @type {Boolean}
             */
            loaded : false,
            defTxt : '',        //缺省显示值
            evtChoose : null    //点击时触发的事件
        },
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                defaults = that.defaults,
                hoverFlag = false,
                hoverTime,
                selectorWrapId,
                selectorWrap,
                selectorTxt,
                selectorOpt,
                options;
            
            if (!option.target) {
                option.target = $(option.node);
            }
            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, this.defaults, option);
            
            if (typeof options.clone == 'string') {
                selectorWrapId = that.selectorWrapId = options.clone.replace('#','');
                options.clone = $(options.clone);
                //如果页面不存在容器,则自动创建容器
                if ($(options.clone).length == 0) {
                    //外围容器
                    selectorWrap = options.clone = that.selectorWrap = $('<div id="' + selectorWrapId + '" class="' + options.classPre + '_root"></div>');
                    //选中显示层
                    selectorTxt = that.selectorTxt = $('<div class="' + options.classPre +'_txt">' + options.defTxt +'</div>') ;
                    //选项层
                    selectorOpt = that.selectorOpt = $('<div class="' + options.classPre +'_sel"></div>');
                    //添加到外围容器
                    selectorWrap.append(selectorTxt).append(selectorOpt);
                    //把外围容器添加到目标控件之后
                    options.target.after(selectorWrap);
                }
                else {
                    //外围容器
                    selectorWrap = that.selectorWrap = options.clone;
                    //选中显示层
                    selectorTxt = that.selectorTxt = selectorWrap.find('.'+ options.classPre +'_txt');
                    //选项层
                    selectorOpt = that.selectorOpt = selectorWrap.find('.'+ options.classPre +'_sel');
                }
            }

            //初始化隐藏
            options.target.hide();
           if (VIPSHOP.isIE6) {
                //修复IE6下不能隐藏select的BUG
                setTimeout(function(){
                    options.target.css({'visibility': 'hidden'});
                },0);
            }
            selectorOpt.hide();

            //绑定显示层事件
            that._bindSelectorTxt();
            return this;
        },
        /**
         * 重置
         * @method reset
         * @chiainable
         */
        reset: function () {
            var that = this,
                options = that.options;

            that.selectorWrap.find('.'+ options.classPre +'_optCur').removeClass(options.classPre +'_optCur');
            that.selectorTxt.html(options.defTxt);
            options.target.val('');
            return this;
        },
        /**
         * DEBUG
         * @method debug
         * @chiainable
         */
        debug: function () {
            VIPSHOP.log(this.options);
            return this;
        },
        /**
         * 点击选项时触发的事件
         * @method click
         * @param {Number}* 序号, 值
         * @chiainable
         */
        click: function () {
            var that = this,
                options = that.options;

            if (arguments.length == 1) {
                options.clone.find('.'+ options.classPre +'_opt:eq('+ arguments[0] +')').trigger('click');
            }
            else {
                options.clone.find('.'+ options.classPre +'_opt[data-val='+ arguments[1] +']').trigger('click');
            }

            return this;
        },
        /**
         * 重新渲染
         * @method render
         * @chiainable
         */
        render: function () {
            this._render();
            return this;
        },
        /**
         * 显示下拉
         * @method show
         * @chiainable
         */
        show: function() {
            this._show();
            this._bindEvent();
            return this;
        },
        /**
         * 隐藏下拉
         * @method hide
         * @chiainable
         */
        hide: function() {
            this._hide();
            this._unbindEvent();
            return this;
        },
        /**
         * 冻结selector控件
         * @method disable
         * @chiainable
         */
        disable : function () {
            var that = this,
                options = this.options;
            //添加冻结样式和解除绑定事件
            that.selectorTxt.addClass('selector_disable');
            that._unbindSelectorTxt();
            return this;
        },
        /**
         * 解冻selector控件
         * @method enable
         * @chiainable
         */
        enable : function () {
            var that = this,
                options = this.options;
            //解除冻结样式和绑定事件
            that.selectorTxt.removeClass('selector_disable');
            that._bindSelectorTxt();
            return this;
        },
        /*
         * 绑定点击显示层事件
         */
        _bindSelectorTxt : function () {
            var that = this,
                options = that.options;

            switch (options.targetType) {
            case 'click':
                that.selectorTxt.on('click', function(e) {
                    that.show();
                });
                break;
            case 'mouse':
                that.selectorTxt.on({
                    'mouseenter' : function() {
                        that.show();
                        hoverFlag = true;
                    }, 
                    'mouseleave' : function() {
                        that.hoverTime = hoverTime = setTimeout(function() {
                            that.hide(); 
                            hoverFlag = false;
                        },200);
                    }
                });
                break;
            }
            return this;
        },
        /*
         * 解除绑定显示层事件
         * @returns {Object} selector Object
         */
        _unbindSelectorTxt : function () {
            var that = this,
                options = that.options;

            switch (options.targetType){
            case 'click':
                that.selectorTxt.off('click');
                break;
            case 'mouse':
                that.selectorTxt.off('hover');
                break;
            }
            return this;
        },
        /*
         * 显示下拉
         */
        _show: function() {
            var that = this,
                options = that.options;
            //把选项层添加到外围容器
            that.selectorWrap.append(that.selectorOpt);
            
            if ( (options.everyLoad || !options.loaded) && !that.selectorOpt.is(':visible') ) {
                that._render(options);
            } else if (that.selectorOpt.is(':visible')) {
                that._hide();
            }

            //把相关的选项层隐藏
            //$('.'+ options.classPre +'_sel').hide();
            that.selectorWrap.find('.'+ options.classPre +'_sel').show();

            that.isShowed = true;
            return false;
        },
        /*
         * 绑定触发事件(选中选项或点击其他元素需要触发的事件)
         */
        _bindEvent: function() {
            var that = this,
                options = that.options;
            switch (options.targetType){
            case 'click':
                //给文档绑定事件
                $(document).on('click.selector_' + that.selectorWrapId, function(e){
                    var target = e.target;
                    that.hoverFlag = false;
                    $(target).parents().each(function(i,n){
                        if($(n).attr('id') == that.selectorWrapId){
                            that.hoverFlag = true;
                        }
                    });
                    if (!that.hoverFlag) {
                        that.hide();
                    }
                });
                //给选项层绑定事件
                that.selectorTxt.on('click.selector', function(){
                    that.isShowed && that.hide();
                });
                break;
            case 'mouse':
                that.selectorOpt.on({
                    'mouseenter' : function(){
                        clearTimeout(that.hoverTime);
                        hoverFlag = true;
                    }, 
                    'mouseleave' : function(){
                        that.hide();
                        hoverFlag = false;
                    }
                });
                break;
            }
        },
        /*
         * 隐藏下拉
         */
        _hide: function(e) {
            var that = this,
                options = that.options;
            that.selectorOpt.hide();
            that.isShowed = false;
        },
        /*
         * 解除触发事件
         */
        _unbindEvent: function() {
            var that = this,
                options = that.options;

            switch (options.targetType) {
            case 'click':
                $(document).off('click.selector_' + that.selectorWrapId);
                that.selectorTxt.off('click.selector');
                break;
            case 'mouse':
                that.selectorOpt.off('hover');
                break;
            }
        },
        /*
         * 填充选项内容
         */
        _render: function() {
            var that = this,
                options = that.options,
                items = $(options.target).find('option'),
                itemsTxt = '';

            //使用固定标签
            for (var i = 0, len = items.length; i < len; i++ ) {
                itemsTxt+= '<span class="'+ options.classPre +'_opt" data-val="'+ $(items[i]).val() +'">'+ $(items[i]).text() +'</span>';
            }
            //把选项添加到选项层
            that.selectorOpt.html(itemsTxt);
            options.loaded = true;
            
            //绑定点击事件
            that.selectorOpt.find('span').on('click', function(){
                that._fill($(this));
            });
        },
        /*
         * 选中菜单
         * @param {jQuery Object} clickOpt 为点击选中的下拉选项
         */
        _fill: function(clickOpt) {
            var that = this,
                options = that.options;
            
            //选中样式
            that.selectorWrap.find('.'+ options.classPre +'_optCur').removeClass(options.classPre +'_optCur');
            clickOpt.addClass(options.classPre +'_optCur');
            that.selectorTxt.html(clickOpt.html());

            //数据联动
            options.target.val(clickOpt.data('val'));

            if (!options.multiple) {
                that.hide();
            }

            //选中回调函数
            switch (typeof options.evtChoose) {
            case 'function':
                options.evtChoose.call(clickOpt);
                break;
            case 'string':
                eval(options.evtChoose);
                break;
            }

            return false;
        }
    });

    VIPSHOP.bridgeTojQuery("Selector", VIPSHOP.Selector);
})(jQuery);

/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.1.1.20130814
 */

 /**
 * 模板组件
 * @class CORE.TEMPLATE
 * @constructor
 */
(function($){
    VIPSHOP.declare("Template", null, {
         /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  templateElement : null,
         *  callback : null,
         *  replace : false
         * }
         */
        defaults: {
            templateElement : null,
            callback : null,
            replace : false
        },
        template: null,
        clTemp: '',
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                options,
                target;

            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, this.defaults, option);

            if ($.type(options.templateElement) === 'object') {
                if ($(options.templateElement).length) {
                    that.template = $(options.templateElement).html();
                }
                else {
                    VIPSHOP.log('缺少目标template', options);
                    that.template = '';
                    return false;
                }
            }
            else {
                that.template = options.templateElement;
            }

            return this;
        },
        /*
         * 按特定标签切割模板
         * @method
         * @param {template String} block 模板片断
         * @param {tag String} tag HTML元素标签
         * @returns {Object} Object
         */
        _CutTemplate: function (block, tag) {
            var start = block.indexOf('{#'+ tag + '}') + 3 + tag.length;
            var end = block.indexOf('{#/'+ tag + '}', start);
            
            if (end != -1) {
                var tt = block.slice(start, end);
                block = block.substr(0, start - 3 - tag.length)  + "{#"+ tag +"}" + block.substr(end + 4 + tag.length);
            }
            else {
                var tt = '';
            }

            return {
                "block" : block,
                "section" : tt
            };
        },
        /*
         * 按表格渲染
         * @method
         * @param {template String} block 模板片断
         * @param {tag String} tag table元素标签
         * @param {json Object} lists 用于渲染模板的JSON数据
         * @returns {String} Table Format String
         */
        _Table: function (block, tag, lists) {
            var that = this,
                blocks = [],
                block = that._CutTemplate.call(this, block, tag);

            for (var i = 0, len = lists.length; i < len; i++) {
                blocks.push(that._Replace(block['section'], lists[i]) );
            }
            return block['block'].replace("{#"+ tag +"}", blocks.join(''));
        },
        /*
         * 按表单渲染
         * @method
         * @param {template String} block 模板片断
         * @param {tag String} tag Form元素标签
         * @param {json Object} form 用于渲染模板的JSON数据
         * @returns {String} Form Format String
         */
        _Form: function (block, tag, form) {
            var vv = this._CutTemplate.call(this.options.node, block, tag);
            return vv['block'].replace("{#"+ tag +"}", this._Replace(vv['section'], form));
        },
        /*
         * 普通渲染
         * @method
         * @param {template String} block 模板片断
         * @param {json Object} json 用于渲染模板的JSON数据
         * @returns {String} template Object
         */
        _Replace: function (block, json) {
            var that = this,
                options = that.options,
                element;

            for (element in json) {
                if (typeof json[element] == 'object') {
                    //复合节点
                    if ( $.isArray(json[element]) ) {
                        block = that._Table.call(this, block, element, json[element]);
                    } else {
                        block = that._Form.call(this, block, element, json[element]);
                    }
                }
                else {
                    //普通节点
                    var reg = RegExp('{\\$' + element + '}', 'g');
                    block = block.replace(reg, json[element]); 
                    //block = block.replace(eval("/{\\$" + element + "}/g"), json[element]);
                    //delete json[element];
                }
            }
            return block;
        },
        /**
         * 执行模版处理方法
         * <pre>如果节点是数组形式，则按table方式处理；如是对象形式，则按form方式处理。最外层亦同样以form方式处理
         * @method process
         * @param {Object} json 用于渲染模板的JSON数据
         * @returns {String} HTML String
         */
        process : function (json) {
            var that = this,
                options = that.options,
                node = $(options.node),
                clTemp = that.template;
            
            clTemp = that._Replace(clTemp, json);

            if (options.callback != null) {
                clTemp = options.callback(clTemp, json);
            }

            if (options.replace === true) {
                node.html(clTemp);
            }
            else if (options.replace === false) {
                node.append(clTemp);
            }
            else {
                return clTemp;
            }
        }
    });

    VIPSHOP.bridgeTojQuery("template,Template", VIPSHOP.Template);
})(jQuery);

/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.1.0.20130313
 */

 /**
 * 表单验证组件
 * @class CORE.VALIDATION
 * @constructor
 */
(function($){
    VIPSHOP.declare("Validation", null, {
        /**
         * 缺省配置
         * @property defaults
         * @type {Object}
         * @default {
         *  errStyle : '',
         *  susStyle : '',
         *  msgType : 'after'
         * }
         */
        defaults: {
            errStyle : '',
            susStyle : '',
            msgType : 'after'   //inner/after/alert/all(inner+after)
        },
        /**
         * 规则
         * @property rules
         * @type {Object}
         * @example {
         *  required : 必填 <br />
         *  email : e-mail <br />
         *  url : url <br />
         *  phone : 电话号码 <br />
         *  mobile : 手机号码 <br />
         *  chinese : 中文 <br />
         *  english : 英文 <br />
         *  currency :  <br />
         *  birthday : 12位日期 <br />
         *  birthday8 : 8位日期 <br />
         *  qq : qq号码 <br />
         *  number : 数字
         * }
         */
        rules: {
            required : /.+/,
            email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
            url : /^((http)|(https)|(ftp)):\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
            phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
            mobile : /^1\d{10}$/,
            chinese : /^[\u4e00-\u9fa5]+$/,
            english : /^[A-Za-z]+$/,
            currency : /^\d+(\.\d+)?$/,
            birthday : /^((19)|(20))(\d{2})\-((0[1-9])|1[0-2])\-((0[1-9])|([1-2][0-9])|(3[0-1]))$/,
            birthday8 : /^((19)|(20))(\d{2})((0[1-9])|1[0-2])((0[1-9])|([1-2][0-9])|(3[0-1]))$/,
            qq : /^[1-9]\d{4,13}$/,
            number : /^\d+$/
        },
        fields: [],
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init: function(option) {
            var that = this,
                options,
                target;
            //合并配置选项,并注册到options
            options = that.options =  $.extend({}, this.defaults, option);

            //整合指定的form对象
            if (!options.target && !options.node) {
                VIPSHOP.log('缺少目标form');
                return false;
            }
            else {
                target = options.node = options.target ? $(options.target) : options.node;
            }

            target = target[0];
            var elemCount = target.elements.length;

            //每一次初始化都清空表单对象数组
            that.fields = [];
            //遍历表单内对象
            for ( var i = 0; i < elemCount; i++ ) {
                that.addField($(target.elements[i]));
            }
            return that;
        },
        /**
         * 添加验证字段
         * @method addField
         * @param {jQuery Object} json 用于渲染模板的JSON数据
         * @chiainable
         */
        addField: function (elem) {
            var that = this,
                options = that.options,
                fields = that.fields;
            if (typeof elem == 'string') { elem = $(elem); }

            if (arguments.length == 1) {
                //将标签内的字符串设定转换为object变量
                eval('var op = ' + elem.data('valid'));
            } 
            else {
                var op = arguments[1];
            }

            //判断是否设定校验规则
            if (typeof op != 'undefined') {
                fields.push({
                    target : elem,
                    option : op
                });
                elem
                    .data({
                        index : fields.length - 1
                    })
                    .on('blur', function () {
                        if ($(this).val() != '') {
                            $('label[for='+ $(this).attr('id') +']').hide();
                        } 
                        else {
                            $('label[for='+ $(this).attr('id') +']').show().animate({opacity: 0.5}, 200);
                        }
                        
                        that.valid($(this).data('index'));
                    })
                    .on('focus', function () { 
                        if ($(this).val() == '') {
                            $('label[for='+ $(this).attr('id') +']').show().animate({opacity: 0.25}, 200);
                        } 
                        else {
                            $('label[for='+ $(this).attr('id') +']').hide();
                        }
                    })
                    .on('keypress change', function () { $('label[for='+ $(this).attr('id') +']').hide(); });
            }

            return that;
        },
        /**
         * 单独验证
         * @method valid
         * @param {Number} i 第i个表单元素
         * @return {Boolean}
         */
        valid: function (i) {
            var that = this,
                options = that.options,
                field = that.fields[i],
                isErr = false,
                errMsg = '';

            for (x in field.option) {
                var val = field.target.val();

                switch (x) {
                case 'func':
                    isErr = !field.option[x][0].apply( field.target, [val] );
                    if (isErr) {
                        errMsg = field.option[x][1];
                    }
                    break;
                case 'regex':
                    //自定义正则
                    if(!field.option[x][0].test(val)) {
                        isErr = true;
                        errMsg = field.option[x][1];
                    }
                    break;
                case 'equalTo':
                    //重复
                    if (val != $(field.option[x][0]).val()) {
                        isErr = true;
                        errMsg = field.option[x][1];
                    }
                    break;
                case 'length':
                    //长度
                    if (val.length < field.option[x][0] || 
                        val.length > field.option[x][1]) {
                        isErr = true;
                        errMsg = field.option[x][2];
                    }
                    break;
                case 'maxLength':
                    if (val.length > field.option[x][0]) {
                        isErr = true;
                        errMsg = field.option[x][1];
                    }
                    break;
                case 'minLength':
                    if (val.length < field.option[x][0]) {
                        isErr = true;
                        errMsg = field.option[x][1];
                    }
                    break;
                case 'rang':
                    if (val < field.option[x][0] || 
                        val > field.option[x][1]) {
                        isErr = true;
                        errMsg = field.option[x][2];
                    }
                    break;
                case 'group':
                    //分组选中
                    var group = $("input[name='" + field.target.attr('name') + "']:checked");
                    if (group.length < field.option[x][0] || 
                        group.length > field.option[x][1]) {
                        isErr = true;
                        errMsg = field.option[x][2];
                    }
                    break;
                case 'required':
                    if(val == '' || val == '0') {
                        isErr = true;
                        errMsg = field.option[x];
                    }
                    break;
                case 'email':
                case 'url':
                case 'phone':
                case 'mobile':
                case 'english':
                case 'chinese':
                case 'currency':
                case 'birthday':
                case 'birthday8':
                case 'number':
                    if(val != '' && !this.rules[x].test(val)) {
                        isErr = true;
                        errMsg = field.option[x];
                    }
                    break;
                }
            };
            
            //外部回调
            if ( !isErr && field.option['cb'] ) {
                isErr = field.option['cb'].call(that, field.target, field.option);
            };

            //提示处理
            if (isErr) {
                switch (options.msgType) {
                case 'alert':
                    alert(errMsg);
                    break;
                case 'all':
                case 'inner':
                    field.target
                        .addClass(options.errStyle)
                        .removeClass(options.susStyle);

                    if (options.msgType == 'inner') { break; }
                case 'after':
                    var id = field.target.attr('id') ? field.target.attr('id') : field.target.attr('name');
                    if ( $('#' + id + '_tip').length == 0 ) {
                        field.target.after('<span id="' + id + '_tip"></span>');
                    }
                    
                    $('#' + id + '_tip')
                        .addClass(options.errStyle)
                        .removeClass(options.susStyle)
                        .html(errMsg);
                    break;
                }
            }
            else {
                switch (options.msgType) {
                case 'all':
                case 'inner':
                    field.target
                        .removeClass(options.errStyle)
                        .addClass(options.susStyle);

                    if (options.msgType == 'inner') { break; }
                case 'after':
                    var id = field.target.attr('id') ? field.target.attr('id') : field.target.attr('name');
                    if ( $('#' + id + '_tip').length == 0 ) {
                        field.target.after('<span id="' + id + '_tip"></span>');
                    }
                    
                    $('#' + id + '_tip')
                        .removeClass(options.errStyle)
                        .addClass(options.susStyle)
                        .html('');
                    break;
                }
            }

            return isErr;
        },
        /**
         * 校验
         * @method run
         * @return {Boolean}
         */
        run: function () {
            var fields = this.fields;
            var fieldCount = fields.length;
            var err = false;

            for (var i = 0;i < fieldCount;i++) {
                if (this.valid(i) || err) { err = true; }
            }

            return err;
        },
        /**
         * 重置表单
         * @method reset
         */
        reset: function () {
            var that = this,
                options = that.options,
                fields = that.fields,
                fieldCount = fields.length;

            for (var i = 0;i < fieldCount;i++) {
                var field = fields[i];

                switch (options.msgType) {
                case 'all':
                case 'inner':
                    field.target.removeClass(options.errStyle + ' ' + options.susStyle);
                    if (options.msgType == 'inner') { break; }
                case 'after':
                    var id = field.target.attr('id') ? field.target.attr('id') : field.target.attr('name');
                    if ( $('#' + id + '_tip').length != 0 ) {
                        $('#' + id + '_tip')
                            .removeClass(options.errStyle + ' ' + options.susStyle)
                            .html('');
                    }
                    
                    break;
                }
            }
        }
    });

    VIPSHOP.bridgeTojQuery("validation,Validation", VIPSHOP.Validation);
})(jQuery);
/**
 * @module CORE
 * @author zhenbo.zheng<zhenbo.zheng@vipshop.com>
 * @version 1.2.1.20130217
 */

 /**
 * 滚动侦测组件
 * @class CORE.SCROLLSPY
 * @constructor
 */
(function($){
    VIPSHOP.declare('ScrollSpy', null, {
        /**
         * 默认配置
         * @property defaults
         * @type {Object}
         * @default {
         *  trigger : '',          // (string | jQuery object)，源html控件，触发按钮。
         *  panel : '',            // (string | jQuery object)，源html控件，目标内容容器。
         *  diffHeight : 0,        // (number) 设置滚动高度差。
         *  triggerType : 'click', // 触发事件类型。
         *  activeCls : 'cur',     // (string) 滚动到目标点时，给触发按钮添加的样式。
         *  isAlwaysCall : true,   // (boolean) 时否在滚动时一直执行触发事件。（false 为只在变换不同对象时才会执行触发事件）
         *  reverse : true,        // (boolean) true 为支持反向触发，即点击按钮可以滚动到对应位置，反之亦然。默认值为true。
         *  speed : 600,           // (number) 页面滚动时间，默认值600
         *  noSpyEvent : $.noop,   // (Function) 当滚动点没落在任何区间时，所执行的事件，指针为spyObject。
         *  spyEvent: $.noop,      // (Function) 当滚动点落在任一区间时，所执行的事件，指针为spyObject。
         *  clickEvent : $.noop    // (Function) 当点击按钮时触发的事件，指针为spyObject。
         *  _curObject : null      // 保存实例私有变量(不公开)
         * }
         */
        defaults : {
            trigger : '',
            panel : '', 
            diffHeight : 0,
            triggerType : 'click',
            activeCls : 'cur',
            isAlwaysCall : true,
            reverse : true,
            speed : 600,
            noSpyEvent : $.noop,
            spyEvent : $.noop,
            clickEvent : $.noop,
            _curObject : null
        },
        /**
         * 初始化
         * @method _init
         * @param {Object} option 自定义配置
         * @chiainable
         */
        _init : function (option) {
            var that = this,
                options,
                triggerDom,
                panelDom;

            //合并配置选项,并注册到options
            options = that.options = $.extend({}, this.defaults, option);
            if (options.target) {
                options.node = $(options.target);
            }
            //初始化侦测队列
            that._spyArray = that.triggerDomArray = that.panelDomArray = null;
            that.timeStamp = +new Date();

            //窗口改变尺寸时，重新计算各项值
            $(window).resize(function () {
                that._expands();
            });

            that._expands();
            that._bindScroll();

            return that;
        },
        /*
         * 扩展全局设置到每个侦测对象
         * @method
         * @access private
         * @param {string | jQuery Object} 要监听的按钮对象
         * @param {string | jQuery Object} 要监听的内容对象
         * @returns {Object} ScrollSpy Object
         */
        _expands : function (trigger, panel) {
            var tempObj,
                that = this,
                options = that.options,
                trigger = trigger || options.trigger,
                panel = panel || options.panel,
                triggerDomArray = that.triggerDomArray = $(trigger),
                panelDomArray = that.panelDomArray = $(panel),
                spyArray = that._spyArray;

            //refresh
            if (arguments.length == 0) {
                spyArray = [];
            }

            for(var i = 0, len = triggerDomArray.length; i < len; i++) {
                tempObj = {};
                tempObj.triggerDom = triggerDomArray.eq(i);
                tempObj.panelDom = panelDomArray.eq(i);
                if (tempObj.panelDom.offset().top > 50000) return;
                tempObj.selfStartDiffHeight = tempObj.panelDom.data('startDiffHeight') || 0;
                tempObj.selfEndDiffHeight = tempObj.panelDom.data('endDiffHeight') || 0;
                if (options.addUp) {
                    if (i == 0) {
                        tempObj.panelDom.length > 0 && (tempObj.startPoint = tempObj.panelDom.offset().top);
                        
                    }
                    else {
                        tempObj.startPoint = spyArray[i-1].endPoint;
                    }
                }
                else {
                    tempObj.panelDom.length > 0 && (tempObj.startPoint = tempObj.panelDom.offset().top);
                }
                tempObj.endPoint = tempObj.startPoint + (tempObj.panelDom.data('preHeight') || tempObj.panelDom.height());
                spyArray.push(tempObj);

                //反向事件绑定
                that._bindClickScroll(tempObj);
            }

            that._spyArray = spyArray;
            
            return this;
        },
        /**
         * 动态增加侦测对象
         * @method push
         * @param {string | jQuery Object} trigger 要监听的按钮对象
         * @param {string | jQuery Object} panel 要监听的内容对象
         * @chiainable
         */
        push : function (trigger, panel) {
            var that = this,
                options = that.options;
            that._expands(trigger, panel);
            return this;
        },
        /**
         * 重新计算各对象的各项属性
         * @method refresh
         * @chiainable
         */
        refresh : function () {
            this._expands();
            return this;
        },
        /**
         * 动态设置diffHeight
         * @method setDiffHeight
         * @chiainable
         */
        setDiffHeight : function (value) {
            this.options.diffHeight = value;
            return this;
        },
        /*
         * 抽象方法
         * @method
         * @access private
         * @returns {Object} ScrollSpy Object
         */
        _runFunction : function (spyObject) {
            var that = this,
                options = that.options,
                activeCls = options.activeCls;

            that.triggerDomArray.removeClass(activeCls);
            spyObject.triggerDom.addClass(activeCls);

            if (options.spyEvent) {
                options.spyEvent.call(spyObject);
            }
        },
        /*
         * 滚动时要执行的事件
         * @method
         * @access private
         * @returns {Object} ScrollSpy Object
         */
        _scrollEvent : function () {
            var i,
                len,
                spyObject,
                _scrollTop,
                that = this,
                options = that.options,
                node = options.node,
                _spyArray = that._spyArray,
                scrollTop = node.scrollTop(),
                isInFlag = false;

                _scrollTop = scrollTop + options.diffHeight;

            if (!_spyArray) {return;}

            for (i = 0, len = _spyArray.length; i < len; i++) {
                spyObject = _spyArray[i];
                //滚动高度在开始值和结束值之间时
                if (_scrollTop >= spyObject.startPoint - spyObject.selfStartDiffHeight &&
                    _scrollTop < spyObject.endPoint + spyObject.selfEndDiffHeight) {

                    if (options.isAlwaysCall) {
                        that._runFunction(spyObject);
                    }
                    else {
                        if (options._curObject != spyObject) {
                            that._runFunction(spyObject);
                            options._curObject = spyObject;
                        }
                    }

                    isInFlag = true;
                }
            }

            if (!isInFlag) {
                if (options.noSpyEvent &&
                $.isFunction(options.noSpyEvent)) {
                    options.noSpyEvent();
                }
                curObject = null;
            }
            return this;
        },
        /*
         * 绑定节点的滚动事件
         * @method
         * @access private
         * @returns {Object} ScrollSpy Object
         */
        _bindScroll : function () {
            var that = this,
                options = that.options,
                node = options.node,
                t,
                t_cur,
                t_start = +new Date();

            //给目标对象绑定滚动事件
            node.on('scroll.spy_' + that.timeStamp, function () {
                //用于减少函数执行次数，提高性能
                t_cur = +new Date();
                clearTimeout(t);

                if (t_cur - t_start > 120) {
                    that._scrollEvent();
                    t_start = t_cur;
                }
                else {
                    t = setTimeout(function () {
                        that._scrollEvent();
                    }, 100);
                }
            });
            that._scrollEvent();
            return this;
        },
        /*
         * 绑定点击滚动事件(反向)
         * @method
         * @access public
         * @returns {Object} ScrollSpy Object
         */
        _bindClickScroll : function (spyObject) {
            var that = this,
                options = that.options,
                triggerDomArray = that.triggerDomArray,
                triggerType = this.triggerType =  options.triggerType + '.spy_' + that.timeStamp,
                speed = options.speed,
                scrollToPoint,
                func,
                i,
                len;

            if (!options.reverse) {
                return this;
            }

            spyObject.triggerDom.off(triggerType);
            spyObject.triggerDom.on(triggerType, function () {
                var clickEvent = options.clickEvent;

                if (!spyObject.panelDom.length > 0) {
                    VIPSHOP.log('没找到对应的内容区');
                    return this;
                }
                $("html, body").stop(true, true).animate({
                    scrollTop: spyObject.startPoint - options.diffHeight - spyObject.selfStartDiffHeight
                }, speed);

                if (clickEvent) {
                    clickEvent.call(spyObject);
                }
            });

            return this;
        },
        /**
         * 销毁组件绑定
         * @method destory
         */
        destory : function () {
            var that = this,
                options = that.options,
                triggerType = this.triggerType;

            options.node
                .off('scroll.spy_' + that.timeStamp)
                .removeData('widget_scrollspy') 
                .removeData('widget_ScrollSpy');

            that.triggerDomArray
                .off(triggerType);
            return ;
        }
    });

    VIPSHOP.bridgeTojQuery("scrollspy,ScrollSpy", VIPSHOP.ScrollSpy);
})(jQuery);
/**
 * @module Functions
 * @author eason<eason.chen@vipshop.com>
 * @version 2.0.0.20121120
 **/

 /**
 * 类库
 * @class Functions
 */
_Globals = {
    'VipDFT' : parseInt(jQuery.Cookie.get("VipDFT"))
};

/**
 * 计时函数 <br />
 *      支持正计时和倒计时
 * @method fn_countDown
 * @param {Number} timestamp 倒计时
 * @param {Number} [timestamp] 正计时
 **/
function fn_countDown (timestamp) {
    var nowTime = Math.round(new Date().getTime() / 1000);
    var recoup = _Globals['VipDFT'] ? _Globals['VipDFT'] : 0;

    if (arguments.length > 1) {
        //正计时
        var lastTime = nowTime - recoup - arguments[1];
    }
    else {
        //倒计时
        var lastTime = timestamp - nowTime - recoup;
    }
    var rt = {};

    if (lastTime > 0) {
        rt.day = Math.floor(lastTime / 86400);

        //剩余天数大于20时，则重新校正
        if (!fn_countDown.hasRegulate && rt.day > 20) {
            fn_countDown.hasRegulate = true;
            jQuery.ajax({
                url : 'http://www.vip.com/index-ajax.php',
                data : {
                    act : 'getServerTime'
                },
                dataType : 'jsonp',
                success : function (data) {
                    _Globals['VipDFT'] = data.time - nowTime;
                    jQuery.Cookie.set('VipDFT', _Globals['VipDFT']);
                }
            });
            return {day:'-', hour:'-', min:'-', sec:'-'};
        }

        var day_timestamp = 86400 * rt.day;

        var v = ('00' + Math.floor((lastTime - day_timestamp) / 3600));
        rt.hour= v.substring(v.length - 2);
        var hour_timestamp = 3600 * rt.hour;

        var v = ('00' + Math.floor((lastTime - day_timestamp - hour_timestamp) / 60));
        rt.min = v.substring(v.length - 2);

        var v = ('00' + (lastTime - day_timestamp - hour_timestamp - 60 * rt.min));
        rt.sec = v.substring(v.length - 2);
    }
    else {
        rt.day = 0;
        rt.hour= 0;
        rt.min = 0;
        rt.sec = 0;
    }

    return rt;
}
/**
 * @method fn_CountDown
 * @deprecated 2.0 use fn_countDown
 **/
function fn_CountDown (timestamp) {
    return fn_countDown(timestamp);
}


/**
 * 字符串截取
 * @method fn_cutString
 * @param {String} str 被截取字符串
 * @param {Number} len 需保留长度
 **/
function fn_cutString (str, len) {
    if (!str) { return ''; }

    var strlen = 0;
    var s = "";

    for(var i = 0, j = str.length;i < j;i++) {
        if(str.charCodeAt(i) > 128) {
            strlen += 2;
        }
        else {
            strlen ++;
        }

        s += str.charAt(i);
        if (strlen >= len) {
            return s ;
        }
    }

    return s;
}
/**
 * @method SetString
 * @deprecated 2.0 use fn_cutString
 **/
function SetString(str, len)  {
    return fn_cutString(str,len);
}


/**
 * 调出在线客服弹窗
 * @method fn_onlineService
 **/
function fn_onlineService () {
    var openner = null;
    var chatUrl = 'http://800.vip.com/live800/chatClient/chatbox.jsp?companyID=8900&configID=13&codeType=custom';   
    chatUrl+="&enterurl="+encodeURIComponent(document.referrer||document.URL);
    chatUrl+="&t="+new Date().getTime();
    try {
        openner = window.open(chatUrl, "chatbox143639", "toolbar=0,scrollbars=0,location=0,menubar=0,resizable=1,width=900,height=720");
        return openner;
    }
    catch(e){}
}
/**
 * @method olService
 * @deprecated 2.0 use fn_onlineService
 **/
function olService(){
    fn_onlineService();
}


/**
 * 调出分享弹窗
 * @param {String} type 分享类型
 * @param {Object} info 分享内容
 * @param {Boolean} addVmark 是否加V值, true表示加V值，false表示不加V值。
 * @method fn_share
 **/
function fn_share (type, info, addVmark) {
    var pic = encodeURIComponent(info.pic),
        content = encodeURIComponent(info.desc),
        title = encodeURIComponent(info.title),
        url = encodeURIComponent(info.url),
        openner = null,
        openSet = 'width=680, height=580, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no';
        if (typeof addVmark == 'undefined') {addVmark = true;}

    switch (type) {
    case 'tsina':
    //分享内容=desc+url
    //图片=pic+url抓取
        openner = window.open('http://v.t.sina.com.cn/share/share.php?title='+ content +'&url='+ url +'&appkey=1493335026&pic='+ pic, '', openSet);
        
        break;
    case 'qzone':
    //title
    //desc
    //url
    //图片=url抓取
        openner = window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ url +'&summary='+ content +'&title='+ title, '', openSet);
        break;
    case 'tqq':
    //分享内容=desc+url
    //图片=url抓取
        openner = window.open('http://v.t.qq.com/share/share.php?title='+ content +'&url='+ url, '', openSet);
        break;
    case 'douban':
    //内容=desc
    //无图
        openner = window.open('http://www.douban.com/recommend/?url='+ url +'&title='+ content, '', openSet);
        break;
    case 'kaixin001':
    //分享内容=title+desc+url
    //图片=url抓取
        openner = window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+ title +'&rurl='+ url +'&rcontent='+ content, '', openSet);
        break;
    case 'renren':
    //所有=url抓取
        openner = window.open('http://share.renren.com/share/buttonshare.do?link='+ url, '', openSet);
        break;
    case 'sohu':
        openner = window.open('http://t.sohu.com/third/post.jsp?&url=' + url + '&title=' + title + '&content=utf-8&pic=', '', openSet);
        break;
    case 't163':    
        content = title + ('  ' + info.url);
        openner = window.open('http://t.163.com/article/user/checkLogin.do?link='+ url +'&source='+ title +'&info='+ content, '', openSet);
        break;
    default:
        break;
    }

    if (addVmark) {
        $.ajax({
            'url' : 'http://myopen.vip.com/my/add_vmark',
            data : {
                type : 'shareGoods'
            },
            jsonp : 'callback',
            dataType : 'jsonp',
            jsonpCallback : 'add_vmark',
            success : function (res) {}
        });
    }

    return openner;
}
/**
 * @method share_sns
 * @deprecated 2.0 use fn_share
 **/
function share_sns(type, info) {
    fn_share(type, info);
}


/**
 * 复制到剪切板
 * @method fn_copy2Clipboard
 * @param {String} str 复制内容
 **/
function fn_copy2Clipboard (txt) {
    if(window.clipboardData){
        window.clipboardData.clearData();
        window.clipboardData.setData("Text",txt);
    }
    else if(navigator.userAgent.indexOf("Opera")!=-1){
        window.location=txt;
    }
    else if(window.netscape){
        try{
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        }
        catch(e){
            alert("您的浏览器安全设置限制了您进行剪贴板操作");
            return false;
        }
        var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if(!clip)return;
        var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if(!trans)return;
        trans.addDataFlavor('text/unicode');
        var str=new Object();
        var len=new Object();
        var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext=txt;str.data=copytext;
        trans.setTransferData("text/unicode",str,copytext.length*2);
        var clipid=Components.interfaces.nsIClipboard;
        if(!clip)return false;
        clip.setData(trans,null,clipid.kGlobalClipboard);
    }
}
/**
 * @method copy2Clipboard
 * @deprecated 2.0 use fn_copy2Clipboard
 **/
function copy2Clipboard (txt){
    fn_copy2Clipboard(txt);
}

/*
 * @desc 核心库升级方法
 * @deprecated
 * @memberof Core
 */
function fn_upgrade () {
    delete jQuery.Loader;
    delete jQuery.Dialog;
    delete jQuery.Menu;
    delete jQuery.Validation;
    delete jQuery.fn.Switchable;
    delete jQuery.fn.Template;
    delete jQuery.fn.Selector;
    delete VIPSHOP;
}
$(function () {
    $.Listeners.pub('ready.first').success();
});

//ready.second的触发规则为dom.ready5秒后定时触发
$.Listeners.sub('ready.first').onsuccess(function () {
    setTimeout(function () {
        $.Listeners.pub('ready.second').success();
    }, 5000);
});

//增加ready.third执行时的规则，
//必须明确为ready.second已经成功触发完成后才可以执行。
//避免window.load执行得比5秒快。
$(window).on('load.Listeners', function () {
    $.Listeners.sub('ready.second').onsuccess(function () {
        $.Listeners.pub('ready.third').success();
        $.Listeners.pub('winLoaded').success();
    });
});

/*
$(window).on('load.Listeners', function () {
    $.Listeners.pub('ready.second').success();
    $.Listeners.pub('winLoaded').success();
    $.Listeners.pub('ready.third').success();
});*/

$.Listeners.sub('ready.first').onsuccess(function () {
    //登录校验
    //VIPSHOP.member.chk();

    /**
     * ie6下通过脚本去设置样式来实现hover
     * html节点上需要加上data-hover如data-hover='className'
     */
    if (VIPSHOP.isIE6) {
        $('body').on({
            mouseenter : function () {
                $(this).addClass($(this).data('hover'));
            },
            mouseleave : function () {
                $(this).removeClass($(this).data('hover'));
            }
        }, '[data-hover]');
    }
    if (VIPSHOP.isMobile) {
        $('.device-pc').removeClass('device-pc');
        $('body').on('touchstart', function (e) {
            var jqTarget = $(e.target);
            var isInner = false;
            if (typeof jqTarget.data('touch') == 'undefined') {
                isInner = true;
                jqTarget = jqTarget.closest('[data-touch]');
            }

            $('[data-touch]').not(jqTarget).removeClass(function () {
                var className = $(this).data('touch') || 'z-touch';
                $(this).removeClass(className);
            });

            // 点击[data-touch]本身 或 [data-touch]的子元素时
            if (jqTarget.length != 0) {
                // z-touch 为默认类名
                var className = jqTarget.data('touch') || 'z-touch';
                // 已加指定类名则去除，否则加上指定类名
                if (!jqTarget.hasClass(className)) {
                    jqTarget.addClass(className);
                }
                else {
                    if (!isInner) {
                        jqTarget.removeClass(className);
                    }
                }
            }
        });
    }
    _lazyLoad();
});

$.Listeners.sub('ready.second').onsuccess(function () {
    _lazyLoad();
});

$.Listeners.sub('ready.third').onsuccess(function () {
    _lazyLoad();
});

function _lazyLoad () {
    if ($('img.lazy').length) {
        $.Loader.advScript( {
            name : 'lazyload',
            url : VIPSHOP.staticJs +'/plugins/lazyload.js?'+ VIPSHOP.jsVer
        }, {
            name : 'lazyloadAfter',
            def : function () { $('img.lazy').lazyload({threshold : 200}); },
            requires : ['lazyload']
        } );
    }
} 

//  创建一个间隔为1s的计时器
//  http://redmine.tools.vipshop.com/issues/84200
setInterval(function () {
    $.Listeners.pub('interval').success();
}, 1000);
//placeholder组件
if (!('placeholder' in document.createElement("input"))) {
    VIPSHOP.Placeholder = {
        /**
         * [初始化，添加监听事件]
         */
        init : function () {
            this.addEvent();
            this.addStyle();
        },
        /**
         * [销毁当前处理]
         */
        destroy : function () {
            this.removeEvent();
            this.removeStyle();
        },
        /**
         * [文本框选择器]
         * @type {String}
         */
        selecter : '[placeholder]',
        /**
         * [伪placeholder文本元素选择器]
         * @type {String}
         */
        tipsSelecter : '.J_placeholder',
        /**
         * [监听事件Id，用于监听事件命名空间]
         * @type {String}
         */
        eventId : '.placeholder',
        /**
         * [初始化的时候会修改.J_placeholder类样式，页面将添加一个style，此为style元素id]
         * @type {String}
         */
        styleId : 'vipStyle_' + new Date().getTime(),
        /**
         * [修改.J_placeholder 类display:block，即.J_placeholder类显示]
         */
        addStyle : function () {
            var div = document.createElement('div');
            div.innerHTML = "+<style id='"+ this.styleId +"'>"+ this.tipsSelecter +"{display:block;}</style>";
            $('head:first').append(div.childNodes[1]);
            div = null;
        },
        /**
         * [删除.J_placeholder 类的修改，即.J_placeholder 类元素不显示]
         */
        removeStyle : function () {
            var $style = $('#'+ this.styleId );
            if (!$style[0]) return this;
            try {
                $style[0].styleSheet.cssText = '';
            } catch(e) {

            }
            $style.remove();
        },
        /**
         * [获取当前文本框伪placeholder提示元素]
         * @param  {[$jqueryObj]} $target [当前目标元素]
         * @return {[$jqueryObj]}         [伪placeholder提示元素]
         */
        getTips : function ($target) {
            return $target.parent().find( this.tipsSelecter );
        },
        /**
         * [keyup事件处理]
         * @param  {[$jqueryObj]} $target [当前目标元素]
         */
        keyup : function ($target) {
            var val = $.trim( $target.val() ),
                $tips = this.getTips( $target );
            if (!$tips[0]) return;
            if (!val.length) {
                $tips.show();
            } else {
                $tips.hide();
            }
        },
        /**
         * [keypress事件处理]
         * @param  {[$jqueryObj]} $target [当前目标元素]
         */
        keypress : function ($target) {
            var val = $.trim( $target.val() ),
                $tips = this.getTips( $target );
            if (!$tips[0]) return;
            $tips.hide();
        },
        /**
         * [添加监听事件]
         */
        addEvent : function () {
            var self = this,
                selecter = self.selecter,
                eventId = self.eventId,
                tipsSelecter = self.tipsSelecter,
                $body = $('body');
            $body.delegate( selecter, 'keyup' + eventId, function(e){
                self.keyup( $( e.target ) );
            });
            $body.delegate( selecter, 'keypress' + eventId, function(e){
                self.keypress( $( e.target ) );
            });
            //点击提示文本，触发input focus事件
            $body.delegate( tipsSelecter, 'click' + eventId, function(e){
                var $target = $( e.target ),
                    $input = $target.parent().find( self.selecter );
                $input[0] && $input.focus();
            });
        },
        /**
         * [删除监听方法]
         */
        removeEvent : function () {
            $("body").undelegate( this.eventId );
            $body.undelegate( this.selecter, 'placeholder' );
        }
    }

    $(function () {
        VIPSHOP.Placeholder.init();
    });
}
$.Mod = (function () {
    var Collection = {};
    var errorMsg = 'depMods only accept string type value!';

    return {
        /**
         * 获取模块
         * @param  {String} modName 需要获取的模块名称，如果为空则获取全部模块。
         * @return {Object}         对应模块的内容
         *                          {
         *                              level : ,
         *                              depMods : 
         *                          }
         */
        'get' : function (modName) {
            return modName ? Collection[modName] : Collection;
        },
        /**
         * 添加模块
         * @param  {String} modName 模块名名称
         * @param  {Number} ready   ready等级  
         *                          // 1 -> domReady, 
         *                          // 2 -> 5 seconds after onload,
         *                          // 3 -> after 2
         * @param  {String} depMods 所依赖的模块，多模块时用逗号链接  
         *                          // 'nsA.init,nsB.init'
         */
        'add' : function (modName, ready, depMods) {
            //default value
            depMods = depMods || '';
            ready = ready || 1;

            if ($.type(depMods) != 'string') {
                VIPSHOP.log(errorMsg);
            }

            Collection[modName] = {
                'level' : ready,
                'depMods' : depMods
            }
        },
        'init' : function () {
            for (var modName in Collection) {
                var curMod = Collection[modName];
                var Listenfunc = function (modName) {
                    return function () {
                        eval(modName + '.init()');
                    }
                }(modName);

                if (!curMod.depMods) {
                    //没依赖模块，按ready等级来初始化
                    var levelMaps = {
                        1 : 'ready.first',
                        2 : 'ready.second',
                        3 : 'ready.third'
                    }

                    var levelName = levelMaps[curMod.level];

                    $.Listeners
                        .sub(levelName)
                        .onsuccess(Listenfunc);
                }
                else {
                    //有依赖模块，则订阅所依赖模块初始化
                    $.Listeners
                        .sub(curMod.depMods)
                        .onsuccess(Listenfunc);
                }
            }
        }
    }
})();$.Var = (function () {
    var Collection = {};
    var lastMethods = {
        'set' : function (currObj, key, value) {
            currObj[key] = value;
            return currObj[key];
        },
        'get' : function (currObj, key) {
            return currObj[key];
        },
        'del' : function (currObj, key) {
           delete currObj[key];
        },
        'add' : function (currObj, key, value) {
            currObj[key] = $.extend(currObj[key], value);
        }
    }

    var objCtrl = function (type, variable, value) {
        var varArr = variable.split('.');
        var currObj = Collection;
        var lastEvt = lastMethods[type];

        for (var i = 0, len = varArr.length; i < len; i++) {
            if (i == len - 1) {
                // last
                return lastEvt(currObj, varArr[i], value);
            }
            else {
                // iter
                if (type == 'get' &&
                    !currObj[varArr[i]]) {
                    return;
                }

                if ($.type(currObj[varArr[i]]) == 'object') {
                    currObj[varArr[i]] = currObj[varArr[i]];
                }
                else {
                    currObj[varArr[i]] = {};
                }
                currObj = currObj[varArr[i]];
            }
        }

    }

    return {
        'set' : function (variable, value) {
            return objCtrl('set', variable, value);
        },
        'get' : function (variable) {
            return objCtrl('get', variable);
        },
        'del' : function (variable) {
            return objCtrl('del', variable);
        },
        'add' : function (variable, value) {
            objCtrl('add', variable, value);
        }
    }
})();