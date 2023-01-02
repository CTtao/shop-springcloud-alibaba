package com.ct.shop.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tao
 * @description 商品服务启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ct.shop.product.mapper")
@EnableDiscoveryClient
public class ProductStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProductStarter.class,args);
    }
}
