package com.ct.shop.order.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CT
 * @description
 */
@Slf4j
public class MyBlockHandler {

    public static String blockHandler(BlockException e){
        log.error("由外部类MyBlockHandler限流：{}",e);
        return "限流了";
    }
}
