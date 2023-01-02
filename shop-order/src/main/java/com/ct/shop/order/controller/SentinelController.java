package com.ct.shop.order.controller;

import com.ct.shop.order.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CT
 * @description
 */
@Slf4j
@RestController
public class SentinelController {

    @Resource
    private SentinelService sentinelService;

    @GetMapping("/request_sentinel6")
    public String requestSentinel6(){
        log.info("测试sentinel6");
        return sentinelService.sendMessage2();
    }
}
