package com.ct.shop.user.service.impl;

import com.ct.shop.bean.User;
import com.ct.shop.user.mapper.UserMapper;
import com.ct.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Async
    @Override
    public void asyncMethod() {
        log.info("执行了异步任务...");
    }
}
