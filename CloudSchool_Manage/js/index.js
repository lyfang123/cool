var accessToken;
var accountId;
var schoolId;
var schoolInfo;
var groupId;
var groupInfo;

(function () {
    //获取登录信息
    var user = localStorage && localStorage.user && JSON.parse(localStorage.user) ? JSON.parse(localStorage.user) : "";
    if (!user) {
        alertx("请登录");
        location = "login.html";
    }else{
        accessToken = user.accessToken;
        accountId = user.accountId;
        schoolInfo = user.schoolInfo;
        $("#accountName").html(decodeURIComponent(user.accountName));
        var accountPhoto = new Image();
        accountPhoto.onload = function(){
            $("#accountPhoto").html(accountPhoto);
        };  
        accountPhoto.src = user.accountPhoto;
        var defaultSchoolId = localStorage.schoolId;//浏览器缓存选择的学校
        var useDefaultSchoolId = false;
        if(schoolInfo.length > 1){
            var schoolInfoHtml = "<div class=\"adminWindow\"><div class=\"adminWindow_t\">▲</div><div class=\"adminWindow_c boxShadow\">";
            for(var i = 0; i < schoolInfo.length; i++){
                if(defaultSchoolId == schoolInfo[i].schoolId){
                    useDefaultSchoolId = true;
                }
                schoolInfoHtml += "<div schoolId=\"" + schoolInfo[i].schoolId + "\" class=\"adminWindow_div\">" + schoolInfo[i].schoolName + "</div>";
            }
            schoolInfoHtml += "</div></div>";
            $("#schoolName").css({background:"url(\"images/arrow_down.png\") right center no-repeat",paddingRight: "15px"});
            $("#schoolName").after(schoolInfoHtml);
            adminWindow($("#schoolName"));
            $("#schoolName").next().find(".adminWindow_div").click(function(){
                changeSchool($(this).attr("schoolId"),schoolId);
            })
        }else{
            $("#schoolName").css({background:"none",paddingRight: "0px"});
            if(schoolInfo.length == 1){
                $("#schoolName").hide();
                $("#logo_text").html(schoolInfo[0].schoolName);
                var img = new Image();
                img.onload = function(){
                    $("#logo_img").attr("src",schoolInfo[0].schoolLogo);
                }
                img.src = schoolInfo[0].schoolLogo;
            }
        }
        if(useDefaultSchoolId && defaultSchoolId != undefined){
            changeSchool(defaultSchoolId);
        }else{
            changeSchool(schoolInfo[0].schoolId);
        }
    }
    function changeSchool(n,_n){
        localStorage.schoolId = n;
        schoolId = n;
        if(schoolInfo.length <= 1){
            $("#schoolName").html(schoolInfo[0].schoolName);
        }else{
            $("#schoolName").next().find(".adminWindow_div").each(function(i){
                if($(this).attr("schoolId") == n){
                    $(this).addClass("active");
                    $("#schoolName").html($(this).html());
                }else{
                    $(this).removeClass("active");
                }
            })
        }
        groupInfo = [];
        //获取group
        $.ajax({
            url: ucIP + "/api/uc/v0.1/" + accountId + "/groups",
            type: "GET",
            dataType: "json",
            beforeSend: function (XMLHttpRequest) {
                XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                XMLHttpRequest.setRequestHeader('schoolId', schoolId);
            },
            success: function (res) {
                groupInfo = res;
                if (res) {
                    if(res.length > 0){
                        var defaultGroupId = localStorage.groupId;//浏览器缓存选择的角色
                        var useDefaultGroupId = false;
                        if(res.length > 0){
                            var groupInfoHtml = "";
                            for(var i = 0; i < res.length; i++){
                                if(res[i].groupId == defaultGroupId) useDefaultGroupId = true;
                                groupInfoHtml += "<div class='adminMsg_div_Line_dashed'></div>";
                                groupInfoHtml += "<div class='groupDiv' groupId=\"" + res[i].groupId + "\">" + res[i].groupName + "</div>";
                            }
                            groupInfoHtml += "";
                            $("#groups").html(groupInfoHtml);
                            $(".groupDiv").click(function(){
                                changeGroup($(this).attr("groupId"),groupId);
                            })
                        }
                        if(useDefaultGroupId && defaultGroupId != undefined) {
                            changeGroup(defaultGroupId);
                        }else{
                            changeGroup(res[0].groupId);
                        }
                    }else{
                        ajaxMenu(_n);
                        /*alertx("当前用户并无权限登录系统",function(){
                            localStorage.user = "";
                            location = "login.html";
                        });*/
                    }
                } else {
                    alertx("用户权限获取失败");
                    if(_n != undefined) {
                        changeSchool(_n);
                    }else{
                        localStorage.user = "";
                        location = "login.html";
                    }
                }
            },
            fail: function (e) {
                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                    alertx("登录过期，请重新登陆");
                    localStorage.user = "";
                    location = "login.html";
                }else{
                    alertx("用户权限获取失败");
                    if(_n != undefined) {
                        changeSchool(_n);
                    }else{
                        localStorage.user = "";
                        location = "login.html";
                    }
                }
            },
            error: function (e) {
                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                    alertx("登录过期，请重新登陆");
                    localStorage.user = "";
                    location = "login.html";
                }else{
                    alertx("用户权限获取出错");
                    if(_n != undefined) {
                        changeSchool(_n);
                    }else{
                        localStorage.user = "";
                        location = "login.html";
                    }
                }
            }
        })
    }
    function changeGroup(n,_n){
        localStorage.groupId = n;
        groupId = n;
        $(".groupDiv").each(function(){
            if($(this).attr("groupId") == n){
                $(this).addClass("active");
                completeName($(this).html());
            }else{
                $(this).removeClass("active");
            }
        })
        function completeName(groupHTML){
            var nameHTML = $("#accountName").html().replace(/\s*/g,"");
            $("#groupName").html(groupHTML);
            if(nameHTML.length < groupHTML.length){
                var len = groupHTML.length - nameHTML.length;
                for(var i = 0; i < len; i++){
                    nameHTML += "　";
                }
            }
            $("#accountName").html(nameHTML);
        }
        ajaxMenu(_n);
        open({url: "modules/school/material/materialList.html", title: "上传管理"});
        //open({url: "modules/leave/qrcode.html", title: "二维码"});
        //note("系统正式上线,欢迎使用...");
    }    
    //鼠标移动显示个人信息
    var adminMsgTimer;
    $("#accountPhoto").mouseover(function(){
        if(adminMsgTimer) clearTimeout(adminMsgTimer);
        $(".adminMsg").show();
    })
    $("#accountPhoto").mouseout(function(){
        adminMsgTimer = setTimeout(function(){
            $(".adminMsg").hide();
        },500)
    })
    $(".adminMsg").mouseover(function(){
        clearTimeout(adminMsgTimer);
    })
    $(".adminMsg").mouseout(function(){
        adminMsgTimer = setTimeout(function(){
            $(".adminMsg").hide();
        },500)
    })
    //退出系统
    $("#loginOutBtn").click(function () {
        confirmx("退出系统？",function(){
            $.ajax({
                url: ucIP + "/api/uc/v0.1/loginout",
                type: "GET",
                dataType: "json",
                data: {accessToken: accessToken},
                beforeSend: function (XMLHttpRequest) {
                    XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                    XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                },
                success: function (res) {
                    alertx("退出成功");
                    localStorage.user = "";
                    location = "login.html";
                },
                fail: function () {
                    alertx("退出失败");
                    localStorage.user = "";
                    location = "login.html";
                },
                error: function () {
                    alertx("退出出错");
                    localStorage.user = "";
                    location = "login.html";
                }
            })
        })
    });
    $("#yunFileBtn").click(function () {
        open({url: "yunFile.html", title: "个人云盘", data: {}});
        $(this).parent().addClass("adminDivActive");
    });
    $("#deskTopBtn").click(function () {
        open({url: "deskTop.html", title: "我的桌面", data: {}});
        $(this).parent().addClass("adminDivActive");
    });
    function adminWindow(){
        for(var i = 0; i < arguments.length; i++){
            arguments[i][0].timer = 0;
            arguments[i].mouseover(function(){
                if($(this)[0].timer) clearTimeout($(this)[0].timer);
                windowShow($(this).next(),$(this));
            })
            arguments[i].mouseout(function(){
                var _this = $(this);
                $(this)[0].timer = setTimeout(function(){
                    _this.next().hide();
                },500);
            })
            arguments[i].next().mouseover(function(){
                clearTimeout($(this).prev()[0].timer);
            })
            arguments[i].next().mouseout(function(){
                var _this = $(this);
                $(this).prev()[0].timer = setTimeout(function(){
                    _this.hide();
                },500);
            })
        }
        function windowShow(w,_w){
            var left = _w.offset().left;
            var top = _w.offset().top + _w.height() + 20;
            if(_w.width() > w.width()){
                left += _w.width() - w.width();
            }else{
                left -= w.width() - _w.width();
            }
            left += 30;
            w.css({display:'block',left:left + "px",top:top + "px"});
            w.find(".adminWindow_t").css({marginLeft:(w.width() - 28) + "px"});
        }
    }
    function ajaxMenu(_n){
        $("#menuActive").animate({top:'113px'}, 300);
        var leftLoading = new loading(999, 0);
        //加载左边菜单
        leftLoading.open("加载菜单中...", $(".left"));
        var menuAjaxUrl = webIP + "/api/security/v0.1/groups/menus";
        $.ajax({
            url: menuAjaxUrl,
            type: "GET",
            dataType: "json",
            beforeSend: function (XMLHttpRequest) {
                XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                XMLHttpRequest.setRequestHeader('schoolId', schoolId);
            },
            success: function (res) {
                leftLoading.close();
                if (res && res.length > 0) {
                    var menus = res;
                    var menuString = "";
                    for (var i = 0; i < menus.length; i++) {
                        menuString += "<div class=\"menu_1\">";
                        menuString += "<div class=\"menu_1_box\" url=\"" + menus[i].action + "\" data-id=\"" + menus[i].id + "\"><span class=\"menu_1_img\"><img src='" + menus[i].icon + "' /></span><font class=\"menu_1_font" + (!menus[i].action ? " menu_arrow" : "") + "\">" + menus[i].title + "</font></div>";
                        menuString += "</div>";
                    }
                    $("#menu_box").html(menuString);
                    $(".menu_1_box").each(function () {
                        $(this).bind("click", function () {
                            $("#menuActive").animate({top: $(this).offset().top + 'px'}, 300);
                            $(".menu_1_active").removeClass("menu_1_active");
                            var _this = $(this);
                            _this.addClass("menu_1_active");
                            var id = $(this).attr("data-id");
                            if ($(this).attr("url") != "") {
                                open({url: $(this).attr("url"), title: $(this).find(".menu_1_font").eq(0).html()});
                                return false;
                            }
                            if(leftWidth < 100) return false;
                            $(this).find(".menu_arrow").css({background:"url(images/arrow_active.png) right center no-repeat"});
                            if (_this.attr("isGetChild") != "true") {
                                leftLoading.open("加载中...", $(".left"));
                                $.ajax({
                                    url: menuAjaxUrl,
                                    type: "GET",
                                    dataType: "json",
                                    data: {parentId: id},
                                    beforeSend: function (XMLHttpRequest) {
                                        XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                                        XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                                    },
                                    success: function (res) {
                                        leftLoading.close();
                                        _this.attr("isGetChild", "true");
                                        if (res && res.length > 0) {
                                            menus = res;
                                            _this.find(".menu_1_font").addClass("menu_arrow");
                                            menuString = "";
                                            for (var i = 0; i < menus.length; i++) {
                                                menuString += "<div class=\"menu_2\">";
                                                menuString += "<div class=\"menu_2_box\" url=\"" + menus[i].action + "\" data-id=\"" + menus[i].id + "\"><span class=\"menu_2_img\"></span><font class=\"menu_2_font" + (!menus[i].action ? " menu_arrow" : "") + "\">" + menus[i].title + "</font></div>";
                                                menuString += "</div>";
                                            }
                                            _this.after("<div class=\"menu_1_children\">" + menuString + "</div>");
                                            _this.parent().find(".menu_2_box").each(function () {
                                                $(this).bind("click", function () {
                                                    if(leftWidth < 100) $(".menu_1_children").hide();
                                                    $("#menuActive").animate({top: $(this).parent().parent().parent().offset().top + 'px'}, 300);
                                                    $(".menu_2_active").removeClass("menu_2_active");
                                                    var _this = $(this);
                                                    _this.addClass("menu_2_active");
                                                    var id_2 = $(this).attr("data-id");
                                                    if ($(this).attr("url") != "") {
                                                        open({
                                                            url: $(this).attr("url"),
                                                            title: $(this).find(".menu_2_font").eq(0).html()
                                                        });
                                                        return false;
                                                    }
                                                    if (_this.attr("isGetChild") != "true") {
                                                        leftLoading.open("加载中...", $(".left"));
                                                        $.ajax({
                                                            url: menuAjaxUrl,
                                                            type: "GET",
                                                            dataType: "json",
                                                            data: {parentId: id_2},
                                                            beforeSend: function (XMLHttpRequest) {
                                                                XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                                                                XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                                                            },
                                                            success: function (res) {
                                                                leftLoading.close();
                                                                _this.attr("isGetChild", "true");
                                                                if (res && res.length > 0) {
                                                                    menus = res;
                                                                    _this.find(".menu_1_font").addClass("menu_arrow");
                                                                    menuString = "";
                                                                    for (var i = 0; i < menus.length; i++) {
                                                                        menuString += "<div class=\"menu_3\">";
                                                                        menuString += "<div class=\"menu_3_box\" url=\"" + menus[i].action + "\"><font class=\"menu_3_font\">" + menus[i].title + "</font></div>";
                                                                        menuString += "</div>";
                                                                    }
                                                                    _this.after("<div class=\"menu_2_children\">" + menuString + "</div>");
                                                                    _this.parent().find(".menu_3_box").each(function () {
                                                                        $(this).bind("click", function () {
                                                                            if(leftWidth < 100) $(".menu_1_children").hide();
                                                                            $(".menu_3_active").removeClass("menu_3_active");
                                                                            $(this).addClass("menu_3_active");
                                                                            if ($(this).attr("url") != "undefined") open({
                                                                                url: $(this).attr("url"),
                                                                                title: $(this).find(".menu_3_font").eq(0).html()
                                                                            });
                                                                        })
                                                                    })
                                                                } else {
                                                                    _this.find(".menu_2_font").removeClass("menu_arrow");
                                                                }
                                                            },
                                                            fail: function (e) {
                                                                leftLoading.close();
                                                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                                                    alertx("登录过期，请重新登陆",function(){
                                                                        localStorage.user = "";
                                                                        location = "login.html";
                                                                    })
                                                                }else{
                                                                    alertx("菜单获取失败");
                                                                }
                                                            },
                                                            error: function (e) {
                                                                leftLoading.close();
                                                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                                                    alertx("登录过期，请重新登陆",function(){
                                                                        localStorage.user = "";
                                                                        location = "login.html";
                                                                    })
                                                                }else{
                                                                    alertx("菜单获取出错");
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        var children = _this.next();
                                                        if(children.attr("isShow") == "true" || children.attr("isShow") == undefined){
                                                            children.css("display","none");
                                                            children.attr("isShow","false")
                                                        }else{
                                                            children.css("display","block");
                                                            children.attr("isShow","true")
                                                        }
                                                    }
                                                })
                                            })
                                        } else {
                                            _this.find(".menu_1_font").removeClass("menu_arrow");
                                        }
                                    },
                                    fail: function () {
                                        leftLoading.close();
                                        alertx("菜单获取失败");
                                    },
                                    error: function () {
                                        leftLoading.close();
                                        alertx("菜单获取出错");
                                    }
                                });
                            } else {
                                var children = _this.next();
                                if(children.attr("isShow") == "true" || children.attr("isShow") == undefined){
                                    children.css("display","none");
                                    children.attr("isShow","false");
                                    _this.find(".menu_arrow").css({background:"url(images/arrow.png) right center no-repeat"});
                                }else{
                                    children.css("display","block");
                                    children.attr("isShow","true");
                                    _this.find(".menu_arrow").css({background:"url(images/arrow_active.png) right center no-repeat"});
                                }
                            }
                        })
                        var hideTimer;
                        //左边图标模式
                        $(this).bind("mouseover", function (e) {
                            if(leftWidth > 100) return false;
                            if(!hideTimer) clearTimeout(hideTimer);
                            $("#menuActive").css({top: $(this).offset().top + 'px'});
                            $(".menu_1_active").removeClass("menu_1_active");
                            var _this = $(this);
                            _this.addClass("menu_1_active");
                            var id = $(this).attr("data-id");
                            if ($(this).attr("url") != "") {
                                open({url: $(this).attr("url"), title: $(this).find(".menu_1_font").eq(0).html()});
                                return false;
                            }
                            $(this).find(".menu_arrow").css({background:"url(images/arrow_active.png) right center no-repeat"});
                            $(".menu_1_children").hide();
                            _this.find(".menu_arrow").css({background:"url(images/arrow.png) right center no-repeat"});
                            if (_this.attr("isGetChild") != "true") {
                                _this.attr("isGetChild", "true");
                                _this.after("<div class=\"menu_1_children\"><div style='line-height:40px;text-align:center;'>获取中...</div></div>");
                                var _menu_1_children = _this.next();
                                _menu_1_children.mouseout(function(){
                                    if(leftWidth > 100) return false;
                                    var $this = $(this);
                                    hideTimer = setTimeout(function(){
                                        $this.hide();
                                        _this.find(".menu_arrow").css({background:"url(images/arrow.png) right center no-repeat"});
                                    },500);
                                })
                                _menu_1_children.mouseover(function(){
                                    if(leftWidth > 100) return false;
                                    clearTimeout(hideTimer);
                                })
                                $.ajax({
                                    url: menuAjaxUrl,
                                    type: "GET",
                                    dataType: "json",
                                    data: {parentId: id},
                                    beforeSend: function (XMLHttpRequest) {
                                        XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                                        XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                                    },
                                    success: function (res) {
                                        if (res && res.length > 0) {
                                            menus = res;
                                            _this.find(".menu_1_font").addClass("menu_arrow");
                                            menuString = "";
                                            for (var i = 0; i < menus.length; i++) {
                                                menuString += "<div class=\"menu_2\">";
                                                menuString += "<div class=\"menu_2_box\" url=\"" + menus[i].action + "\" data-id=\"" + menus[i].id + "\"><span class=\"menu_2_img\"></span><font class=\"menu_2_font" + (!menus[i].action ? " menu_arrow" : "") + "\">" + menus[i].title + "</font></div>";
                                                menuString += "</div>";
                                            }
                                            _menu_1_children.html(menuString);
                                            _this.parent().find(".menu_2_box").each(function () {
                                                $(this).bind("click", function () {
                                                    if(leftWidth < 100) $(".menu_1_children").hide();
                                                    $(".menu_2_active").removeClass("menu_2_active");
                                                    var _this = $(this);
                                                    _this.addClass("menu_2_active");
                                                    var id_2 = $(this).attr("data-id");
                                                    if ($(this).attr("url") != "") {
                                                        open({
                                                            url: $(this).attr("url"),
                                                            title: $(this).find(".menu_2_font").eq(0).html()
                                                        });
                                                        return false;
                                                    }
                                                    if (_this.attr("isGetChild") != "true") {
                                                        leftLoading.open("加载中...", $(".left"));
                                                        $.ajax({
                                                            url: menuAjaxUrl,
                                                            type: "GET",
                                                            dataType: "json",
                                                            data: {parentId: id_2},
                                                            beforeSend: function (XMLHttpRequest) {
                                                                XMLHttpRequest.setRequestHeader('accessToken', accessToken);
                                                                XMLHttpRequest.setRequestHeader('schoolId', schoolId);
                                                            },
                                                            success: function (res) {
                                                                leftLoading.close();
                                                                _this.attr("isGetChild", "true");
                                                                if (res && res.length > 0) {
                                                                    menus = res;
                                                                    _this.find(".menu_1_font").addClass("menu_arrow");
                                                                    menuString = "";
                                                                    for (var i = 0; i < menus.length; i++) {
                                                                        menuString += "<div class=\"menu_3\">";
                                                                        menuString += "<div class=\"menu_3_box\" url=\"" + menus[i].action + "\"><font class=\"menu_3_font\">" + menus[i].title + "</font></div>";
                                                                        menuString += "</div>";
                                                                    }
                                                                    _this.after("<div class=\"menu_2_children\">" + menuString + "</div>");
                                                                    _this.parent().find(".menu_3_box").each(function () {
                                                                        $(this).bind("click", function () {
                                                                            if(leftWidth < 100) $(".menu_1_children").hide();
                                                                            $(".menu_3_active").removeClass("menu_3_active");
                                                                            $(this).addClass("menu_3_active");
                                                                            if ($(this).attr("url") != "undefined") open({
                                                                                url: $(this).attr("url"),
                                                                                title: $(this).find(".menu_3_font").eq(0).html()
                                                                            });
                                                                        })
                                                                    })
                                                                } else {
                                                                    _this.find(".menu_2_font").removeClass("menu_arrow");
                                                                }
                                                            },
                                                            fail: function (e) {
                                                                leftLoading.close();
                                                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                                                    alertx("登录过期，请重新登陆",function(){
                                                                        localStorage.user = "";
                                                                        location = "login.html";
                                                                    })
                                                                }else{
                                                                    alertx("菜单获取失败");
                                                                }
                                                            },
                                                            error: function (e) {
                                                                leftLoading.close();
                                                                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                                                                    alertx("登录过期，请重新登陆",function(){
                                                                        localStorage.user = "";
                                                                        location = "login.html";
                                                                    })
                                                                }else{
                                                                    alertx("菜单获取出错");
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        var children = _this.next();
                                                        if(children.attr("isShow") == "true" || children.attr("isShow") == undefined){
                                                            children.css("display","none");
                                                            children.attr("isShow","false")
                                                        }else{
                                                            children.css("display","block");
                                                            children.attr("isShow","true")
                                                        }
                                                    }
                                                })
                                            })
                                        } else {
                                            _this.find(".menu_1_font").removeClass("menu_arrow");
                                        }
                                    },
                                    fail: function () {
                                        _this.removeAttr("isGetChild");
                                        alertx("菜单获取失败");
                                    },
                                    error: function () {
                                        _this.removeAttr("isGetChild");
                                        alertx("菜单获取出错");
                                    }
                                });
                            } else {
                                var children = _this.next();
                                children.css({display:'block'});
                                children.attr("isShow","false");
                                _this.find(".menu_arrow").css({background:"url(images/arrow.png) right center no-repeat"});
                            }
                        })
                    })
                } else {
                    alertx("抱歉,获取菜单失败");
                    if(_n != undefined) {
                        changeGroup(_n);
                    }
                }
            },
            fail: function (e) {
                leftLoading.close();
                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                    alertx("登录过期，请重新登陆",function(){
                        localStorage.user = "";
                        location = "login.html";
                    })
                }else{
                    alertx("菜单获取失败");
                    if(_n != undefined) changeSchool(_n);
                }
            },
            error: function (e) {
                leftLoading.close();
                if(e.responseText && e.responseText.indexOf("accessToken无效") > 0){
                    alertx("登录过期，请重新登陆",function(){
                        localStorage.user = "";
                        location = "login.html";
                    })
                }else{
                    alertx("菜单获取出错");
                    if(_n != undefined) changeSchool(_n);
                }
            }
        })
    }
    $("#noteClose").click(function () {
        $(".note").hide();
    });
    $("#right").click(function (e) {
        var target = e.target;
        if (target.className.indexOf("input") > -1) {
            $(target).parent().find(".data_error").hide();
        }
        if($("#adminMenus").css("display") == "block"){
            $("#adminMenus").hide();
        }
    });
    var leftWidth = 200; //50 || 200
    setSize();
    $(window).resize(function(){
        setSize(true);
    })
    $("#sidebar-toggle-light").click(function(){
        leftWidth = leftWidth == 50 ? 200 : 50;
        setSize();
    })
    function setSize(b){
        var windowWidth = $(window).width();
        var windowHeight = $(window).height();
        $(".left").css({minHeight:(windowHeight - 127) +  'px',width:leftWidth + 'px'});
        $(".rightContent").css({minHeight:(windowHeight - 127) +  'px'});
        $(".right").css({marginLeft:leftWidth + 'px'});
        if(leftWidth < 100) {
            $(".left").addClass("leftWidth");
            $("#menuActive").css({top: $(".menu_1_active").offset().top + 'px'});
        }else{
            $(".left").removeClass("leftWidth");
        }
        if(!b) $(".menu_1_children").hide();
    }
})()