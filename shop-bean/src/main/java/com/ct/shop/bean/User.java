package com.ct.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.ct.shop.utils.id.SnowFlakeFactory;
import com.ct.shop.utils.psswd.PasswordUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Tao
 * @description 用户
 */
@Data
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = -7032479567987350240L;

    @TableId(value = "id",type = IdType.INPUT)
    @TableField(value = "id",fill = FieldFill.INSERT)
    private Long id;

    @TableField("t_username")
    private String username;

    @TableField("t_password")
    private String password;

    @TableField("t_phone")
    private String phone;

    @TableField("t_address")
    private String address;

    public User(){
        this.id = SnowFlakeFactory.getSnowFlake().nextId();
        //默认密码
        this.password = PasswordUtils.getPassword("123456");
    }
}
