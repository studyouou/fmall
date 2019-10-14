package org.og.fmall.fmallpay;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author:ougen
 * @date:2019/10/1220:11
 */
@SpringBootApplication
@EnableDubbo
@ComponentScan("org.og.fmall")
public class FmallPaySpringAppliction {
    public static void main(String[] args) {
        SpringApplication.run(FmallPaySpringAppliction.class,args);
    }
}
