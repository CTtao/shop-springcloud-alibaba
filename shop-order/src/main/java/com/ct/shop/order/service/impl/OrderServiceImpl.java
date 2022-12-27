package com.ct.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.bean.Order;
import com.ct.shop.bean.OrderItem;
import com.ct.shop.bean.Product;
import com.ct.shop.bean.User;
import com.ct.shop.order.mapper.OrderItemMapper;
import com.ct.shop.order.mapper.OrderMapper;
import com.ct.shop.order.service.OrderService;
import com.ct.shop.params.OrderParams;
import com.ct.shop.utils.constants.HttpCode;
import com.ct.shop.utils.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Tao
 * @description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (OrderParams.isEmpty()){
            throw new RuntimeException("参数异常:" + JSONObject.toJSONString(orderParams));
        }

        User user = restTemplate.getForObject("http://localhost:8060/user/get/" + orderParams.getUserId(), User.class);
        if (user == null){
            throw new RuntimeException("未获取到用户信息:" + JSONObject.toJSONString(orderParams));
        }
        Product product = restTemplate.getForObject("http://localhost:8070/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());

        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        ResponseResult<Integer> result = restTemplate.getForObject("http://localhost:8070/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), ResponseResult.class);
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }
}
