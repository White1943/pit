package com.example.platform.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.platform.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
