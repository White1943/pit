package com.example.platform.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.platform.entity.Adoption;
import org.apache.ibatis.annotations.Mapper;
/**
 * 领养mapper
 */
@Mapper
public interface AdoptionMapper extends BaseMapper<Adoption> {
}
