package org.og.fmall.fmallshopping;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author:ougen
 * @date:2019/10/421:22
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@Component("org.og.fmall")
@MapperScan("org.og.fmall.fmallshopping.mapper")
public class FmallShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallShoppingApplication.class,args);
    }

}
