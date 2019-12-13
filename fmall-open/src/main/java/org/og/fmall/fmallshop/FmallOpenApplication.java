package org.og.fmall.fmallshop;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author:ougen
 * @date:2019/9/2620:13
 */
@SpringBootApplication
@EnableDubbo
@ComponentScan("org.og.fmall")
public class FmallOpenApplication {
    @Value("${soft.needTest}")
    private boolean needTest;

    public static void main(String[] args) {
        SpringApplication.run(FmallOpenApplication.class,args);
    }

}
