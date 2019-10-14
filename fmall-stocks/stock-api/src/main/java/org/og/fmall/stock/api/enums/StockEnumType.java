package org.og.fmall.stock.api.enums;

/**
 * @author:ougen
 * @date:2019/9/2815:22
 */
public enum  StockEnumType {
    FRUIT_NOT_FOUND(-3000,"水果信息未找到，请确定相关信息"),
    FRUIT_STOCK_NOT_ENOUGH(-3001,"水果库存不足，减少失败")
    ;

    private int code;

    private String msg;

    StockEnumType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
