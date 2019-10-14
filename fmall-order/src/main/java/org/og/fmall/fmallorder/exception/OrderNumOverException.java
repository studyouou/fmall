package org.og.fmall.fmallorder.exception;

import org.og.fmall.commonapi.exception.BaseException;

/**
 * @author:ougen
 * @date:2019/9/2517:34
 */
public class OrderNumOverException extends BaseException {

    public OrderNumOverException() {
    }

    public OrderNumOverException(int code, String msg) {
        super(code, msg);
    }

    public OrderNumOverException(int code, String msg, Throwable throwable) {
        super(code, msg, throwable);
    }

    public OrderNumOverException(Throwable throwable) {
        super(throwable);
    }

    public OrderNumOverException(int code, Throwable throwable) {
        super(code, throwable);
    }
}
