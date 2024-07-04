package com.example.platform.service;

import com.example.platform.config.PageInfo;
import com.example.platform.entity.Feedback;
import com.example.platform.uilt.Result;
import org.springframework.stereotype.Service;


public interface FeedbackService {

    /**
     * 反馈提交
     * @param feedback
     * @return
     */
    Result feedbackInsert(Feedback feedback);

    /**
     * 反馈查看
     * @param feedback
     * @return
     */
    Result feedbackList(Feedback feedback);

    Result feedbackAllList(PageInfo page);

    Result feedbackUpdate(Feedback feedback);
}
