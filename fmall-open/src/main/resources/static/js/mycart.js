/**
 * Created by zhm on 17/3/5.
 */
$(function () {
    var main = {
        init: function () {
            this.cart();//购物车事件
            this.submit();//表单提交事件
        },
        //购物车
        cart: function () {
            totl();
            adddel();
            //全选
            $("#all").click(function () {
                all = $(this).prop("checked");
                $(".Each").each(function () {
                    $(this).prop("checked", all);
                })
            });
            //删除当前行
            $(".del").each(function () {
                $(this).click(function () {
                    $(this).parent().remove();
                    if ($(".imfor").length == 0) {
                        $("#susum").text(0);
                    }
                    totl();
                })
            });

            //合计
            function totl() {
                var sum = 0;
                $(".totle").each(function () {
                    sum += parseFloat($(this).text());
                    $("#susum").text(sum);
                })
            }

            function adddel() {
                //小计和加减
                //加
                $(".add").each(function () {
                    $(this).click(function () {
                        var $multi = 0;
                        var vall = $(this).prev().val();
                        vall++;
                        $(this).prev().val(vall);
                        $multi = parseFloat(vall) * parseFloat($(this).parent().prev().text());
                        $(this).parent().next().text(Math.round($multi));
                        totl();
                    })

                });
                //减
                $(".reduc").each(function () {
                    $(this).click(function () {
                        var $multi1 = 0;
                        var vall1 = $(this).next().val();
                        vall1--;
                        if (vall1 <= 0) {
                            vall1 = 1;
                        }
                        $(this).next().val(vall1);
                        $multi1 = parseFloat(vall1) * parseFloat($(this).parent().prev().text());
                        $(this).parent().next().text(Math.round($multi1));
                        totl();
                    })

                })


            }

        },

        //表单提交
        submit:function(){
            $('.btn').click(function(){
                var uname= $('.username').val();
                var uemail= $('.email').val();
                var utel= $('.tel').val();
                var upcode= $('.postalcode').val();
                var uaddr= $('.address').val();


                if (!/^[a-z]\w{5,17}$/i.test(uname)) {
                    /*alert("用户名格式不正确！");
                     return false;*/
                }

                if(!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(uemail) ){
                    alert("邮箱格式不正确！");
                    return false;
                }

                if(!/^1[3|4|5|7|8][0-9]{9}$/g.test(utel)){
                    alert("手机格式不正确！");
                     return false;
                }
                if (!/^\S{6}$/gi.test(upcode)) {
                     alert("邮政编码格式不正确！");
                     return false;
                }

                if (/^\S$/gi.test(uaddr)) {
                     alert("地址格式不正确！不能为空");
                     return false;
                }

                alert("提交成功");
                $('input').val("");

            })
        }
    };
    main.init();
    });
