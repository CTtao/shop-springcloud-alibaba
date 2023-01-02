package com.ct.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.bean.Order;
import com.ct.shop.bean.OrderItem;
import com.ct.shop.bean.Product;
import com.ct.shop.bean.User;
import com.ct.shop.order.feign.ProductService;
import com.ct.shop.order.feign.UserService;
import com.ct.shop.order.mapper.OrderItemMapper;
import com.ct.shop.order.mapper.OrderMapper;
import com.ct.shop.order.service.OrderService;
import com.ct.shop.params.OrderParams;
import com.ct.shop.utils.constants.HttpCode;
import com.ct.shop.utils.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

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
    private UserService userService;
    @Resource
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常:" + JSONObject.toJSONString(orderParams));
        }

        User user = userService.getUser(orderParams.getUserId());
        if (user == null){
            throw new RuntimeException("未获取到用户信息:" + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.getProduct(orderParams.getProductId());
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

        ResponseResult<Integer> result = productService.updateCount(orderParams.getProductId(),orderParams.getCount());
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }

}
