package com.example.platform.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.User;
import com.example.platform.mapper.UserMapper;
import com.example.platform.uilt.PasswordMD5Utils;
import com.example.platform.uilt.Result;
import com.example.platform.uilt.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private  UserMapper userMapper;

    @Override
    public Result userLogin(User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Result result = new Result<>();
        queryWrapper.eq("username",user.getUsername());
        if (StringUtils.isNotEmpty(user.getPassword())){
            if (user.getPassword().length()<30){
                user.setPassword(PasswordMD5Utils.toMD5(user.getPassword()));
            }
        }
        queryWrapper.eq("password", user.getPassword());
        User findUser = userMapper.selectOne(queryWrapper);
        if (findUser==null){
            result.setCode("500");
            result.setMsg("该用户未注册");
        }else{
            result.setCode("200");
            result.setMsg("登录成功");
            result.setData(findUser);
        }
        return result;
    }

    @Override
    public Result userInsert(User user) {
        User insertUser = user;
        Result result = new Result<>();
        insertUser.setId(Util.uuid());
        insertUser.setPassword(PasswordMD5Utils.toMD5(insertUser.getPassword()));
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",insertUser.getUsername());
        if (userMapper.selectOne(queryWrapper)!=null){
           if (user.getId()!=userMapper.selectOne(queryWrapper).getId()){
               result.setCode("500");
               result.setMsg("用户名已存在");
               return result;
           }
        }
        if (userMapper.insert(insertUser)>0) {
            result.setCode("200");
            result.setMsg("注册成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("注册失败");
        return result;
    }

    @Override
    public Result userRemove(User user) {
        User removeUser = user;
        Result result = new Result<>();
        if (userMapper.deleteById(removeUser)>0) {
            result.setCode("200");
            result.setMsg("删除成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("删除失败");
        return result;
    }

    @Override
    public Result userUpdate(User user) {
        User updateUser = user;
        if (StringUtils.isNotEmpty(updateUser.getPassword())){
            if (updateUser.getPassword().length()<30){
                updateUser.setPassword(PasswordMD5Utils.toMD5(updateUser.getPassword()));
            }
        }
        Result result = new Result<>();

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",updateUser.getUsername());
        if (userMapper.selectOne(queryWrapper)!=null){
           if (!updateUser.getPassword().equals(userMapper.selectOne(queryWrapper).getPassword())){
               result.setCode("500");
               result.setMsg("用户名已存在");
               return result;
           }
        }
        if (userMapper.updateById(updateUser)>0) {
            result.setCode("200");
            if (user.getPassword()==("abc123")){
                result.setMsg("重置密码成功");
            }else {
                result.setMsg("修改成功");
            }
            return result;
        }
        result.setCode("500");
        if (user.getPassword()==("abc123")){
            result.setMsg("重置密码失败");
        }else {
            result.setMsg("修改失败");
        }
        return result;
    }

    @Override
    public Result userFindByList(PageInfo page) {
        PageInfo findPage = page;
        Result result = new Result<>();
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(findPage.getPageNum()+"")||findPage.getPageNum()<=0){
            findPage.setPageNum(1);
        }
        if (StringUtils.isEmpty(findPage.getPageSize()+"")||findPage.getPageSize()<=0){
            findPage.setPageSize(10);
        }
        if (StringUtils.isNotEmpty(findPage.getMsg())){
           queryWrapper.like("username",findPage.getMsg());
        }
        Page selectByPage = new Page(findPage.getPageNum(),findPage.getPageSize());
        Page resultPage = userMapper.selectPage(selectByPage, queryWrapper);
        result.setCode("200");
        result.setData(resultPage);
        return result;

    }
}
