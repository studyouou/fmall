/**
 * Created by zhm on 17/3/3.
 */
$(function(){
    //定义json数据
    var main = {
        init:function(){
            this.prevAndNext();//上一个或者下一个按钮
            this.shareAndStart();//分享和产品评星
            this.getCode();//获取四位验证码
            this.proAdd();//产品数量的增加和减少


        },

        //上一个或者下一个按钮
        prevAndNext:function(){
            var buyDetail = '[{"id": 1, "src": "images/buy_list1.jpg", "title": "智利进口新鲜蓝莓 4盒", "price": "99.00", "detail_src":"images/buy_detail1.png"}, {"id": 2, "src": "images/buy_list2.jpg", "title": "进口香蕉超甜蕉", "price": "8.00", "detail_src":"images/buy_detail2.png"}, {"id": 3, "src": "images/buy_list3.jpg", "title": "菲律宾进口菠萝凤梨 2个", "price": "38.80", "detail_src":"images/buy_detail3.png"}, {"id": 4, "src": "images/buy_list4.jpg", "title": "福建特级红心蜜柚 4个", "price": "39.90", "detail_src":"images/buy_detail4.png"}, {"id": 5, "src": "images/buy_list5.jpg", "title": "广西青皮芒果青芒玉芒 5斤", "price": "69.00", "detail_src":"images/buy_detail5.png"}, {"id": 6, "src": "images/buy_list6.jpg", "title": "加力果嗄啦果苹果12个装", "price": "39.90", "detail_src":"images/buy_detail6.png"}, {"id": 7, "src": "images/buy_list7.jpg", "title": "国产绿奇异果 16颗", "price": "48.00", "detail_src":"images/buy_detail7.png"}, {"id": 8, "src": "images/buy_list8.jpg", "title": "佳沛新西兰阳光金奇异果8颗", "price": "59.00", "detail_src":"images/buy_detail8.png"}, {"id": 9, "src": "images/buy_list9.jpg", "title": "精选百香果西番莲8颗", "price": "12.00", "detail_src":"images/buy_detail9.png"}, {"id": 10, "src": "images/buy_list10.jpg", "title": "莲藕5g", "price": "39.90", "detail_src":"images/buy_detail10.png"}, {"id": 11, "src": "images/buy_list11.jpg", "title": "绿芦笋200g", "price": "20.00", "detail_src":"images/buy_detail11.png"}, {"id": 12, "src": "images/buy_list12.jpg", "title": "美国进口红啤梨 6个", "price": "48.00", "detail_src":"images/buy_detail12.png"}, {"id": 13, "src": "images/buy_list13.jpg", "title": "美国进口无籽红提 1kg", "price": "39.00", "detail_src":"images/buy_detail13.png"}, {"id": 14, "src": "images/buy_list14.jpg", "title": "墨西哥进口牛油果 5个", "price": "39.90", "detail_src":"images/buy_detail14.png"}, {"id": 15, "src": "images/buy_list15.jpg", "title": "南非进口黄柠檬 6个装", "price": "29.00", "detail_src":"images/buy_detail15.png"}]';

            var index = 1;
            var count ;
            function createPro(data,index) {
                var html1 = "";
                var html2 = "";
                var html3 = "";
                var html4 = "";

                var prodata = $.parseJSON(data);
                count = prodata.length;
                for (var i = 0; i < prodata.length; i++) {
                    if(prodata[i].id == index){
                        html1 = '<img src="'+prodata[i].src+'" alt=""/>';
                        html2 = '<span>'+prodata[i].title+'</span>';
                        html3 = prodata[i].price;
                        html4 = '<img src="'+prodata[i].detail_src+'" alt=""/>';

                    }
                }
                $('.pro_pic').html(html1);
                $('.show_r_title').html(html2);
                $('.show_r_price_num').html(html3);
                $('.detailShow').html(html4);


            }

            createPro(buyDetail,index);
            //下一个的点击事件
            $('.show_l_next').click(function(){
                index++;
                if(index>count)return alert("已到达最后一页");
                createPro(buyDetail,index)

            });
            //上一个的点击事件
            $('.show_l_prev').click(function(){
                index--;
                if(index<1) return alert("已到达第一页");
                createPro(buyDetail,index);

            })

        },
        //分享和产品评星
        shareAndStart:function(){
            //分享
            $('.show_collect span').eq(1).children("i").mouseover(function(){
                $(".show_share").show();
                $(this).parent().addClass('span_hover');
            }).mouseout(function(){
                $(".show_share").hide();
                $(this).parent().removeClass('span_hover');
            });
            //产品评星
            //1. 给所有的li注册mouseenter事件
            var start = $(".start i");
            start.click(function () {
                $(this).addClass('s2').prevAll().addClass('s2');
                $(this).addClass('s1');
            }).mouseleave(function () {
                $(this).children().addClass('s1');
                $("i.current").addClass('s2').prevAll().addClass('s2');
            });
            start.click(function () {
                $(this).addClass("current").siblings().removeClass("current")
            })

        },
        //购买物品数量的增加和减少
        proAdd:function(){
            var ipt = $('.show_r_count .count_input');
            //增加
            $('.show_r_count #up').click(function(){
                var index = ipt.val();
                index++;
                ipt.val(index);
            });

            $('.show_r_count #down').click(function(){
                var index = ipt.val();
                if(index == 1)return alert('至少请选择一个！');
                index--;
                ipt.val(index);
            })
        },


        //随机验证码
        getCode:function(){
            function createCode(){
                //1.将所有字母，数字装入一个数组备用
                var codes = [];
                //2.数字(0-9)大写字母(A-Z)小写字母（a-z）
                for (var i = 48; i <= 57; i++) {
                    codes.push(i);
                }
                for (var i = 65; i <= 90; i++) {
                    codes.push(i);
                }
                for (var i = 97; i <= 122; i++) {
                    codes.push(i);
                }

                //3 从0-61之间取随机数
                var arr = [];
                for(var i = 0;i<4;i++){
                    var index = Math.floor(Math.random() * (61 - 0 + 1)+0);
                    var char = String.fromCharCode(codes[index]);
                    arr.push(char);
                }
                //4.输出验证码
                var code=arr.join("");
                $('.show_code').html(code);
            }
            createCode();
            $('.show_code').click(createCode);
        }



    };

    main.init();





});
