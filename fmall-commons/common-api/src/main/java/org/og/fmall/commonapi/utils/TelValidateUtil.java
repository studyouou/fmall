package org.og.fmall.commonapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:ougen
 * @date:2019/10/316:03
 */
public class TelValidateUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static void isMobile(Long tel) {
        if(tel == null) {
            throw new  BaseException(CommonEnum.PARAMERTER_WRONG.getCode(),"手机号不能为空");
        }
        Matcher m = mobile_pattern.matcher(String.valueOf(tel));
        if (!m.matches()){
            throw new BaseException(CommonEnum.TEL_WRONT_FORMAT.getCode(),CommonEnum.TEL_WRONT_FORMAT.getMsg());
        }
    }
}
