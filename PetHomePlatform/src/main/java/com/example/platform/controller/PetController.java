package com.example.platform.controller;

import com.example.platform.config.PageInfo;
import com.example.platform.config.SearchInfo;
import com.example.platform.entity.ImgVo;
import com.example.platform.entity.Pet;
import com.example.platform.service.AdoptionService;
import com.example.platform.service.PetService;
import com.example.platform.uilt.Base64Util;
import com.example.platform.uilt.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Log4j
@RestController
@RequestMapping("/pet/")
@Api(tags = "宠物管理接口", description = "提供宠物管理相关的 API")
public class PetController {

    @Autowired
    private PetService petService;

    @Value("${dir}")//优雅,dir的配置见application.yml
    private String imgPath;

    @PostMapping("petFindById")
    @ApiOperation("根据ID查询宠物")
    @ApiImplicitParam(name = "pet", value = "宠物实体", required = true, dataType = "Pet")
    public Result systemPetFindById(@RequestBody Pet pet) {
        return petService.petFindById(pet);
    }

    @PostMapping("petListByUser")
    @ApiOperation("根据用户查询宠物列表")
    @ApiImplicitParam(name = "searchInfo", value = "查询条件", required = true, dataType = "SearchInfo")
    public Result systemPetUserList(@RequestBody SearchInfo searchInfo) {
        return petService.petUserAll(searchInfo);
    }

    @PostMapping("petListAndPage")
    @ApiOperation("分页查询宠物列表")
    @ApiImplicitParam(name = "page", value = "分页信息,msg='pet_name',可以晒名查询", required = true, dataType = "PageInfo")
    public Result systemPetList(@RequestBody PageInfo page) {
        return petService.petListByUser(page);
    }

    @PostMapping("petInsert")
    @ApiOperation("插入宠物")
    @ApiImplicitParam(name = "pet", value = "宠物实体", required = true, dataType = "Pet")
    public Result systemPetInsert(@RequestBody Pet pet) {
        return petService.petInsert(pet);
    }

    @DeleteMapping("petRemove")
    @ApiOperation("删除宠物")
    @ApiImplicitParam(name = "pet", value = "宠物实体仅id即可", required = true, dataType = "Pet")
    public Result systemPetRemove(@RequestBody Pet pet) {
        return petService.petRemove(pet);
    }

    @PostMapping("petUpdate")
    @ApiOperation("更新宠物信息")
    @ApiImplicitParam(name = "pet", value = "宠物实体", required = true, dataType = "Pet")
    public Result systemPetUpdate(@RequestBody Pet pet) {
        return petService.petUpdate(pet);
    }

    @PostMapping("petStatusUpdate")
    @ApiOperation("更新宠物状态")
    @ApiImplicitParam(name = "pet", value = "宠物实体,含isadopted信息", required = true, dataType = "Pet")
    public Result systemPetStatusUpdate(@RequestBody Pet pet) {
        return petService.petStatusUpdate(pet);
    }

    @PostMapping("petImgSelect")
    @ApiOperation("查询宠物图片")
    @ApiImplicitParam(name = "pet", value = "宠物实体,需包含img名", required = true, dataType = "Pet")
    public Result systemPetpetImgSelect(@RequestBody Pet pet) {
        return petService.petImgSelect(pet);
    }

    @PostMapping("uploadImg")
    @ApiOperation("上传图片")
    @ApiImplicitParam(name = "file", value = "上传的文件,应该element的组件属性满足参数", required = true, dataType = "__File")
    public Result uploadByImage(@Param("file") MultipartFile file) throws IOException {
        Result result = new Result<>();
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename = UUID.randomUUID() + substring;
        //路径名
        File file1 = new File(imgPath + originalFilename);
        file.transferTo(file1);
        ImgVo imgVo = new ImgVo();
        imgVo.setImgName(originalFilename);
        imgVo.setImg(Base64Util.imgs(imgPath, originalFilename));
        result.setCode("200");
        result.setData(imgVo);
        return result;
    }
}