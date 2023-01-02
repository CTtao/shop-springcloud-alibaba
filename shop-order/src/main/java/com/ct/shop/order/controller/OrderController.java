package com.ct.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.order.service.OrderService;
import com.ct.shop.params.OrderParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description
 */
@Slf4j
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/submit_order")
    public String submitOrder(OrderParams orderParams){
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }

    @GetMapping("/test_sentinel")
    public String testSentinel(){
        log.info("测试sentinel");
        return "sentinel";
    }
}
