package com.ct.shop.order.feign.fallback;

import com.ct.shop.bean.User;
import com.ct.shop.order.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * @author CT
 * @description
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
