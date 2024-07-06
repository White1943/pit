package com.example.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.config.PageInfo;
import com.example.platform.entity.Feedback;
import com.example.platform.mapper.FeedbackMapper;
import com.example.platform.mapper.PetMapper;
import com.example.platform.mapper.UserMapper;
import com.example.platform.uilt.Result;
import com.example.platform.uilt.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class FeedbackServiceImpl implements FeedbackService{


    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PetMapper petMapper;

    @Override
    public Result feedbackInsert(Feedback feedback) {
        Result result = new Result<>();
        Feedback feedbackInsert = feedback;
        feedbackInsert.setId(Util.uuid());
        feedbackInsert.setTime(Util.initTime(new Date()));
        if (feedbackMapper.insert(feedbackInsert)>0){
            result.setCode("200");
            result.setMsg("反馈成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("反馈失败");
        return result;
    }

    @Override
    public Result feedbackList(Feedback feedback) {
        Result result = new Result<>();
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",feedback.getUserId());
        List<Feedback> list = feedbackMapper.selectList(queryWrapper);
        if (list.size()>0){
            result.setData(list);
            result.setCode("200");
            result.setMsg("查询成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("查询失败");
        return result;
    }

    @Override
    public Result feedbackAllList(PageInfo page) {
        Result result = new Result<>();
        PageInfo findPage = page;
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
        queryWrapper.orderByAsc("status");
        queryWrapper.orderByDesc("time");
        Page selectByPage = new Page(findPage.getPageNum(),findPage.getPageSize());
        Page resultPage = feedbackMapper.selectPage(selectByPage, queryWrapper);
        List<Feedback> records = resultPage.getRecords();

        for (int i=0;i<records.size();i++) {
            QueryWrapper userQuery = new QueryWrapper<>();
            QueryWrapper petQuery = new QueryWrapper<>();
            userQuery.eq("id",records.get(i).getUserId());
            petQuery.eq("id",records.get(i).getPetId());
            records.get(i).setUserName(userMapper.selectOne(userQuery).getUsername());
            records.get(i).setPetName(petMapper.selectOne(petQuery).getPetName());
        }
        resultPage.setRecords(records);
        result.setCode("200");
        result.setData(resultPage);
        return result;
    }

    @Override
    public Result feedbackUpdate(Feedback feedback) {
        Result result = new Result<>();
        Feedback newFeedback = feedback;
        if (newFeedback.getStatus()==0){
            newFeedback.setStatus(1);
            if(feedbackMapper.updateById(newFeedback)>0){
                result.setMsg("该反馈处理成功");
                result.setCode("200");
            }else {
                result.setMsg("该反馈处理失败");
                result.setCode("500");
            }
            return result;
        }
        result.setMsg("该反馈已处理，请耐心等待!");
        result.setCode("500");
        return  result;
    }

    @Override
    public Result feedbackDelete(String id) {
        Result result = new Result<>();
        Feedback feedback= feedbackMapper.selectById(id);

        if (feedbackMapper.deleteById(feedback)>0){
            result.setCode("200");
            result.setMsg("删除成功");
            return result;
        }
        result.setCode("500");
        result.setMsg("删除失败");
        return result;
    }
}
