package org.og.fmall.commonapi.result;

import lombok.Data;

/**
 * @author:ougen
 * @date:2019/9/2419:43
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
