package com.example.platform.service;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.User;
import com.example.platform.uilt.Result;


public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    Result userLogin(User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Result userInsert(User user);


    /**
     * 用户删除
     * @param user
     * @return
     */
    Result userRemove(User user);

    /**
     * 用户更新
     * @param user
     * @return
     */
    Result userUpdate(User user);


    /**
     * 用户查询全部信息
     * @param page
     * @return
     */
    Result userFindByList(PageInfo page);
}
