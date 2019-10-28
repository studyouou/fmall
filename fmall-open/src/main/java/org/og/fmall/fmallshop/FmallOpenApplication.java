package org.og.fmall.fmallshop;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author:ougen
 * @date:2019/9/2620:13
 */
@SpringBootApplication(exclude = DubboAutoConfiguration.class)
@EnableDubbo
@ComponentScan("org.og.fmall")
public class FmallOpenApplication {
    public static void main(String[] args) {
        SpringApplication.run(FmallOpenApplication.class,args);
    }
}
