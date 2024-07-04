package com.example.platform.controller;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.User;
import com.example.platform.service.UserService;
import com.example.platform.uilt.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userCode")
    public Result getCode() throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);
        String code = captcha.getCode();
        return Result.success(code);
    }

    @PostMapping("userLogin")
    public Result systemUserLogin(@RequestBody User user){
        return userService.userLogin(user);
    }

    @PostMapping("userInsert")
    public Result systemUserInsert(@RequestBody User user){
        return userService.userInsert(user);
    }

    @DeleteMapping("userRemove")
    public Result systemUserRemove(@RequestBody User user){
        return userService.userRemove(user);
    }

    @PostMapping("userUpdate")
    public Result systemUserUpdate(@RequestBody User user){
        return userService.userUpdate(user);
    }

    @PostMapping("userFindList")
    public Result systemUserList(@RequestBody PageInfo page){
        return userService.userFindByList(page);
    }
}
