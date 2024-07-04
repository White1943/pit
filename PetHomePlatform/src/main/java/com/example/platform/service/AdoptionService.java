package com.example.platform.service;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.Adoption;
import com.example.platform.uilt.Result;
import org.springframework.stereotype.Service;


public interface AdoptionService {

    /**
     * 领养记录查看
     * @param pageInfo
     * @return
     */
    Result adoptionFindList(PageInfo pageInfo);


    /**
     * 申请领养
     * @param adoption
     * @return
     */
    Result adoptionInsert(Adoption adoption);


    /**
     * 审批领养(批准、拒绝)
     * @param adoption
     * @return
     */
    Result adoptionApproval(Adoption adoption);



}
