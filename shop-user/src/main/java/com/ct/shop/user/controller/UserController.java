package com.ct.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.bean.User;
import com.ct.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description
 */
@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取的用户信息为:{}", JSONObject.toJSONString(user));
        return user;
    }
}
