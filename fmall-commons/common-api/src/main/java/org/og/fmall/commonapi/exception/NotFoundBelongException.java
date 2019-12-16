package org.og.fmall.commonapi.exception;

/**
 * @author:ougen
 * @date:2019/9/3011:28
 */
public class NotFoundBelongException extends RuntimeException {
    public NotFoundBelongException(String msg){
        super(msg);
    }
}
