package com.ct.shop.order.feign;

import com.ct.shop.bean.User;
import com.ct.shop.order.feign.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "server-user",fallback = UserServiceFallback.class)
public interface UserService {
    @GetMapping("/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
