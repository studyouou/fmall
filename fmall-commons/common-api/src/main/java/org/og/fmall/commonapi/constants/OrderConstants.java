package org.og.fmall.commonapi.constants;

/**
 * @author:ougen
 * @date:2019/9/2515:58
 */
public class OrderConstants {
    //redis水果key
    public static final String FRUIT_NUM = "fruit:num:leave:";

    //用户登录时redis储存token键
    public static final String LOGIN_KEY = "fmall_token:";

    //下单成功订单缓存
    public static final String ORDER_SUBMIT_KEY = "order:submit:";

    //浏览器用户cookie储存uuid键
    public static final String COOKIE_NAME_LOIN = "fmall_token";

    //redis主页顶部轮询图
    public static final String TOP_POLLING_KEY = "top:polling:key";

    //redis主页底部轮播图
    public static final String BOTTOM_POLLING_KEY = "bottom:polling:key";

    //redis主页果园推荐缓存key
    public static final String SHOP_RECOMMEND_KEY = "shop:recommend:key";

    //redis主页顶部图框
    public static final String TOP_PANEL_KEY = "top:panel:key";

    //redis主页底部图框
    public static final String BOTTOM_PANEL_KEY = "bottom:panel:key";

    //HTML参数加密密钥
    public static final String ENCODE = "@#html:param#@";

    public static final String AES = "AES";

    public static final String ORDER_QUEUE_NAME = "order_queue";

    public static final String ORDER_DIRECT_EXCHANGE_NAME = "order_direct_exchange";

    public static final String ROUTE_KEY= "order_create";
}
