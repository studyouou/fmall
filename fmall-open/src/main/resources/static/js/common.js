//展示loading
function g_showLoading(msg){
	var idx = layer.msg(msg+'...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;
	return idx;
}

function checkPhone(phone){
    if(!(/^1[34578]\d{9}$/.test(phone))){
        alert("手机号码有误，请重填");
        return false;
    }
}

function backHtml() {
    var prevLink = document.referrer;
    if($.trim(prevLink)==''){
        window.location.href = 'index.html';
        return;
    }else {
        if (prevLink.indexOf('register.html') != -1) {		//来自注册页面
            window.location.href = 'index.html';
            return;
        }
        window.location.href = prevLink;
    }
}
//salt
var g_passsword_salt="1a2b3c4d"

// 获取url参数
function g_getQueryString(name) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == name){return pair[1];}
    }
    return(null);
};
//设定时间格式化函数，使用new Date().format("yyyyMMddhhmmss");  
Date.prototype.format = function (format) {  
    var args = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
    };  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var i in args) {  
        var n = args[i];  
        if (new RegExp("(" + i + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));  
    }  
    return format;  
};  

