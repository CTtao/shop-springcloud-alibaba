package com.ct.shop.user.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author CT
 * @description
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "user-group",topic = "order-topic")
public class RocketConsumeListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("用户收到了订单信息：{}" , JSONObject.toJSONString(order));
    }
}
