package com.example.platform.controller;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.Feedback;
import com.example.platform.service.AdoptionService;
import com.example.platform.service.FeedbackService;
import com.example.platform.uilt.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/feedback/")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("feedbackAllList")
    public Result systemFeedbackList(@RequestBody PageInfo page){
        return feedbackService.feedbackAllList(page);
    }

    @PostMapping("feedbackInsert")
    public Result systemFeedbackInsert(@RequestBody Feedback feedback){
        return feedbackService.feedbackInsert(feedback);
    }

    @PostMapping("feedbackListByUId")
    public Result systemFeedbackListByUId(@RequestBody Feedback feedback){
        return feedbackService.feedbackList(feedback);
    }

    @PostMapping("feedbackUpdate")
    public Result systemFeedbackUpdate(@RequestBody Feedback feedback){
        return feedbackService.feedbackUpdate(feedback);
    }
}
