package org.og.fmall.commonapi.utils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:ougen
 * @date:2019/9/2615:13
 */
public class OrderUtil {
    private static Random random = new Random();
    private static AtomicInteger stream = new AtomicInteger();
    public static String ceateOrderId(Long memberId){
        StringBuilder stringBuilder = new StringBuilder();
        String yyyyMMdd = TimeUtil.formatTime(LocalDateTime.now(), "yyyyMMdd");
        String yyMMdd = yyyyMMdd.substring(2);
        String suid = String.valueOf(memberId);
        String suid4Len = suid.substring(suid.length() > 4 ? suid.length() - 4 : 0);
        stringBuilder.append(suid4Len).append(yyMMdd);
        stringBuilder.append(stream.getAndAdd(1));
        for (int i = 0;i<4;i++){
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }
}
