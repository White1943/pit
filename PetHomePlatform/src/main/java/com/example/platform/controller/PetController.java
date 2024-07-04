package com.example.platform.controller;

import com.example.platform.config.PageInfo;
import com.example.platform.config.SearchInfo;
import com.example.platform.entity.ImgVo;
import com.example.platform.entity.Pet;
import com.example.platform.service.AdoptionService;
import com.example.platform.service.PetService;
import com.example.platform.uilt.Base64Util;
import com.example.platform.uilt.Result;
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
public class PetController {

    @Autowired
    private PetService petService;

    @Value("${dir}")
    private  String imgPath;





    @PostMapping("petFindById")
    public Result systemPetFindById(@RequestBody Pet pet){
        return petService.petFindById(pet);
    }

    @PostMapping("petListByUser")
    public Result systemPetUserList(@RequestBody SearchInfo searchInfo){
        return petService.petUserAll(searchInfo);
    }

    @PostMapping("petListAndPage")
    public Result systemPetList(@RequestBody PageInfo page){
        return petService.petListByUser(page);
    }

    @PostMapping("petInsert")
    public Result systemPetInsert(@RequestBody Pet pet){
        return petService.petInsert(pet);
    }

    @DeleteMapping("petRemove")
    public Result systemPetRemove(@RequestBody Pet pet){
        return petService.petRemove(pet);
    }

    @PostMapping("petUpdate")
    public Result systemPetUpdate(@RequestBody Pet pet){
        return petService.petUpdate(pet);
    }

    @PostMapping("petStatusUpdate")
    public Result systemPetStatusUpdate(@RequestBody Pet pet){
        return petService.petStatusUpdate(pet);
    }

    @PostMapping("petImgSelect")
    public Result systemPetpetImgSelect(@RequestBody Pet pet){
        return petService.petImgSelect(pet);
    }

    @PostMapping("uploadImg")
    public Result uploadByImage(@Param("file") MultipartFile file) throws IOException {
        Result result = new Result<>();
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename= UUID.randomUUID()+substring;
        //路径名
        File file1 = new File(imgPath+originalFilename);
        file.transferTo(file1);
        ImgVo imgVo = new ImgVo();
        imgVo.setImgName(originalFilename);
        imgVo.setImg(Base64Util.imgs(imgPath,originalFilename));
        result.setCode("200");
        result.setData(imgVo);
        return result;
    }
}
