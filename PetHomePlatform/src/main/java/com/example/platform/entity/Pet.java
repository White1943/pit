package com.example.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * 宠物表
 */
@Data
@ToString
@TableName("pet")
public class Pet {


    @TableId
    private String id;

    //宠物名称
    @TableField(value = "pet_name")
    private String petName;

    //品种
    @TableField(value = "breed")
    private String breed;

    //年龄
    @TableField(value = "age")
    private Integer age;

    //性别
    @TableField(value = "sex")
    private String sex;

    //类型(橘猫、拉布拉多)
    @TableField(value = "type")
    private String type;

    //颜色
    @TableField(value = "color")
    private String color;

    //健康状况
    @TableField(value = "health_status")
    private String healthStatus;

    //图片
    @TableField(value = "img")
    private String img;

    //地址
    @TableField(value = "address")
    private String address;

    //电话号码
    @TableField(value = "phone")
    private String phone;

    //是否已遛(0 没有 1有)
    @TableField(value = "is_adopted")
    private Integer isAdopted;



    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "status")
    private String status;

    //图片
    @TableField(exist = false)
    private String imgSrc;

}
