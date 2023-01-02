package com.ct.shop.order.feign;

import com.ct.shop.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("server-user")
public interface UserService {
    @GetMapping("/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
