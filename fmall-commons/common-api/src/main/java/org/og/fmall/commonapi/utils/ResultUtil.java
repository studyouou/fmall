package org.og.fmall.commonapi.utils;

import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;

/**
 * @author:ougen
 * @date:2019/9/2419:45
 */
public class ResultUtil<T>{
    public static Result build(){
        Result result = new Result();
        result.setCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> build(T t){
        Result<T> result = build();
        result.setData(t);
        return result;
    }

    public static Result buildError(){
        Result result = new Result();
        result.setCode(CommonEnum.FAIL.getCode());
        result.setMsg(CommonEnum.FAIL.getMsg());
        return result;
    }
}
