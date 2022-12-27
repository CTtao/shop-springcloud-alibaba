package com.ct.shop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tao
 * @description
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.ct.shop.order.mapper")
@ComponentScan("com.ct.shop")
public class OrderStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderStarter.class,args);
    }
}
