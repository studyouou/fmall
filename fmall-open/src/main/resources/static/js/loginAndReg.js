
/*登录页面验证*/
$(function(){
    var main = {
        init:function(){
            // this.login();
            this.reg();
        },
        reg:function () {
            $("#registerBtn").click(function () {
                window.location.href= "register.html";
            });
        }
    };
    main.init();
});

function login() {
    $("#loginForm").validate({
        submitHandler:function(form){
            doLogin();
        }
    });
}
function doLogin() {
    g_showLoading("正在登陆");
    $.ajax({
        url:"/auth/login",
        type:"POST",
        data:{
            tel:$("#mobile").val(),
            password: $("#password").val()
        },
        success:function (result) {
            if (result.code == 0){
                layer.msg("登录成功,正在跳转");
                layer.closeAll();
                backHtml();
            }else {
                layer.closeAll();
                layer.msg(result.msg);
            }
        },
        error:function () {
            layer.closeAll();
            layer.msg("服务器错误")
        }
    });
};
