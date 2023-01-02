package com.ct.shop.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CT
 * @description
 */
@Slf4j
public class MyFallback {
    public static String fallback(Throwable e){
        log.error("由外部类MyFallback处理异常：{}",e);
        return "异常了";
    }
}
