package com.ct.shop.utils.psswd;

import com.ct.shop.utils.md5.MD5Hash;

/**
 * @author Tao
 * @description 密码加密
 */
public class PasswordUtils {

    public static String getPassword(String password){
        if (password == null || password.trim().isEmpty()) return password;
        for (int i = 0; i < 5; i++) {
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}
