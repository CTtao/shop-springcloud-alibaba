package com.ct.shop.user.service.impl;

import com.ct.shop.bean.User;
import com.ct.shop.user.mapper.UserMapper;
import com.ct.shop.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}
