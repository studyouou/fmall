package org.og.fmall.fmalluser;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@ComponentScan({"org.og.fmall"})
@MapperScan("org.og.fmall.fmalluser.mapper")
public class FmallUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmallUserApplication.class, args);
	}

}
