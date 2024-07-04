package com.example.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * 留言
 */
@Data
@ToString
@TableName("feedback")
public class Feedback {

    @TableId
    private String id;

    //用户id
    @TableField(value = "user_id")
    private String userId;

    //宠物id
    @TableField(value = "pet_id")
    private String petId;

    //反馈时间
    @TableField(value = "time")
    private String time;

    //反馈内容
    @TableField(value = "content")
    private String content;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "health")
    private String health;

    //反馈内容
    @TableField(exist = false)
    private String userName;


    //反馈内容
    @TableField(exist = false)
    private String petName;
}
