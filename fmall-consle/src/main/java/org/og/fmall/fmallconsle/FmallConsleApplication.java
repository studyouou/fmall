package org.og.fmall.fmallconsle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: lyp
 * @description:
 * @date: 2019/11/1
 */
@SpringBootApplication
@ComponentScan("org.og.fmall")
public class FmallConsleApplication {
    public static void main(String[] args) {
        SpringApplication.run(FmallConsleApplication.class,args);
    }
}
