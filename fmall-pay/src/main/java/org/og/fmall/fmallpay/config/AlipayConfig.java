package org.og.fmall.fmallpay.config;

/**
 * @author:ougen
 * @date:2019/9/921:30
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "支付宝商户id";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "私钥";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "支付宝公钥";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";

}
