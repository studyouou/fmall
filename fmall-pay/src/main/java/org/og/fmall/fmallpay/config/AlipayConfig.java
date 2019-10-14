package org.og.fmall.fmallpay.config;

/**
 * @author:ougen
 * @date:2019/9/921:30
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016101200666286";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSVUybJSTfhFNyR8+hltglT471zoR8vvjxg2uXNAeTEZxFDhXZqtRJhuTICofiubH+WLJZ0OYk7L+TxcISVc7uN5mJ8f68+jWWFqtCpZhUmHuWq9/XK+9oZAJEsZHrqpuTD1enmaSjsyUETUKAL6jn306IFJf3akWJPZK8jll//GwH7IDD+8aOddmkrHsnX+1eV4BN1+CK3OMla9/2nmcBMjuEfClGiJCE9GXV9//yh//ie/SfP8F8kJap7ZVGRxHIDRxEkmjT+TzWj9T/W9kPSvq5oc36C4a5hvJqQIEIRjqxyHSSvGR5O8StzqkGtnwVCT0ZTHXMUMD2JeYzun9LAgMBAAECggEBAINHiTlqomDwSxisegbC2Fjovh2hz9RGU/n/6Ac3g1AarXJ6R3oGuvrXzOn2IKBz7c58bk0Lj10zFFATS5+9Ya7vLIzIELwjUO28mpppC3dhcQbkXSWh4d9qUWqlT374iSfuzLmsqjq+Xgg08+MiXsre2IpWx1sM+SM9nSYSIxMg24u0TFNTUmekjP0nSPGexTULyv6Pm7rhjSTMocU8CD8YmQiILVU+ZaiAURtzIkGfSavqMlNi09GsX0QA3pzZnons8iZ+IZ+6T9DjWKHX/VpmcQy+e3CmFIjA6Ws1hDlBasDnDAesx30hznRFLmpBImuClPUW8Dui/2/kipCtHEkCgYEAyaQULrwZLJhK2gD0zYHATtQWf69gmhxJvylHX3QaigkJrD/Qlml+wf25rWHUMv0ke91yWHtaURZBrjBVr3tB/NlnHqlfr+OTQeuWm996SpA1kSrlWYUh4673U1fhHIEIunGkY54jRT7URft8+Miwx5a0UZWDZ1iQCUX0hcZ7V9UCgYEAucg/RNgFnkxmvjLRi7CNt0fqJ7aJhICET3NUI0nzoZDPVscFzxMZomZWqZ2p+U5lwJ+pTN7/AWlBUJj6KbN0UsIbqzzTbYWoV9jIBhW2Jk4gfLt1ktJandfZ4UelPU2WyU6KcduAehyloUAd3xzFmvDU8AJ5GtdpvmuRXG7eKp8CgYASCsRjiiEWhGnuJ7e/y6RRaVi6BA84ut8+Y4snV1QolbZv2yXmz3mwEevTP7Wk+lGRY7cfg6oJJ1ypIVoJYZX98dXwQGd3bX13oITRXaWrlku0uYncALWPkdA/fNV/n2wQgqqa30N4pq6HtIDyIcLvmOR5iE9BqAL7UW8XqTLQIQKBgQC3D5MQQhAfMEdZCPgjvh+WRr16zknpDz3Qj+KLNzZisC8t4cRejAKJXG/uiFXRO6fVyz1maqE5gb6DLYnrB1IKrgvXioMG+1mq3ggSvtgSHI+0eH7c4nu55tRhQFiY9FgtGkAe+tw49vtZzbThgRdRmhcoPDea9EHVCWddfj1aHwKBgFEUpCkCKZPMMcWfEUMVQ1IedLUMGSo4sbPc0QQOZhq3760OBgJni8SgUP0QrOi2aO605ih25dwUE29AM3ztLOgz4Cvpfp8ePu7fSF1pgPTWQeriIJl8KV3eW5jWkeevtE9UzFbCjmQfby3gdJPzQW2E4CKy/qFBZnqNtAei+bIY";
      // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjGFZE2s6WFDROv09shuSkgLLmYfsc/5XLbKShWQiWWC6n3D2HI7xnVw7JpFMSK01FV5zvFTOnwXQWG/s2U1p7zqM1K9PF1u/OrtWzs49Bfo/xA8a09gCKG2rVw5RqpkwgQ/8as4QYUsL+4e844o93Vt4JwTne5TRan+n90h93pEn8UC0w7CshPxp29YPA25NJEezD2aiLBUV5/bGiIM+UwMOBWPTvhDzmzODKy15J/epAMzSRAHNfSz+QHgkHNvjCuFRGf792L/TdXc0YlEryREs2UNfRpZIfHK3etmlg6ceWOPoZgb37XOLqUlBJSHExkMW7yBy10Ni5pvKBucaAQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";

}
