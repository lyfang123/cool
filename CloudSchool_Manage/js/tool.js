(function (a) {
    a.error = function(){}
    //创建屏蔽层s
    a.creatCover = function (id, obj, zIndex, opacity, background, position,unScroll0) {
        var _id = (id == undefined) ? "creatCover" : id;
        var _zIndex = (zIndex == undefined) ? "9999999" : zIndex;
        var _opacity = (opacity == undefined) ? "0.01" : opacity;
        var _background = (background == undefined) ? "#ffffff" : background;
        var _position = (position == undefined) ? "absolute" : position;
        var left = 0;
        var top = 0;
        var width = 0;
        var height = 0;
        if (obj == window || obj == null) {
            width = $(window).width();
            height = $(window).height();
            $("body").append("<div id='" + id + "'></div>");
            if(!unScroll0) $(window).scrollTop(0);
        } else {
            left = obj.offset().left;
            top = obj.offset().top;
            width = obj.width();
            height = obj.height();
            obj.append("<div id='" + id + "'></div>");
            if(!unScroll0) obj.scrollTop(0);
        }
        $("#" + id).css({
            position: _position,
            zIndex: _zIndex,
            left: left + 'px',
            top: top + 'px',
            opacity: _opacity,
            background: _background,
            width: (obj == window ? "100%" : width + 'px'),
            minHeight: height + 'px'
        });
    }
    //loading
    a.loading = function (zIndex, opacity) {
        this.msg = "请稍候...";
        this.isOpen = false;
        this.rgba = "255,255,255," + opacity;
        this.color = "#000000";
        this.imageSrc = "<img src='images/load.gif' style='margin-top:20px;margin-bottom:5px;' width='32' />";
        this.image = $(this.imageSrc);
        this.id = null;
        this.open = function (msg, obj, rgba, color) {
            this.id = "load" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
            this.obj = obj || $(window);
            if (!this.isOpen) {
                var loadImg = this.imageSrc;
                msg = msg == undefined ? this.msg : msg;
                rgba = rgba == undefined ? this.rgba : rgba;
                color = color == undefined ? this.color : color;
                var loadText = "<div>" + msg + "</div>";
                var loadBox = "<div id='" + this.id + "' style='text-align:center;border-radius :5px;height:100px;width:100px;position:fixed;z-index:" + zIndex + ";background:rgba(" + rgba + ");color:" + color + ";left:" + ((this.obj.width() - 100) / 2 + this.obj.offset().left) + "px;top:" + ((this.obj.height() - 100) / 2 + this.obj.offset().top) + "px'>" + loadImg + loadText + "</div>";
                if (this.obj == window) {
                    $("body").append(loadBox);
                } else {
                    this.obj.append(loadBox);
                }
                a.creatCover(this.id + "_cover", this.obj, zIndex - 1, "0", "#ffffff");
                this.isOpen = true;
            }
        },
            this.close = function () {
                this.isOpen = false;
                $("#" + this.id + "_cover").remove();
                $("#" + this.id).remove();
            },
            this.status = function () {
                return this.isOpen;
            }
    }
    a.log = function () {
        if (console.log) {
            for(var i = 0; i < arguments.length; i++){
                console.log(arguments[i]);
            }
        }
    }
    a.confirmx = function (s, endFn,initFn,verifyFn) {
        var w = new a.Windows();
        w.width = 300;
        w.height = 150;
        w.title = "提示";
        w.content = "<div style='text-align: center; padding-top:10px;'>" + s + "</div>";
        w.bottom = "<div style='text-align: right;margin-right:10px;margin-top: 10px;'><span id='alertOk" + w.id + "' style='padding:5px 15px; border-radius: 3px;cursor: pointer;margin-right:10px;' class='bg_00b7ee'>确定</span><span id='alertCancle" + w.id + "' style='padding:5px 15px; border: 1px solid #e4e4e4; border-radius: 3px;cursor: pointer;'>取消</span></div>";
        w.open();
        if(initFn) initFn();
        $("#alertOk" + w.id).click(function () {
            if(verifyFn){
                if(verifyFn()){
                    if (endFn) endFn();
                    w.close();
                }
            }else{
                if (endFn) endFn();
                w.close();
            }
        })
        $("#alertCancle" + w.id).click(function () {
            w.close();
        })
    }
    a.alertx = function (s, fn) {
        var w = new a.Windows();
        w.width = 300;
        w.height = 150;
        w.title = "提示";
        w.content = "<div style='text-align: center; padding-top:10px;'>" + s + "</div>";
        w.bottom = "<div style='text-align: right;margin-right:10px;margin-top: 10px;'><span id='alert" + w.id + "' style='padding:5px 15px; border-radius: 3px;cursor: pointer;' class='bg_00b7ee'>确定</span></div>"; 
        w.open();
        $("#alert" + w.id).click(function () {
            w.close();
            if (fn) fn();
        })
    }
//日历.
    a.Calendar = function (obj, date,timeType) {
        this.type = "";//默认选择年月日的日历    year 只可以年份的日历  month 只选择年份及月份的日历
        this.date = new Date();//当前选择时间
        this.selectedDate = new Date();
        this.yearRange = [1900, 2060];//年限范围
        this.timeType = timeType || 0;//0无 1时 2时分 3时分秒
        this.maxDate = null;
        this.minDate = null;
        if (typeof date == "string") {
            var _date = new Date();
            if (/^\d{4}-\d{1,2}-\d{1,2}/.test(date)) {
                var year = parseInt(date.split("-")[0], 10);
                year = year < this.yearRange[0] ? this.yearRange[0] : year;
                year = year > this.yearRange[1] ? this.yearRange[1] : year;
                var month = parseInt(date.split("-")[1], 10);
                month = month < 1 ? 1 : month;
                month = month > 12 ? 12 : month;
                var day = parseInt(date.split("-")[2], 10);
                var month12 = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                month12[1] = (year % 4 == 0 && year % 100 != 4) || year % 400 == 0 ? 29 : 28;
                day = day < 1 ? 1 : day;
                day = day > month12[month - 1] ? month12[month - 1] : day;
                _date.setFullYear(year, month - 1, day);
            } else if (/^\d{4}-\d{1,2}/.test(date)) {
                this.type = "month";
                var year = parseInt(date.split("-")[0], 10);
                year = year < this.yearRange[0] ? this.yearRange[0] : year;
                year = year > this.yearRange[1] ? this.yearRange[1] : year;
                var month = parseInt(date.split("-")[1], 10);
                month = month < 1 ? 1 : month;
                month = month > 12 ? 12 : month;
                _date.setFullYear(year, month - 1);
            } else if (/^\d{4}/.test(date)) {
                this.type = "year";
                var year = parseInt(date, 10);
                year = year < this.yearRange[0] ? this.yearRange[0] : year;
                year = year > this.yearRange[1] ? this.yearRange[1] : year;
                _date.setFullYear(year);
            }
            this.date.setFullYear(_date.getFullYear(), _date.getMonth(), _date.getDate());
            this.selectedDate.setFullYear(_date.getFullYear(), _date.getMonth(), _date.getDate());
        }
        this.obj = obj;
        obj.css({background:"url(images/icon_calendar.png) 3px 10px no-repeat",paddingLeft:"25px",width:(obj.width() - 22) +'px'});
        obj.attr("readonly","readonly");
        this.id = null;
        this.width = 350;
        this.action = null;//选中后的动作
        this.finalDate = null;
        this.toString = function () {
            var rc = "";
            if (this.type == "year") { 
                this.finalDate = !!this.minDate && this.date < this.minDate ? this.minDate : this.date;
                this.finalDate = !!this.maxDate && this.date > this.maxDate ? this.maxDate : this.finalDate;
                this.finalDate = !!this.minDate && !!this.maxDate && this.minDate > this.maxDate ? this.date : this.finalDate;
                rc = this.finalDate.getFullYear();
            } else if (this.type == "month") {
                this.finalDate = !!this.minDate && this.date < this.minDate ? this.minDate : this.date;
                this.finalDate = !!this.maxDate && this.date > this.maxDate ? this.maxDate : this.finalDate;
                this.finalDate = !!this.minDate && !!this.maxDate && this.minDate > this.maxDate ? this.date : this.finalDate;
                var month = this.finalDate.getMonth() + 1;
                rc = this.finalDate.getFullYear() + "-" + (month < 10 ? "0" + month : month);
            } else {
                this.finalDate = !!this.minDate && this.selectedDate < this.minDate ? this.minDate : this.selectedDate;
                this.finalDate = !!this.maxDate && this.selectedDate > this.maxDate ? this.maxDate : this.finalDate;
                this.finalDate = !!this.minDate && !!this.maxDate && this.minDate > this.maxDate ? this.selectedDate : this.finalDate;
                var month = this.finalDate.getMonth() + 1;
                var date = this.finalDate.getDate();
                rc = this.finalDate.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date);
                if(this.timeType == 1){
                    rc += " 23";
                }else if(this.timeType == 2){
                    rc += " 23:59";
                }else if(this.timeType == 3){
                    rc += " 23:59:59";
                }
            }
            return rc;
        }
        if(date){
            obj.val(date);
        }else{
            this.selectedDate = new Date();
            obj.val(this.toString());
        }
        this.open = function (fn) {
            if(!a.Calendar.isShow){
                a.Calendar.isShow = true;
                var _this = this;
                this.id = "calendar" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
                var scrollTop = $(window).scrollTop();
                a.creatCover(this.id + "_cover", window, "9999", "0", "#ffffff", "fixed");
                var calendarBox = "<div id='" + this.id + "' class='unSelect boxShadow' style='border-radius :5px;width:" + (this.width + 20) + "px;position:absolute;z-index:99999;left:" + this.obj.offset().left + "px;top:" + (this.obj.offset().top + this.obj.height() + 5) + "px;text-align:center;background:#555555;background:rgba(10,10,10,0.8);min-height:200px;color:#fff'>";
                calendarBox += "</div>";
                $("body").append(calendarBox);
                $(window).scrollTop(scrollTop);
                this.show();
                $("#" + this.id + "_cover").click(function () {
                    _this.close();
                });
                if (fn && typeof fn == "function") this.action = fn;
            }
        }
        this.show = function (type,noAnimate) {
            if (this.type == "year" && type != "year") type = "year";//年份日历
            if (this.type == "month" && type != "month" && type != "year") type = "month";//月份日历
            var top = "", style = "<style>", center = "<div class='dateContent'><div id='dateContent'><div id='dateContentBox'>";
            var width = this.width / 7;
            style += noAnimate ? "" : "@keyframes dateContent{from {transform:scale(0.5,0.5);} to {transform:scale(1,1);}}";
            style += ".dateContent{width: " + this.width + "px;height:" + this.width + "px;overflow: hidden;margin:0px 10px;animation: dateContent 0.5s;}";
            style += "#dateContent{width: " + (this.width + 20) + "px;height:" + (this.width + 20) + "px;overflow: scroll;}";
            style += ".dateTitle{text-align: right;height: 50px; line-height: 50px;overflow: hidden;margin-top:10px;}";
            style += ".dayTitle{text-align: center;height: " + width + "px; line-height: " + width + "px;overflow: hidden;width:" + width + "px;float:left;}";
            if (type == "year") {
                width = this.width / 5;
                style += ".dateSpan{cursor: pointer;width:" + width + "px;height:" + width + "px; text-align:center;line-height:" + width + "px;float:left;overflow: hidden;border-radius :" + width + "px}";
                top = "<div class='dateTitle'><span style='float: left; margin-left: 10px;color:#777777'>" + this.yearRange[0] + "-" + this.yearRange[1] + "</span><span style='margin-right: 10px; border:1px solid #fff; border-radius: 3px;padding:0px 5px; cursor: pointer;' id='clearBtn'>清空</span></div>";
                for (var i = this.yearRange[0]; i <= this.yearRange[1]; i++) {
                    center += "<div class='" + (i == this.date.getFullYear() ? "dateSpan dateSpanActive" : "dateSpan") + " years'>" + i + "</div>";
                }
            } else if (type == "month") {
                width = this.width / 5;
                style += ".dateSpan{cursor: pointer;width:" + width + "px;height:" + width + "px; text-align:center;line-height:" + width + "px;float:left;overflow: hidden;border-radius :" + width + "px}";
                top = "<div class='dateTitle'><span style='float: left; margin-left: 10px;cursor: pointer;' id='setYearBtn'>" + this.date.getFullYear() + " 年<font style='font-size: 12px; color: #b0b0b0;margin-left: 5px;'>选择年份</font></span><span style='margin-right: 10px; border:1px solid #fff; border-radius: 3px;padding:0px 5px; cursor: pointer;' id='clearBtn'>清空</span></div>";
                for (var i = 1; i <= 12; i++) {
                    center += "<div class='" + (i == this.date.getMonth() + 1 ? "dateSpan dateSpanActive" : "dateSpan") + " months'>" + i + "月</div>";
                }
            } else {
                style += ".dateSpan{cursor: pointer;width:" + width + "px;height:" + width + "px; text-align:center;line-height:" + width + "px;float:left;overflow: hidden;border-radius :" + width + "px}";
                top = "<div class='dateTitle'><span style='float: left; margin-left: 10px;cursor: pointer;' id='setMonthBtn'>" + this.date.getFullYear() + " 年 " + (this.date.getMonth() + 1) + " 月<font style='font-size: 12px; color: #b0b0b0;margin-left: 5px;'>选择月份</font></span><span style='margin-right: 10px; border:1px solid #fff; border-radius: 3px;padding:0px 5px; cursor: pointer;' id='clearBtn'>清空</span><span id='todayBtn' style='margin-right: 20px; border:1px solid #fff; border-radius: 3px;padding:0px 5px; cursor: pointer;'>今日</span><span id='prevMonthBtn' style='margin-right: 20px;cursor: pointer;font-size: 20px;'><</span><span id='nextMonthBtn' style='margin-right: 20px;cursor: pointer;font-size: 20px;'>></span></div>";
                center += "<div><div class='dayTitle'>日</div><div class='dayTitle'>一</div><div class='dayTitle'>二</div><div class='dayTitle'>三</div><div class='dayTitle'>四</div><div class='dayTitle'>五</div><div class='dayTitle'>六</div><div style='clear:both'></div></div>";
                for (var i = 0; i < this.date.getDay(); i++) {
                    center += "<div class='dateSpan dateSpanNo'> </div>";
                }
                var months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                months[1] = (this.date.getFullYear() % 4 == 0 && this.date.getFullYear() % 100 != 4) || this.date.getFullYear() % 400 == 0 ? 29 : 28;//闰年判断
                for (var i = 1; i <= months[this.date.getMonth()]; i++) {
                    center += "<div class='" + (i == this.selectedDate.getDate() && this.selectedDate.getMonth() == this.date.getMonth() && this.selectedDate.getFullYear() == this.date.getFullYear() ? "dateSpan dateSpanActive" : "dateSpan") + " days'>" + i + "</div>";
                }
            }
            center += "<div style='clear:both;'></div>";
            center += "</div></div></div>";
            style += ".dateSpan:hover{background:#636363;}";
            style += ".dateSpanNo{cursor: default;}";
            style += ".dateSpanNo:hover{background:#555555;}";
            style += ".dateSpanActive{background:#2982cc;}";
            style += ".dateSpanActive:hover{background:#328dd8;}";
            style += "</style>";
            $("#" + this.id).html(top + center + style);
            if (type == "year") {
                $("#dateContent").scrollTop(($("#dateContentBox").height() - $("#dateContent").height()) * ((this.date.getFullYear() - this.yearRange[0]) / (this.yearRange[1] - this.yearRange[0])));
            }
            var _this = this;
            $(".years").click(function () {
                _this.date.setFullYear($(this).html());
                _this.show("month",(_this.type == "year"));
                if (_this.type == "year" && _this.action) _this.action(_this.toString());
            });
            $(".months").click(function () {
                _this.date.setMonth($(this).html().replace("月", "") - 1);
                _this.show("",(_this.type == "month"));
                if (_this.type == "month" && _this.action) _this.action(_this.toString());
            });
            $(".days").click(function () {
                _this.selectedDate.setFullYear(_this.date.getFullYear(), _this.date.getMonth(), $(this).html());
                _this.show("",true);
                if (_this.action) _this.action(_this.toString());
            });
            $("#setMonthBtn").click(function () {
                _this.show("month");
            });
            $("#setYearBtn").click(function () {
                _this.show("year");
            });
            $("#clearBtn").click(function(){
                if (_this.action) _this.action("");
            })
            $("#todayBtn").click(function () {
                var d = new Date();
                _this.date.setFullYear(d.getFullYear(), d.getMonth(), d.getDate());
                _this.selectedDate.setFullYear(d.getFullYear(), d.getMonth(), d.getDate());
                _this.show();
                if (_this.action) _this.action(_this.toString());
            });
            $("#prevMonthBtn").click(function () {
                var year = _this.date.getFullYear();
                var month = _this.date.getMonth();
                if (month == 0) {
                    if (year == _this.yearRange[0]) {
                        log("不能超过最低年限");
                    } else {
                        year--;
                        month = 11;
                    }
                } else {
                    month--;
                }
                _this.date.setFullYear(year, month);
                _this.show("",true);
            });
            $("#nextMonthBtn").click(function () {
                var year = _this.date.getFullYear();
                var month = _this.date.getMonth();
                if (month == 11) {
                    if (year == _this.yearRange[1]) {
                        log("不能超过最高年限");
                    } else {
                        year++;
                        month = 0;
                    }
                } else {
                    month++;
                }
                _this.date.setFullYear(year, month);
                _this.show("",true);
            });
        }
        this.close = function () {
            a.Calendar.isShow = false;
            $("#" + this.id + "_cover").remove();
            $("#" + this.id).remove();
        }
    }
    a.Calendar.isShow = false;//只能显示一个日历

    a.Radio = function (arr) {
        this.objs = arr;
        this.activeObj = arr[0];
        var _this = this;
        this.objs.click(function () {
            _this.activeObj = $(this)[0];
            _this.show();
        });
        this.show = function () {
            this.objs.each(function () {
                $(this)[0].checked = _this.activeObj == $(this)[0] ? true : false;
            });
        }
        this.val = function (s) {
            if (s == undefined) {
                return $(this.activeObj).val();
            } else {
                this.objs.each(function () {
                    if ($(this).val() == s) {
                        _this.activeObj = $(this)[0];
                        _this.show();
                    }
                });
            }
        }
        this.show();
    }

    a.Checkbox = function(arr){
        this.val = function(s){
            if(s == undefined){
                var rc = [];
                for(var i = 0; i < arr.length; i++){
                    if(arr[i].checked) rc.push(arr[i].value);
                }
                return rc.join(",");
            }else{
                var sArr = s.split(",");
                for(var i = 0; i < sArr.length; i++){
                    for(var j = 0; j < arr.length; j++){
                        if(arr[j].value == sArr[i]){
                            arr[j].checked = true;
                        }
                    }
                }
            }
        }
    }

    a.Img = function (obj,emty,noEdit) {
        this.img = "";
        var _this = this;
        obj.html(emty);
        //obj.css({background:"#f9f9f9"});
        if(!noEdit){
            obj.click(function(){
                a.upload("accept='.jpeg,.jpg,.gif,.png'", function (res, filesData) {
                    if (res && res.length > 0) {
                        _this.img = qiniouIP + res[0].key;
                        obj.html("<img src='" + qiniouIP + res[0].key + "' style='cursor: pointer;' />");
                    }
                },1 * 1024 * 1024 * 100)
            })
        }
        this.val = function (s) {
            if (!s) {
                return this.img;
            } else {
                this.img = s;
                obj.html("<img src='" + s + "' style='cursor: pointer;' />");
            }
        }
    }

    a.ImgList = function (obj,n,maxSize) {
        this.obj = obj;
        this.size = 0;
        this.uploadObjShow = this.obj.next();//显示元素
        var _this = this;
        n = !n ? 100 : n;
        this.uploadObjShow.click(function () {
            if(_this.size < n){
                a.upload("accept='.jpeg,.jpg,.gif,.png'", function (res, filesData) {
                    if (res && res.length > 0) {
                        var images = "";
                        for (var i = 0; i < res.length; i++) {
                            images += (qiniouIP + res[i].key) ? "<img title='双击可删除本图片' src='" + qiniouIP + res[i].key + "' />" : "";
                            _this.size ++;
                        }
                        obj.append(images);
                    }
                },maxSize)
            }else{
                alertx("最多上传" + n + "张图片");
            }
        })
        this.obj.dblclick(function (e) {
            var img = e.target;
            confirmx("确定删除？", function () {
                if (img) $(img).remove();
                _this.size --;
            })
        })
        this.val = function (s) {
            if (!s) {
                var value = "";
                var imgs = obj.find("img");
                for (var i = 0; i < imgs.size(); i++) {
                    value += i == 0 ? imgs.eq(i).attr("src") : ("$$&&" + imgs.eq(i).attr("src"));
                }
                return value;
            } else {
                s = s.split("$$&&");
                var imgs = "";
                for (var i = 0; i < s.length; i++) {
                    imgs += s[i] ? "<img src='" + s[i] + "' />" : "";
                    this.size ++;
                }
                obj.html(imgs);
            }
        }
    }
    a.Person = function(obj,clickObj,url, data, dataString,UID,valueString,relevanceFn){
        this.value = [];
        var _this = this;
        this.id = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        var timer;
        var target;
        obj.after("<b style='display:none;position:absolute;background:#ff6450;color:#fff;border-radius:10px;padding:2px 6px;line-height:100%; cursor:pointer;' id='removePerson" + this.id + "'>-</b>");
        $("#removePerson" + _this.id).bind("mouseover",function(){
            clearTimeout(timer);
        })
        $("#removePerson" + _this.id).bind("click",function(){
            $(this).hide();
            var $this = target;
            if($this){
                a.confirmx("确定删除？",function(){
                    var _UID = $this.attr(UID);
                    var rc = [];
                    for(var i = 0; i < _this.value.length; i++){
                        if(_UID != _this.value[i][UID]){
                            rc.push(_this.value[i]);
                        }
                    }
                    _this.value = rc;
                    $this.remove();
                    selectBox.hasValue = _this.val();
                })
            }
        })
        var selectBox = new a.InputSelect(clickObj,url,data,dataString,0,function(res){
            if(res.length > 0){
                var r = relevanceFn ? relevanceFn().value : [];
                for(i = 0; i < res.length; i++){
                    var has = false;
                    for(var j = 0; j < _this.value.length; j++){
                        if(_this.value[j][UID] == res[i][UID]) has = true;
                    }
                    //关联判断
                    for(j = 0; j < r.length; j++){
                        if(r[j][UID] == res[i][UID]) has = true;
                    }
                    if(!has){
                        _this.value.push(res[i]);
                    }
                }
                selectBox.hasValue = _this.val();
                _this.append();
            }
        })
        this.val = function(s){
            if(s){
                this.value = s;
                this.append();
                selectBox.hasValue = this.val();
            }else{
                var rc = [];
                for(var i = 0; i < this.value.length; i++){
                    rc.push(this.value[i][UID]);
                }
                rc = rc.join(",");
                return rc;
            }
        }
        this.append = function(){
            var html = "";
            for(var i = 0; i < this.value.length; i++){
                var list = valueString;
                for(var k in this.value[i]){
                    var v = this.value[i][k];
                    list = list.replace(new RegExp("[$]{" + k + "}","g"), v);
                    list = list.replace(new RegExp("[$]listStyle{" + k + "}","g"), v && v.length > 0 ? v[0] : "?");
                }
                html += list;
            }
            obj.html(html);
            obj.find("img").each(function(){
                var $this = $(this);
                var url = $this.attr("url");
                var img = new Image();
                img.onload = function(){
                    $this.attr("src",url);
                    $this.removeAttr("url");
                }
                img.src = url;
            })
            obj.children().each(function(i){
                $(this).bind("mouseover",function(){
                    if(timer) clearTimeout(timer);
                    var left = $(this).offset().left,top = $(this).offset().top,width = $(this).width();
                    left = left + (width - 10),top = top - 10;
                    $("#removePerson" + _this.id).css({left:left + "px",top:top + "px",display:"block"});
                    if(target) target.css({background:"#ffffff"});
                    target = $(this);
                    $(this).css({background:"#f7f7f7"});
                })
                $(this).bind("mouseout",function(){
                    timer = setTimeout(function(){
                        $("#removePerson" + _this.id).hide();
                        target.css({background:"#ffffff"});
                    },1000);
                })
            })
        }
    }
    a.upload = function (multiple, fn,maxSize,_fn) {
        if ($("#upLoadInput").size() > 0) $("#upLoadInput").remove();
        var input = "<input " + multiple + " id='upLoadInput' type=\"file\" style='display:none' />";
        $("body").append(input);
        $("#upLoadInput").click();
        $("#upLoadInput").change(function () {
            var v = $(this).val();
            if (v) {
                var formData = new FormData();
                var files = document.getElementById('upLoadInput').files;
                var filesData = [];
                var errorFileType = [];
                var filesName = [];
                var overSizeFile = [];//超出大小图片
                var accept = $("#upLoadInput").attr("accept");
                accept = accept ? accept.toUpperCase() : accept;
                for (var i = 0; i < files.length; i++) {
                    if (accept && accept.indexOf(files[i].name.replace(/^.+\.(.+)$/, "$1").toUpperCase()) == -1) {
                        errorFileType.push(files[i].name);
                    }
                    if(maxSize && files[i].size > maxSize) overSizeFile.push(files[i].name);
                    filesData.push(files[i]);
                    filesName.push(files[i].name);
                    formData.append('key', files[i].name);
                    formData.append('token', token);
                    formData.append('file', files[i]);
                }
                if (errorFileType.length > 0) {
                    a.alertx(errorFileType.join(",") + " 文件类型不符，请选择正确的文件类型");
                    return false;
                }
                if (overSizeFile.length > 0) {
                    a.alertx(overSizeFile.join(",") + " 过大,请重新选择");
                    return false;
                }
                var upLoadId = "upLoadId" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
                var _showName = filesName.join(",");
                var showName = _showName.replace(/^(.{1,10})(.*)$/,function($0,$1,$2){
                    if($2.length > 3){
                        return $1 + "...";
                    }else{
                        return $0;
                    }
                });
                if($("#upLoadBox").size() == 0) $("body").append("<div id='upLoadBox'></div>");
                $("#upLoadBox").append("<div class='upLoad' title='" + _showName + "' id='" + upLoadId + "'><span class='upLoad_name'>正在上传 " + showName + "</span><div class='upLoad_loaded'></div><span class='upLoad_close' title='关闭'>x</span></div>");
                var isCancle = false;
                $("#" + upLoadId).find(".upLoad_close").click(function(){
                    isCancle = true;
                    xhr.abort();
                    closeNode();
                })
                var xhr = new XMLHttpRequest();
                xhr.upload.onprogress = function (e) {
                    if (e.lengthComputable) {
                        $("#" + upLoadId).find(".upLoad_loaded").css({width:Math.round(e.loaded / e.total * 100) + "%"});
                    }
                }
                xhr.onload = function (e) {
                    $("#" + upLoadId).find(".upLoad_name").html(showName + " 上传完成");
                    $("#" + upLoadId).find(".upLoad_loaded").css({animation:'none'});
                    $("#upLoadInput").remove();
                    if (xhr.responseText) {
                        var res = xhr.responseText;
                        try {
                            res = [JSON.parse(xhr.responseText)];
                        } catch (e) {
                            a.alertx("上传完成，返回数据格式错误");
                        }
                        fn(res, filesData);
                    } else {
                        a.alertx("上传失败,返回错误", function () {
                            fn(false);
                        });
                    }
                    setTimeout(closeNode,5000);
                }
                xhr.onabort = function (e) {
                    $("#" + upLoadId).find(".upLoad_name").html(showName + " 上传失败,重定向");
                    $("#" + upLoadId).find(".upLoad_loaded").css({animation:'none'});
                    $("#upLoadInput").remove();
                    a.alertx(isCancle ? "用户取消上传" : "上传失败,重定向", function () {
                        fn(false);
                    });
                    if(isCancle) isCancle = false;
                }
                xhr.onerror = function (e) {
                    $("#" + upLoadId).find(".upLoad_name").html(showName + " 上传失败");
                    $("#" + upLoadId).find(".upLoad_loaded").css({animation:'none'});
                    $("#upLoadInput").remove();
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        a.alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }else{
                        a.alertx("上传失败,请求出错", function () {
                            fn(false);
                        });
                    }
                }
                xhr.open("POST", domainUrl);
                xhr.setRequestHeader('token', token);
                xhr.send(formData);
                if(_fn) _fn(upLoadId);
                function closeNode(){
                    $("#" + upLoadId).animate({opacity:0},1000,function(){
                        $("#" + upLoadId).remove();
                        if($(".upLoad").size() == 0){
                            $("#upLoadBox").remove();
                        }
                    })
                }
            }
        })
    }

    a.download = function (url) {
        var id = "downIframe" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        $("body").append("<iframe class='downIframe' id='" + id + "' style='display: none;' src='" + url + "'></iframe>");
        setTimeout(function () {
            $("#" + id).remove();
        }, 1000)
    }

    a.RichEdit = function (obj) {
        this.id = "edit" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        var file = "<img style='position: absolute;margin-left:420px;margin-top:7px;display:none;cursor: pointer;' id='" + this.id + "_uploadFileBtn' src='images/icon_img.png' />";
        var _this = this;
        setTimeout(function () {//插入"上传图片"按钮
            $("#" + _this.id + "_uploadFileBtn").show();
            $("#" + _this.id + "_uploadFileBtn").click(function () {
                upload(yunIP + "/api/cs/v0.1/public", "", function (res, filesData) {
                    if (res && res.length > 0) {
                        var files = "";
                        for (var i = 0; i < res.length; i++) {
                            var r = res[i];
                            var f = filesData[i];
                            if(filesData && f.type.indexOf("image") == 0){
                                var _img = new Image();
                                _img.onload = function(){
                                    var widthString = this.width > obj.width() ? "width='100%' " : "";
                                    files = r ? "<img style='max-width:100%' " + widthString + "src='" + r + "' data-ke-src='" + r + "' />" : "";
                                    _this.insertHtml(files);
                                }
                                _img.src = res[i];
                            }else{
                                files = r ? "<a href='" + r + "' data-ke-src='" + r + "' target='_blank'>" + (filesData && f.name ? f.name : r) + "</a>" : "";
                                _this.insertHtml(files);
                            }
                        }
                    }
                })
            })
        }, 1000);
        obj.append(file);
        obj.append("<iframe id='" + this.id + "_Iframe' width='" + (obj.width() + 20) + "' height='" + obj.height() + "' src='kindeditor/examples/simple.html' frameborder='no' border='0' marginwidth='0' marginheight='0' scrolling='no'></iframe>");
        var content = $("#" + this.id + "_Iframe")[0].contentWindow;
        content._ue = {width: obj.width() - 2, height: obj.height() + 5};
        this.val = function (s) {
            if(content.editor){
                if (!s) {
                    return content.editor.html();
                } else {
                    content.editor.html(s);
                }
            }else{
                if (s) setTimeout(function(){
                    _this.val(s);
                },1000);
            }
        }
        this.insertHtml = function (s) {
            content.editor.insertHtml(s);
        }
    }
//打开页面
    a.open = function () {
        if (arguments[0].url == "deskTop.html") {
            $(".rightContent").css({background: "none"});
        } else {
            $(".rightContent").css({background: "#fff"});
        }
        $(".adminDivActive").removeClass("adminDivActive");
        $("#right").nextAll().remove();//去除遗留的loading
        var rightLoading = new loading(999, 0);
        $("#right").html("");
        var url = "", title = "";
        for (var i = 0; i < arguments.length; i++) {
            url = arguments[i].url;
            title += "<span url='" + url + "'>" + arguments[i].title + "</span>";
            if (!open.history) open.history = [];
            open.history.push(arguments[i]);
        }
        $("#nav").html(title);
        $("#nav span").click(function () {
            open({url: $(this).attr("url"), title: $(this).html()});
        });
        rightLoading.open("加载中...", $(".rightContent"));
        $(".rightContent").css({marginTop: '50px', opacity: 0});
        $.ajax({
            url: url,
            type: "GET",
            async: false,
            dataType: "html",
            success: function (html) {
                rightLoading.close();
                $("#right").html(html);
                $(".rightContent").animate({marginTop: '10px', opacity: 1}, 300, function () {
                    if (html.indexOf("function load()") > -1) load();
                });
            },
            error: function () {
                rightLoading.close();
                $("#right").html("<div style='text-align: center; font-size: 30px; color: #e0e8f3; line-height: 300px;'>" + title + " 页面加载失败...</div>");
                $(".rightContent").animate({marginTop: '10px', opacity: 1}, 300);
            }
        })
        //清除云盘残余操作按钮
        $(".contextmenu").remove();
    }
//页面回退
    a.back = function (n) {
        open.history.length = open.history.length + n;
        open(open.history[open.history.length - 1]);
    }
//刷新页面
    a.refresh = function () {
        var h = open.history[open.history.length - 1];
        open.history.length = open.history.length - 1;
        open(h);
    }
    a.Ajax = function (url) {
        this.url = url;
        this.meth = "GET";
        this.data = null;
        this.header = [{key:"accessToken",vaule:accessToken},{key:"schoolId",vaule:schoolId}];
        this.loading = new loading(999, 0);
        this.title = "";
        var _this = this;
        this.do = function (fn) {
            this.loading.open("", $(".rightContent"));
            $.ajax({
                url: this.url,
                type: this.meth,
                dataType: "json",
                data: this.data,
                beforeSend: function (XMLHttpRequest) {
                    /*XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                    XMLHttpRequest.setRequestHeader('schoolId', schoolId);*/
                    for(var i = 0; i < _this.header.length; i ++){
                        XMLHttpRequest.setRequestHeader(_this.header[i].key, _this.header[i].vaule);
                    }
                },
                success: function (res) {
                    _this.loading.close();
                    if(fn) fn(res,_this.title);
                },
                error: function (e) {
                    _this.loading.close();
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }else{
                        if(fn) {
                            if(e.responseText){
                                fn(JSON.parse(e.responseText),_this.title);
                            }else{
                                fn();
                            }
                        }
                    }
                },
                fail: function (e) {
                    _this.loading.close();
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }else{
                        if(fn) {
                            if(e.responseText){
                                fn(JSON.parse(e.responseText),_this.title);
                            }else{
                                fn();
                            }
                        }
                    }
                }
            })
        }
    }
//获取参数
    a.getUrlParam = function (name) {
        var rc = "";
        try {
            rc = open.history[open.history.length - 1].data[name];
        } catch (e) {
            rc = "";
        }
        return rc; //返回参数值
    }
//window获取参数
    a.getParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return r[2];
        return null; //返回参数值
    }
    a.load = function () {
    }
    a.note = function (msg, type) {
        $("#note").html(msg);
        $(".note").show("fast");
    }
    a.List = function (url, paramet, header) {
        this.url = url;//请求地址
        this.paramet = paramet;//请求参数
        this.th = header || [];//表格头部
        this.total = 0;//记录总条数
        this.listNumTitle = "序号";//序号
        this.checkBtn = false;//是否使用选择框
        this.btns = "";//操作按钮
        this.btnsWidth = 100;
        this.action = null;
        this.isLoading = false;
        this.data = null;
        this.toExcelUrl = url;//导出路径
        this.table = null;
        this.toExcel = function(name){
            var th = this.th;
            for(var i = 0; i <= th.length; i++){
                for(var k in th[i]){
                    if(k == "EN" || k == "CH" || k == "OptionExcel"){
                    }else{
                       delete th[i][k]; 
                    }
                }
            }
            th = JSON.stringify(th);
            var params = this.paramet;
            delete params.pageSize;
            delete params.pageNum;
            params = JSON.stringify(params);
            var url = appIP + "/api/excel/v0.1/export?url=" + this.toExcelUrl + "&th=" + th + "&excelName=" + name + "&accessToken=" + accessToken + "&schoolId=" + schoolId + "&params=" + params;
            a.download(url);
        }
        this.reLoad = function (n) {
            n = !n ? 1 : n;
            this.paramet.pageNum = n;
            this.load();
        }
        this.load = function (fn) {
            var _this = this;
            _this.paramet.pageNum = !_this.paramet.pageNum ? 0 : _this.paramet.pageNum;//默认加载第一页
            _this.paramet.pageSize = !_this.paramet.pageSize ? 10 : _this.paramet.pageSize;//默认每页最大条数
            _this.table = !_this.table ? $("#table") : _this.table;
            var loadImg = new loading(999, 0);
            loadImg.open("数据加载中...", $(".rightContent"));
            _this.isLoading = true;
            _this.toExcelUrl = _this.url;
            $.ajax({
                url: this.url,
                type: "GET",
                dataType: "json",
                data: this.paramet,
                beforeSend: function (XMLHttpRequest)  {
                    XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                    XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                },
                success: function (res) {
                    _this.isLoading = false;
                    _this.data = res;
                    loadImg.close();
                    var table = "<table width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\">";
                    //处理分页
                    if (res && res.total > -1)
                        _this.total = res.total > -1 ? res.total : _this.total;
                    $("#pagaBtns").html("<font style='margin-right:10px;color:#999999;float:left;'>共 " + _this.total + " 条数据</font>");
                    if (_this.total > _this.paramet.pageSize) {
                        var nums = Math.ceil(_this.total / _this.paramet.pageSize);
                        var btns = "<span class='pagaBtn pagaBtnFirst'>首页</span><span class='pagaBtn'><</span>";
                        var flogNume = 3;
                        if (_this.paramet.pageNum - 1 >= flogNume) btns += "<span class='pagaBtn'>...</span>";
                        for (var i = 0; i < nums; i++) {
                            if (i > (_this.paramet.pageNum - 1) - flogNume && i < (_this.paramet.pageNum - 1) + flogNume) {
                                btns += "<span class='pagaBtn" + (i + 1 == _this.paramet.pageNum ? " pagaBtnActive" : "") + "'>" + (i + 1) + "</span>";
                            }
                        }
                        if (nums - (_this.paramet.pageNum - 1) > flogNume) btns += "<span class='pagaBtn'>...</span>";
                        btns += "<span class='pagaBtn'>></span><span class='pagaBtn pagaBtnLast'>尾页</span>";
                        $("#pagaBtns").append(btns);
                        var btnsNum = $("#pagaBtns span").size();
                        $("#pagaBtns span").each(function (i) {
                            switch (i) {
                                case 0:
                                    $(this).attr("num", 1);
                                    break;
                                case 1:
                                    $(this).attr("num", _this.paramet.pageNum == 1 ? 1 : _this.paramet.pageNum - 1);
                                    break;
                                case btnsNum - 1:
                                    $(this).attr("num", nums);
                                    break;
                                case btnsNum - 2:
                                    $(this).attr("num", _this.paramet.pageNum == nums ? nums : _this.paramet.pageNum + 1);
                                    break;
                                default :
                                    $(this).attr("num", $(this).html());
                                    break;
                            }
                            $(this).click(function () {
                                _this.paramet.pageNum = -(-$(this).attr("num"));
                                if (_this.paramet.pageNum >= 0) _this.load();
                            });
                        });
                    }
                    //处理数据表格
                    var colspan = _this.th.length;
                    if (_this.th.length > 0 || (res && res.length > 0)) {//数据头部
                        table += "<tr>";
                        if (_this.checkBtn) {
                            table += "<th width=\"40\" align=\"center\"><input type='checkbox' />全选</th>";
                            colspan++;
                        }
                        if (_this.listNumTitle) {
                            table += "<th width=\"40\" align=\"center\">" + _this.listNumTitle + "</th>";
                            colspan++;
                        }
                        for (var i = 0; i < _this.th.length; i++) {
                            var _width = _this.th[i].CH.length * 20;
                            _width = !_width ? 40 : _width;
                            table += "<th width='" + _width + "' align='" + (_this.th[i].Align ? _this.th[i].Align : "left") + "'>" + _this.th[i].CH + "</th>";
                        }
                        if (_this.btns) {
                            table += "<th " + (_this.btnsWidth ? "width='" + _this.btnsWidth + "'" : "") + " align=\"center\">操作</th>";
                            colspan++;
                        }
                        table += "</tr>";
                        $(".table").css({borderColor: "#e4e4e4"});
                    } else {
                        _this.table.html("加载数据失败,请联系维护人员...");
                        $(".table").css({borderColor: "#fff"});
                        return false;
                    }
                    if (res && res.rows && res.rows.length > 0) {
                        for (var i = 0; i < res.rows.length; i++) {
                            if(res.rows[i].id != undefined){
                                table += "<tr sqlId='" + res.rows[i].id + "' data='" + JSON.stringify(res.rows[i]) + "'>";
                            }else{
                                table += "<tr data='" + JSON.stringify(res.rows[i]) + "'>";
                            }
                            if (_this.checkBtn) table += "<td align=\"center\"><input type='checkbox' /></td>";
                            if (_this.listNumTitle) table += "<td align=\"center\">" + (i + _this.paramet.pageSize * (_this.paramet.pageNum - 1) + 1) + "</td>";
                            for (var j = 0; j < _this.th.length; j++) {
                                var th_j = _this.th[j];
                                var vaule = res.rows[i][th_j.EN];
                                vaule = th_j.Option && vaule in th_j.Option ? th_j.Option[vaule] : vaule;
                                if ((vaule == undefined || vaule == "") && vaule != 0 && vaule != "0") {
                                    if (th_j.Option && "null" in th_j.Option) {
                                        vaule = th_j.Option.null;
                                    } else {
                                        vaule = "--";
                                    }
                                }
                                if(th_j.htmlFormat){
                                    vaule = th_j.htmlFormat.replace("{" + th_j.EN + "}",vaule);
                                }
                                table += "<td" + (th_j.Style ? " style='" + th_j.Style + "'" : "") + " align=\"" + (th_j.Align ? th_j.Align : "left") + "\">" + vaule + "</td>";
                            }
                            if (_this.btns) table += "<td align=\"center\"><div class=\"tdBtns\">" + _this.btns + "</div></td>";//操作按钮
                            table += "</tr>";
                        }
                    } else {
                        if (_this.total > 0) {
                            table += "<tr><td align='center' colspan='" + colspan + "'>数据加载失败,请重试...</td></tr>";
                        } else {
                            table += "<tr><td align='center' colspan='" + colspan + "'>暂无数据...</td></tr>";
                        }
                    }
                    table += "</table>";
                    _this.table.html(table);
                    //全选功能
                    if (_this.checkBtn) {
                        _this.table.find("input[type='checkbox']").eq(0).click(function(){
                            var checked = $(this)[0].checked;
                            _this.table.find("tr").each(function () {
                                $(this).find("input[type='checkbox']").eq(0)[0].checked = checked;
                            });
                        })
                    }
                    if (fn) _this.action = fn;
                    if (_this.action) _this.action();
                },
                fail: function (e) {
                    _this.isLoading = false;
                    loadImg.close();
                    $(".table").css({borderColor: "#fff"});
                    _this.table.html("数据加载失败...");
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }
                },
                error: function (e) {
                    _this.isLoading = false;
                    loadImg.close();
                    $(".table").css({borderColor: "#fff"});
                    _this.table.html("数据加载失败...");
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }
                }
            })
        }
    }
    a.AdvancedSearch = function(list,url,searchObj,advancedSearchObj){
        var url1 = list.url,url2 = url;
        var _this = this;
        this.id = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        var isBindKeyup = false;
        $(".search").click(function(){
            if(!isBindKeyup){
                isBindKeyup = true;
                $(window).bind("keyup",function(e){
                    if(e.which == 13 && !list.isLoading) $("#searchBtn").click();
                })
            }
        })
        //普通查询
        $("#searchBtn").click(function(){
            list.url = url1;
            for(var k in advancedSearchObj){
                if(advancedSearchObj[k].val) advancedSearchObj[k].val("");
                delete list.paramet[k];
            }
            for(var k in searchObj){
                if(searchObj[k].val){
                    list.paramet[k] = searchObj[k].val();
                }else{
                    list.paramet[k] = searchObj[k];
                }
            }
            list.reLoad();
        });
        //高级查询
        $("#advancedSearch_btn").click(function(){
            $("#advancedSearch_box").show();
            a.creatCover(_this.id + "_cover", window, "999", "0", "#ffffff", "fixed");
            $("#" + _this.id + "_cover").click(function(){
                $("#advancedSearch_box").hide();
                $(this).remove();
            })
        })
        $("#advancedSearchCancle").click(function(){
            $("#advancedSearch_box").hide();
            $("#" + _this.id + "_cover").remove();
        })
        $("#advancedSearchBtn").click(function(){
            for(var k in searchObj){
                if(searchObj[k].val) searchObj[k].val("");
                delete list.paramet[k];
            }
            var listUrl = list.url;
            for(var k in advancedSearchObj){
                if(advancedSearchObj[k].val){
                    list.paramet[k] = advancedSearchObj[k].val();
                }else{
                    list.paramet[k] = advancedSearchObj[k];
                }
            }
            list.url = url2;
            list.reLoad();
            $("#advancedSearch_box").hide();
        });
    }
    a.Windows = function () {
        this.time = new Date();
        this.id = "Windows" + this.time.getTime();
        this.width = 500;
        this.height = 300;
        this.title = "新建弹窗";//窗口标题
        this.content = "";//内容
        this.bottom = "";
        this.closeBtn = true;
        var _this = this;
        this.open = function () {
            var windowScrollTop = $(window).scrollTop();
            a.creatCover(this.id + "_cover", window, this.time.getTime() - 1, "0.5", "#000000", "fixed");
            var top = "<div id='" + this.id + "_top' style='height:30px; text-align: right;line-height: 30px; background:#f9f9f9;'><b style='float: left;margin-left:10px;'>" + this.title + "</b>" + (this.closeBtn ? "<span style='margin-right: 10px;cursor: pointer; font-family: Arial;' id='" + this.id + "_close'>X</span>" : "") + "</div>";
            var center = "<div style='border-top:1px solid #e4e4e4;'><div style='margin:5px;height:" + (this.height - (this.bottom ? 82 : 42)) + "px;overflow:auto;' id='" + this.id + "_content'>" + this.content + "</div></div>";
            var bottom = this.bottom ? "<div style='border-top:1px dashed #e4e4e4;height:40px;overflow: hidden;'>" + this.bottom + "</div>" : "";
            $("body").append("<div class='boxShadow' style='border:1px solid #e4e4e4;border-radius:3px;width:" + this.width + "px;background:#fff;position: fixed;z-index:" + this.time.getTime() + "; left:" + ($(window).width() - this.width) / 2 + "px;top:" + ($(window).height() - this.height) / 2 + "px;' id='" + this.id + "'>" + top + center + bottom + "</div>");
            $(window).scrollTop(windowScrollTop);
            $("#" + this.id + "_close").click(function () {
                _this.close();
            })
            var move = a.Drag($("#" + this.id + "_top"),$("#" + this.id));//绑定移动操作
        }
        this.close = function () {
            $("#" + this.id).remove();
            $("#" + this.id + "_cover").remove();
        }
    }
    a.Drag = function(obj,target){
        obj.css({cursor:"move"});
        var left = 0, top = 0,_left = 0,_top = 0,w = $(window).width(), h = $(window).height(),_w = target.width(), _h = target.height();
        obj.bind("mousedown",windowBindMove);
        function windowBindMove(e){
            left = e.clientX,top = e.clientY,_left = target.offset().left,_top = target.offset().top;
            $("body").addClass("unSelect");
            $(window).bind("mousemove",windowMove);
            $(window).bind("mouseup",unBindWindowMove);
        }
        function windowMove(e){
            var l = _left + (e.clientX - left);
            var t = _top + (e.clientY - top);
            l = l < 0 ? 0 : (l > w - _w ? w - _w : l);
            t = t < 0 ? 0 : (t > h - _h ? h - _h : t);
            target.css({left:l + "px",top:t + "px"});
        }
        function unBindWindowMove(e){
            $("body").removeClass("unSelect");
            $(window).unbind("mousemove",windowMove);
            $(window).unbind("mouseup",unBindWindowMove);
        }
    }
    a.AutoComple = function (obj, url, data, dataString, action,isSelect) {
        this.id = "autoComple" + (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        this.obj = obj;
        this.url = url;
        this.data = data;
        this._data = data;
        this.action = action;
        this.timeSpan = 10;//请求时间间隔 毫秒
        this.time = 0;
        this.dataString = dataString;
        var _this = this;
        this.count = 0;
        this.total = 0;
        this.moreClick = false;
        this.datas = [];
        this.selected = "";
        this.close = function(){
            if(!isSelect){
                this.count = 0;
                this.total = 0;
                this.data.pageNum = 1;
            }
            this.moreClick = false;
            $("#" + this.id).remove();
            $("#" + this.id + "_cover").remove();
        }
        this.doAjax = function(fn){
            var nowTime = new Date();
            if (!this.time || nowTime - this.time >= this.timeSpan) {//请求间隔
                this.time = nowTime;
                if(!this.moreClick && isSelect && this.datas.length > 0) {
                    this.append(this.datas);
                }else{
                    if (this.url) {
                        this.count = this.moreClick ? this.count : 0;
                        $.ajax({
                            url: _this.url,
                            type: "GET",
                            dataType: "json",
                            data: _this.data,
                            beforeSend: function (XMLHttpRequest) {
                                XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                                XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                            },
                            success: function (res) {
                                if (res) {
                                    _this.count += res.rows.length;
                                    _this.total = res.total;
                                    _this.append(res.rows,fn);
                                }
                            },
                            error: function (e) {
                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                    a.alertx("登录过期，请重新登陆",function(){
                                        localStorage.user = "";
                                        location = "login.html";
                                    })
                                }else{
                                    log("请求出错");
                                }
                            },
                            fail: function (e) {
                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                    a.alertx("登录过期，请重新登陆",function(){
                                        localStorage.user = "";
                                        location = "login.html";
                                    })
                                }else{
                                    log("请求失败");
                                }
                            }
                        })
                    }
                }
            }
        }
        this.append = function (rows,fn) {
            var autoList = isSelect && !this.moreClick ? "<div class='autoList' vaule=''>请选择</div>" : "";
            this.moreClick = true;
            if(isSelect && _this.datas != rows){
                _this.datas = _this.datas.concat(rows);
            }
            for (var i = 0; i < rows.length; i++) {
                var list = _this.dataString;
                for (var k in rows[i]) {
                    var v = rows[i][k];
                    list = list.replace(new RegExp("[$]{" + k + "}","g"), v);
                    list = list.replace(new RegExp("[$]listStyle{" + k + "}","g"), v && v.length > 0 ? v[0] : "?");
                }
                autoList += list;
            }
            if ($("#" + this.id).size() == 1) {
                if(this.moreClick){
                    $("#" + this.id + "_list").append(autoList);
                    this.moreClick = false;
                }else{
                    $("#" + this.id + "_list").html(autoList);
                }
                $("#" + this.id + "_bottom").html("共 " + this.total + " 条数据,当前显示 " + this.count + " 条");
                if(this.total > this.count){
                    $("#" + this.id + "_more").show();
                }else{
                    $("#" + this.id + "_more").hide();
                }
            } else {
                a.creatCover(this.id + "_cover", window, "9999", "0", "#ffffff", "fixed",true);
                $("body").append("<div class='boxShadow' id='" + this.id + "' style='min-height:30px;max-height:330px;overflow:auto;position: absolute;z-index:9999;left:" + this.obj.offset().left + "px;top:" + (this.obj.offset().top + this.obj.height() + 2) + "px;background:#fff;border-top:1px solid #e4e4e4;width:" + (this.obj.width() + 10) + "px'>" + (isSelect ? "" : ("<div id='" + this.id + "_bottom' style='text-align:center; line-height:30px;border-bottom:1px solid #e7e7e7;background:#f8f8f8'>共 " + this.total + " 条数据,当前显示 " + this.count + " 条</div>")) + "<div id='" + this.id + "_list'>" + autoList + "</div><div style='text-align:center; border-top:1px solid #e7e7e7;color:#2442b2; cursor:pointer;line-height:30px;background:#f8f8f8' id='" + this.id + "_more'>加载更多...</div></div>");
                if(this.total > this.count){
                    $("#" + this.id + "_more").show();
                }else{
                    $("#" + this.id + "_more").remove();
                }
                if(isSelect){
                    $("#" + this.id + "_list").children().each(function(i){
                        if(_this.selected == $(this).attr("value")){
                            obj.val($(this).text());
                        }
                        $(this).click(function(){
                            _this.selected = $(this).attr("value");
                            obj.val($(this).text());
                        })
                    });
                    if(fn) fn();
                }
                $("#" + this.id + "_list").click(function (e) {
                    if (_this.action) {
                        _this.action($(e.target));
                    }
                    _this.close();
                })
                $("#" + this.id + "_more").click(function (e) {
                    _this.data.pageNum ++;
                    _this.moreClick = true;
                    _this.doAjax();
                })
                $("#" + this.id + "_cover").click(function () {
                    _this.close();
                });
            }
            if (this.count == 0) {
                $("#" + this.id + "_list").html("<div class='autoList' vaule=''>无数据</div>");
            }
        }
        this.val = function (s,_s) {
            if (s == undefined) {
                return this.selected;
            } else {
                if(s){
                    this.selected = s;
                    if(_s) obj.val(_s);
                    if(this.datas.length < 1){
                        this.doAjax(function(){
                            _this.close();
                        });
                    }
                }else{
                    this.selected = "";
                    obj.val("请选择");
                }
            }
        }
        obj.focus(function () {
            _this.close();
            _this.doAjax();
            if(obj.prev()[0] && obj.prev()[0].className == "data_error") obj.prev().hide();
        })
        if(!isSelect){
            obj.bind("keyup", function () {
                _this.doAjax();
            });
        }else{
            obj.attr("readonly","readonly");
            obj.css({background:"url(images/arrow_active.png) right center no-repeat",cursor:"pointer"});
        }
        
    }
    a.InputSelect = function(obj, url, data, dataString, selectMax,action){
        this.value = [];
        this.total = 0;
        this.count = 0;
        this.selectedSize = 0;
        this.hasValue = "";
        var _this = this;
        this.open = function(){
            a.creatCover("InputSelect_cover", window, "9999", "0.1", "#000000", "fixed",true);
            var InputSelect = "<div id='InputSelect' class='boxShadow' style='width:300px; position:fixed; left:50%; margin-left:-150px;top:50%;margin-top:-200px;z-index:9999;background:#f8f8f8;border-radius:5px;'><input type='text' placeholder='关键字' style='border:1px solid #e4e4e4;padding:3px 5px;border-radius:3px;width:250px;height:25px;margin-left:20px; margin-top:10px; background:none;' id='InputSelect_input' /><div id='InputSelect_count' style='text-align:center;border-bottom:1px dashed #e4e4e4;padding:5px;color:#ccc;'>共<span id='InputSelect_count_total' style='margin:5px;color:#00b7ee'>0</span>条数据，显示<span id='InputSelect_count_selected' style='margin:5px;color:#00b7ee'>0</span>条</div><div style='min-height:30px;max-height:300px;overflow:auto;background:#fff'><div id='InputSelect_select' ></div><div style='text-align:center;'><span id='InputSelect_more' style='display:none;color:#4a5cdf;margin:5px;padding:3px 7px;cursor:pointer;'>加载更多..</span></div></div><div style='line-height:40px; cursor:pointer;text-align:center;'><span id='InputSelect_ok' style='padding:3px 15px;border-radius:2px;' class='bg_45b6af'>确定</span><span id='InputSelect_cancle' style='padding:3px 15px;border-radius:2px;margin-left:10px;' class='bg_ffffff'>取消</span></div></div>";
            $("body").append(InputSelect);
            _this.selectedSize = _this.hasValue ? _this.hasValue.split(",").length : 0;
            this.doAjax(true);
            $("#InputSelect_cover").click(function(){
                _this.close();
            });
            if(!data["key"]) $("#InputSelect_input").remove();
            $("#InputSelect_input").keyup(function(){
                data[data["key"]] = $(this).val();
                data.pageNum = 1;
                _this.total = 0;
                _this.count = 0;
                _this.selectedSize = 0;
                _this.doAjax(true);
            })
            $("#InputSelect_more").click(function(){
                data.pageNum ++;
                _this.doAjax();
            })
            $("#InputSelect_ok").click(function(){
                var rc = [];
                for(var i = 0; i < _this.value.length; i++){
                    if(_this.value[i].selected) rc.push(_this.value[i]);
                }
                if(action) action(rc);
                _this.close();
            })
            $("#InputSelect_cancle").click(function(){
                _this.close();
            })
        }
        this.doAjax = function(isHTML){
            $.ajax({
                url: url,
                type: "GET",
                dataType: "json",
                data: data,
                beforeSend: function (XMLHttpRequest) {
                    XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                    XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                },
                success: function (res) {
                    if (res && res.rows.length > 0) {
                        var autoList = "";
                        _this.total = res.total;
                        if(isHTML){
                            _this.value = [];
                            _this.count = res.rows.length;
                        }else{
                            _this.count += res.rows.length;
                        }
                        if(_this.total <= _this.count){
                            $("#InputSelect_more").hide();
                        }else{
                            $("#InputSelect_more").show();
                        }
                        for (var i = 0; i < res.rows.length; i++) {
                            var list = dataString;
                            for (var k in res.rows[i]) {
                                var v = res.rows[i][k];
                                list = list.replace(new RegExp("[$]{" + k + "}","g"), v);
                                list = list.replace(new RegExp("[$]listStyle{" + k + "}","g"), v && v.length > 0 ? v[0] : "?");
                                list = list.replace(new RegExp("[$]has{" + k + "}","g"), function(){
                                    if(_this.hasValue && new RegExp(",?" + v + ",?").test(_this.hasValue)){
                                        res.rows[i].selected = true;
                                        return "<div style='color:#e4e4e4;font-size:12px;margin:0px 5px;margin-top:15px;border:1px solid #e4e4e4;width:15px;height:15px;line-height:15px; text-align:center; border-radius:2px; float:left;font-weight:bold;font-size:12px;font-family:Berlin Sans FB;'>√</div>";
                                    }else{
                                        return "<div class='InputSelectChecked' style='color:#00b7ee;font-size:12px;margin:0px 5px;margin-top:15px;border:1px solid #e4e4e4;width:15px;height:15px;line-height:15px; text-align:center; border-radius:2px; cursor:pointer;float:left;font-weight:bold;font-size:12px;font-family:Berlin Sans FB;'></div>";
                                    }
                                });
                            }
                            autoList += list;
                            _this.value.push(res.rows[i]);
                        }
                        if(isHTML){
                            $("#InputSelect_select").html(autoList);
                        }else{
                            $("#InputSelect_select").append(autoList);
                        }
                        $(".icon_img").each(function(){
                            var $this = $(this);
                            var imgUrl = $this.attr("imgUrl");
                            if(imgUrl){
                                var img = new Image();
                                img.onload = function(){
                                    $this.html("<img src='" + imgUrl + "' />");
                                }
                                img.src = imgUrl;
                            }
                            $this.removeAttr("imgUrl");
                        })
                        $("#InputSelect_select").children().each(function(i){
                            var isBind = $(this).attr("isBind");
                            if(isBind != "true"){
                                $(this).attr("isBind","true");
                                $(this).find(".InputSelectChecked").bind("click",function(){
                                    if(!_this.value[i].selected){
                                        if(!selectMax || _this.selectedSize < selectMax){
                                            _this.value[i].selected = true;
                                            $(this).html("√");
                                            $(this).css("border-color","#00b7ee");
                                            _this.selectedSize ++;
                                        }else{
                                            alertx("最多选择 " + selectMax + " 个");
                                        }
                                    }else{
                                        _this.value[i].selected = false;
                                        $(this).html("");
                                        $(this).css("border-color","#e4e4e4");
                                        _this.selectedSize --;
                                    }
                                    //$("#InputSelect_count_selected").html(_this.selectedSize);
                                })
                            }
                        })
                        $("#InputSelect_count_total").html(_this.total);
                        //$("#InputSelect_count_selected").html(_this.selectedSize);
                        $("#InputSelect_count_selected").html(_this.count);
                    }
                },
                error: function (e) {
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }else{
                        log("请求出错");
                    }
                },
                fail: function (e) {
                    if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                        alertx("登录过期，请重新登陆",function(){
                            localStorage.user = "";
                            location = "login.html";
                        })
                    }else{
                        log("请求失败");
                    }
                }
            })
        }
        this.close = function(){
            data.pageNum = 1;
            data[data["key"]] = "";
            this.value = [];
            this.total = 0;
            this.count = 0;
            this.selectedSize = 0;
            $("#InputSelect").remove();
            $("#InputSelect_cover").remove();
        }
        obj.click(function(){
            _this.open();
        })
    }
    a.AutoCompleInputSelect = function(obj, url, data, dataString,vKey,sKey,fn){
        var vaule = "";
        var o = new a.InputSelect(obj,url,data,dataString,1,function(res){
            if(res && res.length == 1) {
                o.val(res[0][vKey],res[0][sKey]); 
                if(fn) fn(res);
            }
        });
        o.val = function(s,_s){
            if (s == undefined) {
                return vaule;
            } else {
                vaule = s;
                if(_s) obj.val(_s);
            }
        }
        return o;
    }
//常用验证方法
    a.Verify = {
        Name: function (b) { /*验证姓名*/
            var b = b.replace(/(^\s*)|(\s*$)/g, "");
            return b.length > 0
        },
        Phone: function (t) {/*验证电话号码*/
            //return /^(0\d{2,3}-?\d{7,8})|(1[3|5|8]\d{9})$/.test(t);
            return /^1[3|5|8]\d{9}$/.test(t);
        },
        IDCard: function (t) {/*验证身份证*/
            var s = t.length, b, i, w, m, d;
            if (s === 15) {
                b = t.match(/^(?:\d{6})(\d{2})(\d{2})(\d{2})(?:\d{3})$/);
                if (!b) {
                    return false
                }
                i = parseInt("19" + b[1], 10);
                w = parseInt(b[2], 10);
                m = parseInt(b[3], 10);
                d = t.charAt(s - 1) % 2
            } else {
                if (s === 18) {
                    var u = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], y = "10X98765432";
                    for (var v = 0, c = 0; v < 17; v++) {
                        c += t.charAt(v) * u[v]
                    }
                    if (y.charAt(c % 11) !== t.charAt(17).toUpperCase()) {
                        return false
                    }
                    b = t.match(/^(?:\d{6})(\d{4})(\d{2})(\d{2})(?:\d{3})(?:[0-9]|X)$/i);
                    if (!b) {
                        return false
                    }
                    i = parseInt(b[1], 10);
                    w = parseInt(b[2], 10);
                    m = parseInt(b[3], 10);
                    d = t.charAt(s - 2) % 2
                }
            }
            var x = new Date(i, w - 1, m);
            if (i !== x.getFullYear() || w !== x.getMonth() + 1 || m !== x.getDate()) {
                return false
            }
            return [i + (w < 10 ? "-0" : "-") + w + (m < 10 ? "-0" : "-") + m,i,w,m];
        },
        Email: function (t) {/*验证邮箱*/
            return /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(t)
        },
        Date: function(b){
            return /^\d{4}(-\d{1,2}(-\d{1,2})?)?$/.test(b)
        }
    }
    a.Tree = function(obj,data,idKey,childKey,nodeHtml,action){//data 数据源,childKey 子节点关键字 htmlStyle 节点html风格
        this.data = data;
        this.action = action;
        this.build = function(node,pid){
            var list = arguments.length == 2 ? node : this.data;
            var tree = "<div class='tree'></div>";
            if(!pid){
                obj.html(tree);
                tree = $(".tree").eq(0);
            }else{
                tree = $("#" + pid).find(".tree").eq(0);
            }
            var hasChild = false;
            for(var i = 0; i < list.length; i++){
                var html = nodeHtml;
                for(var k in list[i]){
                    html = html.replace(new RegExp("[$]{" + k + "}","g"), list[i][k]);
                }
                var node = "<div class='node" + (i == list.length - 1 && list[i][childKey].length <= 0 ? " lastNode" : "") + "' id='node" + list[i][idKey] + "' data='" + JSON.stringify(list[i]) + "'>" + html;
                node += list[i][childKey].length > 0 ? "<div class='tree'></div></div>" : "</div>";
                tree.append(node);
                if(list[i][childKey].length > 0){//递归遍历
                    hasChild = true;
                    this.build(list[i][childKey],"node" + list[i][idKey]);
                }
            }
            /*if(!hasChild){
                tree.find(".node").css({width:"50%",float:"left"});
                tree.append("<div style='clear:both;'></div>");
            }*/
            if(arguments.length == 0 && this.action && typeof(action) == "function") action();//初次加载的时候绑定事件
        }
        this.html = function(s){
            if(s){
                this.data = s;
                this.build();
            }else{
                return this.data;
            }
        }
    }
    a.Area = function(obj,type,lineNum){
        var _this = this;
        this.id = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        this.load = function(){
            var script = document.createElement('script'); 
            script.type = 'text/javascript';
            script.src = "js/AreaData.js";
            document.body.appendChild(script);
            script.onload = function(){
            }
        }
        if(!window.AreaData) this.load();
        this.flog = 0; //1:选择省 2:选择市 3:选择区
        this.province = null;//省
        this.town = null;//市
        this.district = null;//区
        this.memory = [];
        this.setData = function(id){
            var spanString = "<span style='padding:3px 15px;border:1px solid #e4e4e4;margin-left:10px;cursor:pointer;'>" + (this.province ? AreaData[this.province][0] : "选择省") + "</span>";
            if(this.flog == 3){
                spanString += "<span style='padding:3px 15px;border:1px solid #e4e4e4;margin-left:10px;cursor:pointer;'>" + (this.town ? AreaData[this.town][0] : "选择市") + "</span>";
                spanString += "<span style='padding:3px 15px;border:1px solid #e4e4e4;margin-left:10px;cursor:pointer;'>" + (this.district ? AreaData[this.district][0] : "选择区") + "</span>";
            }else if(this.flog == 2){
                spanString += "<span style='padding:3px 15px;border:1px solid #e4e4e4;margin-left:10px;cursor:pointer;'>" + (this.town ? AreaData[this.town][0] : "选择市") + "</span>";
            }
            $("#areaSpan" + this.id).find("div").html(spanString);
            $("#areaSpan" + this.id).find("span").eq(_this.flog - 1).css({background:"#00b7ee",color:"#fff"});
            $("#areaSpan" + this.id).find("span").eq(0).click(function(){
                _this.town = null;//市
                _this.district = null;//区
                _this.flog = 1;
                _this.setData(1);
            })
            $("#areaSpan" + this.id).find("span").eq(1).click(function(){
                _this.district = null;//区
                _this.flog = 2;
                _this.setData(_this.town);
            })
            var dataString = "";
            for(var k in AreaData){
                if(AreaData[k][1] == id){
                    dataString += "<div style='width:100px;line-height:30px;height:30px;overflow:hidden;border:1px solid #fff;overflow:hidden;float:left;cursor:pointer;' vaule='" + k + "' title='" + AreaData[k][0] + "'>" + AreaData[k][0] + "</div>"
                }
            }
            dataString += "<div class='clear'></div>";
            $("#areaContent" + this.id).html(dataString);
            $("#areaContent" + this.id).find("div").each(function(i){
                $(this).mouseover(function(){
                    $(this).css("color","#ff0000");
                })
                $(this).mouseout(function(){
                    $(this).css("color","#666");
                })
                $(this).click(function(){
                    var k = $(this).attr("vaule");
                    switch(_this.flog){
                        case 1:
                            _this.province = k;
                            if(type >= 2){
                                _this.flog ++;
                                _this.town = null;
                                _this.district = null;
                                _this.setData(k);
                            }else{
                                _this.memory = [];
                                _this.val();
                            }
                            break;
                        case 2:
                            _this.town = k;
                            if(type >= 3){
                                _this.flog ++;
                                _this.district = null;
                                _this.setData(k);
                            }else{
                                _this.memory = [];
                                _this.val();
                            }
                            break;
                        case 3:
                            _this.district = k;
                            _this.memory = [];
                            _this.val();
                            break;
                    }
                })
            })
        }
        this.val = function(p,t,d){
            if(p){
                this.province = p;
                this.town = t;
                this.district = d;
                this.setData(1);
            }else{
                if(this.memory.length > 0){
                    this.province = this.memory[0];//省
                    this.town = this.memory[1];//市
                    this.district = this.memory[2];//区
                }
                var areaString = "";
                if(this.province) areaString += AreaData[this.province][0];
                if(this.town) areaString += AreaData[this.town][0];
                if(this.district) areaString += AreaData[this.district][0];
                obj.val(areaString);
                _this.close();
                return [this.province,this.town,this.district];
            }
        }
        lineNum = !lineNum || lineNum < 4 ? 4 : lineNum;
        this.width = lineNum * 100 + 40;
        this.height = 200;
        this.open = function(){
            this.memory = [this.province,this.town,this.district];
            a.creatCover(this.id + "_cover", window, "9999", "0", "#ffffff", "fixed",true);
            var box = "<div id='areaBox" + this.id + "' style='border:1px solid #ccc;overflow:hidden;width:" + this.width + "px;background:#fff;position:absolute;left:" + obj.offset().left + "px;top:" + (obj.offset().top + obj.height()) + "px;z-index:10000;'>";
            box += "<div id='areaSpan" + this.id + "' style='border-bottom:1px solid #e4e4e4;line-height:40px;overflow:hidden;height:40px;text-align:right;'><div style='float:left;'>";
            box += "</div><font style='padding:2px 7px; border:1px solid #e4e4e4;cursor:pointer;margin-right:10px;' id='reset" + this.id + "'>清除</font><span id='close" + this.id + "' style='margin-right:10px;cursor:pointer;'>X</span></div>";
            box += "<div id='areaContent" + this.id + "' style='overflow:auto;height:" + (this.height - 40) + "px;margin-left:10px;padding-top:10px;padding-bottom:10px;'></div>";
            box += "</div>";
            $("body").append(box);
            $("#close" + this.id + ",#" + this.id + "_cover").click(function(){
                _this.val();
            });
            $("#reset" + this.id).click(function(){
                _this.memory = [];
                _this.province = undefined;
                _this.town = undefined;
                _this.district = undefined;
                _this.val();
            })
            this.flog = !this.district ? (!this.town ? 1 : 2) : 3;
            var id = !this.district ? (this.province ? this.province : 1) : this.town;
            this.setData(id.toString());
        }
        this.close = function(){
            $("#" + this.id + "_cover").remove();
            $("#areaBox" + this.id).remove();
            obj.prev(".data_error").hide();
        }
        obj.focus(function(){
            obj.prev(".data_error").hide();
            _this.close();
            _this.open();
        })
    }
    a.utf16to8 = function(str){
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }
    a.getBlobURL = (window.URL && URL.createObjectURL.bind(URL)) || (window.webkitURL && webkitURL.createObjectURL.bind(webkitURL)) || window.createObjectURL;
    a.revokeBlobURL = (window.URL && URL.revokeObjectURL.bind(URL)) || (window.webkitURL && webkitURL.revokeObjectURL.bind(webkitURL)) || window.revokeObjectURL;

    a.TreeModel = function(obj,data,dataModel,levelConfig,editable,fn,levelMax){
        levelMax = levelMax || 3;//最大层数
        var _this = this;
        this.data = data;
        this.getAjax = function(type,_dataModel,n,fn){
            var inputHtml = "";//提示框内容
            var verifyArr = [];//需要输入项
            var config = levelConfig[n];
            var action = "";
            var req = {};
            if(type == 1){//新增
                action = config.res.add;
            }else if(type == 2){//修改
                action = config.res.edit;
            }else if(type == 3){//删除
                action = config.res.delete;
            }
            //初始化参数
            for(var k in dataModel){
                req[k] = dataModel[k];
                if(_dataModel[k]){
                    req[k].vaule = _dataModel[k];
                }
                if(dataModel[k].input){
                    if(type == 1){//新增
                        inputHtml += "<div>" + dataModel[k].input.title + "<br />" + dataModel[k].input.html + "</div>";
                        verifyArr.push(k);
                    }else if(type == 2){//修改
                        inputHtml += "<div>" + dataModel[k].input.title + "<br />" + dataModel[k].input.html + "</div>";
                        verifyArr.push(k);
                    }else if(type == 3){
                        inputHtml = "<div>确定删除?</div>";
                    }
                }
            }
            for(var k in action.req){
                req[k] = action.req[k];
            }
            //获取输入参数
            //弹出框
            a.confirmx(inputHtml,function(){
                //验证并赋值
                var isOk = true;
                var errorMsg = "";
                for(var i = 0; i < verifyArr.length; i ++){
                    var val = req[verifyArr[i]].input.val();
                    if(val.result){
                        req[verifyArr[i]].vaule = val.vaule;
                    }else{
                        isOk = false;
                        if(val.msg) errorMsg += "";
                    }
                }
                if(isOk){
                    var url = action.url;
                    //强制修改参数及url
                    for(var k in req){
                        if(req[k].vaule){
                            req[k] = req[k].vaule;
                        }else{
                            if(typeof req[k] == "object"){
                                req[k] = "";
                            }
                        }
                        url = url.replace(new RegExp("[$]{" + k + "}","g"), req[k]);
                    }
                    //ajax请求
                    var ajax = new Ajax(url);
                    ajax.data = req;
                    ajax.meth = action.meth;
                    ajax.do(function(res){
                        if(res && res[action.res.successKey]){
                            fn(res);
                        }else{
                            if(res.errorMsg){
                                a.alertx("操作失败!" + res.errorMsg);
                            }else{
                                a.alertx("操作失败，未知错误！");
                            }
                        }
                    });
                }else{
                    if(errorMsg) a.alert(errorMsg);
                }
            })
        }
        this.active = [0];//激活状态数组
        this.append = function(id,n,_obj){
            _obj = !_obj ? obj : _obj;
            if(id && this.data["num" + id]){
                var _data = this.data["num" + id];
                var html = "";
                if(levelConfig[n].res.beforeFn) levelConfig[n].res.beforeFn();
                for(var i = 0; i < _data.length; i++){
                    var list = levelConfig[n].res.html;
                    for(var k in _data[i]){
                        var v = _data[i][k];
                        list = list.replace(new RegExp("[$]{" + k + "}","g"), v);
                        list = list.replace(new RegExp("[$]listStyle{" + k + "}","g"), v && v.length > 0 ? v[0] : "?");
                    }
                    html += list;
                }
                if(editable) html += levelConfig[n].res.add.html;
                _obj.html(html + levelConfig[n].res.style);
                if(editable){
                    //新增
                    _obj.find("." + levelConfig[n].res.add.btnClass).eq(0).click(function(){
                        var o = {};
                        o[levelConfig[n].pidKey] = id;
                        _this.getAjax(1,o,n,function(){
                            _this.data["num" + id] = undefined;//清除数据重新从服务器拉取
                            _this.getData(n,id,_obj);
                        })
                    })
                }
                _obj.children().each(function(i){
                    var $this = $(this);
                    //点击加载
                    $(this).find("." + levelConfig[n].res.clickBox).eq(0).click(function(){
                        if(!levelConfig[n].res.obj){
                            if(_this.active.removeClass) _this.active.removeClass("active");
                            _this.active = $(this).parent();
                            _this.active.addClass("active");
                        }else{
                            _obj.children().eq(_this.active[n]).children().eq(0).removeClass("active");
                            _this.active[n] = i;
                            _obj.children().eq(_this.active[n]).children().eq(0).addClass("active");
                        }
                        if(n + 1 == levelMax) return false;
                        levelConfig[n + 1] = !levelConfig[n + 1] ? levelConfig[n] : levelConfig[n + 1];
                        _this.getData(n + 1,_this.data["num" + id][i][levelConfig[n].idKey],!levelConfig[n + 1].res.obj ? $this.find("." + levelConfig[n].res.childBox).eq(0) : levelConfig[n + 1].res.obj,id);
                    })
                    var editBtn = $(this).find("." + levelConfig[n].res.edit.btnClass).eq(0);
                    var deleteBtn = $(this).find("." + levelConfig[n].res.delete.btnClass).eq(0);
                    editBtn.hide();
                    deleteBtn.hide();
                    if(editable){
                        $(this).children().eq(0).mouseover(function(){
                            editBtn.show();
                            deleteBtn.show();
                        })
                        $(this).children().eq(0).mouseout(function(){
                            editBtn.hide();
                            deleteBtn.hide();
                        })
                        //修改
                        editBtn.click(function(){
                            _this.getAjax(2,_this.data["num" + id][i],n,function(){
                                _this.data["num" + id] = undefined;//清除数据重新从服务器拉取
                                _this.getData(n,id,_obj);
                            })
                        })
                        //删除
                        deleteBtn.click(function(){
                            _this.getAjax(3,_this.data["num" + id][i],n,function(){
                                _this.data["num" + id] = undefined;//清除数据重新从服务器拉取
                                _this.getData(n,id,_obj);
                            })
                        })
                    }
                })
                if(levelConfig[n].res.endFn) levelConfig[n].res.endFn();
            }
        }
        this.getData = function(n,id,obj,_pid){
            if(fn) fn(!id ? levelConfig[n].req[levelConfig[n].pidKey] : id,_pid);//进行其他操作
            var pid = levelConfig[n].pidKey;
            if(id && this.data["num" + id]){
                _this.append(id,n,obj);
            }else{
                if(id){
                    levelConfig[n].req[pid] = id;
                }else{
                    id = levelConfig[n].req[pid];
                }
                var url = levelConfig[n].url;
                for(var k in levelConfig[n].req){
                    var v = levelConfig[n].req[k];
                    url = url.replace(new RegExp("[$]{" + k + "}","g"), v);
                }
                var ajax = new Ajax(url);
                ajax.data = levelConfig[n].req;
                ajax.meth = levelConfig[n].meth;
                ajax.do(function(res){
                    if(res && res.rows){
                        _this.data["num" + id] = res.rows;
                        _this.append(id,n,obj);
                    }else{
                        if(res.errorMsg){
                            a.alertx("获取失败!" + res.errorMsg);
                        }else{
                            a.alertx("获取失败,未知错误!");
                        }
                    }
                });
            }
        }
        this.getData(0);
    }

    a.Classify = function(obj,type,editable,loadFn){
        var _this = this;
        //垂直延长
        this.levelConfig = [];
        this.dataModel = {}
        this.data = [];
        this.loadFn = loadFn;
        this.editable = editable;
        this.levelMax = 3;
        this.tree = {};
        this.load = function(){
            this.tree = new a.TreeModel(obj,this.data,this.dataModel,this.levelConfig,this.editable,_this.loadFn,this.levelMax);
            setTimeout(function(){
                obj.children().eq(0).children().eq(0).children().eq(0).click();
            },500);
        }
        //水平延长 自定义延长
        //var config = [{url:"",meth:"",pidKey:"parentId",req:{parentId:1,type:3},res:{obj:obj1,idKey:"id",clickBox:"treeNode_name",childBox:"treeNode_child",html:"<div class='treeNode'><div class='treeNode_name'>${name}</div><div class='treeNode_child'></div></div>"}},
        //    {url:"",meth:"",pidKey:"parentId",req:{type:3},res:{obj:obj2,idKey:"id",clickBox:"treeNode_name",childBox:"treeNode_child",html:"<div class='treeNode'><div class='treeNode_name'>${name}</div><div class='treeNode_child'></div></div>"}}];
    }
    a.Material = function(obj,editable,type){
        var _this = this;
        var timeStamp = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        var html = "<div class='materialBox' id='Material" + timeStamp + "'>";
        if(editable){
            html += "<div class='materialBox_top'><span class='materialBox_top_span1 active'>资源</span><span class='materialBox_top_span2'>课程</span></div>";
            html += "<span class=\"materialBox_upLoad iconBtn bg_00b7ee\"><img src=\"../images/icon_yun_upload.png\">上传文件</span>";
        }
        html += "<div class='materialBox_c'>";
        html += "<div class='materialBox_left'><div class='materialBox_left_t'><span>资源分组</span><span>课程分组</span></div><div class='materialBox_left_c'><div class='materialBox_left_c_content'></div><div class='materialBox_left_c_content'></div></div></div>";
        html += "<div class='materialBox_right'><div class='materialBox_right_content'></div><div class='materialBox_right_content'></div></div>";
        html += "<div class='clear'></div></div>";
        html += "</div>";
        //styletimeStamp
        html += "<style id='Material" + timeStamp + "Style'>";
        html += ".materialBox_top{text-align:center;padding:10px 0px;}";
        html += ".materialBox_top_span1{padding:10px 30px;border-radius:5px 0px 0px 5px;border:1px solid #e4e4e4; cursor:pointer;}";
        html += ".materialBox_top_span2{padding:10px 30px;border-radius:0px 5px 5px 0px;border:1px solid #e4e4e4; cursor:pointer;}";
        html += ".materialBox_top .active{background:#19b0e0; color:#fff;border-color:#19b0e0}";
        html += ".materialBox_upLoad{position:absolute;right:20px;margin-top:-40px;}";
        var leftWith = 200;
        html += ".materialBox_c{margin-top:10px;}";
        html += ".materialBox_left{width:" + leftWith + "px;position:absolute;border:1px solid #e4e4e4;overflow:auto;}";
        html += ".materialBox_left_t{line-height:40px;border-bottom:1px solid #e4e4e4;}";
        html += ".materialBox_left_t span{margin-left:10px;}";
        html += ".materialBox_left_c{min-height:100px;}";
        html += ".materialBox_right{margin-left:" + (leftWith + 10) + "px;border:1px solid #e4e4e4;min-height:140px;}";
        html += ".materialBox_right th{padding: 10px;font-weight: normal;border-bottom:1px solid #e4e4e4;}";
        html += ".materialBox_right td{padding: 10px;}";
        html += ".treeNode_self{text-align:right;height:30px; line-height:30px;background:url(../images/icon_yun_file.png) 5px center no-repeat; padding-left:15px;}";
        html += ".treeNode_name{float:left;margin-left:10px;cursor:pointer;}";
        html += ".treeNode_self .listBtns{margin-right:10px; cursor:pointer;}";
        html += ".treeNode_self.active{color:#19b0e0;background:url(../images/icon_yun_file_active.png) 5px center no-repeat;}";
        html += ".treeNode_child{margin-left:20px;}";
        html += ".listAdd{font-size:12px; color:#ccc;cursor:pointer;margin-left:5px;}";
        html += "</style>";
        obj.html(html);
        var types = [3,4];
        this.list = null;
        this.cusCateId = 1;
        var materialBox_top_span = $("#Material" + timeStamp).find(".materialBox_top span");
        var materialBox_left_t = $("#Material" + timeStamp).find(".materialBox_left_t span");
        var materialBox_left_c_content = $("#Material" + timeStamp).find(".materialBox_left_c_content");
        var materialBox_right_content = $("#Material" + timeStamp).find(".materialBox_right_content");
        this.activeN = 0;
        this.load = function(n){
            n = n > types.length - 1 ? types.length - 1 : n;
            this.activeN = n;
            for(var i = 0; i < types.length; i++){
                if(i == n){
                    materialBox_top_span.eq(i).addClass("active");
                    materialBox_left_t.eq(i).show();
                    materialBox_left_c_content.eq(i).show();
                    materialBox_right_content.eq(i).show();
                }else{
                    materialBox_top_span.eq(i).removeClass("active");
                    materialBox_left_t.eq(i).hide();
                    materialBox_left_c_content.eq(i).hide();
                    materialBox_right_content.eq(i).hide();
                }
                materialBox_top_span.eq(i).click(function(){
                    _this.load($(this).prevAll().size());
                })
            }
            var option = {pageSize: 10, pageNum: 1,type:types[n] == 3 ? 1 : 2};//需要转换成素材分类1 2
            var config = [
                {CH: "文件名称", EN: "name",Align:"center"},
                {CH: "上传时间", EN: "createTime",Align:"center"}
            ];
            var list = new a.List(webIP + "/api/material/v0.1/list", option, config);
            this.list = list;
            list.table = materialBox_right_content.eq(n);
            if(!editable){
                list.listNumTitle = "";
                list.checkBtn = true;
            }else{
                list.btns = "<span class=\"listEdit bg_f11c1c\">编辑</span><span class=\"listDelete bg_f11c1c\">删除</span>";
            }
            var levelConfig = [
            {
                url:webIP + "/api/cus/category/v0.1/${type}/pid/${parentId}",
                meth:"GET",
                idKey:"id",
                pidKey:"parentId",
                req:{parentId:1,type:types[n],pageSize: 10000, pageNum: 1},
                res:{
                    clickBox:"treeNode_name",
                    childBox:"treeNode_child",
                    add:{
                        btnClass:"listAdd",
                        url:webIP + "/api/cus/category/v0.1/${type}",
                        meth:"POST",
                        req:{},
                        res:{successKey:"result"},
                        html:"<div class='listAdd'>+ 添加分类</div>"
                    },
                    edit:{
                        btnClass:"listEdit",
                        url:webIP + "/api/cus/category/v0.1/${type}/${id}",
                        meth:"PUT",
                        req:{},
                        res:{successKey:"result"}
                    },
                    delete:{
                        btnClass:"listDelete",
                        url:webIP + "/api/cus/category/v0.1/${type}/${id}",
                        meth:"DELETE",
                        req:{},
                        res:{successKey:"result"}
                    },
                    beforeFn:function(){},
                    endFn:function(){},
                    editBtn:"listEdit",
                    deleteBtn:"listDelete",
                    html:"<div class='treeNode'><div class='treeNode_self'><span class='treeNode_name'>${name}</span><span class='listEdit listBtns'>编</span><span class='listDelete listBtns'>删</span></div><div class='treeNode_child'></div></div>",
                    style:""
                }
            }];
            var dataModel = {
                id:{primary:true},
                name:{
                    input:{
                        title:"名称",
                        html:"<input vaule='' id='dataModel_name' type='text' style='border:1px solid #e4e4e4;height:25px;padding:0px 3px;' />",
                        val:function(){
                            var vaule = $("#dataModel_name").val();
                            if(!vaule){
                                return {result:false,msg:"名称不能为空"};
                            }else{
                                return {result:true,vaule:vaule};
                            }
                        }
                    }
                },
                sort:{},
                parentId:{},
                type:{vaule:types[n]}
            }
            var classify = {};
            this.checked = [];
            var loadFn = function(id,pid){
                option.cusCateId = id;
                list.paramet = option;
                list.load(function () {
                    //移动文件
                    var datas = classify.tree.data["num" + pid];
                    if(datas && datas.length > 1){//有同级才可移动
                        $("tr").each(function(i){
                            if(i > 0){
                                $(this).find(".tdBtns").append("<span class=\"listChange bg_f11c1c\">更改</span>");
                            }
                        })
                    }
                    list.table.find(".listChange").click(function(){
                        var data = JSON.parse($(this).parents("tr").attr("data"));
                        var confirString = "<div id='changeListBox'>";
                        for(var i = 0; i < datas.length; i++){
                            confirString += "<input numId='" + datas[i].id + "' type='checkbox' />" + datas[i].name;
                        }
                        confirString += "</div>";
                        var changedId = id;
                        a.confirmx(confirString,function(){
                            if(changedId != id){
                                var ajax = new a.Ajax(webIP + "/api/material/v0.1/category");
                                ajax.meth = "PUT";
                                ajax.data = {materialId:data.id,categoryId:changedId};
                                ajax.do(function(res){
                                    if(res && res.result){
                                        a.alertx("移动成功",function(){
                                            _this.list.reLoad();
                                        })
                                    }else{
                                        a.alertx("移动失败");
                                    }
                                });
                            }
                        },function(){
                            $("#changeListBox input[numId='" + id + "']")[0].checked = true;
                            $("#changeListBox input").click(function(){
                                $("#changeListBox input[numId='" + changedId + "']")[0].checked = false;
                                changedId = $(this).attr("numId");
                                $("#changeListBox input[numId='" + changedId + "']")[0].checked = true;
                            })
                        })
                    })
                    //编辑
                    list.table.find(".listEdit").click(function(){
                        var data = JSON.parse($(this).parents("tr").attr("data"));
                        a.confirmx("<div>请输入新的文件名<br /><input type='text' value='' id='newFileName' style='border:1px solid #e4e4e4;height:25px;padding:0px 3px;' /></div>",function(){
                            var name = $("#newFileName").val();
                            if(name != ""){
                                var ajax = new a.Ajax(webIP + "/api/material/v0.1/" + data.materialId);
                                ajax.meth = "PUT";
                                ajax.data = {name:name};
                                ajax.do(function(res){
                                    if(res && res.result){
                                        alertx("修改成功",function(){
                                            _this.list.reLoad();
                                        })
                                    }else{
                                        alertx("修改失败",function(){
                                            _this.list.reLoad();
                                        })
                                    }
                                });
                            }else{
                                a.alertx("新名字不能为空");
                            }
                        })
                    })
                    //删除
                    list.table.find(".listDelete").click(function(){
                        var data = JSON.parse($(this).parents("tr").attr("data"));
                        a.confirmx("<div>确定删除?</div>",function(){
                            var ajax = new a.Ajax(webIP + "/api/material/v0.1/" + data.materialId);
                            ajax.meth = "DELETE";
                            ajax.do(function(res){
                                if(res && res.result){
                                    _this.list.reLoad();
                                }else{
                                    alertx("删除失败",function(){
                                        _this.list.reLoad();
                                    })
                                }
                            });
                        })
                    })
                });
            }
            classify = new a.Classify(materialBox_left_c_content.eq(n),types[n],editable,loadFn);
            classify.levelConfig = levelConfig;
            classify.dataModel = dataModel;
            classify.load();
        }
        this.val = function(s){
            if(s != undefined){
                 
            }else{
                var arr = [];
                materialBox_right_content.eq(this.activeN).find("tr").each(function(i){
                    if(i > 0){
                        if($(this).find("input").eq(0)[0].checked) arr.push(JSON.parse($(this).attr("data")));
                    }
                });
                return [arr,materialBox_left_c_content.eq(this.activeN).find(".active .treeNode_name").html()];
            }
        }
        this.load(type == 4 ? 1 : 0);
        //绑定上传事件
        $("#Material" + timeStamp).find(".materialBox_upLoad").click(function(){
            a.upload("",function(res,files){//上传完成事件 新增素材.
                var failArr = [];
                var successArr = [];
                for(var i = 0; i < res.length; i++){
                    var ajax = new a.Ajax(webIP + "/api/material/v0.1");
                    ajax.meth = "POST";
                    ajax.header.push({key:"Content-Type",vaule:"application/json"});
                    var o = [{"name": files[i].name,"size": files[i].size,"url": res[i].key ,"cusCateId": _this.list.paramet.cusCateId,"type": _this.list.paramet.type,"hash": res[i].hash,"qiniuKey": res[i].key}];
                    ajax.data = JSON.stringify(o);
                    ajax.do(function(res){
                        if(res && res.result){
                            successArr.push(files[i].name);
                        }else{
                            failArr.push(files[i].name);
                        }
                    });
                }
                if(failArr.length == 0){
                    a.alertx("上传成功",function(){
                        _this.list.reLoad();
                    });
                }else{
                    a.alertx("上传失败,其中 " + failArr.join(",") + " 上传失败",function(){
                        _this.list.reLoad();
                    });
                }
            },Infinity,function(res,files){//开始上传事件 新增日志
                /*var ajax = new a.Ajax(webIP + "/api/log/v0.1/materialLog");
                ajax.meth = "POST";
                ajax.data = {qiniuKey:""};
                ajax.do();*/
            })
        })
    }
    //分类目录
    a.Directory = function(obj,type,noEdit){
        var _this = this;
        this.id = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        this.type = type;
        this.data = [];
        var html = "";
        if(!noEdit){
            html += "<div id='directory" + this.id + "'><div class='directory_t'><span>+ 选择文件</span></div>";
        }
        if(this.type != 3){
            html += "<div class='directory_c'><table width='100%'><thead><tr><th align='left'>章节名</th><th align='left'>文件名</th><th align='left'>操作</th></tr></thead><tbody id='table" + this.id + "'>";
        }else{
            html += "<div class='directory_c'><table width='100%'><thead><tr><th align='left'>文件名</th><th align='left'>操作</th></tr></thead><tbody id='table" + this.id + "'>";
        }
        html += "</tbody></table>";
        html += "</div>";
        if(this.type != 3 && !noEdit){
            html += "<div class='directory_b'><span>+ 新增章节</span></div>";
        }
        html += "</div>";
        html += "<style>";
        html += ".directory_t{margin:10px 0px;}";
        html += ".directory_b{padding:10px 0px; border:1px solid #e4e4e4;border-top:none;}";
        html += ".directory_t span{cursor:pointer;background:#f7f7f7;color:#19b0e0;font-weight:bold;padding:10px 30px;border:1px solid #e4e4e4;border-radius:3px;}";
        html += ".directory_b span{cursor:pointer;color:#19b0e0;font-weight:bold; margin-left:5px;}";
        html += ".directory_c{padding-top:10px;}";
        html += ".directory_c table{border:1px solid #e4e4e4;}";
        html += ".directory_c tr{line-height:40px;}";
        html += ".directory_c th{border-bottom:1px solid #e4e4e4;}";
        html += ".directoryInput{background:#f5f5f5;border:none;padding:9px;}";
        html += ".scks{cursor:pointer;background:#fff;color:#19b0e0;font-weight:bold;padding:5px 20px;border:1px solid #e4e4e4;border-radius:3px;margin-left:10px;}";
        html += "</style>";
        obj.html(html);
        $("#directory" + this.id).find(".directory_t span").click(function(){
            _this.addFiles();
        })
        $("#directory" + this.id).find(".directory_b span").click(function(){
            _this.addChapter();
        })
        this.show = function(){
            var html = "";
            for(var i = 0; i < this.data.length; i++){
                var data = this.data[i];
                if(this.type != 3){
                    if(noEdit){
                        html += "<tr flog='" + i + "'><td colspan='2'>" + data.chapter + "</td></tr>";
                    }else{
                        html += "<tr flog='" + i + "'><td colspan='3'><input type='text' class='directoryInput editChapter' value='" + data.chapter + "' /><span class='scks'><img src='../images/icon_up.png' style='margin-right:5px;' />上传课时</span><span class='listDelete bg_dfba49 deleteChapter' style='margin-left:5px;cursor:pointer;'>删</span></td></tr>";
                    }
                }
                for(var j = 0; j < data.materials.length; j ++){
                    var _data = data.materials[j];
                    if(this.type == 3){
                        if(noEdit){
                            html += "<tr flog='" + i + "_" + j + "'><td>" + _data.name + "</td><td></td></tr>";
                        }else{
                            html += "<tr flog='" + i + "_" + j + "'><td><input class='directoryInput editName' type='text' value='" + _data.name + "' /></td><td><span class='listEdit bg_dfba49 editMaterial' style='cursor:pointer;'>改</span><span class='listDelete bg_dfba49 deleteMaterial' style='margin-left:5px;cursor:pointer;'>删</span></td></tr>";
                        }
                    }else{
                        if(noEdit){
                            html += "<tr flog='" + i + "_" + j + "'><td>" + _data.title + "</td><td>" + _data.name + "</td></tr>";
                        }else{
                            html += "<tr flog='" + i + "_" + j + "'><td><input style='margin-left:40px;' class='directoryInput editTitle' type='text' value='" + _data.title + "' /></td><td><input class='directoryInput editName' type='text' value='" + _data.name + "' /></td><td><span class='listEdit bg_dfba49 editMaterial' style='cursor:pointer;'>改</span><span class='listDelete bg_dfba49 deleteMaterial' style='margin-left:5px;cursor:pointer;'>删</span></td></tr>";
                        }
                    }
                }
            }
            html = !html ? "<div style='color:#ccc;font-size:12px;'>暂无数据</div>" : html;
            $("#table" + this.id).html(html);
            $("#directory" + this.id).find(".scks").click(function(){
                var flog = $(this).parent().parent().attr("flog");
                if(!$(this).prev().val()) {
                    a.alertx("章节名不能为空");
                    return false;
                }
                _this.addMaterial(flog);
            })
            $("#directory" + this.id).find(".editChapter").blur(function(){
                var flog = $(this).parent().parent().attr("flog");
                var value = $(this).val();
                if(value && value != _this.data[flog].chapter) _this.data[flog].chapter = value;
            })
            $("#directory" + this.id).find(".deleteChapter").click(function(){
                var flog = $(this).parent().parent().attr("flog");
                a.alertx("确定删除？",function(){
                    var arr = [];
                    for(var i = 0; i < _this.data.length; i++){
                        if(i != flog){
                            arr.push(_this.data[i]);
                        }
                    }
                    _this.data = arr;
                    _this.show();
                })
            })
            $("#directory" + this.id).find(".editTitle").blur(function(){
                var flog = $(this).parent().parent().attr("flog").split("_");
                var value = $(this).val();
                if(value && value != _this.data[flog[0]].materials[flog[1]].title) _this.data[flog[0]].materials[flog[1]].title = value;
            })
            $("#directory" + this.id).find(".editName").blur(function(){
                var flog = $(this).parent().parent().attr("flog").split("_");
                var value = $(this).val();
                if(value && value != _this.data[flog[0]].materials[flog[1]].editName) _this.data[flog[0]].materials[flog[1]].editName = value;
            })
            $("#directory" + this.id).find(".editMaterial").click(function(){
                var flog = $(this).parent().parent().attr("flog").split("_");
                _this.changeMaterial(flog[0],flog[1]);
            })
            $("#directory" + this.id).find(".deleteMaterial").click(function(){
                var flog = $(this).parent().parent().attr("flog").split("_");
                a.alertx("确定删除?",function(){
                    _this.deleteMaterial(flog[0],flog[1]);
                })
            })
        }
        this.val = function(s){
            if(s != undefined){
                this.data = s;
                this.show();
            }else{
                var arr = [];
                for(var i = 0; i < this.data.length; i++){
                    if(this.data[i].chapter || this.data[i].materials.length > 0) arr.push(this.data[i]);
                }
                return arr;
            }
        }
        this.material = function(type,fn){
            var html = "<div class='material'><div id='materialBox'></div><div class='materialBox_btns'><span class='bg_00b7ee materialBox_ok'>确定</span><span class='bg_ffffff materialBox_cancle'>取消</span></div></div>";
            html += "<style>";
            html += ".material{position:absolute;z-index:999;overflow:auto;background:#fff;left:" + $("#right").offset().left + "px;top:" + ($("#right").offset().top + 10) + "px;width:" + $("#right").width() + "px;height:" + $("#right").height() + "px;padding:10px;}";
            html += ".materialBox_btns{text-align:center;margin-top:30px;clear:both;}";
            html += ".materialBox_btns span{padding:10px 30px; border-radius:5px; cursor:pointer;margin-right:10px;}";
            html += "</style>";
            $("#directory" + this.id).append(html);
            $("body").scrollTop(0);
            var material = new a.Material($("#materialBox"),false,this.type);
            $(".materialBox_ok").click(function(){
                var materials = material.val()[0];
                for(var i = 0; i < materials.length; i++){
                    materials[i].title = materials[i].name;
                }
                switch(type){
                    case 1:
                        if(materials.length > 0){
                            fn({chapter:material.val()[1],"materials":materials});
                            $(".material").remove();
                        }
                        break;
                    case 2:
                        if(materials.length > 0){
                            fn(materials);
                            $(".material").remove();
                        }
                        break;
                    case 3:
                        if(materials.length == 0){
                            a.alertx("请选择");
                        }else if(materials.length > 1){
                            a.alertx("只能选择一个");
                        }else if(materials.length == 1){
                            fn(materials[0]);
                            $(".material").remove();
                        }
                        break;
                    default:
                        break;
                }

            })
            $(".materialBox_cancle").click(function(){
                $(".material").remove();
            })
            //material
        }
        this.addFiles = function(){
            this.material(1,function(res){
                _this.data.push(res);
                _this.show();
            });
        }
        this.addChapter = function(){
            this.data.push({chapter:"",materials:[]});
            this.show();
        }
        this.addMaterial = function(i){
            this.material(2,function(res){
                for(var m = 0; m < res.length; m++){
                    _this.data[i].materials.push(res[m]);
                }
                _this.show();
            });
        }
        this.changeMaterial = function(i,j){
            this.material(3,function(res){
                _this.data[i].materials[j] = res;
                _this.show();
            });
        }
        this.deleteMaterial = function(i,j){
            var arr = [];
            for(var m = 0; m < _this.data[i].materials.length; m ++){
                if(m != j) arr.push(_this.data[i].materials[m]);
            }
            _this.data[i].materials = arr;
            this.show();
        }
        this.show();
    }
    //多级联动
    a.Linkage = function(obj,id,api,listStyle,idKey,pidKey,nameKey,inputStyle,noEdit){
        var _this = this;
        this.id = (new Date()).getTime() + Math.ceil(Math.random() * 10000);
        this.data = null;
        this.api = api || {url:webIP + "/api/cus/category/v0.1/${type}/pid/${parentId}",data:{parentId:1,type:5},meth:"GET"};
        this.listStyle = listStyle || "<div class='autoList' vaule='${id}'>$has{id}<b class='listStyle_cicle icon_img'>$listStyle{name}</b><span>${name}</span><span style='color:#ccc;display:block;'></span></div>";
        this.idKey = idKey || "id";
        this.pidKey = pidKey || "parentId";
        this.nameKey = nameKey || "name";
        this.selectData = [];
        this.inputStyle = inputStyle || "<input value='请选择' type='text' />";
        this.append = function(n,id){
            //新增输入框
            this.api.data[this.pidKey] = id;
            var url = this.api.url;
            for(var k in this.api.data){
                url = url.replace(new RegExp("[$]{" + k + "}","g"), this.api.data[k]);
            }
            var _inputStyle = this.inputStyle.replace("id='num${n}'","");
            _inputStyle = _inputStyle.replace("<input","<input id='num" + this.id.toString() + n.toString() + "'")
            obj.append(_inputStyle);
            var select = new a.AutoCompleInputSelect($("#num" + this.id + n),url,this.api.data,this.listStyle,this.idKey,this.nameKey,function(res){
                if(res && res.length == 1){
                    _this.selectData[n] = res[0];
                    $("#num" + _this.id + n).nextAll().remove();
                    _this.selectData.length = n + 1;
                    _this.api.data[_this.pidKey] = _this.selectData[n][_this.idKey];
                    var url = _this.api.url;
                    for(var k in _this.api.data){
                        url = url.replace(new RegExp("[$]{" + k + "}","g"), _this.api.data[k]);
                    }
                    var ajax = new Ajax(url);
                    ajax.meth = _this.api.meth;
                    ajax.data = _this.api.data;
                    ajax.do(function(res){
                        if(res && res.rows && res.rows.length > 0){
                            _this.append(n + 1,_this.selectData[n][_this.idKey]);
                        }
                    })
                }
            });
        }
        this.setSelect = function(n){
            if($("#num" + this.id + n).size() == 1){
                $("#num" + this.id + n).val(this.selectData[n][this.nameKey]);
            }
        }
        this.load = function(id){
            this.append(0,id);
        }
        this.val = function(s){
            if(s != undefined){
                this.selectData = s;
                for(var i = 0; i < s.length; i++){
                    if(i < s.length - 1) this.append(i + 1,s[i][this.idKey]);
                    this.setSelect(i);
                }
            }else{
                return this.selectData.length > 0 ?[{id:this.selectData[this.selectData.length - 1][this.idKey]}] : [];
            }
        }
        this.load(id);
    }
})(window)