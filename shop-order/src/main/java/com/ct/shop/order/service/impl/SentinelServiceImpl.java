package com.ct.shop.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ct.shop.order.handler.MyBlockHandler;
import com.ct.shop.order.handler.MyFallback;
import com.ct.shop.order.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CT
 * @description
 */
@Service
@Slf4j
public class SentinelServiceImpl implements SentinelService {

    private int count = 0;

    @Override
    @SentinelResource(
            value = "sendMessage2",
            blockHandlerClass = MyBlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = MyFallback.class,
            fallback = "fallback"
    )
    public String sendMessage2() {
        count++;
        if (count % 4 == 0){
            throw new RuntimeException("25%异常率");
        }
        return "sendMessage2";
    }

    public String blockHandler(BlockException e){
        log.error("限流了：{}",e);
        return "限流了";
    }

    public String fallback(Throwable e){
        log.error("异常了：{}",e);
        return "异常了";
    }
}
