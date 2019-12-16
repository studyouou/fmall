//package org.og.fmall.fmallshop.logs;
//
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.og.fmall.commonapi.enums.CommonEnum;
//import org.og.fmall.commonapi.exception.BaseException;
//import org.og.fmall.commonapi.result.Result;
//import org.og.fmall.commonapi.utils.ResultUtil;
//import org.og.fmall.fmalllogs.productor.Productor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class ErrorLogAop {
//
//    @Autowired
//    private Productor productor;
//
//    @Pointcut("execution(* org.og.fmall.fmallshop.exceptionhandler.GlobalExceptionHandler.handler(..))))")
//    public void error(){
//
//    }
//
//    @Before("error()")
//    public void send(JoinPoint joinPoint){
//        Exception e = (Exception) joinPoint.getArgs()[0];
//        if (!(e instanceof BaseException)){
//            System.out.println(e.getMessage());
//            productor.sendMessage(e.toString()+"这是我的测试");
//        }
//    }
//}
