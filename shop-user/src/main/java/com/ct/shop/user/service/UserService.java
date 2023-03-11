package com.ct.shop.user.service;

import com.ct.shop.bean.User;

/**
 * @author Tao
 * @description 用户业务接口
 */
public interface UserService {
    User getUserById(Long userId);

    void asyncMethod();
}
