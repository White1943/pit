package com.example.platform.service;

import com.example.platform.config.PageInfo;
import com.example.platform.config.SearchInfo;
import com.example.platform.entity.Pet;
import com.example.platform.uilt.Result;


public interface PetService {

    /**
     * 宠物列表展示(分页和搜索信息)
     * @param page
     * @return
     */
    Result petListByUser(PageInfo page);

    /**
     * 宠物删除
     * @param pet
     * @return
     */
    Result petRemove(Pet pet);

    /**
     * 宠物状态修改
     * @param pet
     * @return
     */
    Result petStatusUpdate(Pet pet);


    /**
     * 宠物信息添加
     * @param pet
     * @return
     */
    Result petInsert(Pet pet);


    /**
     * 宠物信息修改
     * @param pet
     * @return
     */
    Result petUpdate(Pet pet);

    /**
     * 宠物图像查看
     * @param pet
     * @return
     */
    Result petImgSelect(Pet pet);

    Result petUserAll(SearchInfo searchInfo);

    Result petFindById(Pet pet);
}
