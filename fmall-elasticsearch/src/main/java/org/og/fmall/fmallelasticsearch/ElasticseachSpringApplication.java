package org.og.fmall.fmallelasticsearch;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: lyp
 * @description:
 * @date: 2019/11/4
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.og.fmall.fmallelasticsearch.services")
@ComponentScan("org.og.fmall")
public class ElasticseachSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticseachSpringApplication.class,args);
    }
}
