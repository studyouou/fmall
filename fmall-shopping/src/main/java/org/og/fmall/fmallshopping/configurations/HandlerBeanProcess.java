package org.og.fmall.fmallshopping.configurations;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.exception.NotFoundBelongException;
import org.og.fmall.fmallshopping.annotations.Belong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author:ougen
 * @date:2019/9/3010:44
 */
@Component
public class HandlerBeanProcess implements BeanPostProcessor,ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(HandlerBeanProcess.class);

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractPipeLineFactory){
            AbstractPipeLineFactory factory = (AbstractPipeLineFactory) bean;
            Map<String, InvokeHandler> handlerMap = applicationContext.getBeansOfType(InvokeHandler.class);
            List<InvokeHandler> handlerList = new ArrayList<>();
            for (Map.Entry<String,InvokeHandler> entry : handlerMap.entrySet()){
                InvokeHandler value = entry.getValue();
                Class<? extends InvokeHandler> aClass = value.getClass();
                Belong belong = aClass.getAnnotation(Belong.class);
                if (belong == null){
                    throw new NotFoundBelongException(aClass.getName() + "not found Annotation of Belong," +
                            "it is must on InvokeHander");
                }
                if (aClass.getAnnotation(Order.class) != null) {
                    if (factory.getClass() == belong.value()){
                        handlerList.add(value);
                    }
                    continue;
                }
                factory.addLast(value);
            }
            handlerList.sort(new Comparator<InvokeHandler>() {
                @Override
                public int compare(InvokeHandler o1, InvokeHandler o2) {
                    return o1.getClass().getAnnotation(Order.class).value()-o2.getClass().getAnnotation(Order.class).value();
                }
            });
            for (int i = handlerList.size()-1; i>=0; i--){
                factory.addFirst(handlerList.get(i));
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
