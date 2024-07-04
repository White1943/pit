package com.example.platform.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.User;
import com.example.platform.service.UserService;
import com.example.platform.uilt.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j
@RestController
@RequestMapping("/user/")
@Api(tags = "用户管理接口", description = "提供用户管理相关的 API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userCode")
    @ApiOperation("获取验证码")
    public Result getCode() throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);
        String code = captcha.getCode();
        return Result.success(code);
    }

    @PostMapping("userLogin")
    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    public Result systemUserLogin(@RequestBody User user){
        return userService.userLogin(user);
    }

    @PostMapping("userInsert")
    @ApiOperation("插入用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    public Result systemUserInsert(@RequestBody User user){
        return userService.userInsert(user);
    }

    @DeleteMapping("userRemove")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    public Result systemUserRemove(@RequestBody User user){
        return userService.userRemove(user);
    }

    @PostMapping("userUpdate")
    @ApiOperation("更新用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    public Result systemUserUpdate(@RequestBody User user){
        return userService.userUpdate(user);
    }

    @PostMapping("userFindList")
    @ApiOperation("分页查询用户列表")
    @ApiImplicitParam(name = "page", value = "分页信息,可进行msg=username筛选", required = true, dataType = "PageInfo")
    public Result systemUserList(@RequestBody PageInfo page){
        return userService.userFindByList(page);
    }
}
