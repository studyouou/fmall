package org.og.fmall.fmallorder.config;

import org.og.fmall.commonapi.configurations.HandlerBeanProcess;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoInjectHandlerConfig {

    @Bean
    public HandlerBeanProcess handlerBeanProcess(){
        return new HandlerBeanProcess();
    }

}
