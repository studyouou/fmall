package org.og.fmall.fmallpay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:ougen
 * @date:2019/10/141:15
 */

@Configuration
public class AlipayClientConfiguration {

    @Bean
    public AlipayClient alipayClient(){
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT,AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);

        return alipayClient;
    }
}
