package com.example.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.config.PageInfo;
import com.example.platform.config.SearchInfo;
import com.example.platform.entity.Pet;
import com.example.platform.mapper.PetMapper;
import com.example.platform.uilt.Base64Util;
import com.example.platform.uilt.Result;
import com.example.platform.uilt.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Transactional
@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetMapper petMapper;

    @Value("${dir}")
    private  String imgPath;

    @Override
    public Result petListByUser(PageInfo page) {
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
            queryWrapper.like("pet_name",findPage.getMsg());
        }
        if (StringUtils.isNotEmpty(findPage.getId())){
            queryWrapper.eq("user_id",findPage.getId());
        }
        queryWrapper.orderByDesc("is_adopted");
        Page selectByPage = new Page(findPage.getPageNum(),findPage.getPageSize());
        Page resultPage = petMapper.selectPage(selectByPage, queryWrapper);

        List<Pet> pets = resultPage.getRecords();
        for (Pet pet : pets) {
            if (StringUtils.isNotEmpty(pet.getImg())) {
                String base64Img = Base64Util.imgs(imgPath, pet.getImg());
                pet.setImg(base64Img);
            }
        }

        result.setCode("200");
        result.setData(resultPage);
        return result;
    }

    @Override
    public Result petRemove(Pet pet) {
        Result result = new Result<>();
        Pet petDelete = pet;
        if (petMapper.deleteById(petDelete)>0) {
            result.setCode("200");
            result.setMsg("删除成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("删除失败");
        return result;
    }

    @Override
    public Result petStatusUpdate(Pet pet) {
        Result result = new Result<>();
        Pet petDelete = pet;
        UpdateWrapper queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("id",pet.getId());
        queryWrapper.set("is_adopted",pet.getIsAdopted());
        if (petMapper.update(new Pet(),queryWrapper)>0) {
            result.setCode("200");
            result.setMsg("修改状态成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("修改状态失败");
        return result;
    }

    @Override
    public Result petInsert(Pet pet) {
        Result result = new Result<>();
        Pet petInsert = pet;
        petInsert.setId(Util.uuid());
        petInsert.setIsAdopted(0);
        if (petMapper.insert(petInsert)>0) {
            result.setCode("200");
            result.setMsg("添加成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("添加失败");
        return result;
    }

    @Override
    public Result petUpdate(Pet pet) {
        Result result = new Result<>();
        Pet petUpdate = pet;
        if (petMapper.updateById(petUpdate)>0) {
            result.setCode("200");
            result.setMsg("修改成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("修改失败");
        return result;
    }

    @Override
    public Result petImgSelect(Pet pet) {
        Result result = new Result<>();
        Pet petImg = pet;
        if (StringUtils.isNotEmpty(petImg.getImg())){
            String path = Base64Util.imgs(imgPath, petImg.getImg());
            result.setData(path);
            result.setCode("200");
            result.setMsg("查看图片成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("查看图片失败");
        return result;
    }

    @Override
    public Result petUserAll(SearchInfo searchInfo) {
        Result result =new Result<>();
        SearchInfo searchInfoNew = searchInfo;
        QueryWrapper queryWrapper =new QueryWrapper<>();
        if (StringUtils.isNotEmpty(searchInfoNew.getAge()+"")&&searchInfoNew.getAge()!=0){
           queryWrapper.eq("age",searchInfoNew.getAge());
        }
        if (StringUtils.isNotEmpty(searchInfoNew.getColor())){
            queryWrapper.eq("color",searchInfoNew.getColor());
        }
        if (StringUtils.isNotEmpty(searchInfoNew.getSex())){
            queryWrapper.eq("sex",searchInfoNew.getSex());
        }
        if (StringUtils.isNotEmpty(searchInfoNew.getType())){
            queryWrapper.like("breed",searchInfo.getType());
        }
        queryWrapper.eq("status",1);
        List<Pet> list = petMapper.selectList(queryWrapper);
        List<List<Pet>> twoDimensionalList = convertToTwoDimensional(list, 4,imgPath);
        result.setCode("200");
        result.setData(twoDimensionalList);
        return result;
    }

    @Override
    public Result petFindById(Pet pet) {
        Result result = new Result<>();
        if (StringUtils.isNotEmpty(pet.getId())){
            Pet newPet = petMapper.selectById(pet.getId());
            newPet.setImg(Base64Util.imgs(imgPath,newPet.getImg()));
            result.setData(newPet);
            result.setCode("200");
            return  result;
        }
        result.setCode("500");
        result.setMsg("系统异常");
        return  result;
    }


    public static List<List<Pet>> convertToTwoDimensional(List<Pet> pets, int size,String path) {
        int count = (pets.size() + size - 1) / size; // 计算需要多少个子列表
        return IntStream.range(0, count)
                .mapToObj(i -> pets.stream()
                        .skip(i * size)
                        .limit(size)
                        .peek(pet -> pet.setImg(Base64Util.imgs(path, pet.getImg())))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}

