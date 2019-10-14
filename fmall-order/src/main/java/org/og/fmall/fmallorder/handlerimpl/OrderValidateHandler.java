//package org.og.fmall.fmallorder.handlerimpl;
//
//import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
//import org.og.fmall.commonapi.dto.RequestParam;
//import org.og.fmall.commonapi.dto.ResponseContext;
//import org.og.fmall.commonapi.exception.BaseException;
//import org.og.fmall.order.api.dto.OrderDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.validation.constraints.NotNull;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
///**
// * @author:ougen
// * @date:2019/9/2916:54
// */
//@Component
//public class OrderValidateHandler implements InvokeHandler {
//    private static Logger logger = LoggerFactory.getLogger(OrderValidateHandler.class);
//    @Override
//    public void handle(RequestParam requestParam, ResponseContext context) {
//        OrderDto orderDto = (OrderDto) requestParam;
//        Class clazz = orderDto.getClass();
//        Field[] declaredFields = clazz.getDeclaredFields();
//        for (Field field : declaredFields){
//            NotNull annotation = field.getAnnotation(NotNull.class);
//            if (annotation == null){
//                continue;
//            }
//            String name = field.getName();
//            char[] chars = name.toCharArray();
//            chars[0] = (char) (chars[0]+'A'-1);
//            try {
//                Method method = clazz.getMethod("get"+new String(chars));
//                logger.info("method = {}接受类型检查",method.getName());
//                Object invoke = method.invoke(orderDto);
//                if (invoke == null){
//                    throw new BaseException(-1003,name+"不能为空");
//                }
//            } catch (Exception e) {
//                throw new BaseException(e);
//            }finally {
//                chars = null;
//            }
//        }
//    }
//}
