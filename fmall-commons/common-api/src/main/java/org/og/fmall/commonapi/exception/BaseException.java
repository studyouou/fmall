package org.og.fmall.commonapi.exception;

/**
 * @author:ougen
 * @date:2019/9/2517:17
 */
public class BaseException extends RuntimeException {
    private int code;
    private String msg;
    public BaseException(){
        super();
    }

    public BaseException(String msg){
        this.msg = msg;
    }
    public BaseException(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public BaseException(int code,String msg,Throwable throwable){
        super(throwable);
        this.code = code;
        this.msg = msg;
    }
    public BaseException(Throwable throwable){
        super(throwable);
    }

    public BaseException(int code,Throwable throwable){
        super(throwable);
        this.code = code;
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
