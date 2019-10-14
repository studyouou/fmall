$(function () {
    var userTip = $("#userTip");
    var userMenLogin = $("#userMenLogin");

    var init = {
        showInfo:function () {
            $.ajax({
                url:"/isLogin",
                type:"GET",
                success:function (result) {
                    if (result.code==0){
                        var user = result.data;
                        var name = user.nickName;
                        if (!name || name == "" || name=="null" ||name=="NULL"){
                            name = user.tel;
                        }
                        userMenLogin.append("<button class=\"btn btn-default dropdown-toggle\" type=\"button\" id=\"userMenus\" data-toggle=\"dropdown\">\n" +
                            "                            <i class=\"fas fa-user\"></i>\n" +
                            "                            <span>"+name+"</span>\n" +
                            "                            <span class=\"caret\"></span></button>\n" +
                            "                        <ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"menu1\">\n" +
                            "                            <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"orderList.html\">查看订单</a></li>\n" +
                            "                            <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"addresses.html\">查看收货地址</a></li>\n" +
                            "                            <li role=\"presentation\" class=\"divider\"></li>\n" +
                            "                            <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"updateInfo.html\">修改个人信息</a></li>\n" +
                            "                            <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"updatePass.html\">修改密码</a></li>\n" +
                            "                            <li role=\"presentation\" class=\"divider\"></li>\n" +
                            "                            <li role=\"presentation\"><a id=\"logout\" role=\"menuitem\" tabindex=\"-1\" onclick=\"logout()\">注销</a></li>\n" +
                            "                        </ul>");
                        $("#logout").click(function () {
                            g_showLoading("正在注销");
                            $.ajax({
                                url:"/auth/logout",
                                type:"POST",
                                success:function (result) {
                                    if (result.code == 0){
                                        layer.closeAll();
                                        layer.msg("注销成功");
                                        window.location.href="index.html";
                                    }else {
                                        layer.closeAll();
                                        layer.msg(result.msg);
                                        window.location.href="index.html";
                                    }
                                },
                                error:function () {
                                    layer.closeAll();
                                    layer.msg("服务器错误");
                                }
                            });
                        });

                    }else{
                        userTip.append("<span><a href=\"login.html\">登录/免费注册</a></span>");
                    }
                }
            });
        }
    };

    init.showInfo();
});

function logout() {
    g_showLoading("正在注销");
    $.ajax({
        url:"/auth/logout",
        type:"POST",
        success:function (result) {
            if (result.code == 0){
                layer.close();
                layer.msg("注销成功");
                window.location.href="index.html";
            }else {
                layer.close();
                layer.msg(result.msg);
                window.location.href="index.html";
            }
        },
        error:function () {
            layer.msg("服务器错误");
        }
    });
};
