package org.og.fmall.fmallorder;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.iservice.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("org.og.fmall")
@MapperScan("org.og.fmall.fmallorder.mapper")
public class FmallOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmallOrderApplication.class, args);
	}

}
