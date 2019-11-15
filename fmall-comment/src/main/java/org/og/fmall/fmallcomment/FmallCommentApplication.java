package org.og.fmall.fmallcomment;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: og
 * @description:
 * @date: 2019/11/7
 */
@SpringBootApplication
@ComponentScan("org.og.fmall")
@EnableDubbo(scanBasePackages = "org.og.fmall")
public class FmallCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallCommentApplication.class,args);
    }
}
