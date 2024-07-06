package com.example.platform.service;
import com.example.platform.config.PageInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.Adoption;
import com.example.platform.entity.Pet;
import com.example.platform.entity.User;
import com.example.platform.mapper.AdoptionMapper;
import com.example.platform.mapper.PetMapper;
import com.example.platform.mapper.UserMapper;
import com.example.platform.uilt.Base64Util;
import com.example.platform.uilt.Result;
import com.example.platform.uilt.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AdoptionServiceImpl implements AdoptionService{

    @Autowired
    private AdoptionMapper adoptionMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${dir}")
    private  String imgPath;




    @Override
    public Result adoptionFindList(PageInfo pageInfo) {
        Result result = new Result<>();
        PageInfo findPage = pageInfo;
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(findPage.getPageNum()+"")||findPage.getPageNum()<=0){
            findPage.setPageNum(1);
        }
        if (StringUtils.isEmpty(findPage.getPageSize()+"")||findPage.getPageSize()<=0){
            findPage.setPageSize(10);
        }
        if (StringUtils.isNotEmpty(findPage.getMsg())){
            queryWrapper.like("time",findPage.getMsg());
        }
        if (StringUtils.isNotEmpty(findPage.getId())){
            queryWrapper.eq("user_id",findPage.getId());
        }
        queryWrapper.orderByAsc("review_status");
        Page selectByPage = new Page(findPage.getPageNum(),findPage.getPageSize());
        Page resultPage = adoptionMapper.selectPage(selectByPage, queryWrapper);
        List<Adoption> records = resultPage.getRecords();
        for (int i=0;i<records.size();i++) {
            QueryWrapper userQuery = new QueryWrapper<>();
            QueryWrapper petQuery = new QueryWrapper<>();
            userQuery.eq("id",records.get(i).getUserId());
            petQuery.eq("id",records.get(i).getPetId());
            records.get(i).setUserName(userMapper.selectOne(userQuery).getUsername());
            records.get(i).setPetName(petMapper.selectOne(petQuery).getPetName());
            records.get(i).setImg(Base64Util.imgs(imgPath,petMapper.selectOne(petQuery).getImg()));
        }
        resultPage.setRecords(records);
        result.setCode("200");
        result.setData(resultPage);
        return result;
    }@Transactional
    @Override
    public Result adoptionFindListPlus(PageInfo pageInfo) {
//        Result result = new Result<>();
//        PageInfo findPage = pageInfo;
//        QueryWrapper<Adoption> queryWrapper = new QueryWrapper<>();
//
//        if (StringUtils.isEmpty(findPage.getPageNum() + "") || findPage.getPageNum() <= 0) {
//            findPage.setPageNum(1);
//        }
//        if (StringUtils.isEmpty(findPage.getPageSize() + "") || findPage.getPageSize() <= 0) {
//            findPage.setPageSize(10);
//        }
//        if (StringUtils.isNotEmpty(findPage.getMsg())) {
//            queryWrapper.like("time", findPage.getMsg());
//        }
//
//
//        queryWrapper.orderByAsc("review_status");
//
//        Page<Adoption> selectByPage = new Page<>(findPage.getPageNum(), findPage.getPageSize());
//        Page<Adoption> resultPage = adoptionMapper.selectPage(selectByPage, queryWrapper);
//        List<Adoption> records = resultPage.getRecords();
//
//        for (Adoption adoption : records) {
//            QueryWrapper<User> userQuery = new QueryWrapper<>();
//            QueryWrapper<Pet> petQuery = new QueryWrapper<>();
//
//            // 获取学生名字
//            userQuery.eq("id", adoption.getUserId());
//            User studentUser = userMapper.selectOne(userQuery);
//            if (studentUser != null) {
//                adoption.setUserName(studentUser.getUsername());
//            }
//
//            // 获取宠物信息
//            petQuery.eq("id", adoption.getPetId());
//            Pet pet = petMapper.selectOne(petQuery);
//            if (pet != null) {
//                adoption.setPetName(pet.getPetName());
//                adoption.setImg(Base64Util.imgs(imgPath, pet.getImg()));
//
//                // 获取宠物主人名字
//                User petOwner = userMapper.selectById(pet.getUserId());
//                if (petOwner != null) {
//                    adoption.setOwnerName(petOwner.getUsername());
//                    adoption.setOwnerId(petOwner.getId()); // 确保 Adoption 实体类中有 ownerId 字段
//                }
//            }
//        }
//
//        resultPage.setRecords(records);
//        result.setCode("200");
//        result.setData(resultPage);
//        return result;

        Result result = new Result<>();
        PageInfo findPage = pageInfo;
        QueryWrapper<Adoption> queryWrapper = new QueryWrapper<>();
        System.out.println("pagesize    "  +findPage.getPageSize());
        if (StringUtils.isEmpty(findPage.getPageNum() + "") || findPage.getPageNum() <= 0) {
            findPage.setPageNum(1);
        }
        if (StringUtils.isEmpty(findPage.getPageSize() + "") || findPage.getPageSize() <= 0) {
            findPage.setPageSize(10);
        }
        if (StringUtils.isNotEmpty(findPage.getMsg())) {
            queryWrapper.like("time", findPage.getMsg());
        }

        queryWrapper.orderByAsc("review_status");

// 获取总记录数
        long totalRecords = adoptionMapper.selectCount(queryWrapper);

        Page<Adoption> selectByPage = new Page<>(findPage.getPageNum(), findPage.getPageSize());
        Page<Adoption> resultPage = adoptionMapper.selectPage(selectByPage, queryWrapper);
        List<Adoption> records = resultPage.getRecords();

        String filterUserId = findPage.getId();
        List<Adoption> filteredRecords = new ArrayList<>();

        for (Adoption adoption : records) {
            QueryWrapper<User> userQuery = new QueryWrapper<>();
            QueryWrapper<Pet> petQuery = new QueryWrapper<>();

            // 获取学生名字
            userQuery.eq("id", adoption.getUserId());
            User studentUser = userMapper.selectOne(userQuery);
            if (studentUser != null) {
                adoption.setUserName(studentUser.getUsername());
            }

            // 获取宠物信息
            petQuery.eq("id", adoption.getPetId());
            Pet pet = petMapper.selectOne(petQuery);
            if (pet != null) {
                adoption.setPetName(pet.getPetName());
                adoption.setImg(Base64Util.imgs(imgPath, pet.getImg()));

                // 获取宠物主人名字
                User petOwner = userMapper.selectById(pet.getUserId());
                if (petOwner != null) {
                    adoption.setOwnerName(petOwner.getUsername());
                    adoption.setOwnerId(petOwner.getId());
                }
            }

            // 过滤数据
            if (StringUtils.isEmpty(filterUserId) || adoption.getUserId().equals(filterUserId) || (pet != null && pet.getUserId().equals(filterUserId))) {
                filteredRecords.add(adoption);
            }
        }

// 创建一个新的分页对象
        Page<Adoption> filteredPage = new Page<>(findPage.getPageNum(), findPage.getPageSize());
        filteredPage.setRecords(filteredRecords);
//        filteredPage.setTotal(totalRecords); // 设置总记录数为过滤后的总数
        filteredPage.setTotal(filteredRecords.size());
        filteredPage.setCurrent(findPage.getPageNum());
        filteredPage.setSize(findPage.getPageSize());

        result.setCode("200");
        result.setData(filteredPage);
        return result;



//        Result result = new Result<>();
//        PageInfo findPage = pageInfo;
//        QueryWrapper<Adoption> queryWrapper = new QueryWrapper<>();
//
//        if (StringUtils.isEmpty(findPage.getPageNum()+"")||findPage.getPageNum()<=0){
//            findPage.setPageNum(1);
//        }
//        if (StringUtils.isEmpty(findPage.getPageSize()+"")||findPage.getPageSize()<=0){
//            findPage.setPageSize(10);
//        }
//        String ownerId=pageInfo.getMsg();
//
//        queryWrapper.inSql("pet_id", "SELECT id FROM pet WHERE user_id = " + ownerId);
//        queryWrapper.orderByAsc("review_status");
//
//        Page<Adoption> selectByPage = new Page<>(findPage.getPageNum(), findPage.getPageSize());
//        Page<Adoption> resultPage = adoptionMapper.selectPage(selectByPage, queryWrapper);
//        List<Adoption> records = resultPage.getRecords();
//
//        for (Adoption adoption : records) {
//            QueryWrapper<User> userQuery = new QueryWrapper<>();
//            QueryWrapper<Pet> petQuery = new QueryWrapper<>();
//            Pet pet = petMapper.selectById(adoption.getPetId());
//            if (pet != null) {
//                adoption.setPetName(pet.getPetName());
//                adoption.setImg(Base64Util.imgs(imgPath, pet.getImg()));
//
//                User user = userMapper.selectById(pet.getUserId());
//                if (user != null) {
//                    adoption.setUserName(user.getUsername());
//                }
//            }
//        }
//
//        resultPage.setRecords(records);
//        result.setCode("200");
//        result.setData(resultPage);
//        return result;



    }

    @Transactional
    @Override
    public Result adoptionInsert(Adoption adoption) {
        Adoption adoptionInsert = adoption;
        Result result = new Result<>();
        Pet petNew = petMapper.selectById(adoptionInsert.getPetId());
        if (  petNew.getIsAdopted() == 1) {
            result.setCode("500");
            result.setMsg("已被遛走");
            return result;
        }
        User user = userMapper.selectById(adoptionInsert.getUserId());
        if (  user.getOtherInfo() == "3") {
            result.setCode("500");
            result.setMsg("宠物主人不能遛狗");
            return result;
        }
        adoptionInsert.setId(Util.uuid());
        adoptionInsert.setTime(Util.initTime(new Date()));
        adoptionInsert.setReviewStatus("0");
        Pet pet = petMapper.selectById(adoptionInsert.getPetId());
        pet.setIsAdopted(1);
        petMapper.updateById(pet);
        if (adoptionMapper.insert(adoptionInsert)>0){
            result.setCode("200");
            result.setMsg("申请成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("申请失败");
        return result;
    }

    @Transactional
    @Override
    public Result adoptionApproval(Adoption adoption) {
        Result result = new Result<>();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",adoption.getId());
        updateWrapper.set("review_status",adoption.getReviewStatus());
        if (adoption.getReviewStatus().equals("1")){
            UpdateWrapper petUpdate = new UpdateWrapper<>();
            petUpdate.eq("id",adoption.getPetId());
            petUpdate.set("is_adopted",1);
            petMapper.update(new Pet(),petUpdate);
        }
        if (adoption.getReviewStatus().equals("2")){
            UpdateWrapper petUpdate = new UpdateWrapper<>();
            petUpdate.eq("id",adoption.getPetId());
            petUpdate.set("is_adopted",0);
            petMapper.update(new Pet(),petUpdate);
        }
        if (adoptionMapper.update(new Adoption(),updateWrapper)>0){
            result.setCode("200");
            result.setMsg("修改状态成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("修改状态失败");
        return result;
    }
}
