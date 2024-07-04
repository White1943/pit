package com.example.platform.controller;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.Adoption;
import com.example.platform.service.AdoptionService;
import com.example.platform.uilt.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/adoption/")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @PostMapping("adoptionInsert")
    public Result systemAdoptionInsert(@RequestBody Adoption adoption){
        return adoptionService.adoptionInsert(adoption);
    }


    @PostMapping("adoptionApproval")
    public Result systemAdoptionApproval(@RequestBody Adoption adoption){
        return adoptionService.adoptionApproval(adoption);
    }

    @PostMapping("adoptionList")
    public Result systemAdoptionList(@RequestBody PageInfo pageInfo){
        return adoptionService.adoptionFindList(pageInfo);
    }

}
