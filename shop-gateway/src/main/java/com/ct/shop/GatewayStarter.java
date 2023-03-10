package com.ct.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CT
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStarter {
    public static void main(String[] args) {
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(GatewayStarter.class,args);
    }
}
