package com.example.platform.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.platform.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
/**
 * 宠物mapper
 */
@Mapper
public interface PetMapper extends BaseMapper<Pet> {
}
