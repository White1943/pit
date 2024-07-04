package com.example.platform.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 公共的分页对象
 */
@Getter
@Setter

public class PageInfo {
    @JsonProperty("pageNum")
    private int pageNum;//当前页
    @JsonProperty("pageSize")
    private int pageSize;//页面大小
    @JsonProperty("msg")
    private String msg;//页面大小

    @JsonProperty("id")
    private String id;//页面大小
}
