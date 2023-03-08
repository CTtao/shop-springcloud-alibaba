package com.ct.shop.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author CT
 * @description
 */
@Component
public class GrayscaleGatewayFilterFactory extends AbstractGatewayFilterFactory<GrayScaleGatewayFilterConfig> {

    public GrayscaleGatewayFilterFactory(){
        super(GrayScaleGatewayFilterConfig.class);
    }

    @Override
    public GatewayFilter apply(GrayScaleGatewayFilterConfig config) {
        return (exchange, chain) -> {
            if (config.isGrayscale()){
                System.out.println("开启了灰度发布的功能...");
            }else {
                System.out.println("关闭了灰度发布的功能...");
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("grayscale");
    }
}
