package org.og.fmall.fmallshop.exceptionhandler;

import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * @author:ougen
 * @date:2019/10/30:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result handler(Exception e){
        Result result = ResultUtil.build();
        if (e instanceof BaseException){
            BaseException baseException = (BaseException) e;
            result.setData(baseException.getCode());
            result.setMsg(baseException.getMsg());
            LOGGER.warn(e.getMessage());
            return result;
        }
        result.setCode(CommonEnum.UNKOW_ERROR.getCode());
        result.setMsg(CommonEnum.UNKOW_ERROR.getMsg());
        LOGGER.error(e.getMessage(),e);
        return result;
    }
}
