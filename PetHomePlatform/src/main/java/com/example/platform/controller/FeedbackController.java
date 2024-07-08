package com.example.platform.controller;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.Feedback;
import com.example.platform.service.FeedbackService;
import com.example.platform.uilt.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/feedback/")
@Api(tags = "反馈管理接口", description = "反馈控制器接口")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(value = "查询所有反馈", notes = "根据分页信息查询所有反馈列表")
    @PostMapping("feedbackAllList")
    public Result systemFeedbackList(
            @ApiParam(value = "分页信息", required = true) @RequestBody PageInfo page){
        return feedbackService.feedbackAllList(page);
    }

    @ApiOperation(value = "插入反馈", notes = "新增一条反馈信息")
    @PostMapping("feedbackInsert")
    public Result systemFeedbackInsert(
            @ApiParam(value = "反馈信息", required = true) @RequestBody Feedback feedback){
        return feedbackService.feedbackInsert(feedback);
    }

    @ApiOperation(value = "根据用户ID查询反馈", notes = "根据用户ID查询其所有反馈记录")
    @PostMapping("feedbackListByUId")
    public Result systemFeedbackListByUId(
            @ApiParam(value = "反馈信息，包含用户ID", required = true) @RequestBody Feedback feedback){
        return feedbackService.feedbackList(feedback);
    }

    @ApiOperation(value = "更新反馈状态", notes = "更新反馈处理状态")
    @PostMapping("feedbackUpdate")
    public Result systemFeedbackUpdate(
            @ApiParam(value = "反馈信息，包含状态", required = true) @RequestBody Feedback feedback){
        return feedbackService.feedbackUpdate(feedback);
    }
    @ApiOperation(value = "删除反馈", notes = " ")
    @DeleteMapping("feedbackDelete")
    public Result  feedbackDelete(@RequestParam  String id){
        return feedbackService.feedbackDelete(  id);
    }
}
