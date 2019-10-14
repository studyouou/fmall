package org.og.fmall.fmallstock;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.og.fmall.fmallstock.mapper")
@EnableDubbo
@ComponentScan("org.og.fmall")
public class FmallStockApplication{

	public static void main(String[] args) {
		SpringApplication.run(FmallStockApplication.class, args);
	}

}
