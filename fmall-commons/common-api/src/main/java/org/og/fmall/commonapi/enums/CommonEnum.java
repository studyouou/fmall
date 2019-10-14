package org.og.fmall.commonapi.enums;

/**
 * @author:ougen
 * @date:2019/9/2419:45
 */
public enum  CommonEnum {
    SUCCESS(0,"成功"),
    FAIL(-1,"失败"),
    UNKOW_ERROR(-2,"服务器错误"),
    PARAMERTER_WRONG(-3,"参数错误"),
    NUM_OVER(-1000,"下单失败，商品卖完，请等待后续上架"),
    NUM_NOT_ENGHTH(-1001,"订购商品库存不够，请联系卖家"),
    ORDER_NOT_FOUND(-1002,"订单不存在，请刷新"),
    CANNOT_CANCEL_OF_PAY(-1003,"支付订单不能取消，请联系商家"),
    ORDER_HAS_PAY(-1004,"订单已经支付"),
    ORDER_PAY_CANT_CANCEL(-1005,"订单已经支付，无法取消"),
    ORDER_HAS_CANCEL(-1006,"订单已经取消，请刷新重新操作"),
    ORDER_DELETE_UNKNOW(-1007,"删除订单未知错误，请联系商家"),
    ROCKETMQ_SEND_FAIL(-1104,"服务器忙，创建订单失败，请稍后重试(消息发送失败)"),
    ROCKETMQ_CONSUMER_FAIL(-1105,"服务器忙，创建订单失败，请稍后重试(消息消费失败)"),
    MEMBER_NOT_ORDER(-1106,"您还为有订单，可以下单创建哟"),
    ADDRESS_NOT_FOUND(-1201,"该收获地址不存在，请刷新重试"),
    NOT_DEFAUL_ADDRESS(-1202,"您还没有添加默认地址"),
    MEMBER_TEL_WRONG(1301,"你输入的账号不对"),
    MEMBER_NOT_FOUND(1302,"您查找的用户不存在"),
    REQUEST_CHECK_FAILURE(-1303, "请求数据校验失败"),
    MEMBER_HAS_EXIST(1304,"您已注册，不能重复注册"),
    PASSWORD_IS_WRONG(1305,"您的密码错误"),
    TEL_WRONT_FORMAT(-1306,"手机格式错误"),
    PASSWORD_IS_DIFFIRENT(1401,"您输出的密码不一致，请重新输入"),
    PASSWORD_IS_EQUALS(1402,"不能和原始密码一样"),
    MEMBER_NOT_LOGIN(-1501,"用户未登录"),
    FRUIT_NOT_FOUND(-1601,"未查到到该商品"),
    ENCODE_FAIL(-1701,"加密失败"),
    DECODE_FAIL(-1702,"解密失败"),
    RETURN_FUND_WRONG(-1801,"退款失败"),
    PAY_WRONG(-1802,"支付失败")
    ;
    CommonEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
