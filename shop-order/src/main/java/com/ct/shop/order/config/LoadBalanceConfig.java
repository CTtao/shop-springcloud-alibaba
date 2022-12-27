package com.ct.shop.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tao
 * @description
 */
@Configuration
public class LoadBalanceConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
