package com.example.platform.controller;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.Adoption;
import com.example.platform.service.AdoptionService;
import com.example.platform.uilt.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/adoption/")
@Api(tags = "领养管理接口", description = "宠物领养控制器接口")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @ApiOperation(value = "新增领养申请", notes = "插入新的领养申请，如果宠物已经被领养或用户是宠物主人则返回错误信息，否则插入记录并更新宠物状态")
    @PostMapping("adoptionInsert")
    public Result systemAdoptionInsert(@RequestBody Adoption adoption){
        return adoptionService.adoptionInsert(adoption);
    }

    @ApiOperation(value = "审批领养申请", notes = "审批领养申请，根据审批结果更新领养申请状态和宠物的领养状态")
    @PostMapping("adoptionApproval")
    public Result systemAdoptionApproval(@RequestBody Adoption adoption){
        return adoptionService.adoptionApproval(adoption);
    }

    @ApiOperation(value = "查询领养列表", notes = "根据分页信息和查询条件查询领养列表，并返回结果")
    @PostMapping("adoptionList")
    public Result systemAdoptionList(@RequestBody PageInfo pageInfo){
        return adoptionService.adoptionFindList(pageInfo);
    }
    @ApiOperation(value = "订单列表", notes = "不包含id,订单全查,含id时,根据学生/主人id查询")
    @PostMapping("adoptionFindListPlus")
    public Result adoptionFindListPlus(@RequestBody PageInfo pageInfo){
        return adoptionService.adoptionFindListPlus(pageInfo);

    }
}
