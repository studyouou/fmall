/**
 * Created by zhm on 17/2/25.
 */
$(function () {

    //定义要使用的json数据
    var allProData1 = '[{"id":1,"src":"images/hot_list1.jpg","title":"菠菜280g","price":"3.50"},{"id":2,"src":"images/hot_list2.jpg","title": "进口香蕉超甜蕉", "price": "8.00"},{"id":3,"src": "images/hot_list3.jpg","title": "菲律宾进口菠萝凤梨 2个", "price": "38.80"},{"id": 4,"src": "images/hot_list4.jpg", "title": "福建特级红心蜜柚 4个", "price": "39.90"},{"id": 5,"src": "images/hot_list5.jpg", "title": "广西青皮芒果青芒玉芒 5斤", "price": "69.00"},{"id": 6, "src": "images/hot_list6.jpg", "title": "加力果嗄啦果苹果12个装", "price": "39.90"},{"id": 7,"src": "images/hot_list7.jpg", "title": "国产绿奇异果 16颗", "price": "48.00"},{"id": 8,"src": "images/hot_list8.jpg", "title": "佳沛新西兰阳光金奇异果8颗", "price": "59.00"},{"id": 9,"src": "images/hot_list9.jpg","title": "精选百香果西番莲8颗","price": "12.00"},{"id": 10, "src": "images/hot_list10.jpg", "title": "莲藕5g", "price": "30.00"},{"id": 11, "src": "images/hot_list11.jpg", "title": "绿芦笋200g", "price": "20.00"},{"id": 12, "src":"images/hot_list12.jpg","title":"美国进口红啤梨 6个","price":"48.00"}]';
    var allProData2 = '[{"id":13,"src":"images/hot_list13.jpg","title":"美国进口无籽红提1kg","price":"39.00"},{"id":14,"src":"images/hot_list14.jpg","title":"墨西哥进口牛油果 5个","price":"39.90"},{"id":15,"src":"images/hot_list15.jpg","title":"南非进口黄柠檬 6个装","price":"29.00"}]';
    var selfFruit = '[{"id": 1, "src": "images/self_list1.jpg", "title": "大红脆甜水蜜桃 5斤", "price": "29.00", "lImg_src": "images/list_img1.jpg"}, {"id": 2, "src": "images/self_list2.jpg", "title": "福建特级红心蜜柚 4个", "price": "39.90", "lImg_src": "images/list_img2.jpg"}, {"id": 3, "src": "images/self_list3.jpg", "title": "广西青皮芒果青芒玉芒 5斤", "price": "69.00", "lImg_src":"images/list_img3.jpg"}, {"id": 4, "src": "images/self_list4.jpg", "title": "贵长猕猴桃绿心奇异果5斤", "price": "29.90", "lImg_src":"images/list_img4.jpg"}, {"id": 5, "src": "images/self_list5.jpg", "title": "国产绿奇异果 16颗", "price": "48.00", "lImg_src":"images/list_img5.jpg"}, {"id": 6, "src": "images/self_list6.jpg", "title": "精选百香果西番莲8颗", "price": "12.00", "lImg_src":"images/list_img6.jpg"}, {"id": 7, "src": "images/self_list7.jpg", "title": "四川大凉山会理石榴8个", "price": "29.00", "lImg_src":"images/list_img7.jpg"}, {"id": 8, "src": "images/self_list8.jpg", "title": "新疆库尔勒香梨 5斤礼盒装", "price": "59.00", "lImg_src":"images/list_img8.jpg"}, {"id": 9, "src": "images/self_list9.jpg", "title": "新鲜水果龙眼", "price": "10.00", "lImg_src":"images/list_img9.jpg"}, {"id": 10, "src": "images/self_list10.jpg", "title": "浙江涌泉蜜桔无核桔子5斤", "price": "39.00", "lImg_src":"images/list_img10.jpg"}, {"id": 11, "src": "images/self_list11.jpg", "title": "智利进口车厘子樱桃2斤", "price": "128.00", "lImg_src":"images/list_img11.jpg"}]';
    var importFruit1 = '[{"id": 1, "src": "images/import_list1.jpg", "title": "菲律宾进口菠萝凤梨 2个", "price": "38.80", "lImg_src": "images/import_list_img1.jpg"}, {"id": 2, "src": "images/import_list2.jpg", "title": "加力果嗄啦果苹果12个装", "price": "39.90", "lImg_src": "images/import_list_img2.jpg"}, {"id": 3, "src": "images/import_list3.jpg", "title": "佳沛新西兰阳光金奇异果8颗", "price": "59.00", "lImg_src": "images/import_list_img3.jpg"}, {"id": 4, "src": "images/import_list4.jpg", "title": "进口香蕉超甜蕉", "price": "8.00", "lImg_src": "images/import_list_img4.jpg"}, {"id": 5, "src": "images/import_list5.jpg", "title": "进口新鲜青苹果 5斤", "price": "19.00", "lImg_src": "images/import_list_img5.jpg"}, {"id": 6, "src": "images/import_list6.jpg", "title": "美国进口红啤梨 6个", "price": "48.00", "lImg_src": "images/import_list_img6.jpg"}, {"id": 7, "src": "images/import_list7.jpg", "title": "美国进口无籽红提 1kg", "price": "39.00", "lImg_src": "images/import_list_img7.jpg"}, {"id": 8, "src": "images/import_list8.jpg", "title": "墨西哥进口牛油果 5个", "price": "39.90", "lImg_src": "images/import_list_img8.jpg"}, {"id": 9, "src": "images/import_list9.jpg", "title": "南非进口黄柠檬 6个装", "price": "29.00", "lImg_src": "images/import_list_img9.jpg"}, {"id": 10, "src": "images/import_list10.jpg", "title": "泰国金枕头榴莲", "price": "179.00", "lImg_src": "images/import_list_img10.jpg"}, {"id": 11, "src": "images/import_list11.jpg", "title": "泰国进口山竹 2斤", "price": "39.00", "lImg_src": "images/import_list_img11.jpg"}, {"id": 12, "src": "images/import_list12.jpg", "title": "越南进口红心火龙果 5斤", "price": "38.00", "lImg_src": "images/import_list_img12.jpg"}]';
    var importFruit2 = '[{"id": 13, "src": "images/import_list13.jpg", "title": "智利进口新鲜蓝莓 4盒", "price": "99.00"}]';
    var truck = '[{"id": 1, "src": "images/truck_list1.jpg", "title": "菠菜280g", "price": "3.50", "lImg_src": "images/veg_list_img1.jpg"}, {"id": 2, "src": "images/truck_list2.jpg", "title": "莲藕5g", "price": "30.00", "lImg_src": "images/veg_list_img2.jpg"}, {"id": 3, "src": "images/truck_list3.jpg", "title": "绿芦笋200g", "price": "20.00", "lImg_src": "images/veg_list_img3.jpg"}, {"id": 4, "src": "images/truck_list4.jpg", "title": "西葫500g", "price": "14.00", "lImg_src": "images/veg_list_img4.jpg"}, {"id": 5, "src": "images/truck_list5.jpg", "title": "西兰花270g", "price": "19.00", "lImg_src": "images/veg_list_img5.jpg"}, {"id": 6, "src": "images/truck_list6.jpg", "title": "油菜300g", "price": "5.00", "lImg_src": "images/veg_list_img6.jpg"}]';


    var main = {
        //初始化函数
        init: function () {
            initSearch();
            this.getHomePage();
            this.roolNews();//标题滚动方法调用
            this.autoPic();//轮播图方法调用
            this.hotSale();//蔬果热卖的json数据加载调用
            this.aboutUs();//加入我们表单的验证方法
            this.tabChange();//商品详情页的选项卡切换
            this.returnTop();//返回顶部
            this.roolSlide();//滚动的侧边栏菜单
            this.slidePic();//幻灯片开始
            this.proAdd();//产品数量的增加和减少

        }
        ,
        getHomePage: function(){
            this.topPolling();
            this.showPicTop();
            this.gardenRecommendation();
            this.newFruitShow();
            this.buttomPolling();
        }
        ,
        topPolling: function() {
        $.ajax({
            url:"/home/queryTopPolling",
            type:"GET",
            success:function (result) {
                if (result.code == 0){
                    for (var i = 1; i <= result.data.length; i++ ){
                        for (var i = 1; i <= 3; i++ ){
                            $("#topPollingI"+i).attr("src",result.data[i-1].panelImgUrl);
                            $("#topA"+i).attr("href","shop.html?sellerId="+result.data[i-1].sellerId);
                            $("#topPollingP"+i).text(result.data[i-1].fruitName)
                        }
                    }
                }else {
                    layer.msg(result.msg);
                }
            },
            error:function(){
                layer.msg("客户端请求有误",{time:1000});
            }
        })
    }
        ,
        showPicTop: function () {
        $.ajax({
            url:"/home/queryTopPanel",
            type:"GET",
            success:function(result){
                if (result.code == 0){
                    for (var i = 1; i <= 3 && i <= result.data.length; i++ ){
                        var picId = "pic_"+i;
                        var aid = "pic_top_a_"+i;
                        $("#"+picId).attr("src",result.data[i-1].panelImgUrl).attr("alt",result.data[i-1].fruitName);
                        $("#"+aid).attr("href","comment.html?id="+ result.data[i-1].fruitId);
                    }
                }else {
                    layer.msg(result.msg,{time:2000});
                }
            },
            error:function(){
                layer.msg("客户端请求有误",{time:1000});
            }
        });
    }
        ,
        gardenRecommendation: function () {
        $.ajax({
            url:"/home/queryRecommend",
            type:"GET",
            success:function(result){
                if (result.code == 0){
                    for (var i = 1; i <= 6 && i <= result.data.length; i++ ){
                        $("#recommendDiv").append("<div class=\"box\">\n" +
                            "                    <a href=\"comment.html?id="+result.data[i-1].fruitId+"\"><img src=\""+result.data[i-1].panelImgUrl+"\"/></a>\n" +
                            "\n" +
                            "                    <p class=\"box_title\">"+result.data[i-1].fruitName+"<a href=\"#\"></a></p>\n" +
                            "\n" +
                            "                    <p class=\"box_price\">\n" +
                            "                        <span>￥</span><span>"+result.data[i-1].eachPrice+"</span>\n" +
                            "                        <span>￥0.00</span>\n" +
                            "                    </p>\n" +
                            "\n" +
                            "                    <p class=\"buy\"><a href=\"comment.html?id="+result.data[i-1].fruitId+"\">购买</a></p>\n" +
                            "                </div>");
                    }
                }else {
                    layer.msg(result.msg,{time:2000});
                }
            },
            error:function(){
                layer.msg("客户端请求有误",{time:2000});
            }
        })
    }
        ,
        newFruitShow: function () {
        $.ajax({
            url:"/home/everyNew",
            type:"GET",
            success:function (result) {
                if (result.code == 0){
                    for (var i = 1; i <= 4 && i <= result.data.length; i++ ) {
                        $("#everyNew").append("<div class=\"box\">\n" +
                            "                <a href=\"/comment.html?id="+result.data[i-1].fruitId+"\">" +
                            "<img src=\""+result.data[i-1].panelImgUrl+"\" alt=\""+result.data[i-1].fruitName+"\"/></a>\n" +
                            "            </div>")
                    }
                }else {
                    g_showLoading()
                    layer.msg(result.msg,{time:2000});
                }
            },
            error:function(){
                layer.msg("客户端请求有误",{time:2000});
            }
        });
    }
        ,
        buttomPolling: function() {
            $.ajax({
                url:"/home/queryBottomPolling",
                type:"GET",
                success:function (result) {
                    if (result.code == 0){
                        for (var i = 1; i <= 3; i++ ){
                            $("#bottomPollingI"+i).attr("src",result.data[i-1].panelImgUrl);
                            $("#bomA"+i).attr("href","comment.html?fruitId="+result.data[i-1].fruitId);
                            $("#bottomPollingP"+i).text(result.data[i-1].fruitName);
                        }
                    }else {
                        layer.msg(result.msg,{time:2000});
                    }
                },
                error:function(){
                    layer.msg("客户端请求有误",{time:1000});
                }
            });
        }
        ,
        //滚动的侧边栏
        roolSlide: function () {
            $(window).scroll(function () {
                var sTop = $(window).scrollTop();
                $('.banner_list').stop().animate({marginTop: sTop - 150});
            })
        },
        //滚动的标题
        roolNews: function () {
            //1.设置定时器表示
            var timer = null;

            //2.滚动的函数定义
            function rollUp() {
                var $news = $('.infoL_con1');
                var $lineHeight = $news.find('li:first').height();
                $news.animate({'marginTop': -$lineHeight + 'px'}, 1000, function () {
                    $news.css({marginTop: 0}).find('li:first').appendTo($news);
                });
            }

            //3设置滚动定时器
            timer = setInterval(rollUp, 1500);
            //设置鼠标划入和鼠标划出
            $('.infoL_con').mouseover(function () {
                clearInterval(timer);
            }).mouseout(function () {
                timer = setInterval(rollUp, 1500);
            });
        },

        //轮播图
        autoPic: function () {
            //获取元素
            var listBottom = $('.infoR_list li');
            var listTop = $('.infoRtop_list li');

            var btns = $('.btns_a a');
            var btnsTop = $('.btnstop_a a');

            var infos = $('.btns_info p');
            var infostop =$('.btnstop_info p');

            var countB = listBottom.length;
            var countT = listTop.length;

            var indexNowBottom = 0;
            var indexNowTop = 0;
            var timer = null;
            //核心函数
            function core(num) {
                btns.eq(num).addClass('on').siblings().removeClass('on');
                infos.eq(num).addClass('on').siblings().removeClass('on');
                listBottom.eq(num).stop().fadeIn(1000).siblings().fadeOut(500);
            }
            function coreTop(num) {
                btnsTop.eq(num).addClass('on').siblings().removeClass('on');
                infostop.eq(num).addClass('on').siblings().removeClass('on');
                listTop.eq(num).stop().fadeIn(1000).siblings().fadeOut(500);
            }
            //点击变换图片
            btns.click(function () {
                indexNowBottom = $(this).index();
                core(indexNowBottom);
            });
            btnsTop.click(function () {
                indexNowTop = $(this).index();
                coreTop(indexNowTop);
            });
            //自动变换图片
            function play() {
                indexNowBottom++;
                indexNowTop++;
                if (indexNowBottom > countB - 1) {
                    indexNowBottom = 0;
                }
                if (indexNowTop > countT - 1) {
                    indexNowTop = 0;
                }
                core(indexNowBottom);
                coreTop(indexNowTop);
            }

            //设置定时器
            timer = setInterval(play, 3000);

            $('.banner').hover(function () {
                clearInterval(timer);
            }, function () {
                timer = setInterval(play, 3000);
            });

            $('.infoR').hover(function () {
                clearInterval(timer);
            }, function () {
                timer = setInterval(play, 3000);
            });
        }
        ,

        //蔬果热卖的json数据加载
        hotSale: function () {

            //获取元素
            var list = $('.box_list');
            var pagelist = $('.conR_pages');
            var self_list = $('#self_list');
            var import_list = $('#import_list');
            var truck_list = $('#truck_list');

            //动态生成列表
            function createBox(data) {
                var html = "";
                var prodata = $.parseJSON(data);
                for (var i = 0; i < prodata.length; i++) {
                    html += '<div class="box"><img src="' + prodata[i]['src'] + '" alt=""/>';
                    html += '<p class="box_title"><a href="#">' + prodata[i]['title'] + '</a></p>';
                    html += '<p class="box_price"><span>￥</span><span>' + prodata[i]['price'] + '</span></p>';
                    html += '<p class="buy"><a href="buyPro.html">购买</a></p></div>';
                }
                list.html(html);
            }

            function createPage(num,ele) {
                var html = "";
                if (num == 1) {
                    html += '<span>上一页</span><span class="on">1</span>';
                    html += '<span class="nextPage">下一页</span>';
                } else if (num == 2) {
                    html += '<span class="prevPage '+ele+'">上一页</span>';
                    html += '<span class="prevPage on '+ele+'">1</span>';
                    html += '<span class="nextPage '+ele+'">2</span>';
                    html += '<span class="nextPage '+ele+'">下一页</span>';
                }
                pagelist.html(html);
            }

            //所有产品列表
            createBox(allProData1);
            createPage(2,"allpro");

            //切换菜单后的数据显示
            function sideMenu(fn, selector) {
                createBox(fn);
                $(selector).stop().show().siblings('.search_menu').hide();
            }

            //下一页的事件
            function goNextPage(data,num,ele){
                createBox(data);
                createPage(num,ele);
            }

            //点击下一页切换
            $('.conR_pages').on('click','span',function(){

                if($(this).hasClass('allpro')){
                    if($(this).hasClass('nextPage')){
                        goNextPage(allProData2,2,"allpro")
                    }else if($(this).hasClass('prevPage')){
                        goNextPage(allProData1,2,"allpro")
                    }
                }else if($(this).hasClass('importF')){
                    if($(this).hasClass('nextPage')){
                        goNextPage(importFruit2,2,"importF")
                    }else if($(this).hasClass('prevPage')){
                        goNextPage(importFruit1,2,"importF")
                    }
                }

            });
            //给幻灯片按钮添加id
            function addSlide(id){
                $('.top_slide').attr('class','top_slide '+id+"_slide");
            }

            //事件委托--左侧菜单的点击事件
            $('.classify').on('click', 'span', function () {
                var id = $(this).attr("id");
                if (id == "self_list") {
                    sideMenu(selfFruit, '#menu_self');
                    createPage(1,"");
                    addSlide(id);
                } else if (id == "import_list") {
                    sideMenu(importFruit1, '#menu_import');
                    createPage(2,"importF");
                    addSlide(id);
                } else if (id == "truck_list") {
                    sideMenu(truck, '#menu_truck');
                    createPage(1,"");
                    addSlide(id);
                }
            });

            //点击取消当前菜单重新加载数据
            $('.search_left').on('click', 'span', function () {
                createBox(allProData1);
                $('.search_left').children('.search_menu').stop().hide();
            })
        },
        //幻灯片开始
        slidePic:function(){
            //关闭按钮
            $('.close').click(function(){
                $(this).parents('.filmslide').hide();
            });

            //判断点击的是哪个页面的幻灯片
            $('.top_slide').click(function(){
                if($(this).attr("class") == "top_slide"){
                    alert("请选择左侧产品展示！")
                }

                if($(this).hasClass("self_list_slide")){
                    $('.filmslide').show();
                    showLlist(selfFruit,9);
                }
                if($(this).hasClass('import_list_slide')){
                    $('.filmslide').show();
                    showLlist(importFruit1,9);
                }
                if($(this).hasClass('truck_list_slide')){
                    $('.filmslide').show();
                    showLlist(truck,6);
                }
            });


           function showLlist(mydata,count){
               var index = 1;
               var count = count;
               function createPro(data,index) {
                   var html1 = "";
                   var html2 = "";
                   var html3 = "";
                   var html4 = "";

                   var prodata = $.parseJSON(data);
                   for (var i = 0; i < prodata.length; i++) {
                       if(prodata[i].id == index){
                           html1 = '<img src="'+prodata[i].src+'" alt=""/>';
                           html2 = '<span>'+prodata[i].title+'</span>';
                           html3 = prodata[i].price;
                       }
                       html4 += '<li><img src="'+prodata[i].lImg_src+'" alt=""/></li>';

                   }
                   $('.l_img').html(html1);
                   $('.film_r_title').html(html2);
                   $('.show_r_price_num').html(html3);
                   $('.list').html(html4);
               }

               function lunbotu(prodata,count){
                   //轮播图的点击事件
                   var number=1;
                   var lis = $(".filmslide .list li");

                   $(".list li:first-child").addClass('on');

                   function core(data,num){
                       createPro(data,num);
                       $(".list li").eq(num-1).addClass("on").siblings().removeClass("on");
                   }
                   $('.list').on('click','li',function () {
                       var index = $(this).index()+1;//获取Li元素内的值，即1，2，3，4(index为0,1,2，3)
                       number = index;//number等于当前的index
                       if (index >= count) return;
                        core(prodata,number);
                   });

                   $('#next').click(function () {
                       lis.removeAttr("class");
                       if (number == count) {
                           number = 1;
                       } else {
                           number++;
                       }
                       core(prodata,number);
                   });
                   $('#prev').click(function () {
                       lis.removeAttr("class");
                       if (number == 1) {
                           number = count;
                       } else {
                           number--;
                       }
                       core(prodata,number);
                   });

               }
               createPro(mydata,index);
               lunbotu(mydata,count);
           }

        },


        //幻灯片结束

        //关于我们的表单验证
        aboutUs: function () {
            //获取元素
            var uname = $('.username');
            var utel = $('.userTel');
            var utime = $(".sel option:selected");
            var ucon = $('.con');

            //提交按钮的表单验证
            $('.form_group button').click(function () {
                //使用正则进行验证

                //姓名2-5位中文
                if (!/[\u4e00-\u9fa5]/g.test(uname.val())) {
                    alert("请填写正确的姓名,2到5位中文！");
                    return false;
                }
                //电话号码6-11位中文

                if (!/^\d{6,11}$/.test(utel.val())) {
                    alert("请填写6-11位手机号码！");
                    return false;
                }

                //判断时间和留言内容是否为空
                if (!utime.val()){
                    alert("请选择时间！");
                    return false;
                }
                if (ucon.html() == ""){
                    alert("请输入留言内容！");
                    return false;
                }
                //判断购买方式是否为空
                var buyType = $("input[type='radio']:checked");
                if (!buyType.val()){
                    alert("请选择购买方式！");
                    return false;
                }

                alert("提交成功！");
                $('.form_group input,.form_group textarea').val("");
            })
        },

        //商品详情页的选项卡切换
        tabChange: function () {
            //获取元素
            var title_list = $('.top_list li');
            var con_list = $('.con_list li');
            var index = 0;
            //核心函数
            function core(num) {
                title_list.eq(num).addClass('title_selected').siblings().removeClass("title_selected");
                con_list.eq(num).addClass('selected').siblings().removeClass("selected");
            }

            //默认显示第一张
            core(index);
            //事件委托
            $('.top_list').on('click', 'li', function () {
                var indexTemp = $(this).index();
                index = indexTemp;
                core(index);
            })
        },
        //返回顶部开始
        returnTop:function(){
            var reTop = $('.returnTop img');
            $(window).scroll(function(){
                var sTop = $(this).scrollTop();
                sTop>$(window).height()?reTop.show():reTop.hide();
            });
            //点击返回顶部
            reTop.click(function(){
                $('body,html').stop().animate({scrollTop:0},2000)
            })
        },
        //购买物品数量的增加和减少
        proAdd:function(){
            var ipt = $('.film_r_count .count_input');
            //增加
            $('.film_r_count #up').click(function(){
                var index = ipt.val();
                index++;
                ipt.val(index);
            });

            $('.film_r_count #down').click(function(){
                var index = ipt.val();
                if(index == 1)return alert('至少请选择一个！');
                index--;
                ipt.val(index);
            })
        }

    };

    main.init();
});
