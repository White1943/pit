package com.example.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * 用户表
 */
@Data
@ToString
@TableName("user")
public class User {

    @TableId
    private String id;

    //账号
    @TableField(value = "username")
    private String username;

    //密码
    @TableField(value = "password")
    private String password;

    //邮箱
    @TableField(value = "email")
    private String email;

    //手机号码
    @TableField(value = "phone_number")
    private String phoneNumber;

    //其他个人信息
    @TableField(value = "other_info")
    private String otherInfo;



}
