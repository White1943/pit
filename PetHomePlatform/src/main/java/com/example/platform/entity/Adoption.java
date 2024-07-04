package com.example.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * 遛宠申请表
 *
 *
 */
@Data
@ToString
@TableName("adoption")
public class Adoption {

    @TableId
    private String id;

    //用户id
    @TableField(value = "user_id")
    private String userId;

    //宠物id
    @TableField(value = "pet_id")
    private String petId;

    //申请时间
    @TableField(value = "time")
    private String time;

    //审核状态 0未审核 1已通过 2等待确认领养  3是未通过
    @TableField(value = "review_status")
    private String reviewStatus;

    @TableField(exist = false)
    private String petName;


    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String img;
}
